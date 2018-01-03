package com.hsy.record.service.currency;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.record.dao.ChangeLogMapper;
import com.hsy.record.model.currency.ChangeLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 交易记录 业务处理类
*
* Created by huangshuoying on 12/29/17.
*/
@Service
public class ChangeLogService
        extends LongPKBaseService<ChangeLog> {
    private static final Logger log = LoggerFactory.getLogger(ChangeLogService.class);

    @Autowired
    private ChangeLogMapper changeLogMapper;

    /**
    * 获取数据层mapper接口对象，子类必须实现该方法
    *
    * @return GenericMapper<E, PK> 数据层mapper接口对象
    */
    @Override
    protected GenericMapper<ChangeLog, Long> getMapper() {
        return changeLogMapper;
    }

    /**
    * 根据id获取对象，如果为空，返回空对象
    * @param id Long 公司id
    * @return ChangeLog 交易记录对象
    */
    public ChangeLog getSafety(Long id) {
        ChangeLog changeLog = get(id);
        if (changeLog == null) {
            changeLog = new ChangeLog();
        }
        return changeLog;
    }

    public List<ChangeLog> getListByUid(String uid){
        Map<String,Object> params = new HashMap<>();
        params.put("uid",uid);
        return getList(params);
    }
}