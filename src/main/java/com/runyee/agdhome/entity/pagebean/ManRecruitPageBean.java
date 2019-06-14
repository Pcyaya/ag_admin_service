package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;
import com.runyee.agdhome.entity.page.RecruitBusinessPage;

import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManRecruitPageBean extends PageBean{
    private String business;	 //业务类型
    private String name;//职位
    private String start;//开始时间
    private String end;//结束时间
    private int exper;//工作经验
    private String edu;//学历
    private int audit;//审核 0.未审核 1.已审核

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getExper() {
        return exper;
    }

    public void setExper(int exper) {
        this.exper = exper;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }
}
