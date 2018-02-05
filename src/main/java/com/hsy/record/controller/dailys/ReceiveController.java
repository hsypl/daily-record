package com.hsy.record.controller.dailys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by developer2 on 2018/2/5.
 */
@Controller
@RequestMapping(ReceiveController.URL_PREFIX)
public class ReceiveController {

    public final static String URL_PREFIX = "/dailys/wechat";

    private static final Logger log = LoggerFactory.getLogger(ReceiveController.class);

    @ResponseBody
    @RequestMapping(value = "/receive",method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws IOException {
        String result = parseRequest(request);
        log.debug("result="+result);
        return "aaa";
    }

    /**
     *  返回post请求String内容
     * @param request HttpServletRequest
     * @return String
     */
    public  String parseRequest(HttpServletRequest request)
            throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder reportBuilder = new StringBuilder();
        String tempStr;
        while ((tempStr = reader.readLine()) != null) {
            reportBuilder.append(tempStr);
        }
        return reportBuilder.toString();
    }
}
