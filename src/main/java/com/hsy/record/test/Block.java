package com.hsy.record.test;

import org.apache.commons.codec.digest.HmacUtils;

import java.security.MessageDigest;
import java.util.Date;

/**
 * Created by developer2 on 2018/4/25.
 */
public class Block {

    public String hash;
    public String previousHash;
    private String data; //数据
    private long timeStamp; //时间戳
    private int nonce;//增加一个随机数

    //构造函数
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //计算hash值（把新增的随机数也计算在内）
    public String calculateHash() {
        String calculatedhash = applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        System.out.println(calculatedhash);
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        //创建一个string值由难度的位数来决定
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce = nonce + 1;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        String a = "block test!";
        String ccc = "12346";
        int i = 60 ;
        while (!ccc.substring(0,5).equals("00000")){
            ccc = Block.applySha256(a+i);
            i++;
            System.out.println(ccc);
        }
        System.out.println(i);
    }
}
