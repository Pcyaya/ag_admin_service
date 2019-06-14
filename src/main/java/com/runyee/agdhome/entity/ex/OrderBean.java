
package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhOrder;

import java.util.Date;


/**
 * Created by cheri on 2018/8/7.
 */
public class OrderBean extends AhOrder {

    private String handle_user;//处理人id
    private String handle_name;//处理人
    private int handle_schedule;//处理进度
    private String schedule_name;//处理进度名称
    private String  handle_results;//处理结果
    private String results_name;//处理结果名称
    private Date date_handle;//处理时间
    private String tags;//标签

    private     String order_name;    //商品名称
    private     String order_color;    //颜色
    private     int order_price;    //单价
    private     int order_amount;    //数量
    private     int order_total;    //总价
    private     int order_paytype;    //付款类型
    private     int order_payflat;    //支付方式
    private     String order_trans;    //快递
    private     String order_transno;    //快递单号

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_color() {
        return order_color;
    }

    public void setOrder_color(String order_color) {
        this.order_color = order_color;
    }

    public int getOrder_price() {
        return order_price;
    }

    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }

    public int getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(int order_amount) {
        this.order_amount = order_amount;
    }

    public int getOrder_total() {
        return order_total;
    }

    public void setOrder_total(int order_total) {
        this.order_total = order_total;
    }

    public int getOrder_paytype() {
        return order_paytype;
    }

    public void setOrder_paytype(int order_paytype) {
        this.order_paytype = order_paytype;
    }

    public int getOrder_payflat() {
        return order_payflat;
    }

    public void setOrder_payflat(int order_payflat) {
        this.order_payflat = order_payflat;
    }

    public String getOrder_trans() {
        return order_trans;
    }

    public void setOrder_trans(String order_trans) {
        this.order_trans = order_trans;
    }

    public String getOrder_transno() {
        return order_transno;
    }

    public void setOrder_transno(String order_transno) {
        this.order_transno = order_transno;
    }

    public String getResults_name() {
        return results_name;
    }

    public void setResults_name(String results_name) {
        this.results_name = results_name;
    }

    public String getHandle_user() {
        return handle_user;
    }

    public void setHandle_user(String handle_user) {
        this.handle_user = handle_user;
    }

    public String getHandle_name() {
        return handle_name;
    }

    public void setHandle_name(String handle_name) {
        this.handle_name = handle_name;
    }

    public int getHandle_schedule() {
        return handle_schedule;
    }

    public void setHandle_schedule(int handle_schedule) {
        this.handle_schedule = handle_schedule;
    }

    public String getSchedule_name() {
        return schedule_name;
    }

    public void setSchedule_name(String schedule_name) {
        this.schedule_name = schedule_name;
    }

    public String  getHandle_results() {
        return handle_results;
    }

    public void setHandle_results(String handle_results) {
        this.handle_results = handle_results;
    }

    public Date getDate_handle() {
        return date_handle;
    }

    public void setDate_handle(Date date_handle) {
        this.date_handle = date_handle;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


}
