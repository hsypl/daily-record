package com.hsy.record.service.exchangeApi;

import com.hsy.core.service.ServiceProcessException;
import com.hsy.record.model.Depth;
import com.hsy.record.service.MonitorRecordService;
import com.hsy.record.service.WechatService;
import com.hsy.record.service.exchangeApi.cryptopia.CryptopiaService;
import com.hsy.record.service.exchangeApi.huobi.HuobiService;
import com.hsy.record.service.exchangeApi.okex.OkexService;
import com.sungness.core.httpclient.HttpClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer2 on 2018/2/8.
 */
@Service
public class ContrastService {

    private static final Logger log = LoggerFactory.getLogger(ContrastService.class);

    private final static double DNA_DIS_PRICE = 0.000003;

    private final static double SNC_DIS_PRICE = 0.000003;

    private final static double SMT_DIS_PRICE = 0.0000003;

    @Autowired
    private OkexService okexService;

    @Autowired
    private CryptopiaService cryptopiaService;

    @Autowired
    private WechatService wechatService;

    @Autowired
    private MonitorRecordService monitorRecordService;

    @Autowired
    private HuobiService huobiService;

    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            DecimalFormat df = new DecimalFormat("0.00000000");
            return df.format(value);
        }else{
            return "0.00";
        }
    }

    public void powr() throws HttpClientException, ServiceProcessException, IOException {
        Depth hbDepth = huobiService.getDepth("powr");
        Depth cptDepth = cryptopiaService.getDepth("powr");
        if((hbDepth.getFirstBuyPrice() - cptDepth.getFirstSellPrice()) > DNA_DIS_PRICE
                || (cptDepth.getFirstBuyPrice() - hbDepth.getFirstSellPrice()) > DNA_DIS_PRICE){
            String stringBuilder ="hbFirstBuy:"+ formatFloatNumber(hbDepth.getFirstBuyPrice())+"btc------------hbFirstSell:"
                    +formatFloatNumber(hbDepth.getFirstSellPrice())+"btc------------cpFirstBuy:"+ formatFloatNumber(cptDepth.getFirstBuyPrice())
                    +"btc------------cpFirstSell:" +formatFloatNumber(cptDepth.getFirstSellPrice())+"btc------powr";
            int re = monitorRecordService.sendSave("powr",1);
            if (re > 0){
                wechatService.sendMessage(stringBuilder);
            }
        }
    }

    public void dna() throws HttpClientException, ServiceProcessException {
        Depth okDepth = okexService.getDepth("dna");
        Depth cptDepth = cryptopiaService.getDepth("dna");
        if((okDepth.getFirstBuyPrice() - cptDepth.getFirstSellPrice()) > DNA_DIS_PRICE
                || (cptDepth.getFirstBuyPrice() - okDepth.getFirstSellPrice()) > DNA_DIS_PRICE){
            String stringBuilder ="okFirstBuy:"+ formatFloatNumber(okDepth.getFirstBuyPrice())+"btc------------okFirstSell:"
                    +formatFloatNumber(okDepth.getFirstSellPrice())+"btc------------cpFirstBuy:"+ formatFloatNumber(cptDepth.getFirstBuyPrice())
                    +"btc------------cpFirstSell:" +formatFloatNumber(cptDepth.getFirstSellPrice())+"btc------dna";
            int re = monitorRecordService.sendSave("dna",1);
            if (re > 0){
                wechatService.sendMessage(stringBuilder);
            }
        }
    }

    public void snc() throws HttpClientException, IOException, ServiceProcessException {
        Depth okDepth = okexService.getDepth("snc");
        Depth huobiDepth = huobiService.getDepth("snc");
        if((okDepth.getFirstBuyPrice() - huobiDepth.getFirstSellPrice()) > SNC_DIS_PRICE
                || (huobiDepth.getFirstBuyPrice() - okDepth.getFirstSellPrice()) > SNC_DIS_PRICE){
            String stringBuilder ="okFirstBuy:"+ formatFloatNumber(okDepth.getFirstBuyPrice())+"btc------------okFirstSell:"
                    +formatFloatNumber(okDepth.getFirstSellPrice())+"btc------------hbFirstBuy:"+ formatFloatNumber(huobiDepth.getFirstBuyPrice())
                    +"btc------------hbFirstSell:" +formatFloatNumber(huobiDepth.getFirstSellPrice())+"btc------SNC";
            int re = monitorRecordService.sendSave("snc",1);
            if (re > 0){
                wechatService.sendMessage(stringBuilder);
            }
        }
    }

    public void smt() throws HttpClientException, IOException, ServiceProcessException {
        Depth okDepth = okexService.getDepth("smt");
        Depth huobiDepth = huobiService.getDepth("smt");
        if((okDepth.getFirstBuyPrice() - huobiDepth.getFirstSellPrice()) > SMT_DIS_PRICE
                || (huobiDepth.getFirstBuyPrice() - okDepth.getFirstSellPrice()) > SMT_DIS_PRICE){
            String stringBuilder ="okFirstBuy:"+ formatFloatNumber(okDepth.getFirstBuyPrice())+"btc------------okFirstSell:"
                    +formatFloatNumber(okDepth.getFirstSellPrice())+"btc------------hbFirstBuy:"+ formatFloatNumber(huobiDepth.getFirstBuyPrice())
                    +"btc------------hbFirstSell:" +formatFloatNumber(huobiDepth.getFirstSellPrice())+"btc------smt";
            int re = monitorRecordService.sendSave("smt",1);
            if (re > 0){
                wechatService.sendMessage(stringBuilder);
            }
        }
    }

    public static void main(String[] args) throws HttpClientException {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(4);
        a.add(5);
        System.out.print(a.subList(0,3));
    }
}
