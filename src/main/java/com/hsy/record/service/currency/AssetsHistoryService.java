package com.hsy.record.service.currency;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.dao.AssetsHistoryMapper;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.AssetsHistory;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.UserInfoService;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.util.tools.LongTools;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private IcoProjectInfoService icoProjectInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CoinMarketCapService coinMarketCapService;

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

    public List<AssetsHistory> getMonthList(Long startTime,Long endTime){
        Map<String,Object> params = new HashMap<>();
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        return getList(params);
    }

        public Map<String,Long> getMonthMap(String day){
            Long startTime;
            Long endTime;
            if(StringUtils.isBlank(day)){
                startTime = DateUtilExt.getLongOfFirstDayOfMonth();
                endTime = DateUtilExt.getLongOfFirstDayOfNextMonth();
            }else {
                String data = DateUtilExt.formatYear(DateUtilExt.getTimestamp())+day;
                startTime = DateUtilExt.atZoneGetLong(DateUtilExt.parseLocalDate(data));
                endTime = DateUtilExt.getLongPlusMonths(startTime,1L);
            }
            return getMonthMap(startTime,endTime);
        }

    public Map<String,Long> getMonthMap(Long startTime,Long endTime){
        List<AssetsHistory> assetsHistories = getMonthList(startTime,endTime);
        SortedMap<String,Long> valueByDay = new TreeMap<>();
        for(AssetsHistory assetsHistory : assetsHistories){
            valueByDay.put(DateUtilExt.formatDay(assetsHistory.getCreateTime()),assetsHistory.getAmount());
        }
        return valueByDay;
    }

    public void count() throws HttpClientException, ServiceProcessException {
        coinMarketCapService.update();
        List<UserInfo> userInfoList = userInfoService.getList();
        for(UserInfo userInfo : userInfoList){
            Integer sum = icoProjectInfoService.getSum(userInfo.getUid());
            AssetsHistory history = new AssetsHistory();
            history.setUid(userInfo.getUid());
            history.setAmount(LongTools.parse(sum.toString()));
            history.setCreateTime(DateUtilExt.getTimestamp());
            insert(history);
        }
    }
}