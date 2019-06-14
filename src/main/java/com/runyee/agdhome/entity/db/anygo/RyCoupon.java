package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCoupon {
    private 	String	rc_id;	 //uuid
    private 	String	rc_num;	 //优惠券编码
    private 	String	rc_visitor;	 //拥有人
    private 	String	rc_tmp;	 //优惠券模板
    private 	int	rc_den;	 //面额
    private 	String	rc_unit;	 //单位
    private 	int	rc_type;	 //类型
    private     int rc_inteval;//优惠券时效 -1 永不过期-2时限
    private 	Date	rc_expire;	 //过期时间
    private 	int	rc_used;	 //使用标志
    private 	int	rc_source;	 //来源 0.发放1.领取2.购买
    private 	String	rc_order;	 //来源订单
    private 	String	rc_item;	 //订单项
    private 	int	rc_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRc_id() {
        return rc_id;
    }

    public void setRc_id(String rc_id) {
        this.rc_id = rc_id;
    }

    public String getRc_num() {
        return rc_num;
    }

    public void setRc_num(String rc_num) {
        this.rc_num = rc_num;
    }

    public String getRc_visitor() {
        return rc_visitor;
    }

    public void setRc_visitor(String rc_visitor) {
        this.rc_visitor = rc_visitor;
    }

    public String getRc_tmp() {
        return rc_tmp;
    }

    public void setRc_tmp(String rc_tmp) {
        this.rc_tmp = rc_tmp;
    }

    public int getRc_den() {
        return rc_den;
    }

    public void setRc_den(int rc_den) {
        this.rc_den = rc_den;
    }

    public String getRc_unit() {
        return rc_unit;
    }

    public void setRc_unit(String rc_unit) {
        this.rc_unit = rc_unit;
    }

    public int getRc_type() {
        return rc_type;
    }

    public void setRc_type(int rc_type) {
        this.rc_type = rc_type;
    }

    public int getRc_inteval() {
        return rc_inteval;
    }

    public void setRc_inteval(int rc_inteval) {
        this.rc_inteval = rc_inteval;
    }

    public Date getRc_expire() {
        return rc_expire;
    }

    public void setRc_expire(Date rc_expire) {
        this.rc_expire = rc_expire;
    }

    public int getRc_used() {
        return rc_used;
    }

    public void setRc_used(int rc_used) {
        this.rc_used = rc_used;
    }

    public int getRc_source() {
        return rc_source;
    }

    public void setRc_source(int rc_source) {
        this.rc_source = rc_source;
    }

    public String getRc_order() {
        return rc_order;
    }

    public void setRc_order(String rc_order) {
        this.rc_order = rc_order;
    }

    public String getRc_item() {
        return rc_item;
    }

    public void setRc_item(String rc_item) {
        this.rc_item = rc_item;
    }

    public int getRc_del() {
        return rc_del;
    }

    public void setRc_del(int rc_del) {
        this.rc_del = rc_del;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
