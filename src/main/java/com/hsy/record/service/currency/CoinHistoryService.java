package com.hsy.record.service.currency;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.dao.CoinHistoryMapper;
import com.hsy.record.model.currency.CoinHistory;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.tools.DoubleTools;
import com.sungness.core.util.tools.LongTools;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 资产历史记录 业务处理类
*
* Created by huangshuoying on 12/2/17.
*/
@Service
public class CoinHistoryService
        extends LongPKBaseService<CoinHistory> {
    private static final Logger log = LoggerFactory.getLogger(CoinHistoryService.class);

    @Autowired
    private CoinHistoryMapper coinHistoryMapper;

    /**
    * 获取数据层mapper接口对象，子类必须实现该方法
    *
    * @return GenericMapper<E, PK> 数据层mapper接口对象
    */
    @Override
    protected GenericMapper<CoinHistory, Long> getMapper() {
        return coinHistoryMapper;
    }

    /**
    * 根据id获取对象，如果为空，返回空对象
    * @param id Long 公司id
    * @return CoinHistory 资产历史记录对象
    */
    public CoinHistory getSafety(Long id) {
        CoinHistory coinHistory = get(id);
        if (coinHistory == null) {
            coinHistory = new CoinHistory();
        }
        return coinHistory;
    }

    public CoinHistory getByIdAndTime(String id,Long time){
        Map<String,Object> params = new HashMap<>();
        params.put("symbol",id);
        params.put("createTime",time);
        return getByDynamicWhere(params);
    }

    public List<CoinHistory> getListByIdAndBeginTime(String id, Long beginTime){
        Map<String,Object> params = new HashMap<>();
        params.put("beginDate",beginTime);
        params.put("symbol",id);
        params.put("fullordering","a.create_time ASC");
        return getList(params);
    }

    public void setData(List<CoinHistory> coinHistoryList,
                        List<String> dayList,
                        List<Double> priceList,
                        List<Double> volumeList){
        for (CoinHistory coinHistory : coinHistoryList){
            priceList.add(coinHistory.getPrice());
            volumeList.add(coinHistory.getVolume());
            dayList.add(DateUtilExt.formatDay(coinHistory.getCreateTime()));
        }

    }

    public void updateData(String id,Long startTime,Long endTime)
            throws ParseException, ServiceProcessException {
        String startStr = fullFormat(startTime);
        String endStr = fullFormat(endTime);
        String url = "https://coinmarketcap.com/zh/currencies/"
                +id+"/historical-data/?"+"start="+startStr+"&end="+endStr;
        try {
            String result = HttpClientUtils.getString(url);
            Document document = Jsoup.parse(result);
            Elements table = document.select(".table-responsive tbody");
            Elements trList = table.select("tr");
            for (Element tr : trList){
                Elements td = tr.select("td");
                Long time = (parseDate(td.get(0).text()).getTime())/1000;
                CoinHistory coinHistory = getByIdAndTime(id,time);
                if(coinHistory == null){
                    coinHistory = new CoinHistory();
                    coinHistory.setSymbol(id);
                    coinHistory.setCreateTime(time);
                    coinHistory.setPrice(DoubleTools.parseDouble(td.get(4).text()));
                    coinHistory.setVolume(getResultVolume(td.get(5).text()));
                    insert(coinHistory);
                }
            }
        } catch (HttpClientException e) {
            e.printStackTrace();
        }
    }

    public Double getResultVolume(String volume){
        String[] volumeArr = volume.split(",");
        StringBuilder volumeLast = new StringBuilder();
        for (String v : volumeArr){
            volumeLast.append(v);
        }
        return DoubleTools.parseDouble(volumeLast.toString());
    }

    public  Date parseDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, "yyyy年MM月dd日");
    }

    public String fullFormat(Long timestamp) {
        return LongTools.lessEqualZero(timestamp)?"": DateFormatUtils.format(timestamp * 1000L, "yyyyMMdd");
    }

}