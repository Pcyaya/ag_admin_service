package com.runyee.agdhome.entity.page;


import java.util.Date;
import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManRecruitApplyPage {
    private     String  id;//唯一标识
    private 	String	name;	 //联系名称
    private 	String	phone;	 //联系方式
    private 	String	email;	 //邮箱
    private 	int	sex;	 //0.男 1.女
    private 	String	content;	 //留言内容
    private     Date create_date;	 //
    private     int resume_flg;//是否有简历 1.有0.无
    private     String recruit_name;//职位
    private     List<RecruitApplyDetailPage> details;//详情

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getResume_flg() {
        return resume_flg;
    }

    public void setResume_flg(int resume_flg) {
        this.resume_flg = resume_flg;
    }

    public String getRecruit_name() {
        return recruit_name;
    }

    public void setRecruit_name(String recruit_name) {
        this.recruit_name = recruit_name;
    }

    public List<RecruitApplyDetailPage> getDetails() {
        return details;
    }

    public void setDetails(List<RecruitApplyDetailPage> details) {
        this.details = details;
    }
}
