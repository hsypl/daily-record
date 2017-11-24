package com.hsy.record.service.currency;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.record.dao.AssetsHistoryMapper;
import com.hsy.record.model.currency.AssetsHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 资产历史记录 业务处理类
*
* Created by huangshuoying on 11/24/17.
*/
@Service
public class AssetsHistoryService
        extends LongPKBaseService<AssetsHistory> {
    private static final Logger log = LoggerFactory.getLogger(AssetsHistoryService.class);

    @Autowired
    private AssetsHistoryMapper assetsHistoryMapper;

    /**
    * 获取数据层mapper接口对象，子类必须实现该方法
    *
    * @return GenericMapper<E, PK> 数据层mapper接口对象
    */
    @Override
    protected GenericMapper<AssetsHistory, Long> getMapper() {
        return assetsHistoryMapper;
    }

    /**
    * 根据id获取对象，如果为空，返回空对象
    * @param id Long 公司id
    * @return AssetsHistory 资产历史记录对象
    */
    public AssetsHistory getSafety(Long id) {
        AssetsHistory assetsHistory = get(id);
        if (assetsHistory == null) {
            assetsHistory = new AssetsHistory();
        }
        return assetsHistory;
    }
}