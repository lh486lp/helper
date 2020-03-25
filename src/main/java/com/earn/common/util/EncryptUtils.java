package com.earn.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author luhui
 * @since 2018/12/23 18:20
 */
public class EncryptUtils {
    public static String encode(String text) {
        return new BCryptPasswordEncoder().encode(text);
    }

    public static Boolean matches(String text, String encodeText) {
        return new BCryptPasswordEncoder().matches(text, encodeText);
    }
}
