package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RySurvivalCapsule {
    private 	String	rsc_id;	 //uuid
    private 	String	rsc_survival;	 //
    private 	String	rsc_title;	 //标题
    private 	String	rsc_content;	 //内容
    private 	String	rsc_font_size;	 //字体大小 px
    private 	int	rsc_align;	 //0.左对齐 1.居中 2.右对齐 -n 空格数
    private 	int	rsc_del;	 //1.删除
    private     Date create_date;	 //
    private 	Date	update_date;	 //

    public String getRsc_id() {
        return rsc_id;
    }

    public void setRsc_id(String rsc_id) {
        this.rsc_id = rsc_id;
    }

    public String getRsc_survival() {
        return rsc_survival;
    }

    public void setRsc_survival(String rsc_survival) {
        this.rsc_survival = rsc_survival;
    }

    public String getRsc_title() {
        return rsc_title;
    }

    public void setRsc_title(String rsc_title) {
        this.rsc_title = rsc_title;
    }

    public String getRsc_content() {
        return rsc_content;
    }

    public void setRsc_content(String rsc_content) {
        this.rsc_content = rsc_content;
    }

    public String getRsc_font_size() {
        return rsc_font_size;
    }

    public void setRsc_font_size(String rsc_font_size) {
        this.rsc_font_size = rsc_font_size;
    }

    public int getRsc_align() {
        return rsc_align;
    }

    public void setRsc_align(int rsc_align) {
        this.rsc_align = rsc_align;
    }

    public int getRsc_del() {
        return rsc_del;
    }

    public void setRsc_del(int rsc_del) {
        this.rsc_del = rsc_del;
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
