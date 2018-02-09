package com.hsy.record.model.exchangeApi.okex;

import java.util.List;
import java.util.Map;

/**
 * Created by developer2 on 2018/2/6.
 */
public class OkDepth {

    private List<List<Double>> sellList;

    private List<List<Double>> buyList;

    private double firstBuyPrice ;

    private double firstSellPrice;

    public List<List<Double>> getSellList() {
        return sellList;
    }

    public void setSellList(List<List<Double>> sellList) {
        this.sellList = sellList;
    }

    public List<List<Double>> getBuyList() {
        return buyList;
    }

    public void setBuyList(List<List<Double>> buyList) {
        this.buyList = buyList;
    }

    public double getFirstBuyPrice() {
        return firstBuyPrice;
    }

    public void setFirstBuyPrice(double firstBuyPrice) {
        this.firstBuyPrice = firstBuyPrice;
    }

    public double getFirstSellPrice() {
        return firstSellPrice;
    }

    public void setFirstSellPrice(double firstSellPrice) {
        this.firstSellPrice = firstSellPrice;
    }
}
