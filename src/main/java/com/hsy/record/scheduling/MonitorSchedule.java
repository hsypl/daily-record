package com.hsy.record.scheduling;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.exchangeApi.ContrastService;
import com.sungness.core.httpclient.HttpClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by developer2 on 2017/9/5.
 */
@Service
public class MonitorSchedule {
    private static final Logger log = LoggerFactory.getLogger(MonitorSchedule.class);

    @Autowired
    private ContrastService contrastService;

    /**
     * 计划任务定时调度该方法
     */
    public void start(){
        log.debug("价格监控");
        contrastService.monitor();
    }

}
