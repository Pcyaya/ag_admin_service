package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManReportPageBean extends PageBean{
    private String name;//举报人昵称
    private String phone;//举报账号
    private int sex;//性别 0.男 1.女
    private int type;//举报模块
    private String reasons;//举报原因
    private String deal;//处理人
    private int handle_schedule;//处理进程

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

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
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
