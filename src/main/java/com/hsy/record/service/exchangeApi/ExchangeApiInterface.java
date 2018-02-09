package com.hsy.record.service.exchangeApi;

import com.hsy.record.model.Depth;
import com.hsy.record.model.DepthDetail;
import com.sungness.core.httpclient.HttpClientException;

import java.io.IOException;
import java.util.List;

/**
 * Created by developer2 on 2018/2/9.
 */
public interface ExchangeApiInterface {

    Depth getDepth(String name)throws HttpClientException, IOException;

}
