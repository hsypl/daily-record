package com.hsy.record.service;

import com.hsy.core.dao.GenericMapper;
import com.hsy.core.service.LongPKBaseService;
import com.hsy.core.web.Pagination;
import com.hsy.record.dao.IcoProjectInfoMapper;
import com.hsy.record.model.IcoProjectInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<IcoProjectInfo> getListLeftJoinByUid(Pagination pagination,String uid ){
        Map<String,Object> params = new HashMap<>();
        params.put("uid",uid);
        return getListLeftJoin(pagination,params);

    }

    public List<IcoProjectInfo> getListLeftJoinByUid(String uid ){
        Map<String,Object> params = new HashMap<>();
        params.put("uid",uid);
        return icoProjectInfoMapper.getListLeftJoin(params);

    }

    public List<IcoProjectInfo> filterList(List<IcoProjectInfo> list){
        List<IcoProjectInfo> filterList = new ArrayList<>();
        for (IcoProjectInfo icoProjectInfo : list){
            if(StringUtils.isNotBlank(icoProjectInfo.getCount()) && !icoProjectInfo.getCount().equals("0")){
                filterList.add(icoProjectInfo);
            }
        }
        return filterList;
    }

    public String getNameData(List<IcoProjectInfo> list){
        StringBuilder buffer = new StringBuilder();
        for (IcoProjectInfo info : list){
            buffer.append("'").append(info.getSymbol()).append("'").append(",");
        }
        return buffer.toString().substring(0,buffer.length()-1);
    }

    public Integer getSum(){
        return icoProjectInfoMapper.getSum();
    }

    public Integer getInSum(){
        return icoProjectInfoMapper.getInSum();
    }

    public static void main(String[] args){
        List<IcoProjectInfo> list = new ArrayList<>();
        IcoProjectInfo a = new IcoProjectInfo();
        a.setSymbol("dash");
        IcoProjectInfo b = new IcoProjectInfo();
        b.setSymbol("btc");
        list.add(a);
        list.add(b);
//        System.out.print(getNameData(list));
    }
}
