package com.earn.common.annotation;

import java.lang.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * 获取当前登陆用户
 *
 * @author luhui
 * @since  2019/1/14 下午5:55
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface ActiveUser {}
