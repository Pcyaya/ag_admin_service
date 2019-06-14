package com.runyee.agdhome.entity.page;


/**
 * Created by cheri on 2018/8/7.
 */
public class RecruitApplyDetailPage {
    private String title;//标题
    private String content;//内容
    private int type;//类型0.文本，1.图片，2音频，3.视频 4.word. 5.pdf
    private String url;//地址

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
