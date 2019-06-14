package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/8/7.
 */
public class UseHelpForm {
    private     String  id;//唯一标识
    private     String  category;//类别
    private 	String	title;	 //问题
    private     String  icon;    //图标
    private     int sync;//app 同步
    private     String details;//详情

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
