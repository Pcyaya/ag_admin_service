package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

public class RyIntellSpkMan {

    private 	String	rism_id;	 //
    private 	String	rism_guide;	 //导游id
    private 	String	rism_intell_spk;	 //音箱id
    private 	int	rism_state;	 //0.正常 1.失效
    private 	String	rism_db;	 //管理用户数据源
    private 	int	rism_del;	 //
    private Date create_date;	 //
    private 	Date	update_date;	 //

    public String getRism_id() {
        return rism_id;
    }

    public void setRism_id(String rism_id) {
        this.rism_id = rism_id;
    }

    public String getRism_guide() {
        return rism_guide;
    }

    public void setRism_guide(String rism_guide) {
        this.rism_guide = rism_guide;
    }

    public String getRism_intell_spk() {
        return rism_intell_spk;
    }

    public void setRism_intell_spk(String rism_intell_spk) {
        this.rism_intell_spk = rism_intell_spk;
    }

    public int getRism_state() {
        return rism_state;
    }

    public void setRism_state(int rism_state) {
        this.rism_state = rism_state;
    }

    public String getRism_db() {
        return rism_db;
    }

    public void setRism_db(String rism_db) {
        this.rism_db = rism_db;
    }

    public int getRism_del() {
        return rism_del;
    }

    public void setRism_del(int rism_del) {
        this.rism_del = rism_del;
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
