package com.runyee.agdhome.entity.db.anygo;

import java.util.Date;

/**
 * Created by cheri on 2018/3/8.
 */
public class RyCouponTmp {
    private 	String	rct_id;	 //uuid
    private 	int	rct_den;	 //优惠券面额
    private 	String	rct_unit;	 //优惠券单位
    private 	int	rct_type;	 //优惠券类型
    private 	int	rct_inteval;	 //时效-1.永不过期 -2时限
    private 	Date	rct_start;//开始日期
    private 	Date	rct_end;//结束日期
    private     String rct_backup;//被备份模板id
    private 	int	rct_del;	 //1.删除
    private 	Date	create_date;	 //
    private 	Date	update_date;	 //

    public String getRct_id() {
        return rct_id;
    }

    public void setRct_id(String rct_id) {
        this.rct_id = rct_id;
    }

    public int getRct_den() {
        return rct_den;
    }

    public void setRct_den(int rct_den) {
        this.rct_den = rct_den;
    }

    public String getRct_unit() {
        return rct_unit;
    }

    public void setRct_unit(String rct_unit) {
        this.rct_unit = rct_unit;
    }

    public int getRct_type() {
        return rct_type;
    }

    public void setRct_type(int rct_type) {
        this.rct_type = rct_type;
    }

    public int getRct_inteval() {
        return rct_inteval;
    }

    public void setRct_inteval(int rct_inteval) {
        this.rct_inteval = rct_inteval;
    }

    public Date getRct_start() {
        return rct_start;
    }

    public void setRct_start(Date rct_start) {
        this.rct_start = rct_start;
    }

    public Date getRct_end() {
        return rct_end;
    }

    public void setRct_end(Date rct_end) {
        this.rct_end = rct_end;
    }

    public String getRct_backup() {
        return rct_backup;
    }

    public void setRct_backup(String rct_backup) {
        this.rct_backup = rct_backup;
    }

    public int getRct_del() {
        return rct_del;
    }

    public void setRct_del(int rct_del) {
        this.rct_del = rct_del;
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
