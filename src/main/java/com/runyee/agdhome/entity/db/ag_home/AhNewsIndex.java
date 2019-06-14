package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhNewsIndex {
    private 	String	ani_id;	 //uuid
    private 	String	ani_news;	 //新闻id
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //


    public String getAni_id() {
        return ani_id;
    }

    public void setAni_id(String ani_id) {
        this.ani_id = ani_id;
    }

    public String getAni_news() {
        return ani_news;
    }

    public void setAni_news(String ani_news) {
        this.ani_news = ani_news;
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
