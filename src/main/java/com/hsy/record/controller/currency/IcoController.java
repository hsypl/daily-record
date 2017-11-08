package com.hsy.record.controller.currency;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.web.QueryFilter;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.service.IcoProjectInfoService;
import com.sungness.core.util.GsonUtils;
import com.sungness.core.util.tools.LongTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(IcoController.URL_PREFIX)
public class IcoController {

    private static final Logger log = LoggerFactory.getLogger(IcoController.class);

    public final static String URL_PREFIX = "/daily/currency/ico";

    @Autowired
    private IcoProjectInfoService icoProjectInfoService;

    @RequestMapping("/index")
    public void index(@ModelAttribute("queryFilter") QueryFilter queryFilter,
                      Model model,
                      HttpServletRequest request){
        queryFilter.init(request);
        List<IcoProjectInfo> icoProjectInfoList = icoProjectInfoService.getList(queryFilter);
        log.debug(GsonUtils.toJson(queryFilter.getPagination()));
        model.addAttribute("icoProjectInfoList",icoProjectInfoList);
    }

    @RequestMapping("/edit")
    public void edit(Long id, Model model){
        IcoProjectInfo icoProjectInfo = icoProjectInfoService.getSafe(id);
        model.addAttribute("icoProjectInfo",icoProjectInfo);
    }

    @RequestMapping("/save")
    public String save(IcoProjectInfo icoProjectInfo) throws ServiceProcessException {
        log.debug(GsonUtils.toJson(icoProjectInfo));
        if(LongTools.lessEqualZero(icoProjectInfo.getId())){
            icoProjectInfoService.insert(icoProjectInfo);
        }else {
            icoProjectInfoService.update(icoProjectInfo);
        }
        return "redirect:/daily/currency/ico/index";
    }

    @RequestMapping("/delete")
    public String delete(Long id) throws ServiceProcessException {
        icoProjectInfoService.delete(id);
        return "redirect:/daily/currency/ico/index";
    }
}
