package com.runyee.agdhome.entity.db.anygo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyIntelligentSpeaker {
    private 	String	ris_id;	 //
    private 	String	ris_num;	 //设备编码
    private 	String	ris_card;//simbank卡编码 设备序列号
    private 	int	ris_test;	 //1.测试
    private     BigDecimal ris_lon;	 //经度
    private 	BigDecimal ris_lat;	 //纬度
    private 	int	ris_online;	 //1.在线
    private 	int	ris_active;	 //1.激活
    private     String ris_bind;//绑定码
    private     String ris_wifi_name;//wifi名称
    private     String ris_wifi_pwd;//wifi密码
    private     int ris_power;//电量
    private 	int	ris_del;	 //1.删除
    private 	int	create_by;	 //
    private 	Date	create_date;	 //
    private 	int	update_by;	 //
    private Date update_date;	 //

    public String getRis_id() {
        return ris_id;
    }

    public void setRis_id(String ris_id) {
        this.ris_id = ris_id;
    }

    public String getRis_num() {
        return ris_num;
    }

    public void setRis_num(String ris_num) {
        this.ris_num = ris_num;
    }

    public String getRis_card() {
        return ris_card;
    }

    public void setRis_card(String ris_card) {
        this.ris_card = ris_card;
    }

    public int getRis_test() {
        return ris_test;
    }

    public void setRis_test(int ris_test) {
        this.ris_test = ris_test;
    }

    public BigDecimal getRis_lon() {
        return ris_lon;
    }

    public void setRis_lon(BigDecimal ris_lon) {
        this.ris_lon = ris_lon;
    }

    public BigDecimal getRis_lat() {
        return ris_lat;
    }

    public void setRis_lat(BigDecimal ris_lat) {
        this.ris_lat = ris_lat;
    }

    public int getRis_online() {
        return ris_online;
    }

    public void setRis_online(int ris_online) {
        this.ris_online = ris_online;
    }

    public int getRis_active() {
        return ris_active;
    }

    public void setRis_active(int ris_active) {
        this.ris_active = ris_active;
    }

    public String getRis_bind() {
        return ris_bind;
    }

    public void setRis_bind(String ris_bind) {
        this.ris_bind = ris_bind;
    }

    public String getRis_wifi_name() {
        return ris_wifi_name;
    }

    public void setRis_wifi_name(String ris_wifi_name) {
        this.ris_wifi_name = ris_wifi_name;
    }

    public String getRis_wifi_pwd() {
        return ris_wifi_pwd;
    }

    public void setRis_wifi_pwd(String ris_wifi_pwd) {
        this.ris_wifi_pwd = ris_wifi_pwd;
    }

    public int getRis_power() {
        return ris_power;
    }

    public int getRis_del() {
        return ris_del;
    }

    public void setRis_power(int ris_power) {
        this.ris_power = ris_power;
    }

    public void setRis_del(int ris_del) {
        this.ris_del = ris_del;
    }

    public int getCreate_by() {
        return create_by;
    }

    public void setCreate_by(int create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
