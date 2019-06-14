package com.runyee.agdhome.entity.db.anygo;


import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyGuideValid {
    private 	String	rgv_id;	 //uuid
    private 	String	rgv_num;	 //验证编码
    private     String  rgv_visitor;//申请人
    private 	String	rgv_img;	 //手持导游证相片
    private 	String	rgv_name;	 //姓名
    private 	String	rgv_card;	 //第三方导游证号
    private 	String	rgv_phone;	 //手机号
    private 	String	rgv_plus;	 //导游证正面
    private 	String	rgv_minus;	 //
    private 	Date	rgv_issued_date;	 //生效时间
    private 	Date	rgv_expired_date;	 //过期时间
    private 	int	rgv_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRgv_id() {
        return rgv_id;
    }

    public void setRgv_id(String rgv_id) {
        this.rgv_id = rgv_id;
    }

    public String getRgv_num() {
        return rgv_num;
    }

    public void setRgv_num(String rgv_num) {
        this.rgv_num = rgv_num;
    }

    public String getRgv_visitor() {
        return rgv_visitor;
    }

    public void setRgv_visitor(String rgv_visitor) {
        this.rgv_visitor = rgv_visitor;
    }

    public String getRgv_img() {
        return rgv_img;
    }

    public void setRgv_img(String rgv_img) {
        this.rgv_img = rgv_img;
    }

    public String getRgv_name() {
        return rgv_name;
    }

    public void setRgv_name(String rgv_name) {
        this.rgv_name = rgv_name;
    }

    public String getRgv_card() {
        return rgv_card;
    }

    public void setRgv_card(String rgv_card) {
        this.rgv_card = rgv_card;
    }

    public String getRgv_phone() {
        return rgv_phone;
    }

    public void setRgv_phone(String rgv_phone) {
        this.rgv_phone = rgv_phone;
    }

    public String getRgv_plus() {
        return rgv_plus;
    }

    public void setRgv_plus(String rgv_plus) {
        this.rgv_plus = rgv_plus;
    }

    public String getRgv_minus() {
        return rgv_minus;
    }

    public void setRgv_minus(String rgv_minus) {
        this.rgv_minus = rgv_minus;
    }

    public Date getRgv_issued_date() {
        return rgv_issued_date;
    }

    public void setRgv_issued_date(Date rgv_issued_date) {
        this.rgv_issued_date = rgv_issued_date;
    }

    public Date getRgv_expired_date() {
        return rgv_expired_date;
    }

    public void setRgv_expired_date(Date rgv_expired_date) {
        this.rgv_expired_date = rgv_expired_date;
    }

    public int getRgv_del() {
        return rgv_del;
    }

    public void setRgv_del(int rgv_del) {
        this.rgv_del = rgv_del;
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
