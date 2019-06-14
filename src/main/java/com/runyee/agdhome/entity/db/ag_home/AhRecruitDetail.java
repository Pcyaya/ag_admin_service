package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhRecruitDetail {
    private 	String	ard_id;	 //uuid
    private     String  ard_recruit;//招聘信息
    private 	String	ard_title;	 //标题
    private 	String	ard_content;	 //内容
    private 	int	ard_sort;	 //排序
    private 	int	ard_type;	 //类型0.文本，1.图片，2音频，3.视频 4.文件
    private 	String	ard_url;	 //地址
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //


    public String getArd_id() {
        return ard_id;
    }

    public void setArd_id(String ard_id) {
        this.ard_id = ard_id;
    }

    public String getArd_recruit() {
        return ard_recruit;
    }

    public void setArd_recruit(String ard_recruit) {
        this.ard_recruit = ard_recruit;
    }

    public String getArd_title() {
        return ard_title;
    }

    public void setArd_title(String ard_title) {
        this.ard_title = ard_title;
    }

    public String getArd_content() {
        return ard_content;
    }

    public void setArd_content(String ard_content) {
        this.ard_content = ard_content;
    }

    public int getArd_sort() {
        return ard_sort;
    }

    public void setArd_sort(int ard_sort) {
        this.ard_sort = ard_sort;
    }

    public int getArd_type() {
        return ard_type;
    }

    public void setArd_type(int ard_type) {
        this.ard_type = ard_type;
    }

    public String getArd_url() {
        return ard_url;
    }

    public void setArd_url(String ard_url) {
        this.ard_url = ard_url;
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
