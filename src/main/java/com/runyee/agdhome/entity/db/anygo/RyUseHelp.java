package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/7/30.
 */
public class RyUseHelp {
    private 	String	ru_id;	 //uuid
    private 	String	ru_category;	 //类别
    private 	String	ru_title;	 //问题
    private 	String	ru_icon;	 //图标
    private     int ru_sync;//app同步
    private     int ru_sort;//排序
    private 	int	ru_del;	 //
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getRu_id() {
        return ru_id;
    }

    public void setRu_id(String ru_id) {
        this.ru_id = ru_id;
    }

    public String getRu_category() {
        return ru_category;
    }

    public void setRu_category(String ru_category) {
        this.ru_category = ru_category;
    }

    public String getRu_title() {
        return ru_title;
    }

    public void setRu_title(String ru_title) {
        this.ru_title = ru_title;
    }

    public String getRu_icon() {
        return ru_icon;
    }

    public void setRu_icon(String ru_icon) {
        this.ru_icon = ru_icon;
    }

    public int getRu_sync() {
        return ru_sync;
    }

    public void setRu_sync(int ru_sync) {
        this.ru_sync = ru_sync;
    }

    public int getRu_sort() {
        return ru_sort;
    }

    public void setRu_sort(int ru_sort) {
        this.ru_sort = ru_sort;
    }

    public int getRu_del() {
        return ru_del;
    }

    public void setRu_del(int ru_del) {
        this.ru_del = ru_del;
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
