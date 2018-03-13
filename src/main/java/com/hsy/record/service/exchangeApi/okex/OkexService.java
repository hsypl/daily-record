package com.hsy.record.service.exchangeApi.okex;

import com.hsy.record.model.Balance;
import com.hsy.record.model.DepthDetail;
import com.hsy.record.model.Ticket;
import com.hsy.record.service.exchangeApi.ExchangeAbstract;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
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

    public static final String API_KEY = "7b648b69-c0c3-44b2-a825-900830531cb4";

    public static final String SECRET_KEY = "255BDD95527992DEE903ED79F385FA54";

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
                "https://www.okcoin.com/api/v1/depth.do?symbol="+name+"_btc");
        System.out.println(result);
        return GsonUtils.toStrObjMap(result);
    }

    @Override
    public Ticket getTicket(String name, String type) throws HttpClientException {
        String url = "https://www.okex.com/api/v1/ticker.do?symbol="+name+"_btc";
        String result = HttpClientUtils.getString(url);
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(result);
        Map<String,Object> ticker = (Map<String, Object>) resultMap.get("ticker");
        Ticket ticket = new Ticket();
//        ticket.setFirstBuyPrice(Double.parseDouble(((List) detail.get("bid")).get(0).toString()));
//        ticket.setFirstSellPrice(Double.parseDouble(((List) detail.get("ask")).get(0).toString()));
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

    public void getUserInfo() throws IOException, HttpClientException {
        Map<String,String> params = new HashMap<>();
        params.put("api_key",API_KEY);
        params.put("sign",getSign(params));
        System.out.println(GsonUtils.toJson(getResult(params)));
    }

    public String getSign(Map<String, String> data){
        SortedMap<String, String> sortedMap = new TreeMap<>(data);
        StringBuilder sb = new StringBuilder();
        sortedMap.keySet().stream().filter(key -> StringUtils.isNotBlank(sortedMap.get(key))
                && !("sign").equals(key)).forEach(key -> {
            sb.append("&").append(key).append("=").append(sortedMap.get(key));
        });
        sb.append("&secret_key=").append(SECRET_KEY);
        System.out.println("secret_key sub ="+sb.substring(1));
        return DigestUtils.md5Hex(sb.substring(1)).toUpperCase();
    }

    public Map<String,Object> getResult(Map<String,String> params) throws IOException, HttpClientException {
//        HttpPost httpPost = new HttpPost("https://www.okex.com/api/v1/account_records.do");
        HttpPost httpPost = new HttpPost("https://www.okex.com/api/v1/userinfo.do");
        List<NameValuePair> valuePairs = convertMap2PostParams(params);
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
        httpPost.setEntity(urlEncodedFormEntity);
        httpPost.setHeader("contentType ","application/x-www-form-urlencoded");
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
        return GsonUtils.toStrObjMap(HttpClientUtils.parseContent(httpEntity));
    }

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
//        System.out.println(GsonUtils.toJson(okexService.getTicket("DNA")));
//        okexService.getTradeInfo("ltc");
        System.setProperty("https.proxyHost","61.6.54.189");
        System.setProperty("https.proxyPort","53281");
        System.setProperty("https.proxySet", "true");
        URL url = new URL("https://www.okex.com/api/v1/depth.do?symbol=dna_btc");
        URLConnection con = url.openConnection();
        InputStreamReader isr = new InputStreamReader(con.getInputStream());
        char[] cs = new char[1024];
        int i = 0;
        while ((i = isr.read(cs)) > 0) {
            System.out.println(new String(cs, 0, i));
        }
        isr.close();
//        String result = HttpClientUtils.getString("https://www.okex.com/api/v1/depth.do?symbol=dna_btc");
//        System.out.println(result);
    }

}
