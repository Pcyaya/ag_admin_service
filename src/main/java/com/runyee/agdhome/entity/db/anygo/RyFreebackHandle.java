package com.runyee.agdhome.entity.db.anygo;

import com.runyee.agdhome.entity.db.ag_home.AhFreeback;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyFreebackHandle extends AhFreeback {
    private 	String	rfh_id;	 //uuid
    private 	String	rfh_freeback;	 //反馈id
    private 	String	rfh_user;	 //处理人
    private 	int	rfh_status;	 //0处理中1已处理
    private 	String	rfh_content;	 //备注
    private 	int	rfh_del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //


    public String getRfh_id() {
        return rfh_id;
    }

    public void setRfh_id(String rfh_id) {
        this.rfh_id = rfh_id;
    }

    public String getRfh_freeback() {
        return rfh_freeback;
    }

    public void setRfh_freeback(String rfh_freeback) {
        this.rfh_freeback = rfh_freeback;
    }

    public String getRfh_user() {
        return rfh_user;
    }

    public void setRfh_user(String rfh_user) {
        this.rfh_user = rfh_user;
    }

    public int getRfh_status() {
        return rfh_status;
    }

    public void setRfh_status(int rfh_status) {
        this.rfh_status = rfh_status;
    }

    public String getRfh_content() {
        return rfh_content;
    }

    public void setRfh_content(String rfh_content) {
        this.rfh_content = rfh_content;
    }

    public int getRfh_del() {
        return rfh_del;
    }

    public void setRfh_del(int rfh_del) {
        this.rfh_del = rfh_del;
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
