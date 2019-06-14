package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhOrder {
    private 	String	ao_id;	 //uuid
    private 	String	ao_name;	 //姓名
    private 	String	ao_phone;	 //电话
    private 	String	ao_addr;	 //
    private     String  ao_content;//内容
    private 	int	ao_handle;	 //1.已处理
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getAo_id() {
        return ao_id;
    }

    public void setAo_id(String ao_id) {
        this.ao_id = ao_id;
    }

    public String getAo_name() {
        return ao_name;
    }

    public void setAo_name(String ao_name) {
        this.ao_name = ao_name;
    }

    public String getAo_phone() {
        return ao_phone;
    }

    public void setAo_phone(String ao_phone) {
        this.ao_phone = ao_phone;
    }

    public String getAo_addr() {
        return ao_addr;
    }

    public void setAo_addr(String ao_addr) {
        this.ao_addr = ao_addr;
    }

    public String getAo_content() {
        return ao_content;
    }

    public void setAo_content(String ao_content) {
        this.ao_content = ao_content;
    }

    public int getAo_handle() {
        return ao_handle;
    }

    public void setAo_handle(int ao_handle) {
        this.ao_handle = ao_handle;
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
