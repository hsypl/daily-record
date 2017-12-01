package com.hsy.record.controller.currency;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.web.QueryFilter;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.currency.CurrencyInfo;
import com.hsy.record.model.enu.CurrencyStateEnum;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.hsy.record.service.currency.CurrencyInfoService;
import com.sungness.core.enu.StatusEnum;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.util.GsonUtils;
import com.sungness.core.util.tools.LongTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(IcoController.URL_PREFIX)
public class IcoController {

    private static final Logger log = LoggerFactory.getLogger(IcoController.class);

    public final static String URL_PREFIX = "/daily/currency/ico";

    public final static String INDEX_URL = "/daily/currency/ico/index";

    @Autowired
    private IcoProjectInfoService icoProjectInfoService;

    @Autowired
    private CurrencyInfoService currencyInfoService;

    @Autowired
    private CoinMarketCapService coinMarketCapService;


    @RequestMapping("/index")
    public void index(@RequestAttribute UserInfo userInfo,
                      @ModelAttribute("queryFilter") QueryFilter queryFilter,
                      Model model,
                      HttpServletRequest request){
        log.debug(GsonUtils.toJson(userInfo));
        queryFilter.init(request);
        List<IcoProjectInfo> icoProjectInfoList = icoProjectInfoService.getListLeftJoinByUid(queryFilter.getPagination(),userInfo.getUid());
        log.debug(GsonUtils.toJson(icoProjectInfoList));
        model.addAttribute("count",icoProjectInfoService.getSum(userInfo.getUid()));
        model.addAttribute("inSum",icoProjectInfoService.getInSum(userInfo.getUid()));
        model.addAttribute("icoProjectInfoList",icoProjectInfoList);
    }

//    @RequestMapping("/update")
//    public String update(){
//
//    }

    @RequestMapping("/edit")
    public void edit(Long id, Model model){
        IcoProjectInfo icoProjectInfo = icoProjectInfoService.getSafe(id);
        model.addAttribute("icoProjectInfo",icoProjectInfo);
    }

    @RequestMapping("/update")
    public String update() throws IOException, ServiceProcessException, HttpClientException {
        coinMarketCapService.update();
        return "redirect:"+INDEX_URL;
    }

    @RequestMapping("/save")
    public String save(@RequestAttribute UserInfo userInfo,
                       IcoProjectInfo icoProjectInfo) throws ServiceProcessException {
        log.debug(GsonUtils.toJson(icoProjectInfo));
        if(LongTools.lessEqualZero(icoProjectInfo.getId())){
            icoProjectInfoService.insert(icoProjectInfo);
        }else {
            icoProjectInfoService.update(icoProjectInfo);
        }
        return "redirect:"+INDEX_URL;
    }

    @RequestMapping("/delete")
    public String delete(Long id) throws ServiceProcessException {
        icoProjectInfoService.delete(id);
        return "redirect:"+INDEX_URL;
    }

    @RequestMapping("/sync")
    public String sync(@RequestAttribute UserInfo userInfo) throws ServiceProcessException {
        List<IcoProjectInfo> icoProjectInfoList
                = icoProjectInfoService.getListByUid(userInfo.getUid());
        coinMarketCapService.sync(icoProjectInfoList);
        return "redirect:"+INDEX_URL;
    }
}
