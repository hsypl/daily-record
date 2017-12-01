package com.hsy.core.cache;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.currency.CurrencyInfo;
import com.hsy.record.model.enu.CurrencyStateEnum;
import com.hsy.record.service.currency.CurrencyInfoService;
import com.sungness.core.crawler.ClientUserAgent;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer2 on 2017/11/9.
 */
@Service
public class CurrencyCache {

    private static List<String> currencyList = new ArrayList<>();

    @Autowired
    private CurrencyInfoService currencyInfoService;

    public void init() throws IOException, ServiceProcessException {
//        currencyList = getAll();
    }

    public List<String> getAll() {
        List<String> nameList = new ArrayList<>();
        Connection conn = Jsoup.connect("https://coinmarketcap.com/all/views/all/");
        conn.userAgent(ClientUserAgent.getChromeUserAgent());
        try {
            Connection.Response response = conn.execute();
            Document document = Jsoup.parse(response.body());
            Elements tr = document.select("tbody").select("tr");
            for (int i = 0;i<tr.size();i++){
                Element element = tr.get(i);
                String name = element.attr("id").substring(3);
                nameList.add(name);
                CurrencyInfo currencyInfo = currencyInfoService.getByName(name);
                if(currencyInfo == null){
                    currencyInfo = new CurrencyInfo();
                    currencyInfo.setName(name);
                    currencyInfo.setStatus(CurrencyStateEnum.NOT.getValue());
                    currencyInfoService.insert(currencyInfo);
                }
            }
        } catch (ServiceProcessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            nameList = currencyInfoService.getNameList();
        }
        return nameList;
    }

    public List<String> getCurrencyList(){
        return currencyList;
    }
}
