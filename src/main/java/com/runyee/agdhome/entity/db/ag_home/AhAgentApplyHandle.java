package com.runyee.agdhome.entity.db.ag_home;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class AhAgentApplyHandle extends AhAgentApply {
    private 	String	aah_id;	 //
    private 	String	aah_apply;	 //意见反馈id
    private 	String	aah_user;	 //处理人
    private 	int	aah_status;	 //0处理中1已处理
    private 	String	aah_content;	 //备注
    private 	int	del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAah_id() {
        return aah_id;
    }

    public void setAah_id(String aah_id) {
        this.aah_id = aah_id;
    }

    public String getAah_apply() {
        return aah_apply;
    }

    public void setAah_apply(String aah_apply) {
        this.aah_apply = aah_apply;
    }

    public String getAah_user() {
        return aah_user;
    }

    public void setAah_user(String aah_user) {
        this.aah_user = aah_user;
    }

    public int getAah_status() {
        return aah_status;
    }

    public void setAah_status(int aah_status) {
        this.aah_status = aah_status;
    }

    public String getAah_content() {
        return aah_content;
    }

    public void setAah_content(String aah_content) {
        this.aah_content = aah_content;
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
