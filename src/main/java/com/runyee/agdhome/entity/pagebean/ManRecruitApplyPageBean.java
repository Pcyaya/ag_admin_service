package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManRecruitApplyPageBean extends PageBean{
    private String id;//招聘id
    private String name;//姓名
    private String phone;//手机号码
    private String email;//邮箱
    private int sex;//性别
    private String date;//申请时间
    private int resume_flg;//是否有简历


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResume_flg() {
        return resume_flg;
    }

    public void setResume_flg(int resume_flg) {
        this.resume_flg = resume_flg;
    }

}
