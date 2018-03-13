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
        Map<String,String> params = new HashMap<>();
//        Test test = new Test("GetBalance",GsonUtils.toJson(params));
        System.out.println(URLDecoder.decode("https://openapi.alipay.com/gateway.do?charset\\u003dUTF-8\\u0026method\\u003dalipay.trade.wap.pay\\u0026sign\\u003dLm70GQBvhP5fPNLnCs%2FXh%2BMtGuwoBVXXqAMrIMstjnXB80K2Hz%2Flcgau0K%2FEb7Zh2MAgLVcwlmqlQeqDN5hHt%2BDsX8lI%2FLQIXGU1CWti8xCvPnlXHdHx%2FVJHjM%2BLxGAjDxn73Mt%2BWhZbfoqADdUIkv0LnBM%2BXcgqf%2FUoHkkdL9GwMn7aJFNbyLR9itgX9UruXq1p3qVL2GfSKuzKs0H5rG3%2BerLieAvnTgO07%2Fxw%2BbCnt2qGGtd2PJhqCUxgoShehc%2FXsNkDRVGQUD%2FUeHVV2X9fbL1vE%2F53uu2xLtVlZvXxM0ywmIKldzWwe8o9HzH12Y6%2Bnqx6eI4w6S%2FlxF4bcA%3D%3D\\u0026notify_url\\u003dhttps%3A%2F%2Fatest1.bafangtang.com%2Fv1.0%2Fstore%2Falipay%2Fnotify\\u0026version\\u003d1.0\\u0026app_id\\u003d2016072201652629\\u0026sign_type\\u003dRSA2\\u0026timestamp\\u003d2018-03-12+11%3A08%3A50\\u0026alipay_sdk\\u003dalipay-sdk-java-dynamicVersionNo\\u0026format\\u003djson\\u0026biz_content\\u003d%7B%22out_trade_no%22%3A%225aa5ef420093e25400000005%22%2C%22total_amount%22%3A%22128%22%2C%22subject%22%3A%22180%E5%A4%A9vip%E4%BC%9A%E5%91%98%22%2C%22product_code%22%3A%22QUICK_WAP_PAY%22%7D","UTF-8"));

    }

}
