package com.earn.system.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.earn.helper.entity.Userinfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户 认证数据
 *
 * @author luhui
 * @since 2019/1/14 下午5:55
 */
public class SysUserLogin extends User {
    private static final long serialVersionUID = 2031796347181183771L;
    @Getter
    @Setter
    private SysUser sysUser;

    public SysUserLogin(String username, String password, Collection<? extends GrantedAuthority> authorities,
        SysUser sysUser) {
        super(username, password, authorities);
        this.sysUser = sysUser;
    }
}