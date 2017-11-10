package com.hsy.record.model.currency;

import java.io.Serializable;

/**
* 币种记录 Bean
*
* Created by huangshuoying on 11/8/17.
*/
public class CurrencyInfo implements Serializable {

    /** id */
    private Long id;
    /** 币种名称 */
    private String name;
    /** 美元价格 */
    private String usdPrice;
    /** 人民币价格 */
    private String cnyPrice;
    /** 状态 */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(String usdPrice) {
        this.usdPrice = usdPrice;
    }

    public String getCnyPrice() {
        return cnyPrice;
    }

    public void setCnyPrice(String cnyPrice) {
        this.cnyPrice = cnyPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}