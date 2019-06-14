package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class NewsExPage extends NewsPage{

    private List<NewsDetailPage> details;//详情

    public List<NewsDetailPage> getDetails() {
        return details;
    }

    public void setDetails(List<NewsDetailPage> details) {
        this.details = details;
    }
}
