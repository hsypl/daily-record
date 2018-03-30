package com.hsy.record.controller.dailys;

import com.hsy.core.annotation.Command;
import com.hsy.core.annotation.Module;
import com.hsy.record.service.exchangeApi.cryptopia.CryptopiaService;
import com.sungness.core.httpclient.HttpClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(UtilController.URL_PREFIX)
@Module(value = UtilController.MODULE_NAME , order = 6, icon = "fa fa-file-text")
public class UtilController {

    public final static String URL_PREFIX = "/dailys/util";

    public final static String MODULE_NAME = "Util";

    private static final Logger log = LoggerFactory.getLogger(UtilController.class);

    @Autowired
    private CryptopiaService cryptopiaService;

    @Command(value = MODULE_NAME + "-Index", isInlet = true, order = 1)
    @RequestMapping("/index")
    public void index(Model model) throws HttpClientException {
        Map<String,Object> child = cryptopiaService.getTradeInfo("DNA");
        List<Map<String,String>> buyList = (List<Map<String, String>>) child.get("Buy");
        List<Map<String,String>> sellList = (List<Map<String, String>>) child.get("Sell");
        model.addAttribute("buyList",buyList);
        model.addAttribute("sellList",sellList);
    }

}
