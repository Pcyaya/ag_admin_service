package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyReportHandle {
    private 	String	rrh_id;	 //
    private 	String	rrh_report;	 //举报id
    private 	String	rrh_user;	 //处理人
    private 	int	rrh_status;	 //0处理中1已处理
    private 	String	rrh_content;	 //备注
    private 	int	rrh_val;	 //系统通用值
    private 	int	rrh_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRrh_id() {
        return rrh_id;
    }

    public void setRrh_id(String rrh_id) {
        this.rrh_id = rrh_id;
    }

    public String getRrh_report() {
        return rrh_report;
    }

    public void setRrh_report(String rrh_report) {
        this.rrh_report = rrh_report;
    }

    public String getRrh_user() {
        return rrh_user;
    }

    public void setRrh_user(String rrh_user) {
        this.rrh_user = rrh_user;
    }

    public int getRrh_status() {
        return rrh_status;
    }

    public void setRrh_status(int rrh_status) {
        this.rrh_status = rrh_status;
    }

    public String getRrh_content() {
        return rrh_content;
    }

    public void setRrh_content(String rrh_content) {
        this.rrh_content = rrh_content;
    }

    public int getRrh_val() {
        return rrh_val;
    }

    public void setRrh_val(int rrh_val) {
        this.rrh_val = rrh_val;
    }

    public int getRrh_del() {
        return rrh_del;
    }

    public void setRrh_del(int rrh_del) {
        this.rrh_del = rrh_del;
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
