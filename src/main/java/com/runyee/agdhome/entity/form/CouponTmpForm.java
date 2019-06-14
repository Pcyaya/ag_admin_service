package com.runyee.agdhome.entity.form;

/**
 * Created by cheri on 2018/8/7.
 */
public class CouponTmpForm {
    private String id;
    private int den;//优惠券面额
    private String unit;//优惠券单位
    private int type;//优惠券类型 0流量券,1金额券
    private int interval;//时效
    private long start;//开始日期
    private long end;//结束日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        this.den = den;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
