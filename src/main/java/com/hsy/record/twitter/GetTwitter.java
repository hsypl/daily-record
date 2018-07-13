package com.hsy.record.twitter;

import twitter4j.*;

import java.util.List;

/**
 * Created by developer2 on 2018/7/12.
 */
public class GetTwitter {

    public static void main(String[] args) throws TwitterException {
        System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("http.proxyPort", "56016");
        System.getProperties().setProperty("https.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("https.proxyPort", "56016");
        GetConfiguration getConfiguration = new GetConfiguration();
        Twitter twitter = getConfiguration.getNewInstance();
        List<Status> statuses = twitter.getUserTimeline(871155188131823617L);
        for (Status status : statuses) {
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        }
    }

    //获取关注的ID
    public static void getFriendsIDs(Twitter twitter) throws TwitterException {
        IDs ids = twitter.getFriendsIDs(-1);
        for (long id : ids.getIDs()) {
            System.out.println(id);
        }
    }

    //获取推文
    public void getTwitter(){

    }
}
