package com.hsy.record;

import com.sungness.core.crawler.ClientUserAgent;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer2 on 2017/11/6.
 */
public class Test {

    public void getAll() throws IOException {
        List<String> nameList = new ArrayList<>();
        Connection conn = Jsoup.connect("https://coinmarketcap.com/all/views/all/");
        conn.userAgent(ClientUserAgent.getChromeUserAgent());
        Connection.Response response = conn.execute();
        Document document = Jsoup.parse(response.body());
        Elements tr = document.select("tbody").select("tr");
        for (int i = 0;i<tr.size();i++){
            Element element = tr.get(i);
            nameList.add(element.attr("id").substring(3));
        }
        System.out.println("name = " + nameList);
    }

    public static void main(String[] args) throws IOException {
    }
}
