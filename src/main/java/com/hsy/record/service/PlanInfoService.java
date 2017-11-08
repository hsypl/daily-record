package com.hsy.record.service;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.record.dao.PlanInfoMapper;
import com.hsy.record.model.PlanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by developer2 on 2017/7/11.
 */
@Service
public class PlanInfoService extends LongPKBaseService<PlanInfo> {

    @Autowired
    private PlanInfoMapper planInfoMapper;


    @Override
    protected GenericMapper<PlanInfo, Long> getMapper() {
        return planInfoMapper;
    }

    public PlanInfo get(Long id){
        PlanInfo planInfo = planInfoMapper.get(id);
        if(planInfo != null){
            return planInfo;
        }
        return new PlanInfo();
    }

}
