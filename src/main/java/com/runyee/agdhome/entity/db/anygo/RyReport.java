package com.runyee.agdhome.entity.db.anygo;


import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyReport {
    private 	String	rr_id;	 //uuid
    private 	String	rr_num;	 //编码
    private 	int	rr_type;	 //举报类型 0.侗友 1.团 2.游记
    private 	String	rr_visitor;	 //举报人
    private 	String	rr_content;	 //举报内容
    private    String rr_obj;	 //举报对象id
    private 	int rr_del;	 //删除
    private 	Date	create_date;	 //
    private    Date update_date;	 //


    public String getRr_id() {
        return rr_id;
    }

    public void setRr_id(String rr_id) {
        this.rr_id = rr_id;
    }

    public String getRr_num() {
        return rr_num;
    }

    public void setRr_num(String rr_num) {
        this.rr_num = rr_num;
    }

    public int getRr_type() {
        return rr_type;
    }

    public void setRr_type(int rr_type) {
        this.rr_type = rr_type;
    }

    public String getRr_visitor() {
        return rr_visitor;
    }

    public void setRr_visitor(String rr_visitor) {
        this.rr_visitor = rr_visitor;
    }

    public String getRr_content() {
        return rr_content;
    }

    public void setRr_content(String rr_content) {
        this.rr_content = rr_content;
    }

    public String getRr_obj() {
        return rr_obj;
    }

    public void setRr_obj(String rr_obj) {
        this.rr_obj = rr_obj;
    }

    public int getRr_del() {
        return rr_del;
    }

    public void setRr_del(int rr_del) {
        this.rr_del = rr_del;
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
