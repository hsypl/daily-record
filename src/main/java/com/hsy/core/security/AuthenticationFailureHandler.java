package com.hsy.core.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 携带无效token的请求处理器
 * Created by Chwing on 2017/7/6.
 */
public class AuthenticationFailureHandler extends
        SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception){
//        super.onAuthenticationFailure(request, response, exception);
    }
}
