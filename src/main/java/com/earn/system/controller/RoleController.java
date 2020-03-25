package com.earn.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.earn.helper.controller.BaseController;
import com.earn.helper.entity.R;
import com.earn.system.entity.SysRole;
import com.earn.system.service.ISysRoleService;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
    String prefix = "system/role";
    @Autowired
    ISysRoleService ISysRoleService;

    @GetMapping()
    String role() {
        return prefix + "/role";
    }

    @GetMapping("/list")
    @ResponseBody()
    List<SysRole> list() {
        List<SysRole> roles = ISysRoleService.list();
        return roles;
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Integer id, Model model) {
        SysRole sysRole = ISysRoleService.get(id);
        model.addAttribute("role", sysRole);
        return prefix + "/edit";
    }

    @PostMapping("/save")
    @ResponseBody()
    R save(SysRole role) {
        if (ISysRoleService.save(role) > 0) {
            return R.ok();
        } else {
            return R.error("保存失败");
        }
    }

    @PostMapping("/update")
    @ResponseBody()
    R update(SysRole role) {
        if (ISysRoleService.update(role) > 0) {
            return R.ok();
        } else {
            return R.error("保存失败");
        }
    }

    @PostMapping("/remove")
    @ResponseBody()
    R save(Integer id) {
        if (ISysRoleService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error("删除失败");
        }
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Integer[] ids) {
        int r = ISysRoleService.batchremove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }
}
