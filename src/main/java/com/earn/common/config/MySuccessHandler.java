package com.earn.common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.Charsets;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.earn.helper.entity.R;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.entity.Userinfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录成功，返回oauth token
 * 
 * @author luhui
 * @since 2019/2/1 00:09
 */
@Slf4j
@Builder(builderMethodName = "parentBuilder")
@AllArgsConstructor
public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private ObjectMapper objectMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
        throws IOException, ServletException {
        response.setCharacterEncoding(Charsets.UTF_8.name());
        response.getWriter()
            .print(new R<Userinfo>().setData(((UserLogin)auth.getPrincipal()).getUserinfo()).toJSONString());
        response.getWriter().flush();
    }
}