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
import com.earn.helper.entity.Systeminfo;
import com.earn.helper.system.service.ISystemSysteminfoService;

/**
 * 系统配置信息表 luhui
 * 
 * @since 2019-02-11 10:56:24
 */

@Controller
@RequestMapping("/system/systeminfo")
public class SystemSysteminfoController {
    @Autowired
    private ISystemSysteminfoService systeminfoService;

    @GetMapping()
    String Systeminfo() {
        return "helper/systeminfo/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Systeminfo> systeminfoList = systeminfoService.list(query);
        int total = systeminfoService.count(query);
        PageUtils pageUtils = new PageUtils(systeminfoList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/systeminfo/add";
    }

    @GetMapping("/edit/{key}")
    String edit(@PathVariable("key") String key, Model model) {
        Systeminfo systeminfo = systeminfoService.get(key);
        model.addAttribute("systeminfo", systeminfo);
        return "helper/systeminfo/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(Systeminfo systeminfo) {
        if (systeminfoService.save(systeminfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(Systeminfo systeminfo) {
        systeminfoService.update(systeminfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String key) {
        if (systeminfoService.remove(key) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") String[] keys) {
        if (systeminfoService.batchRemove(keys) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
