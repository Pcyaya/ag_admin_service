package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhVideo {
    private 	String	av_id;	 //uuid
    private 	int	av_business;	 //视频类型 0.产品介绍
    private 	String	av_title;	 //标题
    private 	String	av_content;	 //内容
    private 	String	av_url;	 //url
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getAv_id() {
        return av_id;
    }

    public void setAv_id(String av_id) {
        this.av_id = av_id;
    }

    public int getAv_business() {
        return av_business;
    }

    public void setAv_business(int av_business) {
        this.av_business = av_business;
    }

    public String getAv_title() {
        return av_title;
    }

    public void setAv_title(String av_title) {
        this.av_title = av_title;
    }

    public String getAv_content() {
        return av_content;
    }

    public void setAv_content(String av_content) {
        this.av_content = av_content;
    }

    public String getAv_url() {
        return av_url;
    }

    public void setAv_url(String av_url) {
        this.av_url = av_url;
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
