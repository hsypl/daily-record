package com.hsy.record.controller.currency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(CurrencyController.URL_PREFIX)
public class CurrencyController {

    public final static String URL_PREFIX = "/daily/currency";

    @RequestMapping("/index")
    public void index(){

    }
}
