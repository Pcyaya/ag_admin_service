package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyOrderItem {
    private 	String	roi_id;	 //uuid
    private 	String	roi_order;	 //订单id
    private 	String	roi_commodity;	 //商品id
    private     String roi_name;//商品名称
    private 	int	roi_business;	 //0.商品，1.优惠券
    private 	String	roi_rel;	 //关联类型id
    private 	int	roi_vprice;	 //虚拟价格
    private 	int	roi_rprice;	 //真实价格
    private 	int	roi_coin;	 //游币数量
    private 	int	roi_calculate;	 //计算方式 0.仅金额1仅游币2.游币加金额
    private 	int	roi_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRoi_id() {
        return roi_id;
    }

    public void setRoi_id(String roi_id) {
        this.roi_id = roi_id;
    }

    public String getRoi_order() {
        return roi_order;
    }

    public void setRoi_order(String roi_order) {
        this.roi_order = roi_order;
    }

    public String getRoi_commodity() {
        return roi_commodity;
    }

    public void setRoi_commodity(String roi_commodity) {
        this.roi_commodity = roi_commodity;
    }

    public String getRoi_name() {
        return roi_name;
    }

    public void setRoi_name(String roi_name) {
        this.roi_name = roi_name;
    }

    public int getRoi_business() {
        return roi_business;
    }

    public void setRoi_business(int roi_business) {
        this.roi_business = roi_business;
    }

    public String getRoi_rel() {
        return roi_rel;
    }

    public void setRoi_rel(String roi_rel) {
        this.roi_rel = roi_rel;
    }

    public int getRoi_vprice() {
        return roi_vprice;
    }

    public void setRoi_vprice(int roi_vprice) {
        this.roi_vprice = roi_vprice;
    }

    public int getRoi_rprice() {
        return roi_rprice;
    }

    public void setRoi_rprice(int roi_rprice) {
        this.roi_rprice = roi_rprice;
    }

    public int getRoi_coin() {
        return roi_coin;
    }

    public void setRoi_coin(int roi_coin) {
        this.roi_coin = roi_coin;
    }

    public int getRoi_calculate() {
        return roi_calculate;
    }

    public void setRoi_calculate(int roi_calculate) {
        this.roi_calculate = roi_calculate;
    }

    public int getRoi_del() {
        return roi_del;
    }

    public void setRoi_del(int roi_del) {
        this.roi_del = roi_del;
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
