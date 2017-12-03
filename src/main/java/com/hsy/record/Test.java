package com.hsy.record;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.reflect.TypeToken;
import com.hsy.core.util.DateUtilExt;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.currency.CoinHistory;
import com.hsy.record.model.currency.CoinMarketCap;
import com.hsy.record.service.IcoProjectInfoService;
import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.DateUtil;
import com.sungness.core.util.GsonUtils;
import com.sungness.core.util.tools.DoubleTools;
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



    public static void main(String[] args) throws HttpClientException, IOException, ParseException {
        String result = HttpClientUtils.getString("https://coinmarketcap.com/zh/currencies/dash/historical-data/?start=20170902&end=20171202");
//        System.out.println(result);
        Document document = Jsoup.parse(result);
        Elements table = document.select(".table-responsive tbody");
        Elements tr = table.select("tr");
        Elements td = tr.get(0).select("td");
        CoinHistory coinHistory = new CoinHistory();
        coinHistory.setId("dash");
        coinHistory.setCreateTime((Test.parseDate(td.get(0).text()).getTime())/1000);
        coinHistory.setPrice(DoubleTools.parseDouble(td.get(3).text()));
        coinHistory.setVolume((DoubleTools.parseDouble(td.get(5).text())));
        String price = td.get(5).text();
        String[] a = price.split(",");
        StringBuilder volume = new StringBuilder();
        for (String b : a){
            volume.append(b);
        }
        System.out.println(volume);

        System.out.println((DoubleTools.parseDouble(td.get(5).text())));
        System.out.print(GsonUtils.toJson(coinHistory));
    }
}
