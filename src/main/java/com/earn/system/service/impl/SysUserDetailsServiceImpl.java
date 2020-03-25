package com.earn.system.service.impl;

import java.util.Collection;

import com.earn.common.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.earn.system.entity.SysUser;
import com.earn.system.entity.SysUserLogin;
import com.earn.system.mapper.SysUserMapper;
import com.earn.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luhui
 * @since 2019/1/13 22:01
 */
@Slf4j
@Component
public class SysUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserDetail(sysUserMapper.selectByName(username));
    }

    private UserDetails getUserDetail(SysUser sysUser) {
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获取权限
        String[] perms = sysMenuService.listPerms(sysUser.getUserId());
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms);

        // 构造security用户
        return new SysUserLogin(sysUser.getUsername(), "{bcrypt}" + sysUser.getPassword(), authorities, sysUser);
    }
}