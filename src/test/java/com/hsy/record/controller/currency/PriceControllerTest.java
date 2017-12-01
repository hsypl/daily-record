package com.hsy.record.controller.currency;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.currency.AssetsHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.sungness.core.httpclient.HttpClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by developer2 on 2017/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"classpath:test-config.xml"})
public class PriceControllerTest {

    @Autowired
    private IcoProjectInfoService icoProjectInfoService;
    @Autowired
    private AssetsHistoryService assetsHistoryService;
    @Autowired
    private CoinMarketCapService coinMarketCapService;

    @Test
    public void test() throws ServiceProcessException {
        List<IcoProjectInfo> list = icoProjectInfoService.getList();
        for(IcoProjectInfo icoProjectInfo : list){
            icoProjectInfo.setUid("5a138814008027af00000001");
            icoProjectInfoService.update(icoProjectInfo);
        }
    }

    @Test
    public void test1() throws ServiceProcessException {
        Long startTime = 1506787200L;
        Long amount = 20000L;
        for(int i =0;i<55;i++){
            AssetsHistory assetsHistory = new AssetsHistory();
            assetsHistory.setAmount(amount);
            assetsHistory.setCreateTime(startTime);
            amount += 500L;
            startTime += 60*60*24;
            assetsHistoryService.insert(assetsHistory);
        }
    }

    @Test
    public void test2() throws ServiceProcessException, HttpClientException {
        coinMarketCapService.update();
    }

}