package com.runyee.agdhome.entity.pagebean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/3/29.
 */
public class SurvivalCapsulePageBean extends PageBean {
    private String search;//搜索

    @JsonIgnore
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
