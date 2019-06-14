
package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhUser;


/**
 * Created by cheri on 2018/8/7.
 */
public class UserBean extends AhUser {
    private String departs;//部门

    public String getDeparts() {
        return departs;
    }

    public void setDeparts(String departs) {
        this.departs = departs;
    }
}
