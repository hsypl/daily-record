package com.hsy.record;

import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.util.DateUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

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

    public static void main(String[] args) throws IOException {
        Calendar journalDate = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        journalDate.set(Calendar.HOUR_OF_DAY, 0);
        journalDate.set(Calendar.MINUTE, 0);
        journalDate.set(Calendar.SECOND, 0);
        journalDate.set(Calendar.MILLISECOND, 0);
        System.out.println(DateUtil.getTimestamp());
        System.out.println(journalDate.getTimeInMillis());
    }
}
