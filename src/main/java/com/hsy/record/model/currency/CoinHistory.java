package com.hsy.record.model.currency;

import java.io.Serializable;

/**
* 资产历史记录 Bean
*
* Created by huangshuoying on 12/2/17.
*/
public class CoinHistory implements Serializable {

    /** id */
    private String id;
    private String symbol;
    /** 价格 */
    private Double price;
    /** 交易量 */
    private Double volume;
    /** 日期 */
    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


}