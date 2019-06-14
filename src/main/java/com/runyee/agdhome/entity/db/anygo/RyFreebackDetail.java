package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyFreebackDetail {
    private 	String	rfd_id;	 //uuid
    private 	String	rfd_freeback;	 //反馈id
    private 	String	rfd_content;	 //内容
    private 	int	rfd_type;	 //0.文本,1.图片,2.语音,3视频
    private 	String	rfd_url;	 //
    private 	int	rfd_del;	 //1.删除
    private Date create_date;	 //
    private 	Date	update_date;	 //

    public String getRfd_id() {
        return rfd_id;
    }

    public void setRfd_id(String rfd_id) {
        this.rfd_id = rfd_id;
    }

    public String getRfd_freeback() {
        return rfd_freeback;
    }

    public void setRfd_freeback(String rfd_freeback) {
        this.rfd_freeback = rfd_freeback;
    }

    public String getRfd_content() {
        return rfd_content;
    }

    public void setRfd_content(String rfd_content) {
        this.rfd_content = rfd_content;
    }

    public int getRfd_type() {
        return rfd_type;
    }

    public void setRfd_type(int rfd_type) {
        this.rfd_type = rfd_type;
    }

    public String getRfd_url() {
        return rfd_url;
    }

    public void setRfd_url(String rfd_url) {
        this.rfd_url = rfd_url;
    }

    public int getRfd_del() {
        return rfd_del;
    }

    public void setRfd_del(int rfd_del) {
        this.rfd_del = rfd_del;
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
