package com.hsy.record.controller.dailys;
import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.ChangeLog;
import com.hsy.record.model.enu.ChangeTypeEnum;
import com.hsy.record.service.currency.ChangeLogService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.sungness.core.util.tools.LongTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(ChangeLogController.URL_PREFIX)
public class ChangeLogController {

    public final static String URL_PREFIX = "/dailys/change";

    @Autowired
    private ChangeLogService changeLogService;

    @Autowired
    private CoinMarketCapService coinMarketCapService;

    @RequestMapping("/index")
    public void index (@RequestAttribute UserInfo userInfo, Model model){
        model.addAttribute("changeLogList",changeLogService.getListByUid(userInfo.getUid()));
    }

    @RequestMapping("/edit")
    public void edit(Model model,@RequestParam(required = false) Long id){
        model.addAttribute("changeLog",changeLogService.getSafety(id));
        model.addAttribute("ChangeTypeEnum", ChangeTypeEnum.getEnumList());
        model.addAttribute("idList",coinMarketCapService.getIdList());
    }

    @RequestMapping("/add")
    public String add(@RequestAttribute UserInfo userInfo,ChangeLog changeLog)
            throws ServiceProcessException {
        changeLog.setCreateTime(DateUtilExt.getTimestamp());
        changeLog.setUid(userInfo.getUid());
        if(LongTools.lessEqualZero(changeLog.getId())){
            changeLogService.insert(changeLog);
        }else {
            changeLogService.update(changeLog);
        }
        return "redirect:"+URL_PREFIX+"/index";
    }

    @RequestMapping("/delete")
    public String delete(Long id) throws ServiceProcessException {
        changeLogService.delete(id);
        return "redirect:"+URL_PREFIX+"/index";
    }

}
