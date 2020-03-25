package com.earn.common.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.entity.Userinfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luhui
 * @since 2019/1/13 22:01
 */
@Slf4j
@Component
public class HelperUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<Userinfo>().eq("username", username);
        Userinfo user = new Userinfo().selectOne(wrapper);
        return getUserDetail(user);
    }

    private UserDetails getUserDetail(Userinfo userinfo) {
        if (userinfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("admin");
        // 构造security用户
        return new UserLogin(userinfo.getPhone(), "{bcrypt}" + userinfo.getPassword(), authorities, userinfo);
    }
}