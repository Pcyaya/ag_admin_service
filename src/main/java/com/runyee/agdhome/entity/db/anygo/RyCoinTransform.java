package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCoinTransform {
    private 	String	rct_id;	 //uuid
    private 	String	rct_coin;	 //游币id
    private 	String	rct_pid;	 //父id
    private     String  rct_order;//外部单id
    private     String  rct_item;//详情id
    private 	String	rct_visitor;	 //游客id
    private 	int	rct_business;	 //业务类型
    private     int rct_flg;//0.加1.减
    private 	int	rct_number;	 //变动数量
    private 	int	rct_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRct_id() {
        return rct_id;
    }

    public void setRct_id(String rct_id) {
        this.rct_id = rct_id;
    }

    public String getRct_coin() {
        return rct_coin;
    }

    public void setRct_coin(String rct_coin) {
        this.rct_coin = rct_coin;
    }

    public String getRct_pid() {
        return rct_pid;
    }

    public void setRct_pid(String rct_pid) {
        this.rct_pid = rct_pid;
    }

    public String getRct_order() {
        return rct_order;
    }

    public void setRct_order(String rct_order) {
        this.rct_order = rct_order;
    }

    public String getRct_item() {
        return rct_item;
    }

    public void setRct_item(String rct_item) {
        this.rct_item = rct_item;
    }

    public String getRct_visitor() {
        return rct_visitor;
    }

    public void setRct_visitor(String rct_visitor) {
        this.rct_visitor = rct_visitor;
    }

    public int getRct_business() {
        return rct_business;
    }

    public void setRct_business(int rct_business) {
        this.rct_business = rct_business;
    }

    public int getRct_flg() {
        return rct_flg;
    }

    public void setRct_flg(int rct_flg) {
        this.rct_flg = rct_flg;
    }

    public int getRct_number() {
        return rct_number;
    }

    public void setRct_number(int rct_number) {
        this.rct_number = rct_number;
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
