package com.hsy.record.controller;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.controller.currency.IcoController;
import com.hsy.record.model.UserInfo;
import com.hsy.record.service.UserInfoService;
import com.sungness.core.util.UuidGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by developer2 on 2017/11/20.
 */
@Controller
@RequestMapping(UserInfoController.URL_PREFIX)
public class UserInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    public final static String URL_PREFIX = "/daily/user";

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/index")
    public void index (Model model){
        List<UserInfo> userInfoList = userInfoService.getList();
        model.addAttribute("userInfoList",userInfoList);
    }

    @RequestMapping("/add")
    public String add(@RequestParam(required = false) String uid ,
                      @RequestParam String username,
                      @RequestParam String password,
                      @RequestParam String name) throws ServiceProcessException {
        UserInfo userInfo = userInfoService.getSafety(uid);
        if(StringUtils.isNotBlank(userInfo.getUid())){
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            String hashPass = encode.encode(password);
            userInfo.setPassword(hashPass);
            userInfo.setName(name);
            userInfo.setUsername(username);
            userInfoService.update(userInfo);
        }else {
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            String hashPass = encode.encode(password);
            userInfo.setUid(UuidGenerator.nextUid());
            userInfo.setPassword(hashPass);
            userInfo.setName(name);
            userInfo.setUsername(username);
            log.debug(hashPass);
            userInfoService.insert(userInfo);
        }
        return "redirect:"+URL_PREFIX+"/index";
    }

}
