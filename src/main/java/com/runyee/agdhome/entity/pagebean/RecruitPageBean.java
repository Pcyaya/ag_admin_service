package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;
import com.runyee.agdhome.entity.page.NewsBusinessPage;
import com.runyee.agdhome.entity.page.RecruitBusinessPage;

import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class RecruitPageBean extends PageBean{
    private String business;	 //业务类型
    private List<RecruitBusinessPage> blist;//业务类型列表

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public List<RecruitBusinessPage> getBlist() {
        return blist;
    }

    public void setBlist(List<RecruitBusinessPage> blist) {
        this.blist = blist;
    }
}
