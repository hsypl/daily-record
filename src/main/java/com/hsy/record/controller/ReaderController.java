package com.hsy.record.controller;

import com.hsy.record.service.currency.AssetsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(ReaderController.URL_PREFIX)
public class ReaderController {

    public final static String URL_PREFIX = "/daily/reader";

    @Autowired
    private AssetsHistoryService assetsHistoryService;

    @RequestMapping("/index")
    public String index(Model model){
        Map<String,Long> assetsMap = assetsHistoryService.getMonthMap("123",null);
        model.addAttribute("assetsMap",assetsMap);
        model.addAttribute("days",assetsMap.size());
        return "/dailys/assets/index";

    }
}
