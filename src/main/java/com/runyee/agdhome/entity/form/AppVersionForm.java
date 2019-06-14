package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/8/7.
 */
public class AppVersionForm {
    private     String  id;//唯一标识
    private 	String	version;	 //版本
    private 	String	url;	 //下载地址
    private 	String	update_log;	 //更新内容
    private     String  size;    //应用大小
    private     int constraint_flg;       // 1.强制更新
    private 	int	flat_form;	 //应用平台  0.android,1.ios,2.wx,3.web

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpdate_log() {
        return update_log;
    }

    public void setUpdate_log(String update_log) {
        this.update_log = update_log;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
}
