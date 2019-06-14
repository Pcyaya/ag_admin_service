package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhUserToken {
    private 	String	aut_id;	 //uuid
    private 	String	aut_user;	 //管理人
    private 	String	aut_token;	 //
    private 	Date	aut_expire;	 //失效日期
    private 	int	aut_source;	 //登陆来源0.android,1.ios,2.wx,3web）
    private 	int	aut_invalid;	 //1.失效
    private 	String	aut_acs_uri;	 //最后访问接口
    private 	Date	aut_acs_date;	 //最后访问时间
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAut_id() {
        return aut_id;
    }

    public void setAut_id(String aut_id) {
        this.aut_id = aut_id;
    }

    public String getAut_user() {
        return aut_user;
    }

    public void setAut_user(String aut_user) {
        this.aut_user = aut_user;
    }

    public String getAut_token() {
        return aut_token;
    }

    public void setAut_token(String aut_token) {
        this.aut_token = aut_token;
    }

    public Date getAut_expire() {
        return aut_expire;
    }

    public void setAut_expire(Date aut_expire) {
        this.aut_expire = aut_expire;
    }

    public int getAut_source() {
        return aut_source;
    }

    public void setAut_source(int aut_source) {
        this.aut_source = aut_source;
    }

    public int getAut_invalid() {
        return aut_invalid;
    }

    public void setAut_invalid(int aut_invalid) {
        this.aut_invalid = aut_invalid;
    }

    public String getAut_acs_uri() {
        return aut_acs_uri;
    }

    public void setAut_acs_uri(String aut_acs_uri) {
        this.aut_acs_uri = aut_acs_uri;
    }

    public Date getAut_acs_date() {
        return aut_acs_date;
    }

    public void setAut_acs_date(Date aut_acs_date) {
        this.aut_acs_date = aut_acs_date;
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
