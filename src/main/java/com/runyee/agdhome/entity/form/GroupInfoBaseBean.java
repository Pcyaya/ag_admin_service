package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/3/29.
 */
public class GroupInfoBaseBean extends InfoBean{
    private int guide_flg;//0非团员,1团员,2 导游
    private int group_flg;//0.普通团1.旅行团

    public int getGuide_flg() {
        return guide_flg;
    }

    public void setGuide_flg(int guide_flg) {
        this.guide_flg = guide_flg;
    }

    public int getGroup_flg() {
        return group_flg;
    }

    public void setGroup_flg(int group_flg) {
        this.group_flg = group_flg;
    }
}
