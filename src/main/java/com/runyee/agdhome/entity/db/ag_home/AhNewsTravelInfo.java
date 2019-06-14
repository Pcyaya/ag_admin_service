package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhNewsTravelInfo {
    private 	String	anti_id;	 //uuid
    private 	String	anti_news;	 //新闻id
    private 	int	del;	 //
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getAnti_id() {
        return anti_id;
    }

    public void setAnti_id(String anti_id) {
        this.anti_id = anti_id;
    }

    public String getAnti_news() {
        return anti_news;
    }

    public void setAnti_news(String anti_news) {
        this.anti_news = anti_news;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
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
