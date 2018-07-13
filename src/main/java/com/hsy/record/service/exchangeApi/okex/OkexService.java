package com.hsy.record.service.exchangeApi.okex;

import com.hsy.core.util.HttpClient;
import com.hsy.record.model.Balance;
import com.hsy.record.model.DepthDetail;
import com.hsy.record.model.Ticket;
import com.hsy.record.service.ExchangeApiResultService;
import com.hsy.record.service.exchangeApi.ExchangeAbstract;
import com.hsy.record.service.exchangeApi.huobi.HuobiService;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by developer2 on 2018/2/5.
 */
@Service
public class OkexService extends ExchangeAbstract{

    @Autowired
    private ExchangeApiResultService exchangeApiResultService;

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getSellList(Map<String,Object> resultMap) throws HttpClientException {
        List<List<Double>> buyList = (List<List<Double>>) resultMap.get("asks");
        List<List<Double>> orderBuyList
                = buyList.stream().sorted(Comparator.comparing(o->o.get(0)))
                .collect(Collectors.toList());
        return getDepthMapDetailList(orderBuyList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getBuyList(Map<String,Object> resultMap) throws HttpClientException {
        List<List<Double>> sellList = (List<List<Double>>) resultMap.get("bids");
        return getDepthMapDetailList(sellList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String,Object> getTradeInfo(String name) throws HttpClientException {
        String result = HttpClientUtils.getString(
                "https://www.okex.com/api/v1/depth.do?symbol="+name+"_btc&size=3");
        return GsonUtils.toStrObjMap(result);
    }

    @Override
    public Ticket getTicket(String name, String type) throws HttpClientException {
        String result = HttpClientUtils.getString(
                "https://www.okex.com/api/v1/ticker.do?symbol="+name+"_"+type);
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(result);
        Map<String,Object> ticker = (Map<String, Object>) resultMap.get("ticker");
        Ticket ticket = new Ticket();
        ticket.setFirstBuyPrice(Double.parseDouble(ticker.get("buy").toString()));
        ticket.setFirstSellPrice(Double.parseDouble(ticker.get("sell").toString()));
        return ticket;
    }

    @Override
    public List<Balance> getBalance() throws Exception {
        return null;
    }


    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            DecimalFormat df = new DecimalFormat("0.00000000");
            return df.format(value);
        }else{
            return "0.00";
        }
    }

//    public void getUserInfo() throws IOException, HttpClientException {
//        Map<String,String> params = new HashMap<>();
//        params.put("api_key",API_KEY);
//        params.put("sign",getSign(params));
//        System.out.println(GsonUtils.toJson(getResult(params)));
//    }
//
//    public String getSign(Map<String, String> data){
//        SortedMap<String, String> sortedMap = new TreeMap<>(data);
//        StringBuilder sb = new StringBuilder();
//        sortedMap.keySet().stream().filter(key -> StringUtils.isNotBlank(sortedMap.get(key))
//                && !("sign").equals(key)).forEach(key -> {
//            sb.append("&").append(key).append("=").append(sortedMap.get(key));
//        });
//        sb.append("&secret_key=").append(SECRET_KEY);
//        System.out.println("secret_key sub ="+sb.substring(1));
//        return DigestUtils.md5Hex(sb.substring(1)).toUpperCase();
//    }

//    public Map<String,Object> getResult(Map<String,String> params) throws IOException, HttpClientException {
////        HttpPost httpPost = new HttpPost("https://www.okex.com/api/v1/account_records.do");
//        HttpPost httpPost = new HttpPost("https://www.okex.com/api/v1/userinfo.do");
//        List<NameValuePair> valuePairs = convertMap2PostParams(params);
//        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
//        httpPost.setEntity(urlEncodedFormEntity);
//        httpPost.setHeader("contentType ","application/x-www-form-urlencoded");
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
//        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
//        return GsonUtils.toStrObjMap(HttpClientUtils.parseContent(httpEntity));
//    }

    private  List<NameValuePair> convertMap2PostParams(Map<String,String> params){
        List<String> keys = new ArrayList<>(params.keySet());
        if(keys.isEmpty()){
            return null;
        }
        List<NameValuePair>  data = new LinkedList<>() ;
        for (String key : keys) {
            String value = params.get(key);
            data.add(new BasicNameValuePair(key, value));
        }
        return data;
    }

    public static void main(String[] args) throws HttpClientException, IOException {
        OkexService okexService = new OkexService();
        System.out.println(GsonUtils.toJson(okexService.getTicket("snc","btc")));
//        System.out.println("aaaaaaaaaaaaaa");
//        HuobiService huobiService = new HuobiService();
//        System.out.println(GsonUtils.toJson(huobiService.getTicket("snc","btc")));
    }


}
