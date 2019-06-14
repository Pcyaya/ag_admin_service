package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class DepartTreePage extends TreePage {
    private List<DepartTreePage> children;

    public List<DepartTreePage> getChildren() {
        return children;
    }

    public void setChildren(List<DepartTreePage> children) {
        this.children = children;
    }
}
