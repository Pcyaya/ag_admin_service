package com.runyee.agdhome.entity.ex;


import com.runyee.agdhome.entity.db.ag_home.AhContact;
import com.runyee.agdhome.entity.form.ContactForm;

/**
 * Created by cheri on 2018/8/7.
 */
public class ContactInfoBean{
    private int status;//0处理中1已处理
    private String content;//备注
    private int val;//回复通用值
    private String replyVal;//回复

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

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getReplyVal() {
        return replyVal;
    }

    public void setReplyVal(String replyVal) {
        this.replyVal = replyVal;
    }
}
