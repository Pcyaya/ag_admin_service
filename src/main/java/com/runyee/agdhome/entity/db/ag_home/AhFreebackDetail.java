package com.runyee.agdhome.entity.db.ag_home;

import java.util.Date;

/**
 * Created by cheri on 2018/8/7.
 */
public class AhFreebackDetail {
    private 	String	afd_id;	 //uuid
    private 	String	afd_freeback;	 //反馈id
    private 	String	afd_content;	 //内容
    private 	int	afd_type;	 //0.文本,1.图片,2.语音,3视频
    private 	String	afd_url;	 //
    private 	int	del;	 //1删除
    private     Date create_date;	 //
    private 	Date	update_date;	 //

    public String getAfd_id() {
        return afd_id;
    }

    public void setAfd_id(String afd_id) {
        this.afd_id = afd_id;
    }

    public String getAfd_freeback() {
        return afd_freeback;
    }

    public void setAfd_freeback(String afd_freeback) {
        this.afd_freeback = afd_freeback;
    }

    public String getAfd_content() {
        return afd_content;
    }

    public void setAfd_content(String afd_content) {
        this.afd_content = afd_content;
    }

    public int getAfd_type() {
        return afd_type;
    }

    public void setAfd_type(int afd_type) {
        this.afd_type = afd_type;
    }

    public String getAfd_url() {
        return afd_url;
    }

    public void setAfd_url(String afd_url) {
        this.afd_url = afd_url;
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
