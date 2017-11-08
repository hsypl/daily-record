package com.hsy.record.controller;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.web.QueryFilter;
import com.hsy.record.model.PlanInfo;
import com.hsy.record.model.enu.CompleteStateEnum;
import com.hsy.record.service.PlanInfoService;
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
@RequestMapping(PlanController.URL_PREFIX)
public class PlanController {

    private static final Logger log = LoggerFactory.getLogger(PlanController.class);

    public final static String URL_PREFIX = "/daily/plan";

    @Autowired
    private PlanInfoService planInfoService;

    @RequestMapping("/index")
    public void index(@ModelAttribute("queryFilter") QueryFilter queryFilter,
                      Model model,
                      HttpServletRequest request){
        log.debug(GsonUtils.toJson(queryFilter));
        queryFilter.init(request);
        List<PlanInfo> planInfoList = planInfoService.getList(queryFilter);
        model.addAttribute("planInfoList",planInfoList);
    }

    @RequestMapping("/edit")
    public void index(Long id,Model model){
        PlanInfo planInfo = planInfoService.get(id);
        model.addAttribute("completeStateEnumList", CompleteStateEnum.getEnumList());
        model.addAttribute("planInfo",planInfo);
    }

    @RequestMapping("/save")
    public String save(PlanInfo planInfo) throws ServiceProcessException {
        log.debug(GsonUtils.toJson(planInfo));
        if(LongTools.lessEqualZero(planInfo.getId())){
            planInfoService.insert(planInfo);
        }else {
            planInfoService.update(planInfo);
        }
        return "redirect:/daily/plan/index";
    }

    @RequestMapping("/delete")
    public String delete(Long id) throws ServiceProcessException {
        planInfoService.delete(id);
        return "redirect:/daily/plan/index";
    }
}
