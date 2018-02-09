package com.hsy.record.service.exchangeApi.huobi;

import com.hsy.record.model.Depth;
import com.hsy.record.model.DepthDetail;
import com.hsy.record.service.exchangeApi.ExchangeAbstract;
import com.hsy.record.service.exchangeApi.ExchangeApiInterface;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2018/2/8.
 */
@Service
public class HuobiService extends ExchangeAbstract{

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getSellList(Map<String,Object> resultMap) throws HttpClientException {
        List<List<Double>> sellList = (List<List<Double>>) resultMap.get("asks");
        return getDepthMapDetailList(sellList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getBuyList(Map<String,Object> resultMap) throws HttpClientException {
        List<List<Double>> buyList = (List<List<Double>>) resultMap.get("bids");
        return getDepthMapDetailList(buyList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String,Object> getTradeInfo(String name) throws HttpClientException, IOException {
        HttpGet httpGet = new HttpGet("https://api.huobi.pro/market/depth?symbol="+name+"btc&type=step0");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        httpGet.setHeader("Content-Type","Accept-Language:zh-cn");
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(HttpClientUtils.parseContent(httpEntity));
        return (Map<String, Object>) resultMap.get("tick");
    }

    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            DecimalFormat df = new DecimalFormat("0.00000000");
            return df.format(value);
        }else{
            return "0.00";
        }
    }

}
