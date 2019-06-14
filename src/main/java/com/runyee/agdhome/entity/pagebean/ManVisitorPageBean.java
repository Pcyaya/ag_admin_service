package com.runyee.agdhome.entity.pagebean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
@JsonIgnoreProperties(value = {"name","phone","sex","source","reg_date","bind_flg","valid_schedule","freeze"})
public class ManVisitorPageBean extends PageBean{
    private String name;//用户昵称
    private String phone;//账号
    private int sex;//-1.全部0.男1.女
    private int source;//-1全部  0.android,1.ios,2.wx,3.web
    private String reg_date;//注册时间
    private int bind_flg;//-1.全部0.未绑定1.已绑定
    private String bind_num;//绑定设备编码
    private int valid_schedule;//认证状态 -2.未认证 -1.认证中 0.验证中 1.认证通过 2.认证失败 3.认证失效
    private int freeze;//-1.全部0.未冻结1.已冻结

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

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public int getBind_flg() {
        return bind_flg;
    }

    public void setBind_flg(int bind_flg) {
        this.bind_flg = bind_flg;
    }

    public String getBind_num() {
        return bind_num;
    }

    public void setBind_num(String bind_num) {
        this.bind_num = bind_num;
    }

    public int getValid_schedule() {
        return valid_schedule;
    }

    public void setValid_schedule(int valid_schedule) {
        this.valid_schedule = valid_schedule;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }
}
