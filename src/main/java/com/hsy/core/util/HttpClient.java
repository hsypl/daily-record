//package com.hsy.core.util;
//
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//
//import java.net.Authenticator;
//import java.net.InetSocketAddress;
//import java.net.PasswordAuthentication;
//import java.util.Map;
//
///**
// * Created by developer2 on 2018/3/16.
// */
//public class HttpClient {
//
//    // 依次是代理地址，代理端口号，用户密码
//    private static String proxyHost="192.168.1.2";
//    private static int proxyPort=1080;
//    private static String proxyName="user";
//    private static String proxyPwd="123456";
//
//
//    public static String getWithProxy(String url, Map<String, String> headers, String charset) {
//        //用户名和密码验证
//        Authenticator.setDefault(new Authenticator(){
//            protected PasswordAuthentication getPasswordAuthentication(){
//                PasswordAuthentication p=new PasswordAuthentication(proxyName, proxyPwd.toCharArray());
//                return p;
//            }
//        });
//        Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory> create()
//                .register("http", new MyConnectionSocketFactory())
//                .register("https", new MySSLConnectionSocketFactory(SSLContexts.createSystemDefault())).build();
//        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(reg, new FakeDnsResolver());
//        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
//        try {
//            InetSocketAddress socksaddr = new InetSocketAddress(proxyHost,proxyPort);
//            HttpClientContext context = HttpClientContext.create();
//            context.setAttribute("socks.address", socksaddr);
//            HttpGet httpget = new HttpGet(url);
//            if(headers != null) {
//                for(String key:headers.keySet()) {
//                    httpget.setHeader(key, headers.get(key));
//                }
//            }
//            CloseableHttpResponse response = httpclient.execute(httpget,context);
//            try {
//                return new String(EntityUtils.toByteArray(response.getEntity()), charset);
//            } finally {
//                response.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                httpclient.close();
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return null;
//    }
//}
