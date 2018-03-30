package com.hsy.record.controller.dailys;

import com.hsy.core.annotation.Command;
import com.hsy.core.annotation.Module;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.currency.CoinHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.enu.CurrencyStateEnum;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.currency.CoinHistoryService;
import com.hsy.record.service.currency.CoinMarketCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(StatsController.URL_PREFIX)
@Module(value = StatsController.MODULE_NAME , order = 4, icon = "fa fa-usd")
public class StatsController {

    public final static String URL_PREFIX = "/dailys/stats";

    public final static String MODULE_NAME = "Stats";

    @Autowired
    private CoinMarketCapService coinMarketCapService;

    @Autowired
    private CoinHistoryService coinHistoryService;

    @Command(value = MODULE_NAME + "-Index", isInlet = true, order = 1)
    @RequestMapping("/index")
    public void index(Model model){
        List<CoinMarketCap> coinMarketCapList
                = coinMarketCapService.getListByStatus(CurrencyStateEnum.YES.getValue());
        model.addAttribute("coinMarketCapList",coinMarketCapList);
    }

    @Command(value = "详情" + MODULE_NAME, order = 2)
    @RequestMapping("/detail")
    public void index(Model model,
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
    }

}
