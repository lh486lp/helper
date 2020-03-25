package com.earn.system.controller;

import java.util.List;

import com.earn.system.entity.SysUser;
import com.earn.system.entity.SysUserLogin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.earn.common.annotation.ActiveUser;
import com.earn.helper.controller.BaseController;
import com.earn.helper.entity.Tree;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.entity.Userinfo;
import com.earn.common.util.FileUtil;
import com.earn.system.entity.SysMenu;
import com.earn.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luhui
 * @since 2019/1/31 下午5:13
 */
@Slf4j
@Controller
public class LoginController extends BaseController {
    @Autowired
    private ISysMenuService menuService;

    @GetMapping({"/", ""})
    String welcome(Model model) {
        return "redirect:/index";
    }

    @GetMapping("/index")
    String index(@ActiveUser SysUserLogin userLogin, Model model) {
        SysUser user = userLogin.getSysUser();
        List<Tree<SysMenu>> menus = menuService.listMenuTree(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", user.getName());
        String imgPath = user.getImgPath();
        if (imgPath != null && StringUtils.isNoneBlank(imgPath) && FileUtil.isExist(imgPath)) {
            model.addAttribute("picUrl", imgPath);
        } else {
            model.addAttribute("picUrl", "/img/photo_s.jpg");
        }
        model.addAttribute("username", user.getUsername());
        return "index_v1";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
