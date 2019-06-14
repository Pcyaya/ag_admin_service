package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyIntellSpkBind {
    private 	String	risb_id;	 //
    private 	String	risb_guide;	 //导游
    private 	String	risb_intell_spk;	 //音箱
    private 	int	risb_state;	 //0.正常 1.失效
    private 	String	risb_name;//姓名
    private 	String	risb_alias;//称呼
    private 	String	risb_phone;//手机号
    private 	int	risb_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRisb_id() {
        return risb_id;
    }

    public void setRisb_id(String risb_id) {
        this.risb_id = risb_id;
    }

    public String getRisb_guide() {
        return risb_guide;
    }

    public void setRisb_guide(String risb_guide) {
        this.risb_guide = risb_guide;
    }

    public String getRisb_intell_spk() {
        return risb_intell_spk;
    }

    public void setRisb_intell_spk(String risb_intell_spk) {
        this.risb_intell_spk = risb_intell_spk;
    }

    public int getRisb_state() {
        return risb_state;
    }

    public void setRisb_state(int risb_state) {
        this.risb_state = risb_state;
    }

    public String getRisb_name() {
        return risb_name;
    }

    public void setRisb_name(String risb_name) {
        this.risb_name = risb_name;
    }

    public String getRisb_alias() {
        return risb_alias;
    }

    public void setRisb_alias(String risb_alias) {
        this.risb_alias = risb_alias;
    }

    public String getRisb_phone() {
        return risb_phone;
    }

    public void setRisb_phone(String risb_phone) {
        this.risb_phone = risb_phone;
    }

    public int getRisb_del() {
        return risb_del;
    }

    public void setRisb_del(int risb_del) {
        this.risb_del = risb_del;
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
