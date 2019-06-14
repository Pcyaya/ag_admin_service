package com.runyee.agdhome.entity.page;


/**
 * Created by cheri on 2018/8/7.
 */
public class BusinessPage {
    private String id;
    private String name;//名称
    private int show_flg;//1.选中

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

    public int getShow_flg() {
        return show_flg;
    }

    public void setShow_flg(int show_flg) {
        this.show_flg = show_flg;
    }
}
