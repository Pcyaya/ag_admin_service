package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/8/7.
 */
public class UrgentForm {
    private     String  id;//唯一标识
    private 	String	name;	 //版本
    private     int     sync;//1.同步

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

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }


}
