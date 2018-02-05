package com.hsy.record.model.exchangeApi.cryptopia;

/**
 * Created by developer2 on 2018/1/12.
 */
public class HistoryTrade {

    private double price;

    private double volume;

    private double total;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
