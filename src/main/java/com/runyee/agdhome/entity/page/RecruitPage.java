package com.runyee.agdhome.entity.page;


import java.util.Date;
import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class RecruitPage {
    private String id;
    private String name;//职位
    private String addr;//工作地点
    private String exper;//工作经验
    private String edu;//学历
    private Date date;//日期
    private int audit;//审核
    private List<RecruitDetailPage> details;//详情

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getExper() {
        return exper;
    }

    public void setExper(String exper) {
        this.exper = exper;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public List<RecruitDetailPage> getDetails() {
        return details;
    }

    public void setDetails(List<RecruitDetailPage> details) {
        this.details = details;
    }

}
