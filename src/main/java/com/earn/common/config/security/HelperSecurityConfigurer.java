package com.earn.common.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

/**
 * 前端登录配置入口
 * 
 * @author luhui
 * @since 2019/1/31 23:54
 */
@Component
@AllArgsConstructor
public class HelperSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private AuthenticationSuccessHandler mobileLoginSuccessHandler;
    private HelperUserDetailsServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) {
        HelperAuthenticationFilter helperAuthenticationFilter = new HelperAuthenticationFilter();
        helperAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        helperAuthenticationFilter.setAuthenticationSuccessHandler(mobileLoginSuccessHandler);

        HelperAuthenticationProvider helperAuthenticationProvider = new HelperAuthenticationProvider();
        helperAuthenticationProvider.setUserDetailsService(userDetailsService);
        helperAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        http.authenticationProvider(helperAuthenticationProvider).addFilterAt(helperAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class);
    }
}
