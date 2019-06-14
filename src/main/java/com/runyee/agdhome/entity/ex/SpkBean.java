package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RyIntelligentSpeaker;

import java.util.Date;


/**
 * Created by cheri on 2018/8/7.
 */
public class SpkBean extends RyIntelligentSpeaker {
    private String visitor;//绑定人id
    private String name;//昵称
    private String phone;//联系方式
    private int sex;//性别0男1女
    private Date age;//生日
    private int bind_flg;//1.绑定
    private Date bind_date;//绑定时间
    private String binddate;//绑定时间 数据映射使用
    private String bind_db;//绑定环境
    private String urgent_name;//紧急联系人
    private String urgent_phone;//紧急联系人 手机
    private String urgent_alias;//称呼联系人
    private String urgent_toalias;//称呼自己
    private String spk_version;//音箱版本号
    private String spk_manage;//音箱管理者
    private String spk_manager_name;//音箱管理者昵称
    private int manage_flg;//是否被管理 1.被管理


    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public int getBind_flg() {
        return bind_flg;
    }

    public void setBind_flg(int bind_flg) {
        this.bind_flg = bind_flg;
    }

    public String getBind_db() {
        return bind_db;
    }

    public void setBind_db(String bind_db) {
        this.bind_db = bind_db;
    }

    public String getUrgent_name() {
        return urgent_name;
    }

    public void setUrgent_name(String urgent_name) {
        this.urgent_name = urgent_name;
    }

    public String getUrgent_phone() {
        return urgent_phone;
    }

    public void setUrgent_phone(String urgent_phone) {
        this.urgent_phone = urgent_phone;
    }

    public String getUrgent_alias() {
        return urgent_alias;
    }

    public void setUrgent_alias(String urgent_alias) {
        this.urgent_alias = urgent_alias;
    }

    public String getUrgent_toalias() {
        return urgent_toalias;
    }

    public void setUrgent_toalias(String urgent_toalias) {
        this.urgent_toalias = urgent_toalias;
    }

    public Date getBind_date() {
        return bind_date;
    }

    public void setBind_date(Date bind_date) {
        this.bind_date = bind_date;
    }

    public String getBinddate() {
        return binddate;
    }

    public void setBinddate(String binddate) {
        this.binddate = binddate;
    }

    public String getSpk_version() {
        return spk_version;
    }

    public void setSpk_version(String spk_version) {
        this.spk_version = spk_version;
    }

    public String getSpk_manage() {
        return spk_manage;
    }

    public void setSpk_manage(String spk_manage) {
        this.spk_manage = spk_manage;
    }

    public int getManage_flg() {
        return manage_flg;
    }

    public void setManage_flg(int manage_flg) {
        this.manage_flg = manage_flg;
    }

    public String getSpk_manager_name() {
        return spk_manager_name;
    }

    public void setSpk_manager_name(String spk_manager_name) {
        this.spk_manager_name = spk_manager_name;
    }
}
