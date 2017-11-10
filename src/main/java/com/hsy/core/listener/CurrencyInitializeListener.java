package com.hsy.core.listener;

import com.hsy.core.cache.CurrencyCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class CurrencyInitializeListener
        implements ApplicationListener<ContextRefreshedEvent> {
    
    private static final Logger log =
            LoggerFactory.getLogger(CurrencyInitializeListener.class);
    private static int callCounts = 0;

    @Autowired
    private CurrencyCache currencyCache;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            //避免重复执行
            if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
                return;
            }
            log.debug(" 初始化执行");
            callCounts++;
            currencyCache.init();
            log.debug(" call times：" + callCounts);
        } catch (Exception e) {
            log.error(" init failed.");
            e.printStackTrace();
        }
    }

}
