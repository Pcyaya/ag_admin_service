package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class MenuPage extends TreePage {
    private String url;//路由
    private int right_flg;//1.已关联权限
    private int view_flg;//1.对所有人可见
    private List<MenuPage> children;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public List<MenuPage> getChildren() {
        return children;
    }

    public void setChildren(List<MenuPage> children) {
        this.children = children;
    }

    public int getRight_flg() {
        return right_flg;
    }

    public void setRight_flg(int right_flg) {
        this.right_flg = right_flg;
    }

    public int getView_flg() {
        return view_flg;
    }

    public void setView_flg(int view_flg) {
        this.view_flg = view_flg;
    }
}
