package com.hsy.record.controller.currency;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.service.IcoProjectInfoService;
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

    @Test
    public void test() throws ServiceProcessException {
        List<IcoProjectInfo> list = icoProjectInfoService.getList();
        for(IcoProjectInfo icoProjectInfo : list){
            icoProjectInfo.setUid("5a138814008027af00000001");
            icoProjectInfoService.update(icoProjectInfo);
        }
    }

}