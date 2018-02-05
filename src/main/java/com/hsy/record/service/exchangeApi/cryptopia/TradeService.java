package com.hsy.record.service.exchangeApi.cryptopia;

import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;
import com.sungness.core.util.GsonUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;

/**
 * Created by developer2 on 2018/1/12.
 */
@Service
public class TradeService {

    private static final Logger log = LoggerFactory.getLogger(TradeService.class);

    public String privateUrl = "https://www.cryptopia.co.nz/Api/";
    public String publicKey = "148b658687c54d13892a527ece8c32d4";
    public String privateKey = "9aa5AMjwFP16oaQtnKY09zd7oe67X4asgdE/rbgv2DY=";


    public Map<String,Object> getTradeInfo(String name) throws HttpClientException {
        String url = "https://www.cryptopia.co.nz/api/GetMarketOrders/"+name+"_BTC/10";
        String result = HttpClientUtils.getString(url);
        Map<String,Object> resultMap = GsonUtils.toStrObjMap(result);
        log.debug(GsonUtils.toJson(result));
        return (Map<String, Object>) resultMap.get("Data");
    }
//
//    public static void main(String[] args) {
//        try {
//            // YOU CAN FORMAT THE JSON STRING USING THE LIBRARY YOU PREFER (OR MANUALLY)
//            Map<String,String> params = new HashMap<>();
//            params.put("Currency","DNA");
//            new TradeService("GetTradeHistory", GsonUtils.toJson(params));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }





//    public static void main(String[] args) throws Exception {
//        TradeService tradeService = new TradeService();
//        Map<String,Object> child = tradeService.getTradeInfo("DNA");
//        List<Map<String,Object>> buyList = (List<Map<String, Object>>) child.get("Buy");
//        List<Map<String,Object>> sellList = (List<Map<String, Object>>) child.get("Buy");
//        for (Map<String,Object> map : buyList){
//            BigDecimal bg=new BigDecimal(map.get("Price")+"");
//            System.out.println(bg);
//        }
//    }
}
