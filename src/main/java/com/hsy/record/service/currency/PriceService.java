package com.hsy.record.service.currency;

import com.hsy.record.model.currency.CurrencyInfo;
import com.sungness.core.crawler.ClientUserAgent;
import com.sungness.core.util.tools.DoubleTools;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by developer2 on 2017/11/7.
 */
@Service
public class PriceService {

    private static final Logger log = LoggerFactory.getLogger(PriceService.class);

    private static final String CNY = "6.6281";

    public List<CurrencyInfo> getPrice(String nameArr) throws IOException {
        List<String> nameList = parseList(nameArr);
        Connection conn = Jsoup.connect("https://coinmarketcap.com/all/views/all/");
        conn.userAgent(ClientUserAgent.getChromeUserAgent());
        Connection.Response response = conn.execute();
        if(response.statusCode() == 200){
           return parseResult(response.body(),nameList);
        }
        return null;
    }

    public List<CurrencyInfo> parseResult(String result,List<String> nameList){
        List<CurrencyInfo> list = new ArrayList<>();
        Document document = Jsoup.parse(result);
        for(String name : nameList){
            CurrencyInfo currencyInfo = new CurrencyInfo();
            Elements currency = document.select("#id-"+name);
            Elements td = currency.select("td");
            String usPrice = td.get(4).text();
            currencyInfo.setName(name);
            currencyInfo.setUSAPrice(usPrice);
            currencyInfo.setCNYPrice(getCNYPrice(usPrice));
            list.add(currencyInfo);
        }
        return list;
    }

    public String getCNYPrice(String price){
        Double amountDouble = DoubleTools.parseDouble(price.substring(1))*DoubleTools.parseDouble(CNY);
        DecimalFormat df=new DecimalFormat("#.###");
        return df.format(amountDouble);
    }

    public List<String> parseList(String params){
        List<String> idList = new ArrayList<>();
        String idArray[] = params.split(" ");
        Collections.addAll(idList, idArray);
        return idList;
    }

//    public static Integer amountMultiply(String amount){
//        Double amountDouble = DoubleTools.parseDouble(amount)* new BigDecimal(100);
//        return amountDouble.intValue();
//    }

    public static void main(String args[]){
        String bb = "$7197.77";
        Double amountDouble = DoubleTools.parseDouble(bb.substring(1))*DoubleTools.parseDouble("6.6281");
        DecimalFormat df=new DecimalFormat("#.###");
        System.out.println(df.format(amountDouble));
    }
}
