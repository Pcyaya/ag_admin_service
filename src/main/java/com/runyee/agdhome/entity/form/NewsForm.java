package com.runyee.agdhome.entity.form;

/**
 * Created by cheri on 2018/8/7.
 */
public class NewsForm {
    private String id;
    private String business;//类型
    private String title;//标题
    private String content;//内容
    //private String source;//来源
    private String img;//图片
    private int app_flg;//app新闻资讯显示 0.否1.是
    private int index_flg;//官网首页显示 0.否1.是
    private int audit;//审核 0.未审1已审
    private String details;//详情

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
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

    /*public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }*/

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getApp_flg() {
        return app_flg;
    }

    public void setApp_flg(int app_flg) {
        this.app_flg = app_flg;
    }

    public int getIndex_flg() {
        return index_flg;
    }

    public void setIndex_flg(int index_flg) {
        this.index_flg = index_flg;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
