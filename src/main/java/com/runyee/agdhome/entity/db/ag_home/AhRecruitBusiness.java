package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhRecruitBusiness {
    private 	String	arb_id;	 //uuid
    private 	String	arb_name;	 //类别名称
    private 	int	arb_sort;	 //排序
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getArb_id() {
        return arb_id;
    }

    public void setArb_id(String arb_id) {
        this.arb_id = arb_id;
    }

    public String getArb_name() {
        return arb_name;
    }

    public void setArb_name(String arb_name) {
        this.arb_name = arb_name;
    }

    public int getArb_sort() {
        return arb_sort;
    }

    public void setArb_sort(int arb_sort) {
        this.arb_sort = arb_sort;
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
