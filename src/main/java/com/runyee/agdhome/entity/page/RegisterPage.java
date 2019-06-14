package com.runyee.agdhome.entity.page;


/**
 * Created by cheri on 2018/8/7.
 */
public class RegisterPage {
    private int register_flg;//1.已注册
    private String download;//下载页
    private String app_url;//app下载地址

    public int getRegister_flg() {
        return register_flg;
    }

    public void setRegister_flg(int register_flg) {
        this.register_flg = register_flg;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getApp_url() {
        return app_url;
    }

    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }
}
