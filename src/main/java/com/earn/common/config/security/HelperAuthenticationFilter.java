package com.earn.common.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.Getter;
import lombok.Setter;

/**
 * 前端登录验证filter
 * 
 * @author luhui
 * @since 2019/1/31 23:54
 */
public class HelperAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String SPRING_SECURITY_FORM_MOBILE_KEY = "username";
    private static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private AuthenticationEventPublisher eventPublisher = new HelperAuthenticationFilter.NullEventPublisher();
    private HelperAuthenticationEntryPoint authenticationEntryPoint =
        new HelperAuthenticationEntryPoint("/helper/login");

    @Getter
    @Setter
    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    @Getter
    @Setter
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    @Getter
    @Setter
    private boolean postOnly = true;

    public HelperAuthenticationFilter() {
        super(new AntPathRequestMatcher("/helper/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        if (username == null) {
            username = "";
        }

        username = username.trim();
        HelperAuthenticationToken helperAuthenticationToken = new HelperAuthenticationToken(username, password);
        setDetails(request, helperAuthenticationToken);

        try {
            return this.getAuthenticationManager().authenticate(helperAuthenticationToken);
        } catch (Exception failed) {
            SecurityContextHolder.clearContext();
            logger.debug("Authentication request failed: " + failed);

            eventPublisher.publishAuthenticationFailure(new BadCredentialsException(failed.getMessage(), failed),
                new PreAuthenticatedAuthenticationToken("access-token", "N/A"));

            try {
                authenticationEntryPoint.commence(request, response,
                    new UsernameNotFoundException(failed.getMessage(), failed));
            } catch (Exception e) {
                logger.error("authenticationEntryPoint handle error:{}", failed);
            }
        }

        return null;
    }

    private void setDetails(HttpServletRequest request, HelperAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainUsername(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    private String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    private static final class NullEventPublisher implements AuthenticationEventPublisher {
        @Override
        public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {}

        @Override
        public void publishAuthenticationSuccess(Authentication authentication) {}
    }
}
