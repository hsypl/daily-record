package com.hsy.record;

import com.hsy.record.model.currency.CurrencyInfo;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by developer2 on 2017/11/6.
 */
public class Test {

    public String privateUrl = "https://www.cryptopia.co.nz/Api/";
    public String publicKey = "02ff5e4394bb4c13a31d22316a6565ea";
    public String privateKey = "60S0sV3S/mDxzLal8kse6Mb+J2A8HoYW3HUcky7FKDo=";

    public Test(String method,String jSonPostParam) throws Exception {
        String urlMethod = privateUrl + method;
        String nonce     = String.valueOf(System.currentTimeMillis());
        String reqSignature =
                publicKey
                        + "POST"
                        + URLEncoder.encode(urlMethod, StandardCharsets.UTF_8.toString()).toLowerCase()
                        + nonce
                        + getMD5_B64(jSonPostParam);

        String AUTH = "amx "
                + publicKey
                +":"
                + this.sha256_B64(reqSignature)
                +":"
                + nonce;
//        String response = this.continueForHttp(urlMethod, jSonPostParam,AUTH);
        HttpPost httpPost = new HttpPost(urlMethod);
        StringEntity stringEntity = HttpClientUtils.createJsonEntity(jSonPostParam);
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Authorization",AUTH);
        httpPost.setHeader("Content","application/json");
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
        System.out.println(HttpClientUtils.parseContent(httpEntity));
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.print(encoder.encode("lottery-platform"));
    }

}
