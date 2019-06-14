package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhMenu;


/**
 * Created by cheri on 2018/8/7.
 */
public class RightParentBean extends AhMenu {
    private String right_id;//权限id
    private int handle_flg = 0;//是否处理过
    private int select_flg = 0;//是否选中

    public String getRight_id() {
        return right_id;
    }

    public void setRight_id(String right_id) {
        this.right_id = right_id;
    }

    public int getHandle_flg() {
        return handle_flg;
    }

    public void setHandle_flg(int handle_flg) {
        this.handle_flg = handle_flg;
    }

    public int getSelect_flg() {
        return select_flg;
    }

    public void setSelect_flg(int select_flg) {
        this.select_flg = select_flg;
    }

}
