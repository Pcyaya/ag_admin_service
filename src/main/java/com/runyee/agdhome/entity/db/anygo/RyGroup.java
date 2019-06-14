package com.runyee.agdhome.entity.db.anygo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyGroup {
    private 	String	rg_id;	 //
    private 	String	rg_num;	 //团编码
    private 	String	rg_apply;	 //申请人
    private 	String	rg_name;	 //团名称
    private     String  rg_icon;    //团头像
    private     String  rg_init_name; //初始名称
    private     int rg_type;//0.普通团 1.旅行
    private 	String	rg_intell_spk;	 //音箱
    private     int rg_guide_limit;//导游限制数量
    private     int rg_limit;//游客数量限制
    private 	int	rg_status;	 //团状态 0.正常2.解散
    private     String rg_code;//面对面建团代码
    private     BigDecimal rg_lon;	 //  经度
    private 	BigDecimal	rg_lat;	 //  纬度
    private 	int	rg_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRg_id() {
        return rg_id;
    }

    public void setRg_id(String rg_id) {
        this.rg_id = rg_id;
    }

    public String getRg_num() {
        return rg_num;
    }

    public void setRg_num(String rg_num) {
        this.rg_num = rg_num;
    }

    public String getRg_apply() {
        return rg_apply;
    }

    public void setRg_apply(String rg_apply) {
        this.rg_apply = rg_apply;
    }

    public String getRg_name() {
        return rg_name;
    }

    public void setRg_name(String rg_name) {
        this.rg_name = rg_name;
    }

    public String getRg_icon() {
        return rg_icon;
    }

    public void setRg_icon(String rg_icon) {
        this.rg_icon = rg_icon;
    }

    public String getRg_init_name() {
        return rg_init_name;
    }

    public void setRg_init_name(String rg_init_name) {
        this.rg_init_name = rg_init_name;
    }

    public int getRg_type() {
        return rg_type;
    }

    public void setRg_type(int rg_type) {
        this.rg_type = rg_type;
    }

    public String getRg_intell_spk() {
        return rg_intell_spk;
    }

    public void setRg_intell_spk(String rg_intell_spk) {
        this.rg_intell_spk = rg_intell_spk;
    }

    public int getRg_guide_limit() {
        return rg_guide_limit;
    }

    public void setRg_guide_limit(int rg_guide_limit) {
        this.rg_guide_limit = rg_guide_limit;
    }

    public int getRg_limit() {
        return rg_limit;
    }

    public void setRg_limit(int rg_limit) {
        this.rg_limit = rg_limit;
    }

    public int getRg_status() {
        return rg_status;
    }

    public void setRg_status(int rg_status) {
        this.rg_status = rg_status;
    }

    public String getRg_code() {
        return rg_code;
    }

    public void setRg_code(String rg_code) {
        this.rg_code = rg_code;
    }

    public int getRg_del() {
        return rg_del;
    }

    public BigDecimal getRg_lon() {
        return rg_lon;
    }

    public void setRg_lon(BigDecimal rg_lon) {
        this.rg_lon = rg_lon;
    }

    public BigDecimal getRg_lat() {
        return rg_lat;
    }

    public void setRg_lat(BigDecimal rg_lat) {
        this.rg_lat = rg_lat;
    }

    public void setRg_del(int rg_del) {
        this.rg_del = rg_del;
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
