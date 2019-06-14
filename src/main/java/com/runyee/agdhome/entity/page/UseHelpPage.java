package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/3/29.
 */
public class UseHelpPage {
    private String id;//唯一标识
    private String category;//类别
    private String title;//问题
    private String icon;//图标
    private int sync;//app同步
    private List<UseHelpDetailPage> details;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public List<UseHelpDetailPage> getDetails() {
        return details;
    }

    public void setDetails(List<UseHelpDetailPage> details) {
        this.details = details;
    }
}
