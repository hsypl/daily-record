package com.hsy.record.twitter;

import java.util.Properties;

/**
 * Created by huangshuoying on 2017/3/28.
 */
public class TwitterConfig {
    /** 配置属性文件 */
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

    /**
     * 获取驰声的appKey
     * @return String appKey
     */
    public String getOauthKey() {
        return properties.getProperty("oauth.consumerKey");
    }

    public void setOauthKey(){
        properties.setProperty("oauth.consumerKey","aaa");
    }

    /**
     * 获取驰声的secretKey
     * @return String secretKey
     */
    public String getSecretKey() {
        return properties.getProperty("app.secret.key");
    }


}
