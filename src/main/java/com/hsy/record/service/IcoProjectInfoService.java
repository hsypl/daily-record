package com.hsy.record.service;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.core.web.Pagination;
import com.hsy.record.dao.IcoProjectInfoMapper;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.PlanInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<IcoProjectInfo> getListLeftJoin(Pagination pagination, Map<String,Object> params){
        pagination.setTotalCount(getCount(params));
        if (pagination.getTotalCount() <= 0) {
            return new ArrayList<>();
        }
        RowBounds rowBounds =
                new RowBounds(pagination.getOffset(), pagination.getPageSize());
        return icoProjectInfoMapper.getListLeftJoin(rowBounds, params);
    }

    public Integer getSum(){
        return icoProjectInfoMapper.getSum();
    }

    public Integer getInSum(){
        return icoProjectInfoMapper.getInSum();
    }

}
