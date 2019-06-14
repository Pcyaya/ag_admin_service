package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhNews;


/**
 * Created by cheri on 2018/8/7.
 */
public class NewsBean extends AhNews{
    private String img;//图片url
    private String source_name;//来源名称
    private String business_name;//类别名称
    private int app_flg;//app新闻资讯显示 0.否1.是
    private int index_flg;//官网首页显示 0.否1.是

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
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
}
