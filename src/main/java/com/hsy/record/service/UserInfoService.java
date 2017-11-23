package com.hsy.record.service;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.StringPKBaseService;
import com.hsy.record.dao.UserInfoMapper;
import com.hsy.record.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
* 用户信息 业务处理类
*
* Created by huangshuoying on 11/20/17.
*/
@Service
public class UserInfoService
        extends StringPKBaseService<UserInfo> {
    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
    * 获取数据层mapper接口对象，子类必须实现该方法
    *
    * @return GenericMapper<E, PK> 数据层mapper接口对象
    */
    @Override
    protected GenericMapper<UserInfo, String> getMapper() {
        return userInfoMapper;
    }

    /**
    * 根据id获取对象，如果为空，返回空对象
    * @param id Long 公司id
    * @return UserInfo 用户信息对象
    */
    public UserInfo getSafety(String id) {
        UserInfo userInfo = get(id);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public UserInfo getByUsername(String username){
        if(StringUtils.isBlank(username)){
            return null;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("username",username);
        return getByDynamicWhere(params);
    }

    private static String produceCode(){
        Random random = new Random();
        String letter = "ABCDEFGHJKMNPQRSTUVWXYZ";
        String number = "123456789";
        int randomLetter = random.nextInt(letter.length());
        String code = letter.substring(randomLetter,randomLetter+1);
        for (int i = 0; i < 6; i++) {
            int n = random.nextInt(number.length());
            code += number.substring(n, n + 1);
        }
        System.out.println(code);
        return code;
    }

    public static void main(String[] args){
        String a = "A123456";
        System.out.println(a.toUpperCase());
    }
}