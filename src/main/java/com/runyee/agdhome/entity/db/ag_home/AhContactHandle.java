package com.runyee.agdhome.entity.db.ag_home;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class AhContactHandle  {
    private 	String	ach_id;	 //
    private 	String	ach_contact;	 //举报id
    private 	String	ach_user;	 //处理人
    private 	int	ach_status;	 //0处理中1已处理
    private 	String	ach_content;	 //备注
    private 	int	del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAch_id() {
        return ach_id;
    }

    public void setAch_id(String ach_id) {
        this.ach_id = ach_id;
    }

    public String getAch_contact() {
        return ach_contact;
    }

    public void setAch_contact(String ach_contact) {
        this.ach_contact = ach_contact;
    }

    public String getAch_user() {
        return ach_user;
    }

    public void setAch_user(String ach_user) {
        this.ach_user = ach_user;
    }

    public int getAch_status() {
        return ach_status;
    }

    public void setAch_status(int ach_status) {
        this.ach_status = ach_status;
    }

    public String getAch_content() {
        return ach_content;
    }

    public void setAch_content(String ach_content) {
        this.ach_content = ach_content;
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
