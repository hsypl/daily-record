package com.hsy.record.controller.currency;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.currency.AssetsHistory;
import com.hsy.record.model.currency.CoinHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.system.ModuleInfo;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.MobileInfoService;
import com.hsy.record.service.TestService;
import com.hsy.record.service.WechatService;
import com.hsy.record.service.currency.AssetsHistoryService;
import com.hsy.record.service.currency.CoinHistoryService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.hsy.record.service.exchangeApi.ContrastService;
import com.hsy.record.service.exchangeApi.huobi.HuobiService;
import com.hsy.record.service.exchangeApi.okex.OkexService;
import com.hsy.record.service.system.ModuleInfoService;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.util.GsonUtils;
import org.csource.common.MyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.text.ParseException;
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
    @Autowired
    private CoinHistoryService coinHistoryService;
    @Autowired
    private ContrastService contrastService;
    @Autowired
    private HuobiService huobiService;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private OkexService okexService;
    @Autowired
    private ModuleInfoService moduleInfoService;
    @Autowired
    private TestService testService;

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
    public void test2() throws ServiceProcessException, HttpClientException, ParseException {
        Long endTime = DateUtilExt.getLongOfToday();
        List<IcoProjectInfo> list = icoProjectInfoService.getList();
        for (IcoProjectInfo icoProjectInfo : list) {
            coinHistoryService.updateData(icoProjectInfo.getSymbol(),DateUtilExt.getLongPlusDays(endTime,-2L), endTime);
        }
    }

    @Test
    public void test4() throws ServiceProcessException, HttpClientException {
        coinMarketCapService.update();
    }

    @Test
    public void test3() throws HttpClientException, ServiceProcessException, ParseException {
        Long endTime = DateUtilExt.getLongOfToday();
        List<IcoProjectInfo> list = icoProjectInfoService.getList();
        for (IcoProjectInfo icoProjectInfo : list) {
            coinHistoryService.updateData(icoProjectInfo.getSymbol(),DateUtilExt.getLongPlusDays(endTime,-1L), endTime);
        }
    }

    @Test
    public void test5() throws HttpClientException, ServiceProcessException {

        ModuleInfo moduleInfo = moduleInfoService.get(1L);
        moduleInfo.setValue("test");
        moduleInfoService.update(moduleInfo);
    }

    @Test
    public void hsy() throws IOException, MyException {
        testService.test();
    }

}