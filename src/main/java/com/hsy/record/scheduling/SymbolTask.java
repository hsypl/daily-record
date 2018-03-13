//package com.hsy.record.scheduling;
//
//import com.hsy.record.model.Ticket;
//import com.hsy.record.service.exchangeApi.ContrastService;
//import com.sungness.core.httpclient.HttpClientException;
//import com.sungness.core.util.GsonUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by developer2 on 2018/3/5.
// */
//public class SymbolTask extends TimerTask {
//
//
//    private boolean flag = true;
//
//    private Timer timer;
//
//    private String name;
//
//    private double buyPrice;
//
//    private double sellPrice;
//
//    private ContrastService contrastService;
//
//    private String exchange;
//
//    private String type;
//
//    @Override
//    public void run() {
//        while (flag){
//            try {
//                Ticket ticket = contrastService.getTicket(name,type,exchange);
//                if(ticket.getFirstBuyPrice() > buyPrice || ticket.getFirstSellPrice() < sellPrice){
//
//                }
//            } catch (IOException | HttpClientException e) {
//                e.printStackTrace();
//            }
//        }
//        timer.cancel();
//    }
//
//    public void setFlag(){
//        this.flag = false;
//    }
//
//    public Timer getTimer() {
//        return timer;
//    }
//
//    public void setTimer(Timer timer) {
//        this.timer = timer;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public ContrastService getContrastService() {
//        return contrastService;
//    }
//
//    public void setContrastService(ContrastService contrastService) {
//        this.contrastService = contrastService;
//    }
//
//    public String getExchange() {
//        return exchange;
//    }
//
//    public void setExchange(String exchange) {
//        this.exchange = exchange;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public double getBuyPrice() {
//        return buyPrice;
//    }
//
//    public void setBuyPrice(double buyPrice) {
//        this.buyPrice = buyPrice;
//    }
//
//    public double getSellPrice() {
//        return sellPrice;
//    }
//
//    public void setSellPrice(double sellPrice) {
//        this.sellPrice = sellPrice;
//    }
//}
