package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhMenu;

import java.util.List;


/**
 * Created by cheri on 2018/8/7.
 */
public class MenuBean extends AhMenu{
    private int leaf_flg;//1.子节点

    public int getLeaf_flg() {
        return leaf_flg;
    }

    public void setLeaf_flg(int leaf_flg) {
        this.leaf_flg = leaf_flg;
    }
}
