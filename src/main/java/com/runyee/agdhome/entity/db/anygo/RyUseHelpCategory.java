package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/7/30.
 */
public class RyUseHelpCategory {
    private 	String	ruc_id;	 //uuid
    private 	String	ruc_name;	 //
    private 	String	ruc_icon;	 //图标
    private     int ruc_sync;//app同步
    private     int ruc_sort;//排序
    private 	int	ruc_del;	 //
    private 	Date create_date;	 //
    private     Date update_date;	 //

    public String getRuc_id() {
        return ruc_id;
    }

    public void setRuc_id(String ruc_id) {
        this.ruc_id = ruc_id;
    }

    public String getRuc_name() {
        return ruc_name;
    }

    public void setRuc_name(String ruc_name) {
        this.ruc_name = ruc_name;
    }

    public String getRuc_icon() {
        return ruc_icon;
    }

    public void setRuc_icon(String ruc_icon) {
        this.ruc_icon = ruc_icon;
    }

    public int getRuc_sync() {
        return ruc_sync;
    }

    public void setRuc_sync(int ruc_sync) {
        this.ruc_sync = ruc_sync;
    }

    public int getRuc_sort() {
        return ruc_sort;
    }

    public void setRuc_sort(int ruc_sort) {
        this.ruc_sort = ruc_sort;
    }

    public int getRuc_del() {
        return ruc_del;
    }

    public void setRuc_del(int ruc_del) {
        this.ruc_del = ruc_del;
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
