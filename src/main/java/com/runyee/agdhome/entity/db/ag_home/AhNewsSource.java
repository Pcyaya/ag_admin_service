package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhNewsSource {
    private 	String	ans_id;	 //uuid
    private 	String	ans_name;	 //名称
    private 	String	ans_url;	 //网址
    private 	int	ans_sort;	 //排序
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAns_id() {
        return ans_id;
    }

    public void setAns_id(String ans_id) {
        this.ans_id = ans_id;
    }

    public String getAns_name() {
        return ans_name;
    }

    public void setAns_name(String ans_name) {
        this.ans_name = ans_name;
    }

    public String getAns_url() {
        return ans_url;
    }

    public void setAns_url(String ans_url) {
        this.ans_url = ans_url;
    }

    public int getAns_sort() {
        return ans_sort;
    }

    public void setAns_sort(int ans_sort) {
        this.ans_sort = ans_sort;
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
