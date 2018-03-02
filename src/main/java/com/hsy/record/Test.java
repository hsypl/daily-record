package com.hsy.record;

import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.*;

/**
 * Created by developer2 on 2017/11/6.
 */
public class Test {

    public static final String API_KEY = "";

    public static final String SECRET_KEY = "";

    public static String getSign(Map<String, String> data){
        SortedMap<String, String> sortedMap = new TreeMap<>(data);
        StringBuilder sb = new StringBuilder();
        sortedMap.keySet().stream().filter(key -> StringUtils.isNotBlank(sortedMap.get(key))
                && !("sign").equals(key)).forEach(key -> {
            sb.append("&").append(key).append("=").append(sortedMap.get(key));
        });
        sb.append("&secret_key=").append(SECRET_KEY);
        return DigestUtils.md5Hex(sb.substring(1));
    }

    public static void main(String[] args) throws HttpClientException, IOException {
        HttpClientUtils.getString(
                "https://www.okex.com/api/v1/depth.do?symbol=dna_btc");
//        Map<String,String> params = new HashMap<>();
//        params.put("api_key",API_KEY);
//        params.put("sign",getSign(params));
////        String result =
////                HttpClientUtils.postForm("https://www.okex.com/api/v1/userinfo.do",
////                        GsonUtils.toJson(params));
//        HttpPost httpPost = new HttpPost("https://www.okex.com/api/v1/userinfo.do");
//        httpPost.setHeader("contentType ","application/x-www-form-urlencoded");
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
//        HttpEntity httpEntity = HttpClientUtils.parseEntity(closeableHttpResponse);
//        Map<String,Object> resultMap = GsonUtils.toStrObjMap(HttpClientUtils.parseContent(httpEntity));
    }

}
