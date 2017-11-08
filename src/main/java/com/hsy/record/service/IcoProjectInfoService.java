package com.hsy.record.service;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.record.dao.IcoProjectInfoMapper;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.PlanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by developer2 on 2017/7/11.
 */
@Service
public class IcoProjectInfoService extends LongPKBaseService<IcoProjectInfo> {

    @Autowired
    private IcoProjectInfoMapper icoProjectInfoMapper;


    @Override
    protected GenericMapper<IcoProjectInfo, Long> getMapper() {
        return icoProjectInfoMapper;
    }

    public IcoProjectInfo getSafe(Long id){
        IcoProjectInfo icoProjectInfo = icoProjectInfoMapper.get(id);
        if(icoProjectInfo != null){
            return icoProjectInfo;
        }
        return new IcoProjectInfo();
    }

}
