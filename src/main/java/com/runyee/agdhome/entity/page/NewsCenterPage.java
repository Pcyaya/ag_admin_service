package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class NewsCenterPage {
    private NewsPage news;//最新
    private List<NewsPage> list;//新闻列表

    public NewsPage getNews() {
        return news;
    }

    public void setNews(NewsPage news) {
        this.news = news;
    }

    public List<NewsPage> getList() {
        return list;
    }

    public void setList(List<NewsPage> list) {
        this.list = list;
    }
}
