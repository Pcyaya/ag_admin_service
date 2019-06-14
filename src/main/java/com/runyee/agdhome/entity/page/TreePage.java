package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class TreePage {
    private String id;
    private String icon;//图标
    private String name;//菜单名称
    private int leaf_flg;//1.叶子节点

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaf_flg() {
        return leaf_flg;
    }

    public void setLeaf_flg(int leaf_flg) {
        this.leaf_flg = leaf_flg;
    }
}
