package com.hsy.record.service.exchangeApi;

import com.hsy.record.model.Balance;
import com.hsy.record.model.Depth;
import com.hsy.record.model.DepthDetail;
import com.hsy.record.model.Ticket;
import com.sungness.core.httpclient.HttpClientException;

import java.io.IOException;
import java.util.List;

/**
 * Created by developer2 on 2018/2/9.
 */
public interface ExchangeApiInterface {

    Depth getDepth(String name)throws HttpClientException, IOException;

    Ticket getTicket(String name, String type) throws HttpClientException, IOException;

    List<Balance> getBalance()  throws Exception;

}
