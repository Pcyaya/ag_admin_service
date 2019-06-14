package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class NewsContentPage {
    private NewsPage last;//上一条
    private NewsPage current;//当前
    private NewsPage next;//下一条
    private NewsBusinessPage business;
    private List<NewsDetailPage> details;

    public NewsPage getLast() {
        return last;
    }

    public void setLast(NewsPage last) {
        this.last = last;
    }

    public NewsPage getCurrent() {
        return current;
    }

    public void setCurrent(NewsPage current) {
        this.current = current;
    }

    public NewsPage getNext() {
        return next;
    }

    public void setNext(NewsPage next) {
        this.next = next;
    }

    public NewsBusinessPage getBusiness() {
        return business;
    }

    public void setBusiness(NewsBusinessPage business) {
        this.business = business;
    }

    public List<NewsDetailPage> getDetails() {
        return details;
    }

    public void setDetails(List<NewsDetailPage> details) {
        this.details = details;
    }

}
