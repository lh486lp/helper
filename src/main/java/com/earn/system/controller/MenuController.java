package com.earn.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.helper.controller.BaseController;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Tree;
import com.earn.system.entity.SysMenu;
import com.earn.system.service.ISysMenuService;

/**
 * @author earn 1992lcg@163.com
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
    String prefix = "system/menu";
    @Autowired
    ISysMenuService ISysMenuService;

    @GetMapping()
    String menu(Model model) {
        return prefix + "/menu";
    }

    @RequestMapping("/list")
    @ResponseBody
    List<SysMenu> list(@RequestParam Map<String, Object> params) {
        List<SysMenu> menus = ISysMenuService.list(params);
        return menus;
    }

    @GetMapping("/add/{pId}")
    String add(Model model, @PathVariable("pId") Integer pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", ISysMenuService.get(pId).getName());
        }
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer id) {
        SysMenu mdo = ISysMenuService.get(id);
        Integer pId = mdo.getParentId();
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", ISysMenuService.get(pId).getName());
        }
        model.addAttribute("menu", mdo);
        return prefix + "/edit";
    }

    @PostMapping("/save")
    @ResponseBody
    R save(SysMenu menu) {
        if (ISysMenuService.save(menu) > 0) {
            return R.ok();
        } else {
            return R.error("保存失败");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    R update(SysMenu menu) {
        if (ISysMenuService.update(menu) > 0) {
            return R.ok();
        } else {
            return R.error("更新失败");
        }
    }

    @PostMapping("/remove")
    @ResponseBody
    R remove(Integer id) {
        if (ISysMenuService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error("删除失败");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    Tree<SysMenu> tree() {
        Tree<SysMenu> tree = ISysMenuService.getTree();
        return tree;
    }

    @GetMapping("/tree/{roleId}")
    @ResponseBody
    Tree<SysMenu> tree(@PathVariable("roleId") Integer roleId) {
        Tree<SysMenu> tree = ISysMenuService.getTree(roleId);
        return tree;
    }
}
