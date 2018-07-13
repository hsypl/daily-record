package com.hsy.record.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by developer2 on 2018/7/12.
 */
public class Login {

    public static void main(String[] args) throws TwitterException, IOException {
        System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("http.proxyPort", "56016");
        System.getProperties().setProperty("https.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("https.proxyPort", "56016");
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("MBwX4zZQjGA8g3czZ79B1jPyk", "jcixtqTfxzlCiX3aAaCZ81sGRF0EqXNYMtAhNaW73IRNbWZ2gY");
        String callBackUrl = "http://47.75.121.31/dailys/twitter/callback";
        RequestToken requestToken = twitter.getOAuthRequestToken(callBackUrl);
        AccessToken accessToken = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken) {
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();
            try{
                if(pin.length() > 0){
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                }else{
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if(401 == te.getStatusCode()){
                    System.out.println("Unable to get the access token.");
                }else{
                    te.printStackTrace();
                }
            }
        }
        //persist to the accessToken for future reference.
        storeAccessToken((int) twitter.verifyCredentials().getId(), accessToken);
        Status status = twitter.updateStatus(args[0]);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
        System.exit(0);
    }

    private static void storeAccessToken(int useId, AccessToken accessToken){
        System.out.println("hsyToken="+accessToken.getToken());
        System.out.println("hsyTokenSecret="+accessToken.getTokenSecret());
        //store accessToken.getToken()
        //store accessToken.getTokenSecret()
    }
}
