package com.hsy.record.service.exchangeApi.okex;

import com.hsy.record.model.DepthDetail;
import com.hsy.record.service.exchangeApi.ExchangeAbstract;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by developer2 on 2018/2/5.
 */
@Service
public class OkexService extends ExchangeAbstract{

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getSellList(Map<String,Object> resultMap) throws HttpClientException {
        List<List<Double>> buyList = (List<List<Double>>) resultMap.get("asks");
        List<List<Double>> orderBuyList
                = buyList.stream().sorted(Comparator.comparing(o->o.get(0)))
                .collect(Collectors.toList());
        return getDepthMapDetailList(orderBuyList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getBuyList(Map<String,Object> resultMap) throws HttpClientException {
        List<List<Double>> sellList = (List<List<Double>>) resultMap.get("bids");
        return getDepthMapDetailList(sellList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String,Object> getTradeInfo(String name) throws HttpClientException {
        String result = HttpClientUtils.getString(
                "https://www.okex.com/api/v1/depth.do?symbol="+name+"_btc");
        System.out.println(result);
        return GsonUtils.toStrObjMap(result);
    }


    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            DecimalFormat df = new DecimalFormat("0.00000000");
            return df.format(value);
        }else{
            return "0.00";
        }

    }

    public static void main(String[] args) throws HttpClientException, IOException {
        OkexService okexService = new OkexService();
        System.out.print(GsonUtils.toJson(okexService.getDepth("smt")));
    }

}
