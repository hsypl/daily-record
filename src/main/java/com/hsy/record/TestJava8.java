package com.hsy.record;

import com.sungness.core.httpclient.HttpClientException;
import com.sungness.core.httpclient.HttpClientUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by developer2 on 2018/1/24.
 */
public class TestJava8 {

    public static void main(String args[]) throws HttpClientException {
//        String reuslt = HttpClientUtils.getString("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcc21e4c4b82e0696&secret=a56a487fab0515912861e8052617cc87");
        String token = "6_Ts009DHCqjUQtS3Uq2Zfso5HgnrzaIQf11IoZWABD5FuSjcijfEsjY43v9sJvcWW9rZ-97lfkrPQIAV8xnNedOLXM4vpL9gmJPSGsL8oqeP6QLqk7aMJNH5LS_t3SD8x_QY7ndfOAyeZEEmFQNRhAIATQU";
        String content = "{\n" +
                "   \"touser\":[\n" +
                "    \"o_ANm1Nc_SER9g7qGgvlsBD-AqVc\",\n" +
                "    \"o_ANm1Nc_SER9g7qGgvlsBD-AqVc\"\n" +
                "   ],\n" +
                "    \"msgtype\": \"text\",\n" +
                "    \"text\": { \"content\": \"hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.hello from boxer.\"}\n" +
                "}";
        String post = HttpClientUtils.postJson("https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+token,content);
        System.out.print(post);
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.isEmpty()){
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings){
        int count = 0;

        for(String string: strings){

            if(string.length() == 3){
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings){
        List<String> filteredList = new ArrayList<String>();

        for(String string: strings){

            if(!string.isEmpty()){
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator){
        StringBuilder stringBuilder = new StringBuilder();

        for(String string: strings){

            if(!string.isEmpty()){
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length()-2);
    }

    private static List<Integer> getSquares(List<Integer> numbers){
        List<Integer> squaresList = new ArrayList<Integer>();

        for(Integer number: numbers){
            Integer square = new Integer(number.intValue() * number.intValue());

            if(!squaresList.contains(square)){
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers){
        int max = numbers.get(0);

        for(int i=1;i < numbers.size();i++){

            Integer number = numbers.get(i);

            if(number.intValue() > max){
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers){
        int min = numbers.get(0);

        for(int i=1;i < numbers.size();i++){
            Integer number = numbers.get(i);

            if(number.intValue() < min){
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers){
        int sum = (int)(numbers.get(0));

        for(int i=1;i < numbers.size();i++){
            sum += (int)numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers){
        return getSum(numbers) / numbers.size();
    }
}
