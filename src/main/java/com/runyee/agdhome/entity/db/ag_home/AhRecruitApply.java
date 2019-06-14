package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhRecruitApply {
    private 	String	ara_id;	 //uuid
    private 	String	ara_recruit;	 //招聘信息
    private 	String	ara_name;	 //联系名称
    private 	String	ara_phone;	 //联系方式
    private 	String	ara_email;	 //邮箱
    private 	int	ara_sex;	 //0.男 1.女
    private 	String	ara_content;	 //留言内容
    private 	int	del;	 //1删除
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getAra_id() {
        return ara_id;
    }

    public void setAra_id(String ara_id) {
        this.ara_id = ara_id;
    }

    public String getAra_recruit() {
        return ara_recruit;
    }

    public void setAra_recruit(String ara_recruit) {
        this.ara_recruit = ara_recruit;
    }

    public String getAra_name() {
        return ara_name;
    }

    public void setAra_name(String ara_name) {
        this.ara_name = ara_name;
    }

    public String getAra_phone() {
        return ara_phone;
    }

    public void setAra_phone(String ara_phone) {
        this.ara_phone = ara_phone;
    }

    public String getAra_email() {
        return ara_email;
    }

    public void setAra_email(String ara_email) {
        this.ara_email = ara_email;
    }

    public int getAra_sex() {
        return ara_sex;
    }

    public void setAra_sex(int ara_sex) {
        this.ara_sex = ara_sex;
    }

    public String getAra_content() {
        return ara_content;
    }

    public void setAra_content(String ara_content) {
        this.ara_content = ara_content;
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
