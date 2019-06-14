package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyUrgentPhone {
    private 	String	rup_id;	 //uuid
    private 	String	rup_urgent;	 //国家
    private 	String	rup_title;	 //标题
    private 	String	rup_phone;	 //紧急电话
    private     int rup_sync;//1.同步
    private 	int	rup_del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //


    public String getRup_id() {
        return rup_id;
    }

    public void setRup_id(String rup_id) {
        this.rup_id = rup_id;
    }

    public String getRup_urgent() {
        return rup_urgent;
    }

    public void setRup_urgent(String rup_urgent) {
        this.rup_urgent = rup_urgent;
    }

    public String getRup_title() {
        return rup_title;
    }

    public void setRup_title(String rup_title) {
        this.rup_title = rup_title;
    }

    public String getRup_phone() {
        return rup_phone;
    }

    public void setRup_phone(String rup_phone) {
        this.rup_phone = rup_phone;
    }

    public int getRup_sync() {
        return rup_sync;
    }

    public void setRup_sync(int rup_sync) {
        this.rup_sync = rup_sync;
    }

    public int getRup_del() {
        return rup_del;
    }

    public void setRup_del(int rup_del) {
        this.rup_del = rup_del;
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
