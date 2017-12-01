package com.hsy.record;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.reflect.TypeToken;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.service.IcoProjectInfoService;
import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.DateUtil;
import com.sungness.core.util.GsonUtils;
import com.sungness.core.util.tools.DoubleTools;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by developer2 on 2017/11/6.
 */
public class Test {

    public static List<String> parseList(String params){
        List<String> idList = new ArrayList<>();
        String idArray[] = params.split(" ");
        Collections.addAll(idList, idArray);
        return idList;
    }

    public static List<Map<String,Object>> getListByJson(String result){
        Type topicType1 = new TypeToken<List<Map<String,Object>>>() {
        }.getType();
        List<Map<String,Object>> resultList =
                GsonUtils.fromJson(result, topicType1, FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        System.out.print(GsonUtils.toJson(resultList));
        return resultList;
    }

    public static void main(String[] args) throws HttpClientException {
        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUid("123");
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUid("asdf");
        userInfoList.add(userInfo);
        userInfoList.add(userInfo2);
        System.out.print(GsonUtils.toJson(userInfoList));
    }
}
