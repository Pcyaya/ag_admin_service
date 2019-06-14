package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/7/30.
 */
public class RyUseHelpDetail {
    private 	String	rud_id;	 //uuid
    private 	String	rud_usehelp;	 //使用帮助
    private 	String	rud_content;	 //
    private 	int	rud_type;	 //0.文本,1.图片,2.语音,3视频 4.文件
    private 	String	rud_url;	 //url
    private 	int	rud_del;	 //
    private 	Date	create_date;	 //
    private     Date update_date;	 //

    public String getRud_id() {
        return rud_id;
    }

    public void setRud_id(String rud_id) {
        this.rud_id = rud_id;
    }

    public String getRud_usehelp() {
        return rud_usehelp;
    }

    public void setRud_usehelp(String rud_usehelp) {
        this.rud_usehelp = rud_usehelp;
    }

    public String getRud_content() {
        return rud_content;
    }

    public void setRud_content(String rud_content) {
        this.rud_content = rud_content;
    }

    public int getRud_type() {
        return rud_type;
    }

    public void setRud_type(int rud_type) {
        this.rud_type = rud_type;
    }

    public String getRud_url() {
        return rud_url;
    }

    public void setRud_url(String rud_url) {
        this.rud_url = rud_url;
    }

    public int getRud_del() {
        return rud_del;
    }

    public void setRud_del(int rud_del) {
        this.rud_del = rud_del;
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
