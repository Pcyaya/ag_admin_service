package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/8/7.
 */
public class UPhoneForm {
    private     String  id;//唯一标识
    private     String  urgent;//国家id
    private 	String	title;	 //标题
    private 	String	phone;	 //标题
    private     int     sync;//1同步



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
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
