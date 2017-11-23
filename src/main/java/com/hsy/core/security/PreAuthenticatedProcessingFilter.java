package com.hsy.core.security;

import com.hsy.record.model.UserInfo;
import com.hsy.record.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by developer2 on 2017/11/21.
 */
public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    protected final Logger log =
            LoggerFactory.getLogger(PreAuthenticatedProcessingFilter.class);

    private AuthenticationSuccessHandler authenticationSuccessHandler = null;

    private AuthenticationFailureHandler authenticationFailureHandler = null;

    @Autowired
    private UserInfoService userInfoService ;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        UserInfo userInfo = userInfoService.getByUsername(username);
        if(userInfo != null && userInfo.getPassword().equals(password)){
            return userInfo;
        }
        return null;

    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authResult) {
        super.successfulAuthentication(request, response, authResult);

        if(authenticationSuccessHandler != null) {
            try {
                authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        super.unsuccessfulAuthentication(request, response, failed);
        if(authenticationFailureHandler != null) {
            try {
                authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    public void setAuthenticationFailureHandler (AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
