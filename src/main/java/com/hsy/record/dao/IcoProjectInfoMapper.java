package com.hsy.record.dao;

import com.hsy.core.dao.LongPKBaseMapper;
import com.hsy.record.model.IcoProjectInfo;
import com.hsy.record.model.PlanInfo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2017/7/11.
 */
public interface IcoProjectInfoMapper extends LongPKBaseMapper<IcoProjectInfo> {

    List<IcoProjectInfo> getListLeftJoin(RowBounds rowBounds, Map<String,Object> params);
}
