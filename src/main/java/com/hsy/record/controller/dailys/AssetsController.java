package com.hsy.record.controller.dailys;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(AssetsController.URL_PREFIX)
public class AssetsController {

    public final static String URL_PREFIX = "/dailys/assets";

    public final static String URL_INDEX = "/dailys/assets/index";

    @Autowired
    private AssetsHistoryService assetsHistoryService;

    @Autowired
    private IcoProjectInfoService icoProjectInfoService;

    @Autowired
    private CoinMarketCapService coinMarketCapService;

    @RequestMapping("/index")
    public void index(@RequestAttribute UserInfo userInfo, Model model){
        List<IcoProjectInfo> icoProjectInfoList
                = icoProjectInfoService.getListLeftJoinByUid(userInfo.getUid());
        icoProjectInfoList = icoProjectInfoService.filterList(icoProjectInfoList);
        if(!icoProjectInfoList.isEmpty()){
            String nameData = icoProjectInfoService.getNameData(icoProjectInfoList);
            model.addAttribute("nameData",nameData);
        }
        Map<String,Long> assetsMap = assetsHistoryService.getMonthMap(userInfo.getUid(),
                DateUtilExt.getLongPlusDays(DateUtil.getTimestamp(),-8L), DateUtil.getTimestamp());
        Integer amount = icoProjectInfoService.getSum(userInfo.getUid());
        model.addAttribute("count",amount);
        model.addAttribute("rise",assetsHistoryService.getDayRise(userInfo.getUid(),amount));
        model.addAttribute("assetsMap",assetsMap);
        model.addAttribute("days",assetsMap.size());
        model.addAttribute("infoList",icoProjectInfoList);
    }

    @RequestMapping("/update")
    public String update() throws IOException, ServiceProcessException, HttpClientException {
        coinMarketCapService.update();
        return "redirect:"+URL_INDEX;
    }

    @RequestMapping("/history")
    public void history(@RequestAttribute UserInfo userInfo, Model model){
        Map<String,Long> assetsMap = assetsHistoryService.getMonthMap(userInfo.getUid(),null);
        model.addAttribute("assetsMap",assetsMap);
    }

    @ResponseBody
    @RequestMapping("/history/month")
    public Map<String, Object>  update(@RequestAttribute UserInfo userInfo,@RequestParam String month){
        Map<String,Long> result = assetsHistoryService.getMonthMap(userInfo.getUid(),month);
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
