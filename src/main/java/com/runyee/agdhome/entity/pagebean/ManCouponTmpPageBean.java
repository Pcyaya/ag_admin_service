package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManCouponTmpPageBean extends PageBean{
    private int rct_type;   //优惠券类型
    private String date;    //创建时间

    public int getRct_type() {
        return rct_type;
    }

    public void setRct_type(int rct_type) {
        this.rct_type = rct_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

