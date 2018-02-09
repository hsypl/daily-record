package com.hsy.record.dao;


import com.hsy.core.dao.LongPKBaseMapper;
import com.hsy.record.model.MonitorRecord;

import java.util.Map;

/**
* 交易记录 MyBatis 映射接口类
*
* Created by huangshuoying on 2/8/18.
*/
public interface MonitorRecordMapper
        extends LongPKBaseMapper<MonitorRecord> {

    MonitorRecord getLast(Map<String,Object> params);
}