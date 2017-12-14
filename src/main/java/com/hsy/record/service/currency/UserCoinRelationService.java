package com.hsy.record.service.currency;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.dao.UserCoinRelationMapper;
import com.hsy.record.model.currency.UserCoinRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 资产历史记录 业务处理类
*
* Created by huangshuoying on 12/14/17.
*/
@Service
public class UserCoinRelationService
        extends LongPKBaseService<UserCoinRelation> {
    private static final Logger log = LoggerFactory.getLogger(UserCoinRelationService.class);

    @Autowired
    private UserCoinRelationMapper userCoinRelationMapper;

    /**
    * 获取数据层mapper接口对象，子类必须实现该方法
    *
    * @return GenericMapper<E, PK> 数据层mapper接口对象
    */
    @Override
    protected GenericMapper<UserCoinRelation, Long> getMapper() {
        return userCoinRelationMapper;
    }

    /**
    * 根据id获取对象，如果为空，返回空对象
    * @param id Long 公司id
    * @return UserCoinRelation 资产历史记录对象
    */
    public UserCoinRelation getSafety(Long id) {
        UserCoinRelation userCoinRelation = get(id);
        if (userCoinRelation == null) {
            userCoinRelation = new UserCoinRelation();
        }
        return userCoinRelation;
    }

    public List<UserCoinRelation> getListByUid(String uid){
        Map<String,Object> params = new HashMap<>();
        params.put("uid",uid);
        return getList(params);
    }

    public void save(String uid,String symbol) throws ServiceProcessException {
        UserCoinRelation userCoinRelation = new UserCoinRelation();
        userCoinRelation.setUid(uid);
        userCoinRelation.setSymbol(symbol);
        insert(userCoinRelation);
    }
}