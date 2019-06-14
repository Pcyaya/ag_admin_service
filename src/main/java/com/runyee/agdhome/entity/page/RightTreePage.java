package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/8/7.
 */
public class RightTreePage extends TreePage {
    private String menu_id;//菜单id
    private String pmenu_id;//父菜单id
    private int select_flg;//1.选中
    private List<RightTreePage> children;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getPmenu_id() {
        return pmenu_id;
    }

    public void setPmenu_id(String pmenu_id) {
        this.pmenu_id = pmenu_id;
    }

    public int getSelect_flg() {
        return select_flg;
    }

    public void setSelect_flg(int select_flg) {
        this.select_flg = select_flg;
    }

    public List<RightTreePage> getChildren() {
        return children;
    }

    public void setChildren(List<RightTreePage> children) {
        this.children = children;
    }
}
