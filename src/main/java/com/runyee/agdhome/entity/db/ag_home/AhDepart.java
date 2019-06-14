package com.runyee.agdhome.entity.db.ag_home;


import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhDepart {
    private 	String	ad_id;	 //
    private 	String	ad_name;	 //部门名称
    private 	String	ad_pid;	 //父id
    private 	String	ad_icon;	 //
    private 	int	del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_pid() {
        return ad_pid;
    }

    public void setAd_pid(String ad_pid) {
        this.ad_pid = ad_pid;
    }

    public String getAd_icon() {
        return ad_icon;
    }

    public void setAd_icon(String ad_icon) {
        this.ad_icon = ad_icon;
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
