package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhNews {
    private 	String	an_id;	 //uuid
    private 	String	an_business;	 //类别
    private 	String	an_title;	 //新闻标题
    private 	String	an_content;	 //新闻内容
    private 	String	an_source;	 //来源
    private 	String	an_url;	 //静态资源地址
    private 	String	an_origin;	 //原网址
    private     int an_audit;//审核 0未审1已审
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //


    public String getAn_id() {
        return an_id;
    }

    public void setAn_id(String an_id) {
        this.an_id = an_id;
    }

    public String getAn_business() {
        return an_business;
    }

    public void setAn_business(String an_business) {
        this.an_business = an_business;
    }

    public String getAn_title() {
        return an_title;
    }

    public void setAn_title(String an_title) {
        this.an_title = an_title;
    }

    public String getAn_content() {
        return an_content;
    }

    public void setAn_content(String an_content) {
        this.an_content = an_content;
    }

    public String getAn_source() {
        return an_source;
    }

    public void setAn_source(String an_source) {
        this.an_source = an_source;
    }

    public String getAn_url() {
        return an_url;
    }

    public void setAn_url(String an_url) {
        this.an_url = an_url;
    }

    public String getAn_origin() {
        return an_origin;
    }

    public void setAn_origin(String an_origin) {
        this.an_origin = an_origin;
    }

    public int getAn_audit() {
        return an_audit;
    }

    public void setAn_audit(int an_audit) {
        this.an_audit = an_audit;
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
