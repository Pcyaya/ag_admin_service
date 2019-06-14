package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManCommodityTmpPageBean extends PageBean{
    private int business;   //商品类型0.商品，1.优惠券
    private String name;//商品名称
    private int online;//-1.未上架0.下架1.上架
    private String on_date;    //最近上架时间
    private String under_date;    //最近下架时间

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getOn_date() {
        return on_date;
    }

    public void setOn_date(String on_date) {
        this.on_date = on_date;
    }

    public String getUnder_date() {
        return under_date;
    }

    public void setUnder_date(String under_date) {
        this.under_date = under_date;
    }
}

