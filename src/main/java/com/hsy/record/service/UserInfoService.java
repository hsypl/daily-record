package com.hsy.record.service;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.ServiceProcessException;
import com.hsy.core.service.StringPKBaseService;
import com.hsy.record.dao.UserInfoMapper;
import com.hsy.record.model.UserInfo;
import com.hsy.record.model.enu.JudgeEnum;
import com.hsy.record.model.system.ItemTypeEnum;
import com.hsy.record.model.system.UserPrivilege;
import com.hsy.record.service.system.UserPrivilegeService;
import com.sungness.core.util.UuidGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserPrivilegeService userPrivilegeService;

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

    public UserInfo getWithPrivilege(String id){
        UserInfo userInfo = getSafety(id);
        if(StringUtils.isNotBlank(userInfo.getUid())){
            List<Long> moduleIdList
                    = userPrivilegeService.getItemIdListByUserIdAndType(id, ItemTypeEnum.MODULE.getValue());
            userInfo.setModuleIdList(moduleIdList);
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

    public void save(UserInfo userInfo) throws ServiceProcessException {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode(userInfo.getPassword());
        userInfo.setPassword(hashPass);
        if(StringUtils.isBlank(userInfo.getUid())){
            userInfo.setUid(UuidGenerator.nextUid());
            insert(userInfo);
        }
        update(userInfo);
        userPrivilegeService.save(userInfo);
    }

    /**
     * 根据用户id获取权限key集合,包括其角色的权限
     * @return Set<String> 权限 key 集合
     */
    public Set<String> getPrivilegeSet(UserInfo userInfo) {
        Set<String> privilegeSet = new HashSet<>();
        List<UserPrivilege> userPrivilegeList =
                userPrivilegeService.getListByUserId(userInfo.getUid());
        privilegeSet.addAll(userPrivilegeList.stream().map(UserPrivilege::getPrivilegeKey).collect(Collectors.toList()));
        return privilegeSet;
    }
}