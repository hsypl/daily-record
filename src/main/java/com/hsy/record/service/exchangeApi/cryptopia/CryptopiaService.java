package com.hsy.record.service.exchangeApi.cryptopia;

import com.hsy.record.model.Balance;
import com.hsy.record.model.DepthDetail;
import com.hsy.record.model.Ticket;
import com.hsy.record.service.exchangeApi.ExchangeAbstract;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by developer2 on 2018/1/12.
 */
@Service
public class CryptopiaService extends ExchangeAbstract {

    private static final Logger log = LoggerFactory.getLogger(CryptopiaService.class);

    public String privateUrl = "https://www.cryptopia.co.nz/Api/";
    public String publicKey = "02ff5e4394bb4c13a31d22316a6565ea";
    public String privateKey = "60S0sV3S/mDxzLal8kse6Mb+J2A8HoYW3HUcky7FKDo=";

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getSellList(Map<String,Object> resultMap) throws HttpClientException {
        List<Map<String,Object>> sellList = (List<Map<String, Object>>) resultMap.get("Sell");
        return getDepthDoubleDetailList(sellList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getBuyList(Map<String,Object> resultMap) throws HttpClientException {
        List<Map<String,Object>> buyList = (List<Map<String, Object>>) resultMap.get("Buy");
        return getDepthDoubleDetailList(buyList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String,Object> getTradeInfo(String name) throws HttpClientException {
        String url = "https://www.cryptopia.co.nz/api/GetMarketOrders/"+name+"_BTC/10";
        String result = HttpClientUtils.getString(url);
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(result);
        return (Map<String, Object>) resultMap.get("Data");
    }

    @Override
    public Ticket getTicket(String name, String type) throws HttpClientException {
        String url = "https://www.cryptopia.co.nz/api/GetMarket/"+name+"_"+type;
        String result = HttpClientUtils.getString(url);
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(result);
        Map<String, Object> data = (Map<String, Object>) resultMap.get("Data");
        Ticket ticket = new Ticket();
        ticket.setFirstSellPrice(Double.parseDouble(data.get("AskPrice").toString()));
        ticket.setFirstBuyPrice(Double.parseDouble(data.get("BidPrice").toString()));
        return ticket;
    }

    public List<DepthDetail> getDepthDoubleDetailList(List<Map<String,Object>> detailList){
        List<DepthDetail> depthDetailList = new ArrayList<>();
        for (Map<String,Object> child : detailList){
            DepthDetail depthDetail = new DepthDetail();
            depthDetail.setPrice((Double) child.get("Price"));
            depthDetail.setTotal((Double) child.get("Volume"));
            depthDetailList.add(depthDetail);
        }
        return depthDetailList;
    }

    @Override
    public List<Balance> getBalance() throws Exception {
        String urlMethod = privateUrl+"GetBalance";
        return getBalanceList(getResult(urlMethod,GsonUtils.toJson(new HashMap<>())));
    }

    public List<Balance> getBalanceList(String result){
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(result);
        List<Map<String,Object>> data = (List<Map<String, Object>>) resultMap.get("Data");
        List<Map<String,Object>> filterList
                = data.stream().filter(d->(double)d.get("Total") > 0).collect(Collectors.toList());
        List<Balance> balanceList = new ArrayList<>();
        for (Map<String,Object> b : filterList){
            Balance balance = new Balance();
            balance.setSymbol((String) b.get("Symbol"));
            balance.setTotal(Double.parseDouble(b.get("Total").toString()));
            balanceList.add(balance);
        }
        return balanceList;
    }

    public String getResult(String urlMethod,String jSonPostParam) throws Exception {
        HttpPost httpPost = new HttpPost(urlMethod);
        StringEntity stringEntity = HttpClientUtils.createJsonEntity(jSonPostParam);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Authorization",getAuth(urlMethod,jSonPostParam));
        httpPost.setHeader("Content","application/json");
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
        return HttpClientUtils.parseContent(httpEntity);
    }

    public String getSign(String urlMethod,String jSonPostParam,String nonce) throws Exception {
        return publicKey
                        + "POST"
                        + URLEncoder.encode(urlMethod, StandardCharsets.UTF_8.toString()).toLowerCase()
                        + nonce
                        + getMD5_B64(jSonPostParam);
    }

    public String getAuth(String urlMethod,String jSonPostParam) throws Exception {
        String nonce  = String.valueOf(System.currentTimeMillis());
        return  "amx "
                + publicKey
                +":"
                + this.sha256_B64(getSign(urlMethod,jSonPostParam,nonce))
                +":"
                + nonce;
    }

    private String getMD5_B64(String postParameter) throws Exception {
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(postParameter.getBytes("UTF-8")));
    }

    private String sha256_B64(String msg) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(Base64.getDecoder().decode(privateKey), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(msg.getBytes("UTF-8")));
    }

    public static void main(String[] args) throws Exception {
        CryptopiaService cryptopiaService = new CryptopiaService();
        System.out.println(GsonUtils.toJson(cryptopiaService.getBalance()));

    }
}
