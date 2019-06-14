package com.runyee.agdhome.entity.ex;
import com.runyee.agdhome.entity.db.ag_home.AhRecruit;


/**
 * Created by cheri on 2018/8/7.
 */
public class RecruitBean extends AhRecruit{
    private String business_name;//职位类别
    private int apply_num;//申请人数

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public int getApply_num() {
        return apply_num;
    }

    public void setApply_num(int apply_num) {
        this.apply_num = apply_num;
    }
}
