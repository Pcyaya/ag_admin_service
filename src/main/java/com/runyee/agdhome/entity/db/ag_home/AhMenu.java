package com.runyee.agdhome.entity.db.ag_home;


import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhMenu {
    private 	String	am_id;	 //uuid
    private 	String	am_name;	 //菜单名称
    private 	String	am_url;	 //路由
    private 	String	am_pid;	 //父id
    private     int  am_level;//菜单级别
    private     int  am_view;//1.所有人可见
    private 	String	am_icon;	 //图标
    private     int am_sort;//排序
    private 	int	del;	 //
    private     Date create_date;	 //
    private 	Date update_date;	 //


    public String getAm_id() {
        return am_id;
    }

    public void setAm_id(String am_id) {
        this.am_id = am_id;
    }

    public String getAm_name() {
        return am_name;
    }

    public void setAm_name(String am_name) {
        this.am_name = am_name;
    }

    public String getAm_url() {
        return am_url;
    }

    public void setAm_url(String am_url) {
        this.am_url = am_url;
    }

    public String getAm_pid() {
        return am_pid;
    }

    public void setAm_pid(String am_pid) {
        this.am_pid = am_pid;
    }

    public int getAm_level() {
        return am_level;
    }

    public void setAm_level(int am_level) {
        this.am_level = am_level;
    }

    public int getAm_view() {
        return am_view;
    }

    public void setAm_view(int am_view) {
        this.am_view = am_view;
    }

    public String getAm_icon() {
        return am_icon;
    }

    public void setAm_icon(String am_icon) {
        this.am_icon = am_icon;
    }

    public int getAm_sort() {
        return am_sort;
    }

    public void setAm_sort(int am_sort) {
        this.am_sort = am_sort;
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
