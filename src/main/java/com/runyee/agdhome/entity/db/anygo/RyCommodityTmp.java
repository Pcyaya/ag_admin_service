package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCommodityTmp {
    private 	String	rct_id;	 //uuid
    private 	String	rct_num;//商品编码
    private 	String	rct_name;	 //商品名称
    private     String  rct_icon;//商品图片
    private 	int	rct_hot;//hot标志
    private 	int	rct_recommend;//推荐
    private 	int	rct_business;	 //0.商品，1.优惠券
    private 	String	rct_rel;	 //关联类型id
    private 	int	rct_vprice;	 //虚拟价格
    private 	int	rct_rprice;	 //真实价格
    private 	int	rct_coin;	 //游币数量
    private 	int	rct_online;	 //0.下架1.上架
    private 	Date	rct_ondate;//上架时间
    private 	Date	rct_underdate;//下架时间
    private 	int	rct_limit;//限制数量:-1.不限量
    private 	int	rct_sell;//售出数量
    private 	int	rct_del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRct_id() {
        return rct_id;
    }

    public void setRct_id(String rct_id) {
        this.rct_id = rct_id;
    }

    public String getRct_num() {
        return rct_num;
    }

    public void setRct_num(String rct_num) {
        this.rct_num = rct_num;
    }

    public String getRct_name() {
        return rct_name;
    }

    public void setRct_name(String rct_name) {
        this.rct_name = rct_name;
    }

    public String getRct_icon() {
        return rct_icon;
    }

    public void setRct_icon(String rct_icon) {
        this.rct_icon = rct_icon;
    }

    public int getRct_hot() {
        return rct_hot;
    }

    public void setRct_hot(int rct_hot) {
        this.rct_hot = rct_hot;
    }

    public int getRct_recommend() {
        return rct_recommend;
    }

    public void setRct_recommend(int rct_recommend) {
        this.rct_recommend = rct_recommend;
    }

    public int getRct_business() {
        return rct_business;
    }

    public void setRct_business(int rct_business) {
        this.rct_business = rct_business;
    }

    public String getRct_rel() {
        return rct_rel;
    }

    public void setRct_rel(String rct_rel) {
        this.rct_rel = rct_rel;
    }

    public int getRct_vprice() {
        return rct_vprice;
    }

    public void setRct_vprice(int rct_vprice) {
        this.rct_vprice = rct_vprice;
    }

    public int getRct_rprice() {
        return rct_rprice;
    }

    public void setRct_rprice(int rct_rprice) {
        this.rct_rprice = rct_rprice;
    }

    public int getRct_coin() {
        return rct_coin;
    }

    public void setRct_coin(int rct_coin) {
        this.rct_coin = rct_coin;
    }

    public int getRct_online() {
        return rct_online;
    }

    public void setRct_online(int rct_online) {
        this.rct_online = rct_online;
    }

    public Date getRct_ondate() {
        return rct_ondate;
    }

    public void setRct_ondate(Date rct_ondate) {
        this.rct_ondate = rct_ondate;
    }

    public Date getRct_underdate() {
        return rct_underdate;
    }

    public void setRct_underdate(Date rct_underdate) {
        this.rct_underdate = rct_underdate;
    }

    public int getRct_limit() {
        return rct_limit;
    }

    public void setRct_limit(int rct_limit) {
        this.rct_limit = rct_limit;
    }

    public int getRct_sell() {
        return rct_sell;
    }

    public void setRct_sell(int rct_sell) {
        this.rct_sell = rct_sell;
    }

    public int getRct_del() {
        return rct_del;
    }

    public void setRct_del(int rct_del) {
        this.rct_del = rct_del;
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
