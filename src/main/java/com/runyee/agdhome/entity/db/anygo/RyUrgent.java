package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyUrgent {
    private 	String	ru_id;	 //uuid
    private 	String	ru_name;	 //名称int
    private     int ru_sync;//1.同步
    private 	int	ru_del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRu_id() {
        return ru_id;
    }

    public void setRu_id(String ru_id) {
        this.ru_id = ru_id;
    }

    public String getRu_name() {
        return ru_name;
    }

    public void setRu_name(String ru_name) {
        this.ru_name = ru_name;
    }

    public int getRu_sync() {
        return ru_sync;
    }

    public void setRu_sync(int ru_sync) {
        this.ru_sync = ru_sync;
    }

    public int getRu_del() {
        return ru_del;
    }

    public void setRu_del(int ru_del) {
        this.ru_del = ru_del;
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
