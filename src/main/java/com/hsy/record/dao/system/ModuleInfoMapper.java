package com.hsy.record.dao.system;

import com.hsy.core.dao.LongPKBaseMapper;
import com.hsy.record.model.system.ModuleInfo;

/**
 * 模块信息 MyBatis 映射接口类
 * Created by wanghongwei on 3/14/16.
 */
public interface ModuleInfoMapper extends LongPKBaseMapper<ModuleInfo> {
    /**
     * 更新模块的discard状态
     * @param moduleInfo ModuleInfo 模块对象
     * @return int 受影响的结果记录数
     */
    int updateDiscard(ModuleInfo moduleInfo);

    ModuleInfo getByPkgName(String pkgName);
}
