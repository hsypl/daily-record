package com.hsy.record.service;

import com.sungness.core.crawler.ClientUserAgent;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by developer2 on 2017/7/27.
 */
public class CrawCoinService {

    public void crawler() throws IOException {
        Connection connection = Jsoup.connect("https://www.jubi.com/");
        connection.userAgent(ClientUserAgent.getChromeUserAgent());
        Connection.Response response = connection.execute();
        if(response.statusCode() == 200){
            System.out.print(response.body());
        }

    }

    public static void main(String[] args) throws IOException {
        CrawCoinService crawCoinService = new CrawCoinService();
        crawCoinService.crawler();
    }
}
