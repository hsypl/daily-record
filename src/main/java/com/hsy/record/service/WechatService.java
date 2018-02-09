package com.hsy.record.service;

import com.hsy.record.service.exchangeApi.ContrastService;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.DateUtil;
import com.sungness.core.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by developer2 on 2018/2/8.
 */
@Service
public class WechatService {

    private static final Logger log = LoggerFactory.getLogger(WechatService.class);

    private final static String URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcc21e4c4b82e0696&secret=a56a487fab0515912861e8052617cc87";

    public static String token ;

    private void setToken() throws HttpClientException {
        String reuslt = HttpClientUtils.getString(URL);
        Map<String,String> resultMap = GsonUtils.toStrStrMap(reuslt);
        token = resultMap.get("access_token");
    }

    public void sendMessage(String message) throws HttpClientException {
        String content = "{\n" +
                "   \"touser\":[\n" +
                "    \"o_ANm1Nc_SER9g7qGgvlsBD-AqVc\",\n" +
                "    \"o_ANm1Nc_SER9g7qGgvlsBD-AqVc\"\n" +
                "   ],\n" +
                "    \"msgtype\": \"text\",\n" +
                "    \"text\": { \"content\": \""+message+"\"}\n" +
                "}";
        log.debug("wechatCont="+content);
        String post = HttpClientUtils.postJson("https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+token,content);
        Map<String,String> resultMap = GsonUtils.toStrStrMap(post);
        if(!resultMap.get("errcode").equals("0")){
            setToken();
            HttpClientUtils.postJson("https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+token,content);
        }
    }
}
