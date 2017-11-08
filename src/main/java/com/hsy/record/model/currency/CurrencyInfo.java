package com.hsy.record.model.currency;

/**
 * Created by developer2 on 2017/11/7.
 */
public class CurrencyInfo {

    private String name;

    private String USAPrice;

    private String CNYPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUSAPrice() {
        return USAPrice;
    }

    public void setUSAPrice(String USAPrice) {
        this.USAPrice = USAPrice;
    }

    public String getCNYPrice() {
        return CNYPrice;
    }

    public void setCNYPrice(String CNYPrice) {
        this.CNYPrice = CNYPrice;
    }
}
