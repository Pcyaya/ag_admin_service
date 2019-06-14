package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManAppOrderPageBean extends PageBean{
    private String num;//订单编码
    private String visitor;   //账号
    private String commodity_num;//商品编码
    private int business;//交易类型 0.商品，1.优惠券 2.流量 3.提现
    private int transaction_type;//交易方式
    private String transaction_date;//交易时间
    private int status;//订单类型


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getCommodity_num() {
        return commodity_num;
    }

    public void setCommodity_num(String commodity_num) {
        this.commodity_num = commodity_num;
    }

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public int getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(int transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

