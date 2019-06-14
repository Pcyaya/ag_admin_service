package com.runyee.agdhome.entity.db.ag_home;


import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhDepartRight {
    private 	String	adr_id;	 //uuid
    private 	String	adr_depart;	 //部门
    private 	String	adr_right;	 //权限
    private 	int	del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAdr_id() {
        return adr_id;
    }

    public void setAdr_id(String adr_id) {
        this.adr_id = adr_id;
    }

    public String getAdr_depart() {
        return adr_depart;
    }

    public void setAdr_depart(String adr_depart) {
        this.adr_depart = adr_depart;
    }

    public String getAdr_right() {
        return adr_right;
    }

    public void setAdr_right(String adr_right) {
        this.adr_right = adr_right;
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
