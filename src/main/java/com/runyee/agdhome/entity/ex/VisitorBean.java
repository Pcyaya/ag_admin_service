
package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.anygo.RyVisitor;


/**
 * Created by cheri on 2018/8/7.
 */
public class VisitorBean extends RyVisitor {
    private String source_name;//来源名称
    private int bind_flg;//1.已绑定
    private String bind_no;//绑定设备编码
    private int valid_status;//认证状态 -2.未认证 -1.待验证 0.验证中 1.验证通过 2.验证失败 3.验证失效
    private String status_name;//状态名称
    private int coin_amount;//游币数
    private int convert_amount;//游币兑换数量


    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public int getBind_flg() {
        return bind_flg;
    }

    public void setBind_flg(int bind_flg) {
        this.bind_flg = bind_flg;
    }

    public String getBind_no() {
        return bind_no;
    }

    public void setBind_no(String bind_no) {
        this.bind_no = bind_no;
    }

    public int getValid_status() {
        return valid_status;
    }

    public void setValid_status(int valid_status) {
        this.valid_status = valid_status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public int getCoin_amount() {
        return coin_amount;
    }

    public void setCoin_amount(int coin_amount) {
        this.coin_amount = coin_amount;
    }

    public int getConvert_amount() {
        return convert_amount;
    }

    public void setConvert_amount(int convert_amount) {
        this.convert_amount = convert_amount;
    }
}
