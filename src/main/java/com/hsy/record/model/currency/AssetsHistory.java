package com.hsy.record.model.currency;

import com.sungness.core.util.DateUtil;

import java.io.Serializable;

/**
* 资产历史记录 Bean
*
* Created by huangshuoying on 11/24/17.
*/
public class AssetsHistory implements Serializable {

    /** id */
    private Long id;
    /** 总资产 */
    private Long amount;
    /** 统计日期 */
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getFormatCreateTime(){
        return DateUtil.fullFormat(createTime);
    }

}