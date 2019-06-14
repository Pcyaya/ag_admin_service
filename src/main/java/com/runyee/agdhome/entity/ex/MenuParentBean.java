package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhMenu;

import java.util.List;


/**
 * Created by cheri on 2018/8/7.
 */
public class MenuParentBean extends AhMenu{
    List<MenuParentBean> childs;
    private int handle_flg = 0;//是否处理过
    private int right_flg;//是否关联权限

    public List<MenuParentBean> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuParentBean> childs) {
        this.childs = childs;
    }

    public int getHandle_flg() {
        return handle_flg;
    }

    public void setHandle_flg(int handle_flg) {
        this.handle_flg = handle_flg;
    }

    public int getRight_flg() {
        return right_flg;
    }

    public void setRight_flg(int right_flg) {
        this.right_flg = right_flg;
    }
}
