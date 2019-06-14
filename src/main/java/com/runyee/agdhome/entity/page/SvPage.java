package com.runyee.agdhome.entity.page;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheri on 2018/3/29.
 */
public class SvPage {
    private String id;//唯一标识
    private String title;//标题
    private String content;//内容
    private int sync;//1同步
    private List<SvCapPage> capsules = new ArrayList<>();
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

    public List<SvCapPage> getCapsules() {
        return capsules;
    }

    public void setCapsules(List<SvCapPage> capsules) {
        this.capsules = capsules;
    }
}
