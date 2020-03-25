package com.earn.common.config;

import com.earn.system.service.impl.SysUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.earn.common.config.security.HelperLoginSuccessHandler;
import com.earn.common.config.security.HelperSecurityConfigurer;
import com.earn.common.config.security.HelperUserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luhui
 * @since 2019/1/31 下午5:02
 */
@Slf4j
@Primary
@Order(90)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SessionRegistry sessionRegistry;
    @Autowired
    private HelperUserDetailsServiceImpl helperUserDetailsService;
    @Autowired
    private SysUserDetailsServiceImpl systemUserDetailsService;
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public SessionRegistry getSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/img/**", "/login", "/error", "/403").permitAll()
            .antMatchers("/app/tgzs.apk", "/app/tgzs.json", "/app/xjk.apk", "/app/xjk.json", "/app/xjkmanager.apk", "/app/xjkmanager.json").permitAll()
            .antMatchers("/userinfo/register**").permitAll().antMatchers("/userinfo/resetPassword**").permitAll()
            .antMatchers("/task/list**").permitAll().antMatchers("/product/list**").permitAll()
            .antMatchers("/article/***").permitAll().antMatchers("/systeminfo/list**").permitAll()
            .antMatchers("/system/sysFile/getFile").permitAll().anyRequest().authenticated().and().headers()
            .frameOptions().disable()
            // 登录
            .and().csrf().disable().formLogin().loginPage("/login")
            // 登陆成功后跳转的请求,要自己写一个controller转发
            .defaultSuccessUrl("/index").and().exceptionHandling().accessDeniedPage("/403")
            // 前端登录
            .and().apply(helperSecurityConfigurer())
            // session管理
            .and().sessionManagement()
            // 系统中同一个账号的登陆数量限制
            .maximumSessions(2).sessionRegistry(sessionRegistry)
            // 登出
            .and().and().logout()
            // 使session失效
            .invalidateHttpSession(true)
            // 清除证信息
            .clearAuthentication(true).and().httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("phone").password("password").roles("USER");
    }

    /**
     * 定义认证用户信息获取来源，密码效验规则
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // log.info("______________________configure");
        // auth.authenticationProvider(new MyAuthenticationProvider());
        auth.userDetailsService(systemUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-updated Encoded password
     * does not look like BCrypt
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler helperLoginSuccessHandler() {
        return HelperLoginSuccessHandler.childBuilder().objectMapper(objectMapper).passwordEncoder(passwordEncoder())
            .build();

    }

    @Bean
    public HelperSecurityConfigurer helperSecurityConfigurer() {
        return new HelperSecurityConfigurer(helperLoginSuccessHandler(), helperUserDetailsService, passwordEncoder());
    }
}