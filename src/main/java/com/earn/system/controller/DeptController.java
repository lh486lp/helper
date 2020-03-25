package com.earn.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.earn.helper.controller.BaseController;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Tree;
import com.earn.common.util.Const;
import com.earn.system.entity.SysDept;
import com.earn.system.service.ISysDeptService;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController {
    private String prefix = "system/dept";
    @Autowired
    private ISysDeptService sysDeptService;

    @GetMapping()
    String dept() {
        return prefix + "/dept";
    }

    @ResponseBody
    @GetMapping("/list")
    public List<SysDept> list() {
        Map<String, Object> query = new HashMap<>(16);
        List<SysDept> sysDeptList = sysDeptService.list(query);
        return sysDeptList;
    }

    @GetMapping("/add/{pId}")
    String add(@PathVariable("pId") Integer pId, Model model) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "总部门");
        } else {
            model.addAttribute("pName", sysDeptService.get(pId).getName());
        }
        return prefix + "/add";
    }

    @GetMapping("/edit/{deptId}")
    String edit(@PathVariable("deptId") Integer deptId, Model model) {
        SysDept sysDept = sysDeptService.get(deptId);
        model.addAttribute("sysDept", sysDept);
        if (Const.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
            model.addAttribute("parentDeptName", "无");
        } else {
            SysDept parDept = sysDeptService.get(sysDept.getParentId());
            model.addAttribute("parentDeptName", parDept.getName());
        }
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(SysDept sysDept) {
        if (sysDeptService.save(sysDept) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(SysDept sysDept) {
        if (sysDeptService.update(sysDept) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer deptId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", deptId);
        if (sysDeptService.count(map) > 0) {
            return R.error("包含下级部门,不允许修改");
        }
        if (sysDeptService.checkDeptHasUser(deptId)) {
            if (sysDeptService.remove(deptId) > 0) {
                return R.ok();
            }
        } else {
            return R.error("部门包含用户,不允许修改");
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] deptIds) {
        sysDeptService.batchRemove(deptIds);
        return R.ok();
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<SysDept> tree() {
        Tree<SysDept> tree = new Tree<SysDept>();
        tree = sysDeptService.getTree();
        return tree;
    }

    @GetMapping("/treeView")
    String treeView() {
        return prefix + "/deptTree";
    }

}
