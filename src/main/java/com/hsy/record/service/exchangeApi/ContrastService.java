package com.hsy.record.service.exchangeApi;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.Depth;
import com.hsy.record.model.MonitorSymbol;
import com.hsy.record.model.Ticket;
import com.hsy.record.model.enu.ExchangeTypeEnum;
import com.hsy.record.service.MobileInfoService;
import com.hsy.record.service.MonitorRecordService;
import com.hsy.record.service.WechatService;
import com.sungness.core.httpclient.HttpClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by developer2 on 2018/2/8.
 */
@Service
public class ContrastService {

    private static final Logger log = LoggerFactory.getLogger(ContrastService.class);

    private final static double DNA_DIS_PRICE = 0.0000015;

    private final static double SNC_DIS_PRICE = 0.0000015;

    private final static double SMT_DIS_PRICE = 0.0000003;

    private final static double POWR_DIS_PRICE = 0.000003;

    private static Map<String,List<String>> symbolExchangeList = new ConcurrentHashMap<>();

    private static Map<String,Double> disPrice = new ConcurrentHashMap<>();

    @Autowired
    private WechatService wechatService;

    @Autowired
    private MonitorRecordService monitorRecordService;

    @Autowired
    private MobileInfoService mobileInfoService;

    @Autowired
    private Map<String,ExchangeApiInterface> exchangeMap;

    public ContrastService(){
        List<String> dnaExchange = new ArrayList<>();
        dnaExchange.add("cryptopiaService");
        dnaExchange.add("okexService");
        List<String> powrExchange = new ArrayList<>();
        powrExchange.add("cryptopiaService");
        powrExchange.add("huobiService");
        List<String> sncExchange = new ArrayList<>();
        sncExchange.add("okexService");
        sncExchange.add("huobiService");
        List<String> smtExchange = new ArrayList<>();
        smtExchange.add("okexService");
        smtExchange.add("huobiService");
        symbolExchangeList.put("dna",dnaExchange);
        symbolExchangeList.put("snc",sncExchange);
        disPrice.put("dna",DNA_DIS_PRICE);
        disPrice.put("snc",SNC_DIS_PRICE);
        disPrice.put("smt",SMT_DIS_PRICE);
        disPrice.put("powr",POWR_DIS_PRICE);
    }

    public void monitor(){
        for (String name : symbolExchangeList.keySet()){
            Thread thread = new Thread(){
                public void run(){
                    try {
                        monitor(name);
                    } catch (IOException | HttpClientException | ServiceProcessException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    public Ticket getTicket(MonitorSymbol monitorSymbol)
            throws IOException, HttpClientException {
        return exchangeMap.get(monitorSymbol.getExchange()).getTicket(monitorSymbol.getSymbol(),
                ExchangeTypeEnum.getDescription(monitorSymbol.getType()));
    }

    public List<Depth> getDepthList(String name) throws IOException, HttpClientException {
        List<String> exchangeList = symbolExchangeList.get(name);
        List<Depth> depthList = new ArrayList<>();
        for (String exchange : exchangeList){
            Depth depth = exchangeMap.get(exchange).getDepth(name);
            depth.setExchangeName(exchange.substring(0,3));
            depthList.add(depth);
        }
        return depthList;
    }

    public void monitor(String name) throws IOException, HttpClientException, ServiceProcessException {
        List<Depth> depthList = getDepthList(name);
        if((depthList.get(0).getFirstBuyPrice() - depthList.get(1).getFirstSellPrice()) > disPrice.get(name)
                || (depthList.get(1).getFirstBuyPrice() - depthList.get(0).getFirstSellPrice()) > disPrice.get(name)){
            mobileInfoService.sendMsg(name,"dm8ce4",2);
            String stringBuilder =depthList.get(0).getExchangeName()+"Buy:"
                    + formatFloatNumber(depthList.get(0).getFirstBuyPrice())
                    +"btc------------"
                    +depthList.get(0).getExchangeName()+"Sell:"
                    +formatFloatNumber(depthList.get(0).getFirstSellPrice())
                    +"btc------------"+depthList.get(1).getExchangeName()+"Buy:"
                    + formatFloatNumber(depthList.get(1).getFirstBuyPrice())
                    +"btc------------"+depthList.get(1).getExchangeName()+"Sell:"
                    +formatFloatNumber(depthList.get(1).getFirstSellPrice())+"btc------"+name;
            int re = monitorRecordService.sendSave(name,1);
            if (re > 0){
                wechatService.sendMessage(stringBuilder);
            }
        }

    }

    public Map<String,Double> getDisPrice(){
        return disPrice;
    }



    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            DecimalFormat df = new DecimalFormat("0.00000000");
            return df.format(value);
        }else{
            return "0.00";
        }
    }

    public void getBalance(){

    }

}
