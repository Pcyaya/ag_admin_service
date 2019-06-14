package com.runyee.agdhome.entity.page;


/**
 * Created by cheri on 2018/8/7.
 */
public class CardPage {
    private String id;
    private String num;//编码
    private String icon;//头像
    private String name;//昵称
    private String download;//下载页

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

}
