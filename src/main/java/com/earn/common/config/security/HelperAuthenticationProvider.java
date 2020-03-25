package com.earn.common.config.security;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 前端登录校验逻辑
 * 
 * @author luhui
 * @since 2019/1/31 23:53
 */
@Slf4j
public class HelperAuthenticationProvider implements AuthenticationProvider {
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    @Getter
    @Setter
    private HelperUserDetailsServiceImpl userDetailsService;
    @Getter
    @Setter
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HelperAuthenticationToken helperAuthenticationToken = (HelperAuthenticationToken)authentication;

        String principal = helperAuthenticationToken.getPrincipal().toString();
        String credentials = helperAuthenticationToken.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);

        if (userDetails == null) {
            log.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.noopBindAccount", "Noop Bind Account"));
        }
        if (!passwordEncoder.matches(credentials, userDetails.getPassword())) {
            log.debug("Authentication failed:   credentials wrong");

            throw new BadCredentialsException(
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.BadCredentials", "BadCredentials"));
        }

        HelperAuthenticationToken authenticationToken =
            new HelperAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authenticationToken.setDetails(helperAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return HelperAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
