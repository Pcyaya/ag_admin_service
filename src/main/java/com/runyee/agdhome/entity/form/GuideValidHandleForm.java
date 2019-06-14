package com.runyee.agdhome.entity.form;

/**
 * Created by cheri on 2018/8/7.
 */
public class GuideValidHandleForm {
    private String id;
    private int status;//状态 0.验证中1.验证通过，2.验证失败 3.失效
    private String content;//

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
}
