package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhDepart;
import com.runyee.agdhome.entity.db.ag_home.AhMenu;

import java.util.List;


/**
 * Created by cheri on 2018/8/7.
 */
public class DepartParentBean extends AhDepart{
    private int handle_flg = 0;//是否处理过

    public int getHandle_flg() {
        return handle_flg;
    }

    public void setHandle_flg(int handle_flg) {
        this.handle_flg = handle_flg;
    }


}
