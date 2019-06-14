package com.runyee.agdhome.entity.db.anygo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cheri on 2018/7/30.
 */
public class RyVisitor {
    private 	String	rv_id;	 //
    private     String rv_num;//游客编码
    private 	String	rv_name;	 //账户
    private 	String	rv_passwd;	 //密码
    private 	String	rv_phone;	 //手机号
    private 	int	rv_test;	 //1.测试
    private 	int	rv_source;	 //来源 0.and 1 ios 2 wx
    private     String rv_plat;//手机系统平台
    private     String rv_icon;//头像
    private 	int	rv_sex;	 // 性别 0.男 1.女
    private 	Date	rv_age;	 // 生日
    private     BigDecimal rv_lon;	 //  经度
    private 	BigDecimal	rv_lat;	 //  纬度
    private 	int	rv_online;	 // 在线状态 0.离线 1.侗游在线 2.wifi 3.4G
    private     String rv_code;//原手机收到的code
    private     String rv_invite;//邀请人编码
    private     String rv_spk_num;//绑定设备编码
    private     int rv_freeze;//冻结标志
    private 	int	rv_del;	 //1.删除
    private 	int	create_by;	 //
    private 	Date	create_date;	 //
    private 	int	update_by;	 //
    private 	Date	update_date;	 //


    public String getRv_id() {
        return rv_id;
    }

    public void setRv_id(String rv_id) {
        this.rv_id = rv_id;
    }

    public String getRv_num() {
        return rv_num;
    }

    public void setRv_num(String rv_num) {
        this.rv_num = rv_num;
    }

    public String getRv_name() {
        return rv_name;
    }

    public void setRv_name(String rv_name) {
        this.rv_name = rv_name;
    }

    public String getRv_passwd() {
        return rv_passwd;
    }

    public void setRv_passwd(String rv_passwd) {
        this.rv_passwd = rv_passwd;
    }

    public String getRv_phone() {
        return rv_phone;
    }

    public void setRv_phone(String rv_phone) {
        this.rv_phone = rv_phone;
    }

    public int getRv_test() {
        return rv_test;
    }

    public void setRv_test(int rv_test) {
        this.rv_test = rv_test;
    }

    public int getRv_source() {
        return rv_source;
    }

    public void setRv_source(int rv_source) {
        this.rv_source = rv_source;
    }

    public String getRv_plat() {
        return rv_plat;
    }

    public void setRv_plat(String rv_plat) {
        this.rv_plat = rv_plat;
    }

    public String getRv_icon() {
        return rv_icon;
    }

    public void setRv_icon(String rv_icon) {
        this.rv_icon = rv_icon;
    }

    public int getRv_sex() {
        return rv_sex;
    }

    public void setRv_sex(int rv_sex) {
        this.rv_sex = rv_sex;
    }

    public Date getRv_age() {
        return rv_age;
    }

    public void setRv_age(Date rv_age) {
        this.rv_age = rv_age;
    }

    public BigDecimal getRv_lon() {
        return rv_lon;
    }

    public void setRv_lon(BigDecimal rv_lon) {
        this.rv_lon = rv_lon;
    }

    public BigDecimal getRv_lat() {
        return rv_lat;
    }

    public void setRv_lat(BigDecimal rv_lat) {
        this.rv_lat = rv_lat;
    }

    public int getRv_online() {
        return rv_online;
    }

    public void setRv_online(int rv_online) {
        this.rv_online = rv_online;
    }

    public String getRv_code() {
        return rv_code;
    }

    public void setRv_code(String rv_code) {
        this.rv_code = rv_code;
    }

    public String getRv_invite() {
        return rv_invite;
    }

    public void setRv_invite(String rv_invite) {
        this.rv_invite = rv_invite;
    }

    public String getRv_spk_num() {
        return rv_spk_num;
    }

    public void setRv_spk_num(String rv_spk_num) {
        this.rv_spk_num = rv_spk_num;
    }

    public int getRv_freeze() {
        return rv_freeze;
    }

    public void setRv_freeze(int rv_freeze) {
        this.rv_freeze = rv_freeze;
    }

    public int getRv_del() {
        return rv_del;
    }

    public void setRv_del(int rv_del) {
        this.rv_del = rv_del;
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
