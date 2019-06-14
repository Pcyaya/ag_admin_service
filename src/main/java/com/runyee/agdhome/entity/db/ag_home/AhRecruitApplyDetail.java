package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhRecruitApplyDetail {
    private 	String	arad_id;	 //uuid
    private 	String	arad_apply;	 //申请id
    private 	String	arad_title;	 //标题
    private 	String	arad_content;	 //内容
    private 	int	arad_sort;	 //排序
    private 	int	arad_type;	 //类型0.文本，1.图片，2音频，3.视频 4.文件
    private 	String	arad_url;	 //地址
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getArad_id() {
        return arad_id;
    }

    public void setArad_id(String arad_id) {
        this.arad_id = arad_id;
    }

    public String getArad_apply() {
        return arad_apply;
    }

    public void setArad_apply(String arad_apply) {
        this.arad_apply = arad_apply;
    }

    public String getArad_title() {
        return arad_title;
    }

    public void setArad_title(String arad_title) {
        this.arad_title = arad_title;
    }

    public String getArad_content() {
        return arad_content;
    }

    public void setArad_content(String arad_content) {
        this.arad_content = arad_content;
    }

    public int getArad_sort() {
        return arad_sort;
    }

    public void setArad_sort(int arad_sort) {
        this.arad_sort = arad_sort;
    }

    public int getArad_type() {
        return arad_type;
    }

    public void setArad_type(int arad_type) {
        this.arad_type = arad_type;
    }

    public String getArad_url() {
        return arad_url;
    }

    public void setArad_url(String arad_url) {
        this.arad_url = arad_url;
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
