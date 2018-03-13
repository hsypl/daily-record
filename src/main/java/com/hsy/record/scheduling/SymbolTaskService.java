//package com.hsy.record.scheduling;
//
//import com.hsy.record.service.exchangeApi.ContrastService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.Timer;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Created by developer2 on 2018/3/5.
// */
//@Service
//public class SymbolTaskService {
//
//    @Autowired
//    private ContrastService contrastService;
//
//    Map<String,SymbolTask> symbolTaskMap = new ConcurrentHashMap<>();
//
//    public SymbolTask getTask(Timer timer,String name,double sellPrice,double buyPrice,
//                              ContrastService contrastService,String exchange){
//        SymbolTask symbolTask = new SymbolTask();
//        symbolTask.setTimer(timer);
//        symbolTask.setName(name);
//        symbolTask.setSellPrice(sellPrice);
//        symbolTask.setBuyPrice(buyPrice);
//        symbolTask.setContrastService(contrastService);
//        symbolTask.setExchange(exchange);
//        return symbolTask;
//    }
//
//    public void start(String name,double sellPrice,double buyPrice,String exchange){
//        Timer timer = new Timer();
//        SymbolTask symbolTask = getTask(timer,name,sellPrice,buyPrice,contrastService,exchange);
//        symbolTaskMap.put(name,symbolTask);
//        timer.schedule(symbolTask,3 * 10000, 10000);
//
//    }
//
//    public void stop(String name){
//        SymbolTask symbolTask = symbolTaskMap.get(name);
//        symbolTask.setFlag();
//    }
//}
