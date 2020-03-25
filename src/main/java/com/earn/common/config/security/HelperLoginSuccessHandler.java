package com.earn.common.config.security;

import com.earn.common.config.MySuccessHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * 前端登录成功，返回oauth token
 * 
 * @author luhui
 * @since 2019/2/1 00:09
 */
@Slf4j
public class HelperLoginSuccessHandler extends MySuccessHandler {
    @Builder(builderMethodName = "childBuilder")
    public HelperLoginSuccessHandler(ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        super(objectMapper, passwordEncoder);
    }
}
