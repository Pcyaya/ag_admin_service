package com.runyee.agdhome.entity.page;


/**
 * Created by cheri on 2018/8/7.
 */
public class VisitorCardPage extends CardPage{
    private int sex;//性别
    private int age;//年龄
    private String phone;//电话

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
