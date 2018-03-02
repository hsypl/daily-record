package com.hsy.record.service.exchangeApi;

import com.hsy.record.model.Depth;
import com.sungness.core.httpclient.HttpClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by developer2 on 2018/3/1.
 */
@Service
public class ExchangeService {
    @Autowired
    private ContrastService contrastService;

    public void exchange() throws IOException, HttpClientException {
        double disPrice = contrastService.getDisPrice().get("dna");
        List<Depth> depthList = contrastService.getDepthList("dna");
        if((depthList.get(0).getFirstBuyPrice() - depthList.get(1).getFirstSellPrice()) > disPrice){

        }else if ((depthList.get(1).getFirstBuyPrice() - depthList.get(0).getFirstSellPrice()) > disPrice){
        }
    }
}
