package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;
import com.runyee.agdhome.entity.page.NewsBusinessPage;

import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class NewsPageBean extends PageBean{
    private String business;	 //业务类型
    private List<NewsBusinessPage> blist;//业务类型列表

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public List<NewsBusinessPage> getBlist() {
        return blist;
    }

    public void setBlist(List<NewsBusinessPage> blist) {
        this.blist = blist;
    }
}
