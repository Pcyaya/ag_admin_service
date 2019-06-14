package com.runyee.agdhome.entity.db.ag_home;


import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhUserDepart {
    private 	String	aud_id;	 //uuid
    private 	String	aud_user;	 //人员
    private 	String	aud_depart;	 //部门
    private 	int	del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAud_id() {
        return aud_id;
    }

    public void setAud_id(String aud_id) {
        this.aud_id = aud_id;
    }

    public String getAud_user() {
        return aud_user;
    }

    public void setAud_user(String aud_user) {
        this.aud_user = aud_user;
    }

    public String getAud_depart() {
        return aud_depart;
    }

    public void setAud_depart(String aud_depart) {
        this.aud_depart = aud_depart;
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
