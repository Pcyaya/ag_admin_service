package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhUser {
    private 	String	au_id;	 //uuid
    private     String  au_no;  //工号
    private 	String	au_name;	 //名称
    private 	String	au_phone;	 //联系方式
    private 	String	au_pwd;	 //密码
    private     String  au_position;//职位
    private 	int	au_level;	 //级别
    private 	String	au_email;	 //邮箱
    private 	int	au_sex;	 //0.男 1.女
    private 	String	au_descript;	 //描述
    private 	int	del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAu_id() {
        return au_id;
    }

    public void setAu_id(String au_id) {
        this.au_id = au_id;
    }

    public String getAu_no() {
        return au_no;
    }

    public void setAu_no(String au_no) {
        this.au_no = au_no;
    }

    public String getAu_name() {
        return au_name;
    }

    public void setAu_name(String au_name) {
        this.au_name = au_name;
    }

    public String getAu_phone() {
        return au_phone;
    }

    public void setAu_phone(String au_phone) {
        this.au_phone = au_phone;
    }

    public String getAu_pwd() {
        return au_pwd;
    }

    public void setAu_pwd(String au_pwd) {
        this.au_pwd = au_pwd;
    }

    public String getAu_position() {
        return au_position;
    }

    public void setAu_position(String au_position) {
        this.au_position = au_position;
    }

    public int getAu_level() {
        return au_level;
    }

    public void setAu_level(int au_level) {
        this.au_level = au_level;
    }

    public String getAu_email() {
        return au_email;
    }

    public void setAu_email(String au_email) {
        this.au_email = au_email;
    }

    public int getAu_sex() {
        return au_sex;
    }

    public void setAu_sex(int au_sex) {
        this.au_sex = au_sex;
    }

    public String getAu_descript() {
        return au_descript;
    }

    public void setAu_descript(String au_descript) {
        this.au_descript = au_descript;
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
