package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RySpkSos;

import java.util.Date;


/**
 * Created by cheri on 2018/8/7.
 */
public class SpkSosBean extends RySpkSos{
    private String name;//昵称
    private String phone;//联系方式
    private String sex;//性别0男1女
    private Date age;//生日
    private String occupation;//职业
    private String guide_no;//导游证号
    private String company;//公司
    private String handle_user;//处理人id
    private String handle_name;//处理人
    private String handle_schedule;//处理进度
    private String schedule_name;//处理进度名称


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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGuide_no() {
        return guide_no;
    }

    public void setGuide_no(String guide_no) {
        this.guide_no = guide_no;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getHandle_schedule() {
        return handle_schedule;
    }

    public void setHandle_schedule(String handle_schedule) {
        this.handle_schedule = handle_schedule;
    }

    public String getSchedule_name() {
        return schedule_name;
    }

    public void setSchedule_name(String schedule_name) {
        this.schedule_name = schedule_name;
    }
}
