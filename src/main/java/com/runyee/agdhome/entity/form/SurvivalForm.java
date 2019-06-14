package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/8/7.
 */
public class SurvivalForm {
    private     String  id;//唯一标识
    private 	String	title;	 //版本
    private 	String	content;	 //下载地址
    private     int     sync;//1.同步

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }
}
