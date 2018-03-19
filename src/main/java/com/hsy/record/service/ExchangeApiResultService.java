package com.hsy.record.service;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.record.dao.ExchangeApiResultMapper;
import com.hsy.record.model.ExchangeApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* 交易所接口信息 业务处理类
*
* Created by huangshuoying on 3/19/18.
*/
@Service
public class ExchangeApiResultService
        extends LongPKBaseService<ExchangeApiResult> {
    private static final Logger log = LoggerFactory.getLogger(ExchangeApiResultService.class);

    @Autowired
    private ExchangeApiResultMapper exchangeApiResultMapper;

    /**
    * 获取数据层mapper接口对象，子类必须实现该方法
    *
    * @return GenericMapper<E, PK> 数据层mapper接口对象
    */
    @Override
    protected GenericMapper<ExchangeApiResult, Long> getMapper() {
        return exchangeApiResultMapper;
    }

    /**
    * 根据id获取对象，如果为空，返回空对象
    * @param id Long 公司id
    * @return ExchangeApiResult 交易所接口信息对象
    */
    public ExchangeApiResult getSafety(Long id) {
        ExchangeApiResult exchangeApiResult = get(id);
        if (exchangeApiResult == null) {
            exchangeApiResult = new ExchangeApiResult();
        }
        return exchangeApiResult;
    }

    public ExchangeApiResult getByParams(String name,Integer type,String exchange){
        Map<String,Object> params = new HashMap<>();
        params.put("symbol",name);
        params.put("type",type);
        params.put("exchange",exchange);
        return getByDynamicWhere(params);
    }

}