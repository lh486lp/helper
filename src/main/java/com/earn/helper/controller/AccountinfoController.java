package com.earn.helper.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.common.annotation.ActiveUser;
import com.earn.helper.entity.Accountinfo;
import com.earn.helper.entity.R;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.entity.Userinfo;
import com.earn.helper.enume.AccountTypeEnum;
import com.earn.helper.service.IAccountinfoService;
import com.earn.common.util.EncryptUtils;
import com.earn.common.util.FileUtil;
import com.earn.common.util.StringUtil;

/**
 * <p>
 * 账户信息表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2019-01-05
 */
@Controller
@RequestMapping("/account")
public class AccountinfoController extends BaseController {
    @Autowired
    private IAccountinfoService accountinfoService;
    @Value("${uploadPath}")
    private String uploadPath;

    @ResponseBody
    @GetMapping("/list")
    public R list(@ActiveUser UserLogin userLogin, Accountinfo accountinfo) {
        Userinfo user = userLogin.getUserinfo();
        if (user == null) {
            return R.error("未登录");
        }
        accountinfo.setUserId(user.getUserId());
        Page<Accountinfo> list = accountinfoService.pageList(accountinfo);
        return new R<Page<Accountinfo>>().setData(list);
    }

    @ResponseBody
    @GetMapping("/getAccountinfo")
    public R getAccountinfo(@ActiveUser UserLogin userLogin) {
        Userinfo user = userLogin.getUserinfo();
        if (user == null) {
            return R.error("未登录");
        }
        Map<String, Object> account = new HashMap<>();
        Accountinfo accountinfo = new Accountinfo();
        accountinfo.setUserId(user.getUserId());
        // 账户余额
        account.put("amount", accountinfoService.getAmount(accountinfo));

        // 累计收入
        accountinfo.setIsGain(1);
        account.put("totalIncome", accountinfoService.getAmount(accountinfo));

        // 今日收入
        accountinfo.setCreateTime(new Date());
        account.put("currentIncome", accountinfoService.getAmount(accountinfo));

        accountinfo.setIsGain(null);
        accountinfo.setCreateTime(null);

        // 累计提现
        accountinfo.setType(AccountTypeEnum.tx.ordinal());
        account.put("totalWithdraw", accountinfoService.getWithdraw(accountinfo));

        // 冻结审核
        accountinfo.setType(AccountTypeEnum.sh.ordinal());
        account.put("audit", accountinfoService.getWithdraw(accountinfo));

        return new R<Map<String, Object>>().setData(account);
    }

    @ResponseBody
    @PostMapping("/withdraw")
    public R withdraw(@ActiveUser UserLogin userLogin, Accountinfo accountinfo) {
        Userinfo user = userLogin.getUserinfo();
        if (user == null) {
            return R.error("未登录");
        } else if (StringUtil.isNullAndBlank(accountinfo.getAccountPassword())) {
            return R.error("资金密码为空");
        } else if (accountinfo.getFile() == null) {
            return R.error("请上传图片");
        }

        Integer userId = userLogin.getUserinfo().getUserId();
        Userinfo userinfo = new Userinfo().selectById(userId);
        if (StringUtil.isNullAndBlank(userinfo.getAccountPassword())) {
            return R.error("E0002", "未设置资金密码");
        } else if (!EncryptUtils.matches(accountinfo.getAccountPassword(), userinfo.getAccountPassword())) {
            return R.error("资金密码错误");
        }
        MultipartFile file = accountinfo.getFile();
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        try {
            accountinfo.setImgPath(FileUtil.uploadFile(file.getBytes(), uploadPath, fileName));
        } catch (Exception e) {
            return R.error("图片上传失败");
        }

        accountinfoService.addAccount(userinfo, AccountTypeEnum.tx, accountinfo.getAmount(), accountinfo.getImgPath(),
            accountinfo.getPayType(), userId);
        return R.ok();
    }
}
