package com.runyee.agdhome.entity.form;

/**
 * Created by cheri on 2018/8/7.
 */
public class RecruitForm {
    private String id;
    private String business;//类型
    private String name;//职位
    private String addr;//工作地点
    private int exper;//工作经验
    private int exper_end;//
    private String edu;//学历
    private int audit;//审核
    /*private String duty;//职责
    private String require;//职位要求*/
    private String details;//详情

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

    public int getExper() {
        return exper;
    }

    public void setExper(int exper) {
        this.exper = exper;
    }

    public int getExper_end() {
        return exper_end;
    }

    public void setExper_end(int exper_end) {
        this.exper_end = exper_end;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
