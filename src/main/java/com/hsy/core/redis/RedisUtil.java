//package com.hsy.core.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by developer2 on 2018/2/28.
// */
//@Service
//public class RedisUtil {
//
//    /** 默认超时时间 */
//    private static final long DEFAULT_TIMEOUT = 3600;
//
//    @Autowired
//    private RedisTemplate<String, byte[]> redisTemplate;
//
//    /**
//     * 从redis服务器获取指定key的值
//     * @param key String redis key
//     * @return byte[] key对应的值
//     */
//    public byte[] redisGet(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 将值设置到指定的key，有效期通过抽象方法从子类中获取
//     * @param key String redis key
//     * @param value key对应的值
//     */
//    public void redisSet(String key, byte[] value) {
//
//        redisTemplate.opsForValue().set(
//                key, value, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//    }
//
//
//}
