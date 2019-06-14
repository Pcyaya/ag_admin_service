package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RyGuideValid;
import com.runyee.agdhome.entity.db.anygo.RySpkSos;

import java.util.Date;


/**
 * Created by cheri on 2018/8/7.
 */
public class GuideValidBean extends RyGuideValid {
    private String name;//昵称
    private String phone;//联系方式
    private String sex;//性别0男1女
    private Date age;//生日
    private String handle_user;//处理人id
    private String handle_name;//处理人
    private int handle_schedule;//处理进度
    private String schedule_name;//处理进度名称
    private String handle_result;//处理结果
    private String handle_date;//处理时间


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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
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

    public String getHandle_result() {
        return handle_result;
    }

    public void setHandle_result(String handle_result) {
        this.handle_result = handle_result;
    }

    public String getHandle_date() {
        return handle_date;
    }

    public void setHandle_date(String handle_date) {
        this.handle_date = handle_date;
    }
}
