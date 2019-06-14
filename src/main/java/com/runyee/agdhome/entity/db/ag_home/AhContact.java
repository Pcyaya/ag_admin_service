package com.runyee.agdhome.entity.db.ag_home;

import com.runyee.agdhome.entity.ex.ContactInfoBean;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhContact extends ContactInfoBean {
    private 	String	ah_id;	 //uuid
    private 	String	ac_name;	 //联系名称
    private 	String	ac_phone;	 //联系方式
    private 	String	ac_business;	 //业务
    private 	String	ac_email;	 //邮箱
    private 	int	ac_sex;	 //0.男 1.女
    private 	String	ac_content;	 //留言内容
    private 	int	del;	 //1删除
    private     String handle_user;//处理人id
    private     String handle_name;//处理人
    private     int handle_schedule;//处理进度
    private     String schedule_name;//处理进度名称
    private     int handle_results;//处理结果
    private     String results_name;//处理结果名称
    private Date date_handle;//处理时间
    private     Date create_date;	 //
    private 	Date	update_date;	 //


    public String getAh_id() {
        return ah_id;
    }

    public void setAh_id(String ah_id) {
        this.ah_id = ah_id;
    }

    public String getAc_name() {
        return ac_name;
    }

    public void setAc_name(String ac_name) {
        this.ac_name = ac_name;
    }

    public String getAc_phone() {
        return ac_phone;
    }

    public void setAc_phone(String ac_phone) {
        this.ac_phone = ac_phone;
    }

    public String getAc_business() {
        return ac_business;
    }

    public void setAc_business(String ac_business) {
        this.ac_business = ac_business;
    }

    public String getAc_email() {
        return ac_email;
    }

    public void setAc_email(String ac_email) {
        this.ac_email = ac_email;
    }

    public int getAc_sex() {
        return ac_sex;
    }

    public void setAc_sex(int ac_sex) {
        this.ac_sex = ac_sex;
    }

    public String getAc_content() {
        return ac_content;
    }

    public void setAc_content(String ac_content) {
        this.ac_content = ac_content;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
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
