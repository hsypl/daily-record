package com.hsy.record.dao;

import com.hsy.core.dao.LongPKBaseMapper;
import com.hsy.record.model.currency.CurrencyInfo;

import java.util.List;

/**
 * Created by developer2 on 2017/7/11.
 */
public interface CurrencyInfoMapper extends LongPKBaseMapper<CurrencyInfo> {

    int batchInsert(List<CurrencyInfo> currencyInfoList);


    int deleteAll();

}
