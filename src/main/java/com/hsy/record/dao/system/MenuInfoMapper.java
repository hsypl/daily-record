package com.hsy.record.dao.system;

import com.hsy.core.dao.LongPKBaseMapper;
import com.hsy.record.model.system.MenuInfo;

/**
 * 菜单信息 MyBatis 映射接口类
 * Created by wanghongwei on 3/14/16.
 */
public interface MenuInfoMapper extends LongPKBaseMapper<MenuInfo> {
    /**
     * 更新菜单的discard状态
     * @param menuInfo MenuInfo 菜单对象
     * @return int 受影响的结果记录数
     */
    int updateDiscard(MenuInfo menuInfo);

    MenuInfo getByPkgName(String pkgName);

}
