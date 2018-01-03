package com.hsy.record.model.currency;

import com.hsy.record.model.enu.ChangeTypeEnum;
import com.sungness.core.util.DateUtil;

import java.io.Serializable;

/**
* 交易记录 Bean
*
* Created by huangshuoying on 12/29/17.
*/
public class ChangeLog implements Serializable {

    /** id */
    private Long id;
    /** 用户id */
    private String uid;
    /** 代币符号 */
    private String symbol;
    /** 金额 */
    private String price;
    /** 数量 */
    private String number;
    /** 总量 */
    private Integer amount;
    /** 类型 */
    private Integer type;
    /** 备注 */
    private String remark;
    /** 交易日期 */
    private Long createTime;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTypeEnum(){
        return ChangeTypeEnum.getDescription(type);
    }

    public String getFormatCreateTime(){
       return DateUtil.fullFormat(createTime);
    }

}