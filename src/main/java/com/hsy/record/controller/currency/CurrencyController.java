package com.hsy.record.controller.currency;

import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.enu.CurrencyStateEnum;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.sungness.core.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AssetsHistoryService assetsHistoryService;

    @Autowired
    private CoinMarketCapService coinMarketCapService;

    @RequestMapping("/index")
    public void index(@RequestAttribute UserInfo userInfo,Model model,
                      @RequestParam(required = false) String month){
        List<IcoProjectInfo> icoProjectInfoList
                = icoProjectInfoService.getListLeftJoinByUid(userInfo.getUid());
        icoProjectInfoList = icoProjectInfoService.filterList(icoProjectInfoList);
        if(!icoProjectInfoList.isEmpty()){
            String nameData = icoProjectInfoService.getNameData(icoProjectInfoList);
            model.addAttribute("nameData",nameData);
        }
        Map<String,Long> assetsMap = assetsHistoryService.getMonthMap(month);
        model.addAttribute("assetsMap",assetsMap);
        model.addAttribute("days",assetsMap.size());
        model.addAttribute("infoList",icoProjectInfoList);
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map<String, Object>  update(@RequestParam String month){
        Map<String,Long> result = assetsHistoryService.getMonthMap(month);
        Map<String,Object> resultData = new HashMap<>();
        if( result != null && !result.isEmpty()){
            List<String> monthList = new ArrayList<>();
            List<Long> valueList = new ArrayList<>();
            for (String key : result.keySet()){
                monthList.add(key);
                valueList.add(result.get(key));
            }
            resultData.put("month",monthList);
            resultData.put("value",valueList);
        }
        return resultData;
    }
}
