package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

public class RyTravelNote {

    private 	String	rtn_id;	 //
    private 	String	rtn_num;	 //编码
    private     String  rtn_title;//景点标记
    private 	String	rtn_apply;	 //发起人
    private 	int	rtn_authority;	 //权限 0公开1侗友2私有
    private 	String	rtn_content;	 //内容
    private 	int	rtn_source;	 //0.正常 1.转发
    private     int rtn_narrate;//讲解标志1.讲解
    private     int rtn_position;//位置权限 1.显示
    private 	String	rtn_poi;//poi id
    private 	String	rtn_root;	 //根游记id
    private 	int	rtn_del;	 //1删除
    private     Date create_date;	 //
    private 	Date	update_date;	 //

    public String getRtn_id() {
        return rtn_id;
    }

    public void setRtn_id(String rtn_id) {
        this.rtn_id = rtn_id;
    }

    public String getRtn_num() {
        return rtn_num;
    }

    public void setRtn_num(String rtn_num) {
        this.rtn_num = rtn_num;
    }

    public String getRtn_title() {
        return rtn_title;
    }

    public void setRtn_title(String rtn_title) {
        this.rtn_title = rtn_title;
    }

    public String getRtn_apply() {
        return rtn_apply;
    }

    public void setRtn_apply(String rtn_apply) {
        this.rtn_apply = rtn_apply;
    }

    public int getRtn_authority() {
        return rtn_authority;
    }

    public void setRtn_authority(int rtn_authority) {
        this.rtn_authority = rtn_authority;
    }

    public String getRtn_content() {
        return rtn_content;
    }

    public void setRtn_content(String rtn_content) {
        this.rtn_content = rtn_content;
    }

    public int getRtn_source() {
        return rtn_source;
    }

    public void setRtn_source(int rtn_source) {
        this.rtn_source = rtn_source;
    }

    public int getRtn_narrate() {
        return rtn_narrate;
    }

    public void setRtn_narrate(int rtn_narrate) {
        this.rtn_narrate = rtn_narrate;
    }

    public int getRtn_position() {
        return rtn_position;
    }

    public void setRtn_position(int rtn_position) {
        this.rtn_position = rtn_position;
    }

    public String getRtn_poi() {
        return rtn_poi;
    }

    public void setRtn_poi(String rtn_poi) {
        this.rtn_poi = rtn_poi;
    }

    public String getRtn_root() {
        return rtn_root;
    }

    public void setRtn_root(String rtn_root) {
        this.rtn_root = rtn_root;
    }

    public int getRtn_del() {
        return rtn_del;
    }

    public void setRtn_del(int rtn_del) {
        this.rtn_del = rtn_del;
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
