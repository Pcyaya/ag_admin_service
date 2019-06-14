package com.runyee.agdhome.entity.db.ag_home;

import com.runyee.agdhome.entity.ex.FreebackInfoBean;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhFreeback extends FreebackInfoBean {
    private 	String	af_id;	 //uuid
    private 	String	af_name;	 //联系名称
    private 	String	af_phone;	 //联系方式
    private 	String	af_email;	 //
    private 	String	af_title;	 //
    private 	String	af_content;	 //
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAf_id() {
        return af_id;
    }

    public void setAf_id(String af_id) {
        this.af_id = af_id;
    }

    public String getAf_name() {
        return af_name;
    }

    public void setAf_name(String af_name) {
        this.af_name = af_name;
    }

    public String getAf_phone() {
        return af_phone;
    }

    public void setAf_phone(String af_phone) {
        this.af_phone = af_phone;
    }

    public String getAf_email() {
        return af_email;
    }

    public void setAf_email(String af_email) {
        this.af_email = af_email;
    }

    public String getAf_title() {
        return af_title;
    }

    public void setAf_title(String af_title) {
        this.af_title = af_title;
    }

    public String getAf_content() {
        return af_content;
    }

    public void setAf_content(String af_content) {
        this.af_content = af_content;
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
