package com.hsy.record.scheduling;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.sungness.core.httpclient.HttpClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by developer2 on 2017/9/5.
 */
@Service
public class DailyCountSchedule {
    private static final Logger log = LoggerFactory.getLogger(DailyCountSchedule.class);

    @Autowired
    private AssetsHistoryService assetsHistoryService;

    /**
     * 计划任务定时调度该方法
     */
    public void start(){
        log.debug("资产统计");
        try {
            assetsHistoryService.count();
        } catch (HttpClientException | ServiceProcessException e) {
            e.printStackTrace();
        }
    }

}
