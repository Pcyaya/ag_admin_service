
package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RyOrder;


/**
 * Created by cheri on 2018/8/7.
 */
public class AppOrderBean extends RyOrder {
    private String phone;//账号
    private String name;//昵称
    private String commodity_num;//商品编码
    private String business;//业务类型
    private String business_name;//业务类型名称
    private String pay_name;//交易方式
    private String status;//订单状态
    private String status_name;//订单状态名称

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommodity_num() {
        return commodity_num;
    }

    public void setCommodity_num(String commodity_num) {
        this.commodity_num = commodity_num;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
