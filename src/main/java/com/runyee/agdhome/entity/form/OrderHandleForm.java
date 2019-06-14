package com.runyee.agdhome.entity.form;

import com.runyee.agdhome.entity.db.ag_home.AhOrderHandle;

/**
 * Created by cheri on 2018/8/7.
 */
public class OrderHandleForm extends AhOrderHandle {
    private String id;
    private int status;//状态 1.已处理
    private String content;//确认内容
    private     String  ao_name;    //申请人
    private     String  ao_phone;   //申请人电话
    private     String  ao_addr;
    private     String ao_content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAo_name() {
        return ao_name;
    }

    public void setAo_name(String ao_name) {
        this.ao_name = ao_name;
    }

    public String getAo_phone() {
        return ao_phone;
    }

    public void setAo_phone(String ao_phone) {
        this.ao_phone = ao_phone;
    }

    public String getAo_addr() {
        return ao_addr;
    }

    public void setAo_addr(String ao_addr) {
        this.ao_addr = ao_addr;
    }

    public String getAo_content() {
        return ao_content;
    }

    public void setAo_content(String ao_content) {
        this.ao_content = ao_content;
    }
}
