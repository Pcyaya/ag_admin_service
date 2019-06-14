package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyGuideValidHandle {
    private 	String	rgvh_id;	 //uuid
    private 	String	rgvh_valid;	 //认证id
    private 	String	rgvh_user;	 //处理人
    private 	int	rgvh_status;	 //0.验证中1.验证通过，2.验证失败 3.失效
    private 	String	rgvh_content;	 //验证失败原因
    private 	int	rgvh_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRgvh_id() {
        return rgvh_id;
    }

    public void setRgvh_id(String rgvh_id) {
        this.rgvh_id = rgvh_id;
    }

    public String getRgvh_valid() {
        return rgvh_valid;
    }

    public void setRgvh_valid(String rgvh_valid) {
        this.rgvh_valid = rgvh_valid;
    }

    public String getRgvh_user() {
        return rgvh_user;
    }

    public void setRgvh_user(String rgvh_user) {
        this.rgvh_user = rgvh_user;
    }

    public int getRgvh_status() {
        return rgvh_status;
    }

    public void setRgvh_status(int rgvh_status) {
        this.rgvh_status = rgvh_status;
    }

    public String getRgvh_content() {
        return rgvh_content;
    }

    public void setRgvh_content(String rgvh_content) {
        this.rgvh_content = rgvh_content;
    }

    public int getRgvh_del() {
        return rgvh_del;
    }

    public void setRgvh_del(int rgvh_del) {
        this.rgvh_del = rgvh_del;
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
