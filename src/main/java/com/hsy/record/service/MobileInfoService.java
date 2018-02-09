package com.hsy.record.service;

import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.service.ServiceProcessException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by developer2 on 2018/2/6.
 */
public class MobileInfoService {

    private final static String APP_ID = "19922";

    private final static String SECRET = "e5fc227faa56b5581e680028f07a9a3e";

    private final static String SEND_URL = "https://api.submail.cn/message/xsend.json";

    /**
     * 发送参数到url
     * @return String , 解析发送接口返回的json  status为success表示成功，error表示失败
     */
    private void sendToUrl() throws HttpClientException,
            UnsupportedEncodingException, ServiceProcessException {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("appid", APP_ID);
        map.put("to", "13750450369");
        map.put("project", "1xXUH1");
        map.put("signature", SECRET);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            stringBuilder.append(key)
                    .append("=").append(URLEncoder.encode(map.get(key), "UTF-8"))
                    .append("&");
        }
        String response = HttpClientUtils.postForm(SEND_URL,
                stringBuilder.substring(0,stringBuilder.length()-1));
        System.out.print(response);
    }

    public static void main(String[] args) throws HttpClientException, UnsupportedEncodingException, ServiceProcessException {
        MobileInfoService mobileInfoService = new MobileInfoService();
        mobileInfoService.sendToUrl();
    }
}
