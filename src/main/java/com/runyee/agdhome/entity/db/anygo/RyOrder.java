package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyOrder {
    private 	String	ro_id;	 //
    private 	String	ro_num;	 //订单编码
    private 	String	ro_visitor;	 //下单人
    private 	int	ro_status;	 //订单状态：0.申请1完成
    private 	int	ro_price;//总金额
    private 	int	ro_coin;//总游币
    private     int ro_amount;//总数量
    private 	int	ro_payprice;//已支付金额
    private 	int	ro_paycoin;//已支付游币
    private 	int	ro_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRo_id() {
        return ro_id;
    }

    public void setRo_id(String ro_id) {
        this.ro_id = ro_id;
    }

    public String getRo_num() {
        return ro_num;
    }

    public void setRo_num(String ro_num) {
        this.ro_num = ro_num;
    }

    public String getRo_visitor() {
        return ro_visitor;
    }

    public void setRo_visitor(String ro_visitor) {
        this.ro_visitor = ro_visitor;
    }

    public int getRo_status() {
        return ro_status;
    }

    public void setRo_status(int ro_status) {
        this.ro_status = ro_status;
    }

    public int getRo_price() {
        return ro_price;
    }

    public void setRo_price(int ro_price) {
        this.ro_price = ro_price;
    }

    public int getRo_coin() {
        return ro_coin;
    }

    public void setRo_coin(int ro_coin) {
        this.ro_coin = ro_coin;
    }

    public int getRo_amount() {
        return ro_amount;
    }

    public void setRo_amount(int ro_amount) {
        this.ro_amount = ro_amount;
    }

    public int getRo_payprice() {
        return ro_payprice;
    }

    public void setRo_payprice(int ro_payprice) {
        this.ro_payprice = ro_payprice;
    }

    public int getRo_paycoin() {
        return ro_paycoin;
    }

    public void setRo_paycoin(int ro_paycoin) {
        this.ro_paycoin = ro_paycoin;
    }

    public int getRo_del() {
        return ro_del;
    }

    public void setRo_del(int ro_del) {
        this.ro_del = ro_del;
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
