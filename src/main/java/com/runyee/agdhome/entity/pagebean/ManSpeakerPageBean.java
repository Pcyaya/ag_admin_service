package com.runyee.agdhome.entity.pagebean;


import com.runyee.agdhome.entity.PageBean;

/**
 * Created by cheri on 2018/8/7.
 */
public class ManSpeakerPageBean extends PageBean{
    private String visitor;//绑定人
    private String speak_num;//设备编码
    private int test;//测试设备
    private int online;//在线
    private int active;//激活
    private int bind;//是否被绑定 1.未绑定 //1.已经绑定
    private String card;//设备序列号
    private int manage;//是否被管理 0.未管理 1.已管理
    private String manager;//管理者名称
    private String wifi;//艾侗游网名称

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getSpeak_num() {
        return speak_num;
    }

    public void setSpeak_num(String speak_num) {
        this.speak_num = speak_num;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getManage() {
        return manage;
    }

    public void setManage(int manage) {
        this.manage = manage;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }
}
