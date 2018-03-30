package com.hsy.record.service.exchangeApi.huobi;

import com.hsy.record.model.Balance;
import com.hsy.record.model.DepthDetail;
import com.hsy.record.model.Ticket;
import com.hsy.record.service.exchangeApi.ExchangeAbstract;
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
        String url = "https://api.huobipro.com/market/depth?symbol="+name+"btc&type=step0";
        Map<String,Object> resultMap = getResult(url);
        return (Map<String, Object>) resultMap.get("tick");
    }

    @Override
    public Ticket getTicket(String name,String type) throws IOException, HttpClientException {
        String url = "https://api.huobipro.com/market/detail/merged?symbol="+name+type;
        Map<String, Object> detail = (Map<String, Object>) getResult(url).get("tick");
        System.out.println(GsonUtils.toJson(detail));
        Ticket ticket = new Ticket();
        ticket.setFirstBuyPrice(Double.parseDouble(((List) detail.get("bid")).get(0).toString()));
        ticket.setFirstSellPrice(Double.parseDouble(((List) detail.get("ask")).get(0).toString()));
        return ticket;
    }

    @Override
    public List<Balance> getBalance() throws Exception {
        return null;
    }

    public Map<String,Object> getResult(String url) throws IOException, HttpClientException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        httpGet.setHeader("Content-Type","Accept-Language:zh-cn");
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
        return GsonUtils.toStrObjMap(HttpClientUtils.parseContent(httpEntity));
    }

    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            DecimalFormat df = new DecimalFormat("########0.000000000#");
            return df.format(value);
        }else{
            return "0.00";
        }
    }

    public static void main(String[] args) throws IOException, HttpClientException {
        HuobiService huobiService = new HuobiService();
        System.out.println(GsonUtils.toJson(huobiService.getTicket("powr","btc")));

    }

}
