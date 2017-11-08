package com.hsy.record.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by developer2 on 2017/7/10.
 */
@Controller
@RequestMapping(ReaderController.URL_PREFIX)
public class ReaderController {

    public final static String URL_PREFIX = "/daily/reader";

    @RequestMapping("/index")
    public void index(){

    }
}
