package com.hsy.record;

import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.DateUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.java_websocket.client.WebSocketClient;

import java.io.IOException;

/**
 * Created by developer2 on 2018/2/5.
 */
public class Okex {

    public static void main(String[] args) throws HttpClientException, IOException {
        HttpGet httpGet = new HttpGet("https://www.okex.com/v2/markets/dna_btc/depth");
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
        httpGet.setHeader(":authority", "www.okex.com");
        System.out.print(HttpClientUtils.parseContent(httpEntity));
    }

}
