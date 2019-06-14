package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCoin {
    private 	String	rc_id;	 //uuid
    private 	String	rc_visitor;	 //游客id
    private 	int	rc_number;	 //数量
    private 	int	rc_del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRc_id() {
        return rc_id;
    }

    public void setRc_id(String rc_id) {
        this.rc_id = rc_id;
    }

    public String getRc_visitor() {
        return rc_visitor;
    }

    public void setRc_visitor(String rc_visitor) {
        this.rc_visitor = rc_visitor;
    }

    public int getRc_number() {
        return rc_number;
    }

    public void setRc_number(int rc_number) {
        this.rc_number = rc_number;
    }

    public int getRc_del() {
        return rc_del;
    }

    public void setRc_del(int rc_del) {
        this.rc_del = rc_del;
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
