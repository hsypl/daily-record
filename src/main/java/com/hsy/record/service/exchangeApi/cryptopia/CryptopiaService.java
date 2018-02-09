package com.hsy.record.service.exchangeApi.cryptopia;

import com.hsy.record.model.Depth;
import com.hsy.record.model.DepthDetail;
import com.hsy.record.service.exchangeApi.ExchangeAbstract;
import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2018/1/12.
 */
@Service
public class CryptopiaService extends ExchangeAbstract {

    private static final Logger log = LoggerFactory.getLogger(CryptopiaService.class);

    public String privateUrl = "https://www.cryptopia.co.nz/Api/";
    public String publicKey = "148b658687c54d13892a527ece8c32d4";
    public String privateKey = "9aa5AMjwFP16oaQtnKY09zd7oe67X4asgdE/rbgv2DY=";

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getSellList(Map<String,Object> resultMap) throws HttpClientException {
        List<Map<String,Object>> sellList = (List<Map<String, Object>>) resultMap.get("Sell");
        return getDepthDoubleDetailList(sellList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DepthDetail> getBuyList(Map<String,Object> resultMap) throws HttpClientException {
        List<Map<String,Object>> buyList = (List<Map<String, Object>>) resultMap.get("Buy");
        return getDepthDoubleDetailList(buyList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String,Object> getTradeInfo(String name) throws HttpClientException {
        String url = "https://www.cryptopia.co.nz/api/GetMarketOrders/"+name+"_BTC/10";
        String result = HttpClientUtils.getString(url);
        System.out.print(result);
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(result);
        log.debug(GsonUtils.toJson(result));
        return (Map<String, Object>) resultMap.get("Data");
    }

    public List<DepthDetail> getDepthDoubleDetailList(List<Map<String,Object>> detailList){
        List<DepthDetail> depthDetailList = new ArrayList<>();
        for (Map<String,Object> child : detailList){
            DepthDetail depthDetail = new DepthDetail();
            depthDetail.setPrice((Double) child.get("Price"));
            depthDetail.setTotal((Double) child.get("Total"));
            depthDetailList.add(depthDetail);
        }
        return depthDetailList;
    }

    public static void main(String[] args) throws Exception {
        CryptopiaService cryptopiaService = new CryptopiaService();
        System.out.print(GsonUtils.toJson(cryptopiaService.getDepth("dna")));
    }
}
