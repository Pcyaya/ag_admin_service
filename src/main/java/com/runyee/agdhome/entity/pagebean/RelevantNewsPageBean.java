package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;
import com.runyee.agdhome.entity.page.NewsBusinessPage;

import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class RelevantNewsPageBean extends PageBean{
    private String id;//新闻id
    private String business;	 //业务类型


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

}
