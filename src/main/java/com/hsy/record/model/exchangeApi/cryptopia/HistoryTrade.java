package com.hsy.record.model.exchangeApi.cryptopia;

/**
 * Created by developer2 on 2018/1/12.
 */
public class HistoryTrade {

    private double price;

    private double amount;

    private double total;

    private String type;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
