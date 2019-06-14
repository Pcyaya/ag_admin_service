package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/3/29.
 */
public class UrgentPhonePage {
    private String id;//唯一标识
    private String title;//标题
    private String phone;//紧急电话
    private int sync;//1.同步
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }
}
