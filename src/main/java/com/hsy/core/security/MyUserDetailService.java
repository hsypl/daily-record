package com.hsy.core.security;

import com.hsy.record.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by developer2 on 2017/9/13.
 */
public class MyUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("admin");
        userInfo.setPassword("$2a$10$gyRwt/7NXtRpU61VaY4O6Oy7v4tqEqtE5jIuSvBMHTaowf/4uXSOG");
        MyUserDetail myUserDetail = new MyUserDetail(userInfo);
        return myUserDetail;
    }

}
