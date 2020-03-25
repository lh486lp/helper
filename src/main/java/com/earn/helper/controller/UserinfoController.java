package com.earn.helper.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earn.common.annotation.ActiveUser;
import com.earn.common.util.Const;
import com.earn.common.util.EncryptUtils;
import com.earn.common.util.StringUtil;
import com.earn.helper.entity.R;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.entity.Userinfo;
import com.earn.helper.service.IUserinfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Slf4j
@Controller
@RequestMapping("/userinfo")
public class UserinfoController extends BaseController {
    @Autowired
    private IUserinfoService userinfoService;

    @ResponseBody
    @GetMapping("/")
    public String getUsers() {
        return "Hello Spring Security";
    }

    @ResponseBody
    @PostMapping("/register")
    public R register(Userinfo userinfo) {
        if (StringUtil.isNullAndBlank(userinfo.getPhone())) {
            return R.error("手机号不能为空");
        } else if (StringUtil.isNullAndBlank(userinfo.getPassword())) {
            return R.error("密码不能为空");
        } else if (!"1111".equals(userinfo.getCode())) {
            return R.error("验证码错误");
        }
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<Userinfo>().eq("phone", userinfo.getPhone());
        if (new Userinfo().selectCount(wrapper) > 0) {
            return R.error("手机号已存在");
        }
        wrapper = new QueryWrapper<Userinfo>().eq("rcode", userinfo.getRcode());
        if (new Userinfo().selectCount(wrapper) == 0) {
            return R.error("推荐人不存在");
        }
        Userinfo parentinfo = new Userinfo().selectOne(wrapper);
        userinfo.setParentId(parentinfo.getUserId());
        userinfo.setUsername(userinfo.getPhone());
        userinfo.setNickName(userinfo.getPhone());
        userinfo.setPassword(EncryptUtils.encode(userinfo.getPassword()));
        userinfo.insert();
        userinfo.setRcode(Const.getRcode(userinfo.getUserId()));
        userinfo.updateById();

        return R.ok();
    }

    @ResponseBody
    @PostMapping("/resetPassword")
    public R resetPassword(Userinfo userinfo) {
        if (StringUtil.isNullAndBlank(userinfo.getPhone())) {
            return R.error("手机号不能为空");
        } else if (StringUtil.isNullAndBlank(userinfo.getCode())) {
            return R.error("验证码不能为空");
        } else if (!"1111".equals(userinfo.getCode())) {
            return R.error("验证码错误");
        }
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<Userinfo>().eq("phone", userinfo.getPhone());
        Userinfo user = new Userinfo().selectOne(wrapper);
        if (user == null) {
            return R.error("账号不存在");
        }
        user.setPassword(EncryptUtils.encode(StringUtil.isNullDefault(userinfo.getNewPassword(), "123456")));
        user.updateById();
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/setAccountPsw")
    public R setAccountPsw(@ActiveUser UserLogin userLogin, Userinfo userinfo) {
        Userinfo user = userLogin.getUserinfo();
        if (user == null) {
            return R.error("未登录");
        } else if (!"1111".equals(userinfo.getCode())) {
            return R.error("验证码错误");
        } else if (StringUtil.isNullAndBlank(userinfo.getAccountPassword())) {
            return R.error("交易密码不能为空");
        } else if (EncryptUtils.matches(userinfo.getAccountPassword(), userinfo.getPassword())) {
            return R.error("交易密码不能与账户密码一致");
        }
        user.setAccountPassword(EncryptUtils.encode(userinfo.getAccountPassword()));
        user.updateById();
        return R.ok();
    }

    @ResponseBody
    @GetMapping("/getMyTeam")
    public R getMyTeam(@ActiveUser UserLogin userLogin) {
        Integer userId = userLogin.getUserinfo().getUserId();

        Map<String, Object> team = new HashMap<>();
        team.put("myFansCount", userinfoService.getMyFansCount(userId));
        team.put("myFansFansCount", userinfoService.getMyFansFansCount(userId));
        return new R<Map>().setData(team);
    }

    @ResponseBody
    @GetMapping("/getMyFansCount/{level}")
    public R getMyFansCount(@ActiveUser UserLogin userLogin, @PathVariable("level") Integer level) {
        Integer userId = userLogin.getUserinfo().getUserId();

        List<Map<String, Object>> list;
        switch (level) {
            case 1:
                list = userinfoService.getMyFansLevelCount(userId);
                break;
            case 2:
                list = userinfoService.getMyFansFansLevelCount(userId);
                break;
            default:
                return R.error("参数错误！");
        }

        Map<String, Object> team = new HashMap<>();
        for (Map<String, Object> map : list) {
            team.put("level" + map.get("vip_level"), map.get("count"));
        }

        return new R<Map>().setData(team);
    }

    @ResponseBody
    @GetMapping("getFansList/{level}/{vipLevel}")
    public R getFansList(@ActiveUser UserLogin userLogin, @PathVariable("level") Integer level,
        @PathVariable("vipLevel") Integer vipLevel) {
        Integer userId = userLogin.getUserinfo().getUserId();

        List<Userinfo> list;
        Userinfo userinfo = new Userinfo();
        userinfo.setUserId(userId);
        userinfo.setVipLevel(vipLevel);
        switch (level) {
            case 1:
                list = userinfoService.getMyFansList(userinfo);
                break;
            case 2:
                list = userinfoService.getMyFansFansList(userinfo);
                break;
            default:
                return R.error("参数错误！");
        }

        return new R<List>().setData(list);
    }
}
