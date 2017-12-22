package com.hsy.record.controller.dailys;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.UserInfo;
import com.hsy.record.service.UserInfoService;
import com.sungness.core.util.UuidGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(UserController.URL_PREFIX)
public class UserController {

    public final static String URL_PREFIX = "/dailys/user";

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/index")
    public void index (Model model){
        List<UserInfo> userInfoList = userInfoService.getList();
        model.addAttribute("userInfoList",userInfoList);
    }

    @RequestMapping("/edit")
    public void edit(Model model,@RequestParam(required = false) String id){
            model.addAttribute("userInfo",userInfoService.getSafety(id));
    }

    @RequestMapping("/add")
    public String add(UserInfo userInfo) throws ServiceProcessException {
        if(StringUtils.isNotBlank(userInfo.getUid())){
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            String hashPass = encode.encode(userInfo.getPassword());
            userInfo.setPassword(hashPass);
            userInfo.setName(userInfo.getName());
            userInfo.setUsername(userInfo.getUsername());
            userInfoService.update(userInfo);
        }else {
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            String hashPass = encode.encode(userInfo.getPassword());
            userInfo.setUid(UuidGenerator.nextUid());
            userInfo.setPassword(hashPass);
            userInfo.setName(userInfo.getName());
            userInfo.setUsername(userInfo.getUsername());
            userInfoService.insert(userInfo);
        }
        return "redirect:"+URL_PREFIX+"/index";
    }
}
