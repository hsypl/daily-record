package com.hsy.record.controller.dailys;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.MonitorSymbol;
import com.hsy.record.model.enu.ExchangeTypeEnum;
import com.hsy.record.service.exchangeApi.MonitorSymbolService;
import com.sungness.core.util.tools.LongTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by developer2 on 2018/3/5.
 */
@Controller
@RequestMapping(MonitorController.URL_PREFIX)
public class MonitorController {

    public final static String URL_PREFIX = "/dailys/monitor";

    public final static String INDEX_URL = "/dailys/monitor/index";

    private static final Logger log = LoggerFactory.getLogger(UtilController.class);

    @Autowired
    private MonitorSymbolService monitorSymbolService;

    @RequestMapping("/index")
    public void index(Model model){
        List<MonitorSymbol> monitorSymbolList = monitorSymbolService.getList();
        model.addAttribute("monitorSymbolList",monitorSymbolList);
    }

    @RequestMapping("/edit")
    public void edit(@RequestParam(required = false) Long id, Model model){
        MonitorSymbol monitorSymbol = monitorSymbolService.getSafety(id);
        model.addAttribute("monitorSymbol",monitorSymbol);
        model.addAttribute("exchangeTypeEnumList", ExchangeTypeEnum.getEnumList());
    }


    @RequestMapping("/save")
    public String save(MonitorSymbol monitorSymbol) throws ServiceProcessException {
        if(LongTools.lessEqualZero(monitorSymbol.getId())){
            monitorSymbolService.insert(monitorSymbol);
        }else {
            monitorSymbolService.update(monitorSymbol);
        }
        return "redirect:"+INDEX_URL;
    }

    @RequestMapping("/delete")
    public String delete(Long id) throws ServiceProcessException {
        monitorSymbolService.delete(id);
        return "redirect:"+INDEX_URL;
    }
}
