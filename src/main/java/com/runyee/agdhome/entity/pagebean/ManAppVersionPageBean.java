package com.runyee.agdhome.entity.pagebean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
@JsonIgnoreProperties(value = {"version","plat","constraint_flg","date"})
public class ManAppVersionPageBean extends PageBean{
    private String version;//版本号
    private int plat;//应用平台
    private int constraint_flg;//1.强制更新
    private String date;//创建日期

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPlat() {
        return plat;
    }

    public void setPlat(int plat) {
        this.plat = plat;
    }

    public int getConstraint_flg() {
        return constraint_flg;
    }

    public void setConstraint_flg(int constraint_flg) {
        this.constraint_flg = constraint_flg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
