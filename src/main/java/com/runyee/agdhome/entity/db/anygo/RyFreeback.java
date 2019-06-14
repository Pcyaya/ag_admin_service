package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyFreeback {
    private 	String	rf_id;	 //uuid
    private 	int	rf_type;	 //反馈类型 0.其他 1.音箱设备 2.软件功能
    private     String rf_visitor;//反馈人
    private 	String	rf_token;	 //反馈用户token
    private 	String	rf_content;	 //反馈内容
    private 	Date	rf_time;	 //反馈时间
    private 	String	rf_speaker_num;	 //设备编码
    private 	int	rf_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRf_id() {
        return rf_id;
    }

    public void setRf_id(String rf_id) {
        this.rf_id = rf_id;
    }

    public int getRf_type() {
        return rf_type;
    }

    public void setRf_type(int rf_type) {
        this.rf_type = rf_type;
    }

    public String getRf_visitor() {
        return rf_visitor;
    }

    public void setRf_visitor(String rf_visitor) {
        this.rf_visitor = rf_visitor;
    }

    public String getRf_token() {
        return rf_token;
    }

    public void setRf_token(String rf_token) {
        this.rf_token = rf_token;
    }

    public String getRf_content() {
        return rf_content;
    }

    public void setRf_content(String rf_content) {
        this.rf_content = rf_content;
    }

    public Date getRf_time() {
        return rf_time;
    }

    public void setRf_time(Date rf_time) {
        this.rf_time = rf_time;
    }

    public String getRf_speaker_num() {
        return rf_speaker_num;
    }

    public void setRf_speaker_num(String rf_speaker_num) {
        this.rf_speaker_num = rf_speaker_num;
    }

    public int getRf_del() {
        return rf_del;
    }

    public void setRf_del(int rf_del) {
        this.rf_del = rf_del;
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
