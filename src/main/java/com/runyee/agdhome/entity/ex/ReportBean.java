package com.runyee.agdhome.entity.ex;


import com.runyee.agdhome.entity.db.anygo.RyReport;

import java.util.Date;



/**
 * Created by cheri on 2018/8/7.
 */
public class ReportBean extends RyReport {
    private String name;//昵称
    private String phone;//举报人账号
    private int sex;//性别0男1女
    private String handle_user;//处理人id
    private String handle_name;//处理人
    private int handle_schedule;//处理进度
    private String schedule_name;//处理进度名称
    private int handle_results;//处理结果
    private String results_name;//处理结果名称
    private Date date_handle;//处理时间
    private String reported_info;//被举报者信息
    private String tags;//举报原因

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

    public int getHandle_results() {
        return handle_results;
    }

    public void setHandle_results(int handle_results) {
        this.handle_results = handle_results;
    }

    public String getResults_name() {
        return results_name;
    }

    public void setResults_name(String results_name) {
        this.results_name = results_name;
    }

    public Date getDate_handle() {
        return date_handle;
    }

    public void setDate_handle(Date date_handle) {
        this.date_handle = date_handle;
    }

    public String getReported_info() {
        return reported_info;
    }

    public void setReported_info(String reported_info) {
        this.reported_info = reported_info;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
