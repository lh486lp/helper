package com.earn.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.earn.common.annotation.ActiveUser;
import com.earn.common.util.EncryptUtils;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.controller.BaseController;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Tree;
import com.earn.system.entity.SysDept;
import com.earn.system.entity.SysRole;
import com.earn.system.entity.SysUser;
import com.earn.system.entity.SysUserLogin;
import com.earn.system.service.ISysRoleService;
import com.earn.system.service.ISysUserService;

@Slf4j
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {
    private String prefix = "system/user";
    @Autowired
    ISysUserService userService;
    @Autowired
    ISysRoleService roleService;

    @GetMapping("")
    String user(Model model) {
        return prefix + "/user";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<SysUser> sysUserList = userService.list(query);
        int total = userService.count(query);
        PageUtils pageUtil = new PageUtils(sysUserList, total);
        return pageUtil;
    }

    @GetMapping("/add")
    String add(Model model) {
        List<SysRole> roles = roleService.list();
        model.addAttribute("roles", roles);
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer id) {
        SysUser userDO = userService.get(id);
        model.addAttribute("user", userDO);
        List<SysRole> roles = roleService.list(id);
        model.addAttribute("roles", roles);
        return prefix + "/edit";
    }

    @PostMapping("/save")
    @ResponseBody
    R save(SysUser user) {
        user.setPassword(EncryptUtils.encode(user.getPassword()));
        if (userService.save(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/update")
    @ResponseBody
    R update(SysUser user) {
        if (userService.update(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/updatePeronal")
    @ResponseBody
    R updatePeronal(SysUser user) {
        if (userService.updatePersonal(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/remove")
    @ResponseBody
    R remove(Integer id) {
        if (userService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Integer[] userIds) {
        int r = userService.batchremove(userIds);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/exit")
    @ResponseBody
    boolean exit(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exit(params);
    }

    @GetMapping("/resetPwd/{id}")
    String resetPwd(@PathVariable("id") Integer userId, Model model) {

        SysUser userDO = new SysUser();
        userDO.setUserId(userId);
        model.addAttribute("user", userDO);
        return prefix + "/reset_pwd";
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    R resetPwd(SysUser user) {
        try {
            userService.resetPwd(user);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }

    }

    @PostMapping("/adminResetPwd")
    @ResponseBody
    R adminResetPwd(SysUser userVO) {
        try {
            userService.adminResetPwd(userVO);
            return R.ok();
        } catch (Exception e) {
            return R.error(e.getMessage());
        }

    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<SysDept> tree() {
        Tree<SysDept> tree = new Tree<SysDept>();
        tree = userService.getTree();
        return tree;
    }

    @GetMapping("/treeView")
    String treeView() {
        return prefix + "/userTree";
    }

    @GetMapping("/personal")
    String personal(@ActiveUser SysUserLogin userLogin, Model model) {
        SysUser userDO = userService.get(userLogin.getSysUser().getUserId());
        model.addAttribute("user", userDO);
        // model.addAttribute("hobbyList", dictService.getHobbyList(userDO));
        // model.addAttribute("sexList", dictService.getSexList());
        return prefix + "/personal";
    }

    @ResponseBody
    @PostMapping("/uploadImg")
    R uploadImg(@ActiveUser SysUserLogin userLogin, @RequestParam("avatar_file") MultipartFile file, String avatar_data,
        HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error("演示系统不允许修改,完整体验请部署程序");
        }
        Map<String, Object> result = new HashMap<>();
        try {
            result = userService.updatePersonalImg(file, avatar_data, userLogin.getSysUser().getUserId());
        } catch (Exception e) {
            return R.error("更新图像失败！");
        }
        if (result != null && result.size() > 0) {
            return R.ok(result);
        } else {
            return R.error("更新图像失败！");
        }
    }
}
