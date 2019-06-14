package com.runyee.agdhome.entity.form;

/**
 * Created by cheri on 2018/8/7.
 */
public class SosHandleForm {
    private String id;
    private int status;//状态 0.未确定,1已确定/0.未通知,1.通知
    private int operate;//确认结果 0误操作，1危险事故，3 电话无法接通
    private String content;//确认内容

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

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
