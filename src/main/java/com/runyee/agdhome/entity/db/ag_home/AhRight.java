package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhRight {
    private 	String	ar_id;	 //uuid
    private 	String	ar_name;	 //权限名称
    private 	String	ar_menu;	 //关联菜单
    private 	int	ar_opt;	 //0.菜单,1.操作,2.按钮
    private 	int	del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAr_id() {
        return ar_id;
    }

    public void setAr_id(String ar_id) {
        this.ar_id = ar_id;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    public String getAr_menu() {
        return ar_menu;
    }

    public void setAr_menu(String ar_menu) {
        this.ar_menu = ar_menu;
    }

    public int getAr_opt() {
        return ar_opt;
    }

    public void setAr_opt(int ar_opt) {
        this.ar_opt = ar_opt;
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
