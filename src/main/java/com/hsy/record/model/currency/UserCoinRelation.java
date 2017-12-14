package com.hsy.record.model.currency;

import java.io.Serializable;

/**
* 资产历史记录 Bean
*
* Created by huangshuoying on 12/14/17.
*/
public class UserCoinRelation implements Serializable {

    /** id */
    private Long id;
    /** 用户id */
    private String uid;
    /** 代币符号 */
    private String symbol;
    /** 优先级 */
    private Integer priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}