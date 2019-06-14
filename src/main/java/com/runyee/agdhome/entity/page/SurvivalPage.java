package com.runyee.agdhome.entity.page;


/**
 * Created by cheri on 2018/3/29.
 */
public class SurvivalPage {
    private String id;//唯一标识
    private String title;//标题
    private int sync;//1.同步
    private String content;//内容
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

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
