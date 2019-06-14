package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RySurvival {
    private 	String	rs_id;	 //uuid
    private 	String	rs_title;	 //标题
    private 	String	rs_content;	 //内容
    private     int  rs_sync;//1.同步
    private 	int	rs_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //


    public String getRs_id() {
        return rs_id;
    }

    public void setRs_id(String rs_id) {
        this.rs_id = rs_id;
    }

    public String getRs_title() {
        return rs_title;
    }

    public void setRs_title(String rs_title) {
        this.rs_title = rs_title;
    }

    public String getRs_content() {
        return rs_content;
    }

    public void setRs_content(String rs_content) {
        this.rs_content = rs_content;
    }

    public int getRs_sync() {
        return rs_sync;
    }

    public void setRs_sync(int rs_sync) {
        this.rs_sync = rs_sync;
    }

    public int getRs_del() {
        return rs_del;
    }

    public void setRs_del(int rs_del) {
        this.rs_del = rs_del;
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
