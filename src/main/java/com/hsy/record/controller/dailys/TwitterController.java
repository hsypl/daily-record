package com.hsy.record.controller.dailys;

import com.hsy.record.twitter.TwitterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by developer2 on 2018/7/12.
 */
@Controller
@RequestMapping("/dailys/twitter")
public class TwitterController {

    private static final Logger log = LoggerFactory.getLogger(TwitterController.class);

    @Autowired
    private TwitterConfig twitterConfig;

    @RequestMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String verifier = request.getParameter("oauth_verifier");
        log.debug("aouth_verifier="+verifier);
        log.debug(request.getContextPath());
        response.sendRedirect(request.getContextPath()+ "/");
//        FileInputStream in = new FileInputStream(this.getClass().getResource("/conf/twitter4j.properties").getPath());
//        Properties prop = new Properties();
//        prop.load(in);
//        prop.setProperty("oauth.consumerKey","aaa");
//        prop.setProperty("hsy","xz");
//        FileOutputStream out = new FileOutputStream(this.getClass().getResource("/conf/twitter4j.properties").getPath());
//        prop.store(out, "twitter4j.properties");
//        out.close();
//        in.close();
//        FileInputStream in1 = new FileInputStream(this.getClass().getResource("/conf/twitter4j.properties").getPath());
//        Properties prop1 = new Properties();
//        prop1.load(in1);
//        log.debug("prop hsy =" + prop1.get("oauth.consumerKey"));
        twitterConfig.setOauthKey();
        log.debug("prop hsy ="+ twitterConfig.getOauthKey());
    }
}
