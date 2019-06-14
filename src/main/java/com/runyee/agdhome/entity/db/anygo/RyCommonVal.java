package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCommonVal {
    private 	String	rcv_id;	 //uuid
    private 	String	rcv_key;	 //唯一标识
    private 	String	rcv_name;	 //名称
    private 	String	rcv_group;	 //组
    private 	String	rcv_desc;	 //描述
    private 	int	rcv_priority;	 //优先级
    private 	String	rcv_val;	 //值
    private 	int	rcv_type;	 //值类型
    private 	String	rcv_unit;	 //单位
    private 	int	rcv_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRcv_id() {
        return rcv_id;
    }

    public void setRcv_id(String rcv_id) {
        this.rcv_id = rcv_id;
    }

    public String getRcv_key() {
        return rcv_key;
    }

    public void setRcv_key(String rcv_key) {
        this.rcv_key = rcv_key;
    }

    public String getRcv_name() {
        return rcv_name;
    }

    public void setRcv_name(String rcv_name) {
        this.rcv_name = rcv_name;
    }

    public String getRcv_group() {
        return rcv_group;
    }

    public void setRcv_group(String rcv_group) {
        this.rcv_group = rcv_group;
    }

    public String getRcv_desc() {
        return rcv_desc;
    }

    public void setRcv_desc(String rcv_desc) {
        this.rcv_desc = rcv_desc;
    }

    public int getRcv_priority() {
        return rcv_priority;
    }

    public void setRcv_priority(int rcv_priority) {
        this.rcv_priority = rcv_priority;
    }

    public String getRcv_val() {
        return rcv_val;
    }

    public void setRcv_val(String rcv_val) {
        this.rcv_val = rcv_val;
    }

    public int getRcv_type() {
        return rcv_type;
    }

    public void setRcv_type(int rcv_type) {
        this.rcv_type = rcv_type;
    }

    public String getRcv_unit() {
        return rcv_unit;
    }

    public void setRcv_unit(String rcv_unit) {
        this.rcv_unit = rcv_unit;
    }

    public int getRcv_del() {
        return rcv_del;
    }

    public void setRcv_del(int rcv_del) {
        this.rcv_del = rcv_del;
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
