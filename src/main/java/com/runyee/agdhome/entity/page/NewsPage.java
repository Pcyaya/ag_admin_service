package com.runyee.agdhome.entity.page;


import java.util.Date;
import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class NewsPage {
    private String id;
    private String title;//标题
    private String content;//内容
    private String resource;//静态资源
    private String business;//类型
    private String source;//来源
    private String source_name;//来源名称
    private String origin;//原网址
    private String img;//图片
    private Date date;//日期
    private String business_name;//类别名称
    private int app_flg;//app新闻资讯显示 0.否1.是
    private int index_flg;//官网首页显示 0.否1.是
    private int audit;//审核 0未审1.已审核

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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
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
}
