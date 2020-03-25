package com.earn.helper.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Tree;
import com.earn.helper.entity.Userinfo;
import com.earn.helper.system.service.ISystemUserinfoService;

/**
 * 用户信息表 luhui
 * 
 * @since 2019-02-11 10:56:33
 */

@Controller
@RequestMapping("/system/userinfo")
public class SystemUserinfoController {
    @Autowired
    private ISystemUserinfoService userinfoService;

    @GetMapping()
    String Userinfo() {
        return "helper/userinfo/list";
    }

    @ResponseBody
    @GetMapping("/get/{userId}")
    R edit(@PathVariable("userId") Integer userId) {
        return R.ok(userinfoService.get(userId).getNickName());
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Userinfo> userinfoList = userinfoService.list(query);
        int total = userinfoService.count(query);
        PageUtils pageUtils = new PageUtils(userinfoList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/userinfo/add";
    }

    @GetMapping("/edit/{userId}")
    String edit(@PathVariable("userId") Integer userId, Model model) {
        Userinfo userinfo = userinfoService.get(userId);
        model.addAttribute("userinfo", userinfo);
        return "helper/userinfo/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(Userinfo userinfo) {
        if (userinfoService.save(userinfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(Userinfo userinfo) {
        userinfoService.update(userinfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer userId) {
        if (userinfoService.remove(userId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] userIds) {
        if (userinfoService.batchRemove(userIds) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/fansTree/{userId}")
    @ResponseBody
    public List<Tree<Userinfo>> fansTree(@PathVariable("userId") Integer userId) {
        return userinfoService.getFansTree(userId);
    }
}
