
package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RyCommodityTmp;

import java.util.Date;


/**
 * Created by cheri on 2018/8/7.
 */
public class CommodityTmpBean extends RyCommodityTmp {
    private int coupon_den;//优惠面值
    private String coupon_unit;//单位
    private int coupon_type;//优惠券类型 0流量券,1金额券
    private int coupon_interval;//时效
    private int coupon_valid;//是否失效1.已失效
    private Date coupon_start;//有效期
    private Date coupon_end;//有效期


    public int getCoupon_den() {
        return coupon_den;
    }

    public void setCoupon_den(int coupon_den) {
        this.coupon_den = coupon_den;
    }

    public String getCoupon_unit() {
        return coupon_unit;
    }

    public void setCoupon_unit(String coupon_unit) {
        this.coupon_unit = coupon_unit;
    }

    public int getCoupon_type() {
        return coupon_type;
    }

    public void setCoupon_type(int coupon_type) {
        this.coupon_type = coupon_type;
    }

    public int getCoupon_interval() {
        return coupon_interval;
    }

    public void setCoupon_interval(int coupon_interval) {
        this.coupon_interval = coupon_interval;
    }

    public int getCoupon_valid() {
        return coupon_valid;
    }

    public void setCoupon_valid(int coupon_valid) {
        this.coupon_valid = coupon_valid;
    }

    public Date getCoupon_start() {
        return coupon_start;
    }

    public void setCoupon_start(Date coupon_start) {
        this.coupon_start = coupon_start;
    }

    public Date getCoupon_end() {
        return coupon_end;
    }

    public void setCoupon_end(Date coupon_end) {
        this.coupon_end = coupon_end;
    }
}
