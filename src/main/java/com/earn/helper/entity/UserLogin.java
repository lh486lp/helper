package com.earn.helper.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户 认证数据
 *
 * @author luhui
 * @since 2019/1/14 下午5:55
 */
public class UserLogin extends User {
    private static final long serialVersionUID = 2031796347181183771L;
    @Getter
    @Setter
    private Userinfo userinfo;

    public UserLogin(String username, String password, Collection<? extends GrantedAuthority> authorities,
        Userinfo userinfo) {
        super(username, password, authorities);
        this.userinfo = userinfo;
    }
}