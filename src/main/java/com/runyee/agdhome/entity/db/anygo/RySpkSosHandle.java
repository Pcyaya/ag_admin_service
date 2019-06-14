package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RySpkSosHandle {
    private 	String	rssh_id;	 //uuid
    private 	String	rssh_sos;	 //sos 信息
    private 	String	rssh_user;	 //处理人
    private 	int	rssh_confirm;	 //0未确认1已确认
    private 	int	rssh_operate;	 //0误操作1危险事故2电话无法接通
    private 	String	rssh_content;	 //备注
    private 	int	rssh_inform;	 //0未通知1.已通知
    private 	int	rssh_del;	 //
    private     Date create_date;	 //
    private 	Date	update_date;	 //

    public String getRssh_id() {
        return rssh_id;
    }

    public void setRssh_id(String rssh_id) {
        this.rssh_id = rssh_id;
    }

    public String getRssh_sos() {
        return rssh_sos;
    }

    public void setRssh_sos(String rssh_sos) {
        this.rssh_sos = rssh_sos;
    }

    public String getRssh_user() {
        return rssh_user;
    }

    public void setRssh_user(String rssh_user) {
        this.rssh_user = rssh_user;
    }

    public int getRssh_confirm() {
        return rssh_confirm;
    }

    public void setRssh_confirm(int rssh_confirm) {
        this.rssh_confirm = rssh_confirm;
    }

    public int getRssh_operate() {
        return rssh_operate;
    }

    public void setRssh_operate(int rssh_operate) {
        this.rssh_operate = rssh_operate;
    }

    public String getRssh_content() {
        return rssh_content;
    }

    public void setRssh_content(String rssh_content) {
        this.rssh_content = rssh_content;
    }

    public int getRssh_inform() {
        return rssh_inform;
    }

    public void setRssh_inform(int rssh_inform) {
        this.rssh_inform = rssh_inform;
    }

    public int getRssh_del() {
        return rssh_del;
    }

    public void setRssh_del(int rssh_del) {
        this.rssh_del = rssh_del;
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
