package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCommonValGrp {
    private 	String	rcvg_id;	 //uuid
    private 	String	rcvg_key;	 //唯一标识
    private 	String	rcvg_name;	 //组名称
    private 	String	rcvg_desc;	 //描述
    private     int     rcvg_del;    //1.删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getRcvg_id() {
        return rcvg_id;
    }

    public void setRcvg_id(String rcvg_id) {
        this.rcvg_id = rcvg_id;
    }

    public String getRcvg_key() {
        return rcvg_key;
    }

    public void setRcvg_key(String rcvg_key) {
        this.rcvg_key = rcvg_key;
    }

    public String getRcvg_name() {
        return rcvg_name;
    }

    public void setRcvg_name(String rcvg_name) {
        this.rcvg_name = rcvg_name;
    }

    public String getRcvg_desc() {
        return rcvg_desc;
    }

    public void setRcvg_desc(String rcvg_desc) {
        this.rcvg_desc = rcvg_desc;
    }

    public int getRcvg_del() {
        return rcvg_del;
    }

    public void setRcvg_del(int rcvg_del) {
        this.rcvg_del = rcvg_del;
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
