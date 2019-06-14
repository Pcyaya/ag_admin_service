package com.runyee.agdhome.entity.db.anygo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cheri on 2018/7/30.
 */
public class AppVersion {
    private 	String	id;	 //主键
    private 	Date	create_date;	 //创建日期
    private 	Date	update_date;	 //更新日期
    private 	String	new_version;	 //新版本号
    private 	String	apk_file_url;	 //下载地址
    private 	String	update_log;	 //更新内容
    private 	String	target_size;	 //apk大小
    private 	String	new_md5;	 //MD5
    private 	int	constraint_flg;	 //强制更新
    private 	int	flat_form;	 //应用平台  0.android,1.ios,2.wx,3.web
    private 	int	app_type;	 //1.历史版本，删除

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNew_version() {
        return new_version;
    }

    public void setNew_version(String new_version) {
        this.new_version = new_version;
    }

    public String getApk_file_url() {
        return apk_file_url;
    }

    public void setApk_file_url(String apk_file_url) {
        this.apk_file_url = apk_file_url;
    }

    public String getUpdate_log() {
        return update_log;
    }

    public void setUpdate_log(String update_log) {
        this.update_log = update_log;
    }

    public String getTarget_size() {
        return target_size;
    }

    public void setTarget_size(String target_size) {
        this.target_size = target_size;
    }

    public String getNew_md5() {
        return new_md5;
    }

    public void setNew_md5(String new_md5) {
        this.new_md5 = new_md5;
    }

    public int getConstraint_flg() {
        return constraint_flg;
    }

    public void setConstraint_flg(int constraint_flg) {
        this.constraint_flg = constraint_flg;
    }

    public int getFlat_form() {
        return flat_form;
    }

    public void setFlat_form(int flat_form) {
        this.flat_form = flat_form;
    }

    public int getApp_type() {
        return app_type;
    }

    public void setApp_type(int app_type) {
        this.app_type = app_type;
    }
}
