package com.hsy.record;

import com.hsy.record.model.currency.CurrencyInfo;
import com.sungness.core.util.GsonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by developer2 on 2017/11/6.
 */
public class Test {

    public String privateUrl = "https://www.cryptopia.co.nz/Api/";
    public String publicKey = "148b658687c54d13892a527ece8c32d4";
    public String privateKey = "9aa5AMjwFP16oaQtnKY09zd7oe67X4asgdE/rbgv2DY=";

    public Test(String method,String jSonPostParam) throws Exception {
        String urlMethod = privateUrl + method;
        String nonce     = String.valueOf(System.currentTimeMillis());

        String reqSignature =
                publicKey
                        + "POST"
                        + URLEncoder.encode(urlMethod, StandardCharsets.UTF_8.toString()).toLowerCase()
                        + nonce
                        + getMD5_B64(jSonPostParam);

        String AUTH = "amx "
                + publicKey
                +":"
                + this.sha256_B64(reqSignature)
                +":"
                + nonce;

        String response = this.continueForHttp(urlMethod, jSonPostParam,AUTH);

        System.out.println("API RESPONSE : " + response);
    }

    private String continueForHttp(String urlMethod, String postParam,String AUTH) throws Exception {
        URLConnection con = new URL(urlMethod).openConnection(); // CREATE POST CONNECTION
        con.setDoOutput(true);

        HttpsURLConnection httpsConn = (HttpsURLConnection) con;
        httpsConn.setRequestMethod("POST");
        httpsConn.setInstanceFollowRedirects(true);

        con.setRequestProperty("Authorization", AUTH);
        con.setRequestProperty("Content-Type", "application/json");

        // WRITE POST PARAMS
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParam);
        wr.flush();
        wr.close();

        // READ THE RESPONSE
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private String getMD5_B64(String postParameter) throws Exception {
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(postParameter.getBytes("UTF-8")));
    }

    private String sha256_B64(String msg) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(Base64.getDecoder().decode(privateKey), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(msg.getBytes("UTF-8")));
    }


    public static void main(String[] args) {
        CurrencyInfo currencyInfo1 = new CurrencyInfo();
        CurrencyInfo currencyInfo2 = new CurrencyInfo();
        CurrencyInfo currencyInfo3 = new CurrencyInfo();
        CurrencyInfo currencyInfo4 = new CurrencyInfo();
        CurrencyInfo currencyInfo5 = new CurrencyInfo();
        currencyInfo1.setId(1L);
        currencyInfo2.setId(2L);
        currencyInfo3.setId(3L);
        currencyInfo4.setId(4L);
        currencyInfo5.setId(1L);
        currencyInfo1.setStatus(1);
        currencyInfo2.setStatus(2);
        currencyInfo3.setStatus(1);
        currencyInfo4.setStatus(2);
        currencyInfo5.setStatus(3);
        currencyInfo1.setName("a");
        currencyInfo2.setName("b");
        currencyInfo3.setName("c");
        currencyInfo4.setName("d");
        currencyInfo5.setName("e");
        List<CurrencyInfo> currencyInfoList = Arrays.asList(currencyInfo1,currencyInfo2,currencyInfo3,currencyInfo4,currencyInfo5);
//        Map<Long,List<CurrencyInfo>> longListMap
//                = currencyInfoList.stream().collect(Collectors.groupingBy(c->c.getId()));
//        Map<Integer,Map<Long,List<CurrencyInfo>>> test
//                = currencyInfoList.stream().collect(
//                        Collectors.groupingBy(CurrencyInfo::getStatus,
//                                Collectors.groupingBy(CurrencyInfo::getId)));
//        System.out.print(GsonUtils.toJson(test));
        System.out.print(GsonUtils.toJson(currencyInfoList.stream().map(c->c.getId()).collect(Collectors.toSet())));
    }

}
