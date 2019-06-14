package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhRecruit {
    private 	String	ar_id;	 //uuid
    private     String  ar_business;//类别
    private 	String	ar_name;	 //职位名称
    private 	String	ar_addr;	 //工作地点
    private 	int	ar_exper;	 //工作经验
    private     int  ar_exper_end;//工作经验
    private 	String	ar_edu;	 //学历
    private 	int	ar_sort;	 //排序
    private     int ar_audit;//审核
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getAr_id() {
        return ar_id;
    }

    public void setAr_id(String ar_id) {
        this.ar_id = ar_id;
    }

    public String getAr_business() {
        return ar_business;
    }

    public void setAr_business(String ar_business) {
        this.ar_business = ar_business;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    public String getAr_addr() {
        return ar_addr;
    }

    public void setAr_addr(String ar_addr) {
        this.ar_addr = ar_addr;
    }

    public int getAr_exper() {
        return ar_exper;
    }

    public void setAr_exper(int ar_exper) {
        this.ar_exper = ar_exper;
    }

    public int getAr_exper_end() {
        return ar_exper_end;
    }

    public void setAr_exper_end(int ar_exper_end) {
        this.ar_exper_end = ar_exper_end;
    }

    public String getAr_edu() {
        return ar_edu;
    }

    public void setAr_edu(String ar_edu) {
        this.ar_edu = ar_edu;
    }

    public int getAr_sort() {
        return ar_sort;
    }

    public void setAr_sort(int ar_sort) {
        this.ar_sort = ar_sort;
    }

    public int getAr_audit() {
        return ar_audit;
    }

    public void setAr_audit(int ar_audit) {
        this.ar_audit = ar_audit;
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
