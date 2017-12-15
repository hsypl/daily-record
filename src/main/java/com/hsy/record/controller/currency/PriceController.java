package com.hsy.record.controller.currency;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.currency.CurrencyInfo;
import com.hsy.record.model.currency.UserCoinRelation;
import com.hsy.record.model.enu.CurrencyStateEnum;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(PriceController.URL_PREFIX)
public class PriceController {

    private static final Logger log = LoggerFactory.getLogger(PriceController.class);

    public final static String URL_PREFIX = "/daily/currency/price";

    public final static String URL_INDEX = "/daily/currency/price/index";

    @Autowired
    private CurrencyInfoService currencyInfoService;

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
}
