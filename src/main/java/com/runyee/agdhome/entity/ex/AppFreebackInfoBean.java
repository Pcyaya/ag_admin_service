
package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RyFreebackDetail;

import java.util.List;


/**
 * Created by cheri on 2018/8/7.
 */
public class AppFreebackInfoBean extends AppFreebackBean {
    private List<RyFreebackDetail> details;//标签

    public List<RyFreebackDetail> getDetails() {
        return details;
    }

    public void setDetails(List<RyFreebackDetail> details) {
        this.details = details;
    }
}
