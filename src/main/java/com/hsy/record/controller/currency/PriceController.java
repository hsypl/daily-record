package com.hsy.record.controller.currency;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.currency.CurrencyInfo;
import com.hsy.record.model.enu.CurrencyStateEnum;
import com.hsy.record.service.currency.CurrencyInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(PriceController.URL_PREFIX)
public class PriceController {

    private static final Logger log = LoggerFactory.getLogger(PriceController.class);

    public final static String URL_PREFIX = "/daily/currency/price";

    @Autowired
    private CurrencyInfoService currencyInfoService;

    @RequestMapping("/index")
    public void index(@RequestParam(required = false) String coin_names, Model model,
                      HttpServletRequest request){
        log.debug(coin_names);
        List<CurrencyInfo> infoList = null;
        try {
            if(StringUtils.isNotBlank(coin_names)){
                infoList = currencyInfoService.getPrice(coin_names);
            }else {
                infoList = currencyInfoService.getByStatus(CurrencyStateEnum.YES.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceProcessException e) {
            e.printStackTrace();
        }
        List<CurrencyInfo>  currentList = currencyInfoService.getByStatus(CurrencyStateEnum.YES.getValue());
        model.addAttribute("currentList",currentList);
        model.addAttribute("infoList",infoList);
    }
}
