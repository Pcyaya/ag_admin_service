package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class AhOrderHandle {
    private 	String	aoh_id;	 //
    private 	String	aoh_order;	 //意见反馈id
    private 	String	aoh_user;	 //处理人
    private 	int	aoh_status;	 //0处理中1已处理
    private 	String	aoh_content;	 //备注
    private     String aoh_name;    //商品名称
    private     String aoh_color;    //颜色
    private     int aoh_price;    //单价
    private     int aoh_amount;    //数量
    private     int aoh_total;    //总价
    private     int aoh_paytype;    //付款类型
    private     int aoh_payflat;    //支付方式
    private     String aoh_trans;    //快递
    private     String aoh_transno;    //快递单号
    private 	int	del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAoh_id() {
        return aoh_id;
    }

    public void setAoh_id(String aoh_id) {
        this.aoh_id = aoh_id;
    }

    public String getAoh_order() {
        return aoh_order;
    }

    public void setAoh_order(String aoh_order) {
        this.aoh_order = aoh_order;
    }

    public String getAoh_user() {
        return aoh_user;
    }

    public void setAoh_user(String aoh_user) {
        this.aoh_user = aoh_user;
    }

    public int getAoh_status() {
        return aoh_status;
    }

    public void setAoh_status(int aoh_status) {
        this.aoh_status = aoh_status;
    }

    public String getAoh_content() {
        return aoh_content;
    }

    public void setAoh_content(String aoh_content) {
        this.aoh_content = aoh_content;
    }

    public String getAoh_name() {
        return aoh_name;
    }

    public void setAoh_name(String aoh_name) {
        this.aoh_name = aoh_name;
    }

    public String getAoh_color() {
        return aoh_color;
    }

    public void setAoh_color(String aoh_color) {
        this.aoh_color = aoh_color;
    }

    public int getAoh_price() {
        return aoh_price;
    }

    public void setAoh_price(int aoh_price) {
        this.aoh_price = aoh_price;
    }

    public int getAoh_amount() {
        return aoh_amount;
    }

    public void setAoh_amount(int aoh_amount) {
        this.aoh_amount = aoh_amount;
    }

    public int getAoh_total() {
        return aoh_total;
    }

    public void setAoh_total(int aoh_total) {
        this.aoh_total = aoh_total;
    }

    public int getAoh_paytype() {
        return aoh_paytype;
    }

    public void setAoh_paytype(int aoh_paytype) {
        this.aoh_paytype = aoh_paytype;
    }

    public int getAoh_payflat() {
        return aoh_payflat;
    }

    public void setAoh_payflat(int aoh_payflat) {
        this.aoh_payflat = aoh_payflat;
    }

    public String getAoh_trans() {
        return aoh_trans;
    }

    public void setAoh_trans(String aoh_trans) {
        this.aoh_trans = aoh_trans;
    }

    public String getAoh_transno() {
        return aoh_transno;
    }

    public void setAoh_transno(String aoh_transno) {
        this.aoh_transno = aoh_transno;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
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
