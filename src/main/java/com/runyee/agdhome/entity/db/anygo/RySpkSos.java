package com.runyee.agdhome.entity.db.anygo;


import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RySpkSos {
    private 	String	rss_id;	 //
    private 	String	rss_bind;	 //当前绑定id
    private 	String	rss_speak_num;	 //音箱编码
    private 	String	rss_visitor;	 //当前绑定导游
    private 	String	rss_group;	 //当前团
    private     BigDecimal rss_lon;	 //经度
    private 	BigDecimal	rss_lat;	 //纬度
    private 	String	rss_name;//姓名
    private 	String	rss_alias;//称呼
    private     String  rss_toalias;//称呼自己
    private 	String	rss_phone;//手机号
    private 	int	rss_del;	 //1.删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //


    public String getRss_id() {
        return rss_id;
    }

    public void setRss_id(String rss_id) {
        this.rss_id = rss_id;
    }

    public String getRss_bind() {
        return rss_bind;
    }

    public void setRss_bind(String rss_bind) {
        this.rss_bind = rss_bind;
    }

    public String getRss_speak_num() {
        return rss_speak_num;
    }

    public void setRss_speak_num(String rss_speak_num) {
        this.rss_speak_num = rss_speak_num;
    }

    public String getRss_visitor() {
        return rss_visitor;
    }

    public void setRss_visitor(String rss_visitor) {
        this.rss_visitor = rss_visitor;
    }

    public String getRss_group() {
        return rss_group;
    }

    public void setRss_group(String rss_group) {
        this.rss_group = rss_group;
    }

    public BigDecimal getRss_lon() {
        return rss_lon;
    }

    public void setRss_lon(BigDecimal rss_lon) {
        this.rss_lon = rss_lon;
    }

    public BigDecimal getRss_lat() {
        return rss_lat;
    }

    public void setRss_lat(BigDecimal rss_lat) {
        this.rss_lat = rss_lat;
    }

    public String getRss_name() {
        return rss_name;
    }

    public void setRss_name(String rss_name) {
        this.rss_name = rss_name;
    }

    public String getRss_alias() {
        return rss_alias;
    }

    public void setRss_alias(String rss_alias) {
        this.rss_alias = rss_alias;
    }

    public String getRss_toalias() {
        return rss_toalias;
    }

    public void setRss_toalias(String rss_toalias) {
        this.rss_toalias = rss_toalias;
    }

    public String getRss_phone() {
        return rss_phone;
    }

    public void setRss_phone(String rss_phone) {
        this.rss_phone = rss_phone;
    }

    public int getRss_del() {
        return rss_del;
    }

    public void setRss_del(int rss_del) {
        this.rss_del = rss_del;
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
