package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhNewsDetail {
    private 	String	and_id;	 //uuid
    private 	String	and_news;	 //新闻id
    private 	String	and_title;	 //新闻标题
    private 	String	and_content;	 //新闻内容
    private     int and_type;//类型0.文本，1.图片，2音频，3.视频
    private     String and_url;//地址
    private     String and_align;//对齐方式
    private     int and_sort;//排序
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAnd_id() {
        return and_id;
    }

    public void setAnd_id(String and_id) {
        this.and_id = and_id;
    }

    public String getAnd_news() {
        return and_news;
    }

    public void setAnd_news(String and_news) {
        this.and_news = and_news;
    }

    public String getAnd_title() {
        return and_title;
    }

    public void setAnd_title(String and_title) {
        this.and_title = and_title;
    }

    public String getAnd_content() {
        return and_content;
    }

    public void setAnd_content(String and_content) {
        this.and_content = and_content;
    }

    public int getAnd_type() {
        return and_type;
    }

    public void setAnd_type(int and_type) {
        this.and_type = and_type;
    }

    public String getAnd_url() {
        return and_url;
    }

    public void setAnd_url(String and_url) {
        this.and_url = and_url;
    }

    public String getAnd_align() {
        return and_align;
    }

    public void setAnd_align(String and_align) {
        this.and_align = and_align;
    }

    public int getAnd_sort() {
        return and_sort;
    }

    public void setAnd_sort(int and_sort) {
        this.and_sort = and_sort;
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
