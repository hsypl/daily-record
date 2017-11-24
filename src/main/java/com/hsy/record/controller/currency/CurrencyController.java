package com.hsy.record.controller.currency;

import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.service.IcoProjectInfoService;
import com.sungness.core.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(CurrencyController.URL_PREFIX)
public class CurrencyController {
    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);

    public final static String URL_PREFIX = "/daily/currency";

    @Autowired
    private IcoProjectInfoService icoProjectInfoService;

    @RequestMapping("/index")
    public void index(@RequestAttribute UserInfo userInfo,Model model){
        List<IcoProjectInfo> icoProjectInfoList
                = icoProjectInfoService.getListLeftJoinByUid(userInfo.getUid());
        icoProjectInfoList = icoProjectInfoService.filterList(icoProjectInfoList);
        if(!icoProjectInfoList.isEmpty()){
            String nameData = icoProjectInfoService.getNameData(icoProjectInfoList);
            model.addAttribute("nameData",nameData);
        }
        log.debug(GsonUtils.toJson(icoProjectInfoList));
        model.addAttribute("infoList",icoProjectInfoList);

    }
}
