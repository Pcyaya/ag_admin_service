package com.runyee.agdhome.entity.autopage;


import com.runyee.agdhome.entity.AutoPage;

/**
 * Created by cheri on 2018/8/7.
 */
public class VisitorAutoPage extends AutoPage{
    private String num;//
    private String phone;//联系方式

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
