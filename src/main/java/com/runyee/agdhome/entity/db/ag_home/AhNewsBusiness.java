package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhNewsBusiness {
    private 	String	anb_id;	 //uuid
    private 	String	anb_name;	 //类别名称
    private     int anb_sort;//排序
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getAnb_id() {
        return anb_id;
    }

    public void setAnb_id(String anb_id) {
        this.anb_id = anb_id;
    }

    public String getAnb_name() {
        return anb_name;
    }

    public void setAnb_name(String anb_name) {
        this.anb_name = anb_name;
    }

    public int getAnb_sort() {
        return anb_sort;
    }

    public void setAnb_sort(int anb_sort) {
        this.anb_sort = anb_sort;
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
