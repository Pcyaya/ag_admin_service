package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManSosPageBean extends PageBean{
    private String name;//昵称
    private int sex;//性别 0.男 1.女
    private String phone;//用户账号
    private String occupation;//职业
    private String speak_num;//设备编码
    private String date;//求救时间
    private String handle_schedule;//处理进程

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSpeak_num() {
        return speak_num;
    }

    public void setSpeak_num(String speak_num) {
        this.speak_num = speak_num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHandle_schedule() {
        return handle_schedule;
    }

    public void setHandle_schedule(String handle_schedule) {
        this.handle_schedule = handle_schedule;
    }

}
