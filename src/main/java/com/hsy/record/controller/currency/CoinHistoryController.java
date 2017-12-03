package com.hsy.record.controller.currency;

import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.enu.CurrencyStateEnum;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.currency.CoinHistoryService;
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
@RequestMapping(CoinHistoryController.URL_PREFIX)
public class CoinHistoryController {
    private static final Logger log = LoggerFactory.getLogger(CoinHistoryController.class);

    public final static String URL_PREFIX = "/daily/currency/history";

    @Autowired
    private CoinHistoryService coinHistoryService;

    @RequestMapping("/index")
    public String index(Model model,
                      @RequestParam(required = true) String id){
        Long beginTime = DateUtilExt.getLongPlusDays(DateUtilExt.getTimestamp(),-30L);
        List<String> dayList = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();
        List<Double> volumeList = new ArrayList<>();
        List<CoinHistory> coinHistoryList = coinHistoryService.getListByIdAndBeginTime(id,beginTime);
        if(coinHistoryList !=null && !coinHistoryList.isEmpty()){
            coinHistoryService.setData(coinHistoryList,dayList,priceList,volumeList);
        }
        model.addAttribute("id",id);
        model.addAttribute("dayList",dayList);
        model.addAttribute("priceList",priceList);
        model.addAttribute("volumeList",volumeList);
        return URL_PREFIX+"/index";
    }

}
