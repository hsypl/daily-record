package com.hsy.record.controller.dailys;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.currency.UserCoinRelation;
import com.hsy.record.service.currency.CoinHistoryService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.hsy.record.service.currency.CurrencyInfoService;
import com.hsy.record.service.currency.UserCoinRelationService;
import com.sungness.core.httpclient.HttpClientException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(QueryController.URL_PREFIX)
public class QueryController {

    private static final Logger log = LoggerFactory.getLogger(QueryController.class);

    public final static String URL_PREFIX = "/dailys/query";

    public final static String URL_INDEX = "/dailys/query/index";

    @Autowired
    private CoinMarketCapService coinMarketCapService;

    @Autowired
    private UserCoinRelationService userCoinRelationService;

    @Autowired
    private CoinHistoryService coinHistoryService;


    @RequestMapping("/index")
    public void index(@RequestAttribute UserInfo userInfo,
                      @RequestParam(required = false) String coinNames, Model model,
                      HttpServletRequest request) throws HttpClientException, ServiceProcessException {
        log.debug(coinNames);
        if(StringUtils.isNotBlank(coinNames)){
            CoinMarketCap coinMarketCap = coinMarketCapService.getPrice(coinNames);
            model.addAttribute("currentCoin",coinMarketCap);
        }
        List<CoinMarketCap> coinMarketCapList
                = coinMarketCapService.getListLeftJoinByUid(userInfo.getUid());
        model.addAttribute("coinMarketCapList",coinMarketCapList);
        model.addAttribute("idList",coinMarketCapService.getIdList());
    }

    @RequestMapping("/add")
    public String add(@RequestAttribute UserInfo userInfo,@RequestParam String symbol) throws ServiceProcessException {
        userCoinRelationService.save(userInfo.getUid(),symbol);
        return "redirect:"+URL_INDEX;
    }

    @RequestMapping("/update")
    public String update(@RequestAttribute UserInfo userInfo) throws HttpClientException, ServiceProcessException {
        List<UserCoinRelation> userCoinRelationList = userCoinRelationService.getListByUid(userInfo.getUid());
        coinMarketCapService.update(userCoinRelationList);
        return "redirect:"+URL_INDEX;
    }

    @RequestMapping("/remove")
    public String remove(@RequestParam Long relationId) throws ServiceProcessException {
        userCoinRelationService.delete(relationId);
        return "redirect:"+URL_INDEX;
    }

    @RequestMapping("/rise")
    public String rise(@RequestAttribute UserInfo userInfo,
                       @RequestParam String symbol) throws ServiceProcessException {
        userCoinRelationService.risePriority(userInfo.getUid(),symbol);
        return "redirect:"+URL_INDEX;
    }

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
