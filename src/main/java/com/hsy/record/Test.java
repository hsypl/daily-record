package com.hsy.record;

import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.service.IcoProjectInfoService;
import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.DateUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
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

    public static void main(String[] args) throws IOException, HttpClientException {
        String result =HttpClientUtils.getString("https://api.ethplorer.io/getAddressInfo/0xaA943b6989491654283ceDFA3ff51fc4636a4dF1?apiKey=freekey");
        System.out.print(result);
    }
}
