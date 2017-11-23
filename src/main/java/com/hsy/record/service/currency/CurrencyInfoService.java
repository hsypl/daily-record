package com.hsy.record.service.currency;

import com.hsy.core.cache.CurrencyCache;
import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.dao.CurrencyInfoMapper;
import com.hsy.record.dao.IcoProjectInfoMapper;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.currency.CurrencyInfo;
import com.hsy.record.model.enu.CurrencyStateEnum;
import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.util.tools.DoubleTools;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by developer2 on 2017/7/11.
 */
@Service
public class CurrencyInfoService extends LongPKBaseService<CurrencyInfo> {

    @Autowired
    private CurrencyInfoMapper currencyInfoMapper;

    @Autowired
    private CurrencyCache currencyCache;

    private static final Logger log = LoggerFactory.getLogger(CurrencyInfoService.class);

    private static final String CNY = "6.6399";

    @Override
    protected GenericMapper<CurrencyInfo, Long> getMapper() {
        return currencyInfoMapper;
    }

    public CurrencyInfo getSafe(Long id){
        CurrencyInfo currencyInfo = currencyInfoMapper.get(id);
        if(currencyInfo != null){
            return currencyInfo;
        }
        return new CurrencyInfo();
    }

    public List<String> getNameList(){
        return currencyInfoMapper.getNameList();
    }

    public List<String> getNameListByStatus(Integer status){
        Map<String,Object> params = new HashMap<>();
        params.put("status",status);
        return currencyInfoMapper.getNameList(params);
    }

    public CurrencyInfo getByName(String name){
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        return getByDynamicWhere(params);
    }

    public List<CurrencyInfo> getByStatus(Integer status){
        Map<String,Object> params = new HashMap<>();
        params.put("status",status);
        return getList(params);
    }


    public List<CurrencyInfo> getPrice(String nameArr) throws IOException, ServiceProcessException {
        List<String> nameList = parseList(nameArr);
        return updatePrice(nameList);
    }

    @Transactional(timeout = 1000)
    public List<CurrencyInfo> updatePrice(List<String> nameList) throws IOException, ServiceProcessException {
        Connection conn = Jsoup.connect("https://coinmarketcap.com/all/views/all/");
        conn.userAgent(ClientUserAgent.getChromeUserAgent());
        conn.maxBodySize(0);
        Connection.Response response = conn.execute();
        log.debug("statusCode =" + response.body());
        if(response.statusCode() == response.statusCode()){
            return parseResult(response.body(),nameList);
        }
        return null;
    }

    public List<CurrencyInfo> parseResult(String result,List<String> nameList) throws ServiceProcessException {
        List<String> nameCacheList = currencyCache.getCurrencyList();
        List<CurrencyInfo> list = new ArrayList<>();
        Document document = Jsoup.parse(result);
        for(String name : nameList){
            if(nameCacheList.contains(name)){
                log.debug("name =" + name);
                Elements currency = document.select("#id-"+name);
                Elements td = currency.select("td");
                String usPrice = td.get(4).text();
                CurrencyInfo currencyInfo = getByName(name);
                currencyInfo.setName(name);
                currencyInfo.setUsdPrice(usPrice);
                currencyInfo.setCnyPrice(getCNYPrice(usPrice));
                currencyInfo.setStatus(CurrencyStateEnum.YES.getValue());
                update(currencyInfo);
                list.add(currencyInfo);
            }
        }
        return list;
    }

    public String getCNYPrice(String price){
        Double amountDouble = DoubleTools.parseDouble(price.substring(1))*DoubleTools.parseDouble(CNY);
        DecimalFormat df=new DecimalFormat("#.####");
        return df.format(amountDouble);
    }

    public List<String> parseList(String params){
        List<String> idList = new ArrayList<>();
        String idArray[] = params.split(" ");
        Collections.addAll(idList, idArray);
        return idList;
    }


}
