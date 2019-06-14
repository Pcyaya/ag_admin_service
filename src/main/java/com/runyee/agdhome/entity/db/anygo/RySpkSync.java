package com.runyee.agdhome.entity.db.anygo;

import java.math.BigDecimal;
import java.util.Date;

public class RySpkSync {
    private String rss_id;//uuid
    private String rss_spk_id;//设备id
    private String rss_spk_num;//设备编码
    private BigDecimal rss_lon;//经度
    private BigDecimal rss_lat;//维度
    private String rss_flow;//使用流量
    private String rss_tflow;//总流量
    private String rss_version;//音箱版本
    private int rss_del;//是否删除 0未删除，1已删除
    private Date create_date;//创建时间
    private Date update_date;//修改时间

    public String getRss_id() {
        return rss_id;
    }

    public void setRss_id(String rss_id) {
        this.rss_id = rss_id;
    }

    public String getRss_spk_id() {
        return rss_spk_id;
    }

    public void setRss_spk_id(String rss_spk_id) {
        this.rss_spk_id = rss_spk_id;
    }

    public String getRss_spk_num() {
        return rss_spk_num;
    }

    public void setRss_spk_num(String rss_spk_num) {
        this.rss_spk_num = rss_spk_num;
    }

    public BigDecimal getRss_lon() {
        return rss_lon;
    }

    public void setRss_lon(BigDecimal rss_lon) {
        this.rss_lon = rss_lon;
    }

    public BigDecimal getRss_lat() {
        return rss_lat;
    }

    public void setRss_lat(BigDecimal rss_lat) {
        this.rss_lat = rss_lat;
    }

    public String getRss_flow() {
        return rss_flow;
    }

    public void setRss_flow(String rss_flow) {
        this.rss_flow = rss_flow;
    }

    public String getRss_tflow() {
        return rss_tflow;
    }

    public void setRss_tflow(String rss_tflow) {
        this.rss_tflow = rss_tflow;
    }

    public String getRss_version() {
        return rss_version;
    }

    public void setRss_version(String rss_version) {
        this.rss_version = rss_version;
    }

    public int getRss_del() {
        return rss_del;
    }

    public void setRss_del(int rss_del) {
        this.rss_del = rss_del;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
