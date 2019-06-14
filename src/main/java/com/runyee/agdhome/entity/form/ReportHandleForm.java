package com.runyee.agdhome.entity.form;

/**
 * Created by cheri on 2018/8/7.
 */
public class ReportHandleForm {
    private String id;
    private int status;//状态 0.未确定,1已确定/0.未通知,1.通知
    private String content;//确认内容
    private int val;//回复通用值
    private String replyVal;//回复

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
