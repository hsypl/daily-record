package com.hsy.record;

import com.hsy.core.util.DateUtilExt;
import com.hsy.core.util.XmlUtils;
import com.hsy.record.model.exchangeApi.cryptopia.HistoryTrade;
import com.sungness.core.crawler.ClientConfigure;
import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.DateUtil;
import com.sungness.core.util.GsonUtils;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by developer2 on 2018/1/24.
 */
public class TestJava8 {

    public static void main(String[] args) throws IOException {
        System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("http.proxyPort", "56016");
        System.getProperties().setProperty("https.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("https.proxyPort", "56016");

        Connection conn = Jsoup.connect("https://t.co/Gpa2etffDz");
        conn.userAgent(ClientUserAgent.getChromeUserAgent())
                .timeout(ClientConfigure.TIMEOUT_FIVE_MINUTE)
                .ignoreContentType(true)
                .followRedirects(false);
        Connection.Response response = conn.execute();
        System.out.println("=======");
        System.out.println("getComplianReportPage status:" + response.body());
        Document doc = Jsoup.parse(response.body());
        Elements elements = doc.select("title");
//        String rgex = "abc(.*?)abc";
//        System.out.println(getSubUtil(str,rgex));
//        System.out.println(getSubUtilSimple(str, rgex));

//        Connection conn1 = Jsoup.connect("https://twitter.com/i/web/status/1017051899701485568");
//        conn1.userAgent(ClientUserAgent.getChromeUserAgent())
//                .timeout(ClientConfigure.TIMEOUT_FIVE_MINUTE)
//                .ignoreContentType(true)
//                .followRedirects(false);
//        Connection.Response response1 = conn1.execute();
//        System.out.print("getComplianReportPage status:" + response1.body());
    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap,String rgex){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

}
