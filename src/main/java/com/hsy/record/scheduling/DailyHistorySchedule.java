package com.hsy.record.scheduling;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.currency.CoinHistoryService;
import com.sungness.core.httpclient.HttpClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by developer2 on 2017/9/5.
 */
@Service
public class DailyHistorySchedule {
    private static final Logger log = LoggerFactory.getLogger(DailyHistorySchedule.class);

    @Autowired
    private CoinHistoryService coinHistoryService;

    @Autowired
    private IcoProjectInfoService icoProjectInfoService;

    /**
     * 计划任务定时调度该方法
     */
    public void start(){
        log.debug("每日统计");
        try {
            Long endTime = DateUtilExt.getLongOfToday();
            List<IcoProjectInfo> list = icoProjectInfoService.getList();
            for (IcoProjectInfo icoProjectInfo : list) {
                coinHistoryService.updateData(icoProjectInfo.getSymbol(),DateUtilExt.getLongPlusDays(endTime,-1L), endTime);

            }
        } catch (ServiceProcessException | ParseException e) {
            e.printStackTrace();
        }
    }

}
