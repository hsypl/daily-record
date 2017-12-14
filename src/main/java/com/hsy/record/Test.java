package com.hsy.record;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.reflect.TypeToken;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.model.enu.WeekEnum;
import com.hsy.record.service.IcoProjectInfoService;
import com.hsy.record.service.currency.CoinMarketCapService;
import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.DateUtil;
import com.sungness.core.util.GsonUtils;
import com.sungness.core.util.tools.DoubleTools;
import com.sungness.core.util.tools.IntegerTools;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
import java.text.ParseException;
import java.util.*;

/**
 * Created by developer2 on 2017/11/6.
 */
public class Test {

    public static final int MONDAY = 1<<0;
    public static final int TUESDAY = 1<<1;
    public static final int WEDNESDAY = 1<<2;
    public static final int THURSDAY = 1<<3;
    public static final int FRIDAY = 1<<4;
    public static final int SATURDAY = 1<<5;
    public static final int SUNDAY = 1<<6;


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

    public static Date parseDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, "yyyy年MM月dd日");
    }

    public static Integer getBinary(String whatDay){
        Integer binary = 0;
        for (int i = 0;i<whatDay.length();i++) {
            Integer day = IntegerTools.parse(whatDay.substring(i, i + 1));
            binary = binary | WeekEnum.valueOfCode(day).getBinary();
        }
        return binary;
    }

    public static void main(String[] args) throws HttpClientException, IOException, ParseException {
        CoinMarketCapService coinMarketCapService = new CoinMarketCapService();
        System.out.println("aa"+GsonUtils.toJson(coinMarketCapService.getPrice("dash")));
    }
}
