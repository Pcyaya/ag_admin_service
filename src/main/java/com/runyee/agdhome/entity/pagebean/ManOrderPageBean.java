package com.runyee.agdhome.entity.pagebean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
@JsonIgnoreProperties(value = {"name","phone","sex","type","tags","deal","handle_schedule"})
public class ManOrderPageBean extends PageBean{
    private  String date;
    private String name;//反馈人昵称
    private String phone;//反馈账号
    private String color;//产品颜色
    private int paytype; //付款类型
    private int payflat; //付款方式
    private int sex;//性别 0.男 1.女
    private int type;//反馈模块
    private String deal;//处理人
    private int handle_schedule;//处理进程

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public int getPayflat() {
        return payflat;
    }

    public void setPayflat(int payflat) {
        this.payflat = payflat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public int getHandle_schedule() {
        return handle_schedule;
    }

    public void setHandle_schedule(int handle_schedule) {
        this.handle_schedule = handle_schedule;
    }
}
