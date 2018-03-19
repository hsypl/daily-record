package com.hsy.record.model;

import java.io.Serializable;

/**
* 交易所接口信息 Bean
*
* Created by huangshuoying on 3/19/18.
*/
public class ExchangeApiResult implements Serializable {

    /** id */
    private Long id;
    /** 代币符号 */
    private String symbol;
    /** 交易所 */
    private String exchange;
    /** 内容 */
    private String result;
    /** 类型 */
    private Integer type;

    private Long time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}