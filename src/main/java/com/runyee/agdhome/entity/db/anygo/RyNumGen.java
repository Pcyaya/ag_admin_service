package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyNumGen {
    private 	String	rng_id;	 //
    private 	String	rng_key;	 //前缀
    private 	String	rng_num;	 //次数
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRng_id() {
        return rng_id;
    }

    public void setRng_id(String rng_id) {
        this.rng_id = rng_id;
    }

    public String getRng_key() {
        return rng_key;
    }

    public void setRng_key(String rng_key) {
        this.rng_key = rng_key;
    }

    public String getRng_num() {
        return rng_num;
    }

    public void setRng_num(String rng_num) {
        this.rng_num = rng_num;
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
