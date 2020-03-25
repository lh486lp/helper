package com.earn.helper.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.earn.helper.entity.R;
import com.earn.helper.entity.UserLogin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {
    public static String getUsername(HttpServletRequest request) {
        SecurityContextImpl securityContextImpl =
            (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        // 登录名
        System.out.println("Username:" + securityContextImpl.getAuthentication().getName());
        // 登录密码，未加密的
        System.out.println("Credentials:" + securityContextImpl.getAuthentication().getCredentials());
        WebAuthenticationDetails details =
            (WebAuthenticationDetails)securityContextImpl.getAuthentication().getDetails();
        // 获得访问地址
        System.out.println("RemoteAddress" + details.getRemoteAddress());
        // 获得sessionid
        System.out.println("SessionId" + details.getSessionId());
        // 获得当前用户所拥有的权限
        List<GrantedAuthority> authorities =
            (List<GrantedAuthority>)securityContextImpl.getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            System.out.println("Authority" + grantedAuthority.getAuthority());
        }
        return securityContextImpl.getAuthentication().getName();
    }

    public static String getUsername() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        UserLogin user = (UserLogin)auth.getPrincipal();
        return user.getUsername();
    }

    public static R getR(boolean b) {
        try {
            if (b) {
                return R.ok();
            }
            return R.error();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return R.error();
        }
    }
}
