package com.hsy.record.controller.dailys;

import com.hsy.core.annotation.Command;
import com.hsy.core.annotation.Module;
import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.enu.JudgeEnum;
import com.hsy.record.model.system.ModuleInfo;
import com.hsy.record.service.UserInfoService;
import com.hsy.record.service.system.ModuleInfoService;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.UuidGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(UserController.URL_PREFIX)
@Module(value = UserController.MODULE_NAME , order = 2, icon = "fa fa-user-o")
public class UserController {

    public final static String URL_PREFIX = "/dailys/user";

    public final static String MODULE_NAME = "User";

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ModuleInfoService moduleInfoService;

    @Command(value = MODULE_NAME + "-Index", isInlet = true, order = 1)
    @RequestMapping("/index")
    public void index (Model model) throws HttpClientException, IOException {
        List<UserInfo> userInfoList = userInfoService.getList();
        model.addAttribute("userInfoList",userInfoList);
    }

    @Command(value = "编辑" + MODULE_NAME, order = 2)
    @RequestMapping("/edit")
    public void edit(Model model,@RequestParam(required = false) String id){
        model.addAttribute("userInfo",userInfoService.getWithPrivilege(id));
        model.addAttribute("moduleList",moduleInfoService.getVailMenuInfoList());
        model.addAttribute("judgeEnumList", JudgeEnum.getEnumList());
    }

    @Command(value = "添加" + MODULE_NAME, order = 2)
    @RequestMapping("/add")
    public String add(UserInfo userInfo) throws ServiceProcessException {
        userInfoService.save(userInfo);
        return "redirect:"+URL_PREFIX+"/index";
    }
}
