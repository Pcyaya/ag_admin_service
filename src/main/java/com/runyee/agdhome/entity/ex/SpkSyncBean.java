package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RySpkSync;

public class SpkSyncBean extends RySpkSync {
    private String spk_num;//设备编码
    private long startDate;//开始时间
    private long endDate;//结束时间

    public String getSpk_num() {
        return spk_num;
    }

    public void setSpk_num(String spk_num) {
        this.spk_num = spk_num;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
}
