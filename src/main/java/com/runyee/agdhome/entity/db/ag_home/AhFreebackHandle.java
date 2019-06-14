package com.runyee.agdhome.entity.db.ag_home;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class AhFreebackHandle extends AhFreeback {
    private 	String	afh_id;	 //
    private 	String	afh_freeback;	 //意见反馈id
    private 	String	afh_user;	 //处理人
    private 	int	afh_status;	 //0处理中1已处理
    private 	String	afh_content;	 //备注
    private 	int	del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAfh_id() {
        return afh_id;
    }

    public void setAfh_id(String afh_id) {
        this.afh_id = afh_id;
    }

    public String getAfh_freeback() {
        return afh_freeback;
    }

    public void setAfh_freeback(String afh_freeback) {
        this.afh_freeback = afh_freeback;
    }

    public String getAfh_user() {
        return afh_user;
    }

    public void setAfh_user(String afh_user) {
        this.afh_user = afh_user;
    }

    public int getAfh_status() {
        return afh_status;
    }

    public void setAfh_status(int afh_status) {
        this.afh_status = afh_status;
    }

    public String getAfh_content() {
        return afh_content;
    }

    public void setAfh_content(String afh_content) {
        this.afh_content = afh_content;
    }

    @Override
    public int getDel() {
        return del;
    }

    @Override
    public void setDel(int del) {
        this.del = del;
    }

    @Override
    public Date getCreate_date() {
        return create_date;
    }

    @Override
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public Date getUpdate_date() {
        return update_date;
    }

    @Override
    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
