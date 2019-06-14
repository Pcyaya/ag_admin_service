package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/3/29.
 */
public class UseHelpDetailForm {
    private String id;//唯一标识
    private String content;//内容
    private int type;//类型 0.文本,1.图片,2.语音,3视频 4.文件
    private String url;//url

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
