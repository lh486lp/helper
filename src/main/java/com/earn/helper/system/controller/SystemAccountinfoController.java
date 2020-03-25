package com.earn.helper.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.earn.helper.enume.AccountTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.entity.Accountinfo;
import com.earn.helper.entity.R;
import com.earn.helper.system.service.ISystemAccountinfoService;

/**
 * 账户信息表 luhui
 * 
 * @since 2019-02-11 12:16:58
 */

@Controller
@RequestMapping("/system/accountinfo")
public class SystemAccountinfoController {
    @Autowired
    private ISystemAccountinfoService accountinfoService;

    @GetMapping()
    String Accountinfo(Model model) {
        // 系统总收益
        model.addAttribute("amount", "100");
        // 系统总支付
        model.addAttribute("cost", "100");

        // 用户总收入
        model.addAttribute("totalIncome", "100");
        // 用户总提现
        model.addAttribute("totalWithdraw", "100");

        return "helper/accountinfo/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Accountinfo> accountinfoList = accountinfoService.list(query);
        int total = accountinfoService.count(query);
        PageUtils pageUtils = new PageUtils(accountinfoList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/accountinfo/add";
    }

    @GetMapping("/edit/{accountId}")
    String edit(@PathVariable("accountId") Integer accountId, Model model) {
        Accountinfo accountinfo = accountinfoService.get(accountId);
        model.addAttribute("accountinfo", accountinfo);
        return "helper/accountinfo/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(Accountinfo accountinfo) {
        if (accountinfoService.save(accountinfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(Accountinfo accountinfo) {
        accountinfoService.update(accountinfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer accountId) {
        if (accountinfoService.remove(accountId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] accountIds) {
        if (accountinfoService.batchRemove(accountIds) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
