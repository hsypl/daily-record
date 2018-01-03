package com.hsy.record.dao;


import com.hsy.core.dao.LongPKBaseMapper;
import com.hsy.record.model.currency.AssetsHistory;

import java.util.Map;

/**
* 资产历史记录 MyBatis 映射接口类
*
* Created by huangshuoying on 11/24/17.
*/
public interface AssetsHistoryMapper
        extends LongPKBaseMapper<AssetsHistory> {

    Integer getCountByUidAndTime(Map<String,Object> params);
}