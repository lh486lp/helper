package com.earn.helper.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.annotation.ActiveUser;
import com.earn.common.util.Const;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.entity.Orderinfo;
import com.earn.helper.entity.Product;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Userinfo;
import com.earn.helper.enume.AccountTypeEnum;
import com.earn.helper.service.IAccountinfoService;
import com.earn.helper.service.IUserinfoService;
import com.earn.helper.system.service.ISystemOrderinfoService;
import com.earn.system.entity.SysUserLogin;

/**
 * 订单信息表 luhui
 * 
 * @since 2019-02-11 10:56:13
 */

@Controller
@RequestMapping("/system/orderinfo")
public class SystemOrderinfoController {
    @Autowired
    private ISystemOrderinfoService orderinfoService;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IAccountinfoService accountinfoService;

    @GetMapping()
    String Orderinfo() {
        return "helper/orderinfo/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Orderinfo> orderinfoList = orderinfoService.list(query);
        int total = orderinfoService.count(query);
        PageUtils pageUtils = new PageUtils(orderinfoList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/orderinfo/add";
    }

    @GetMapping("/edit/{orderId}")
    String edit(@PathVariable("orderId") Integer orderId, Model model) {
        Orderinfo orderinfo = orderinfoService.get(orderId);
        model.addAttribute("orderinfo", orderinfo);
        return "helper/orderinfo/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(Orderinfo orderinfo) {
        if (orderinfoService.save(orderinfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(@ActiveUser SysUserLogin userLogin, Orderinfo orderinfo) {
        switch (orderinfo.getStatus()) {
            case 1:
                orderinfo.setPayTime(new Date());
                Product product = new Product().selectById(orderinfo.getProductId());
                Userinfo userinfo = new Userinfo().selectById(orderinfo.getUserId());
                userinfo.setVipLevel(product.getProductType());
                userinfo.updateById();

                // 直推人城主资格
                Userinfo parentinfo = new Userinfo().selectById(userinfo.getParentId());
                if (parentinfo != null) {
                    if (parentinfo.getCastellanLevel() == 1) {
                        Integer fansCount = userinfoService.getMyFansCount(parentinfo.getUserId());
                        if (fansCount >= Const.getCastellanCount(parentinfo.getVipLevel())
                            && parentinfo.getCastellanLevel() == 1) {
                            parentinfo.setCastellanLevel(parentinfo.getVipLevel());
                            parentinfo.updateById();
                        }
                    }

                    // 直推人推广奖励
                    Integer opsUserId = userLogin.getSysUser().getUserId();
                    accountinfoService.addAccount(parentinfo, AccountTypeEnum.yq,
                        Const.getInvitationReward(userinfo.getVipLevel(), parentinfo.getVipLevel()), null, null,
                        opsUserId);

                    // 城主奖励
                    Userinfo grandpa = new Userinfo().selectById(parentinfo.getParentId());
                    if (grandpa != null) {
                        if (Const.GOLD.equals(grandpa.getCastellanLevel())
                            || Const.DIAMOND.equals(grandpa.getCastellanLevel())) {
                            accountinfoService.addAccount(grandpa, AccountTypeEnum.cz,
                                Const.getCastellanReward(grandpa.getCastellanLevel(), userinfo.getVipLevel()), null,
                                null, opsUserId);
                        }
                    }
                }
                break;
            case 2:
                orderinfo.setPostTime(new Date());
                break;
            default:
                break;
        }
        orderinfoService.update(orderinfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer orderId) {
        if (orderinfoService.remove(orderId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] orderIds) {
        if (orderinfoService.batchRemove(orderIds) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
