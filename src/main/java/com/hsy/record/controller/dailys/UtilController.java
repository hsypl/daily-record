package com.hsy.record.controller.dailys;

import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.exchangeApi.cryptopia.TradeService;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(UtilController.URL_PREFIX)
public class UtilController {

    public final static String URL_PREFIX = "/dailys/util";

    private static final Logger log = LoggerFactory.getLogger(UtilController.class);

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/index")
    public void index(Model model) throws HttpClientException {
        Map<String,Object> child = tradeService.getTradeInfo("DNA");
        List<Map<String,String>> buyList = (List<Map<String, String>>) child.get("Buy");
        List<Map<String,String>> sellList = (List<Map<String, String>>) child.get("Sell");
        model.addAttribute("buyList",buyList);
        model.addAttribute("sellList",sellList);
    }


}
