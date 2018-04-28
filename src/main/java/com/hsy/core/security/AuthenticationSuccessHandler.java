package com.hsy.core.security;

import com.hsy.record.model.UserInfo;
import com.hsy.record.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 登录成功处理器
 * Created by wanghongwei on 4/14/16.
 */
public class AuthenticationSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {
    protected final Logger log =
            LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        MyUserDetail clientUserDetails =
                (MyUserDetail) authentication.getPrincipal();
        UserInfo userInfo = clientUserDetails.getUserInfo();
        String targetUrl = "/dailys/assets/index";
        Set<String> privilegeSet = userInfoService.getPrivilegeSet(userInfo);
        clientUserDetails.addPrivilegeKeys(privilegeSet);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }


    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }


}