package com.runyee.agdhome.entity.form;

/**
 * Created by cheri on 2018/8/7.
 */
public class CommodityTmpForm {
    private String id;
    private String name;//商品名称
    private String icon;//商品图片
    private int hot;//火爆标志
    private int recommend;//推荐
    private int business;//0.商品，1.优惠券
    private String rel;//关联类型id
    private int vprice;//虚拟价格（单位分）
    private int rprice;//实际价格（单位分）
    private int coin;//游币数量
    private int online;//0.下架1.上架
    private int limit_amount;//限制销售数量


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public int getVprice() {
        return vprice;
    }

    public void setVprice(int vprice) {
        this.vprice = vprice;
    }

    public int getRprice() {
        return rprice;
    }

    public void setRprice(int rprice) {
        this.rprice = rprice;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getLimit_amount() {
        return limit_amount;
    }

    public void setLimit_amount(int limit_amount) {
        this.limit_amount = limit_amount;
    }
}
