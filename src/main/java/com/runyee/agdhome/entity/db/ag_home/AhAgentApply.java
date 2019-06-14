package com.runyee.agdhome.entity.db.ag_home;

import com.runyee.agdhome.entity.ex.FreebackInfoBean;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhAgentApply extends FreebackInfoBean{
    private 	String	aaa_id;	 //uuid
    private 	String	aaa_name;	 //联系名称
    private 	String	aaa_phone;	 //联系方式
    private 	String	aaa_content;	 //留言内容
    private 	String	aaa_addr;	 //地址
    private 	String	aaa_province;	 //省份
    private 	String	aaa_provinceid;	 //
    private 	String	aaa_city;	 //城市
    private 	String	aaa_cityid;	 //
    private 	String	aaa_area;	 //地区
    private 	String	aaa_areaid;	 //
    private 	int	del;	 //1删除
    private     Date create_date;	 //
    private 	Date	update_date;	 //


    public String getAaa_id() {
        return aaa_id;
    }

    public void setAaa_id(String aaa_id) {
        this.aaa_id = aaa_id;
    }

    public String getAaa_name() {
        return aaa_name;
    }

    public void setAaa_name(String aaa_name) {
        this.aaa_name = aaa_name;
    }

    public String getAaa_phone() {
        return aaa_phone;
    }

    public void setAaa_phone(String aaa_phone) {
        this.aaa_phone = aaa_phone;
    }

    public String getAaa_content() {
        return aaa_content;
    }

    public void setAaa_content(String aaa_content) {
        this.aaa_content = aaa_content;
    }

    public String getAaa_addr() {
        return aaa_addr;
    }

    public void setAaa_addr(String aaa_addr) {
        this.aaa_addr = aaa_addr;
    }

    public String getAaa_province() {
        return aaa_province;
    }

    public void setAaa_province(String aaa_province) {
        this.aaa_province = aaa_province;
    }

    public String getAaa_provinceid() {
        return aaa_provinceid;
    }

    public void setAaa_provinceid(String aaa_provinceid) {
        this.aaa_provinceid = aaa_provinceid;
    }

    public String getAaa_city() {
        return aaa_city;
    }

    public void setAaa_city(String aaa_city) {
        this.aaa_city = aaa_city;
    }

    public String getAaa_cityid() {
        return aaa_cityid;
    }

    public void setAaa_cityid(String aaa_cityid) {
        this.aaa_cityid = aaa_cityid;
    }

    public String getAaa_area() {
        return aaa_area;
    }

    public void setAaa_area(String aaa_area) {
        this.aaa_area = aaa_area;
    }

    public String getAaa_areaid() {
        return aaa_areaid;
    }

    public void setAaa_areaid(String aaa_areaid) {
        this.aaa_areaid = aaa_areaid;
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
