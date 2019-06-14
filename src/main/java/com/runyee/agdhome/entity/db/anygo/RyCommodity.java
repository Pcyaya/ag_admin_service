package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCommodity {
    private 	String	rc_id;	 //
    private 	String	rc_tmp;	 //商品模板
    private 	String	rc_name;	 //商品名称
    private 	String rc_icon;//商品图片
    private     int rc_hot;//hot标志
    private     int rc_recommend;//推荐
    private 	int	rc_business;	 //0.商品，1.优惠券
    private 	String	rc_rel;	 //关联类型id
    private 	int	rc_vprice;	 //虚拟价格
    private 	int	rc_rprice;	 //真实价格
    private 	int	rc_coin;	 //游币数量
    private 	int	rc_calculate;	 //计算方式 0.仅金额1仅游币2.游币加金额
    private     int rc_online;//1.上架
    private 	int	rc_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRc_id() {
        return rc_id;
    }

    public void setRc_id(String rc_id) {
        this.rc_id = rc_id;
    }

    public String getRc_tmp() {
        return rc_tmp;
    }

    public void setRc_tmp(String rc_tmp) {
        this.rc_tmp = rc_tmp;
    }

    public String getRc_name() {
        return rc_name;
    }

    public void setRc_name(String rc_name) {
        this.rc_name = rc_name;
    }

    public String getRc_icon() {
        return rc_icon;
    }

    public void setRc_icon(String rc_icon) {
        this.rc_icon = rc_icon;
    }

    public int getRc_hot() {
        return rc_hot;
    }

    public void setRc_hot(int rc_hot) {
        this.rc_hot = rc_hot;
    }

    public int getRc_recommend() {
        return rc_recommend;
    }

    public void setRc_recommend(int rc_recommend) {
        this.rc_recommend = rc_recommend;
    }

    public int getRc_business() {
        return rc_business;
    }

    public void setRc_business(int rc_business) {
        this.rc_business = rc_business;
    }

    public String getRc_rel() {
        return rc_rel;
    }

    public void setRc_rel(String rc_rel) {
        this.rc_rel = rc_rel;
    }

    public int getRc_vprice() {
        return rc_vprice;
    }

    public void setRc_vprice(int rc_vprice) {
        this.rc_vprice = rc_vprice;
    }

    public int getRc_rprice() {
        return rc_rprice;
    }

    public void setRc_rprice(int rc_rprice) {
        this.rc_rprice = rc_rprice;
    }

    public int getRc_coin() {
        return rc_coin;
    }

    public void setRc_coin(int rc_coin) {
        this.rc_coin = rc_coin;
    }

    public int getRc_calculate() {
        return rc_calculate;
    }

    public void setRc_calculate(int rc_calculate) {
        this.rc_calculate = rc_calculate;
    }

    public int getRc_online() {
        return rc_online;
    }

    public void setRc_online(int rc_online) {
        this.rc_online = rc_online;
    }

    public int getRc_del() {
        return rc_del;
    }

    public void setRc_del(int rc_del) {
        this.rc_del = rc_del;
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
