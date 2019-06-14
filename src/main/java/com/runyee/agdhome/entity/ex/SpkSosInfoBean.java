package com.runyee.agdhome.entity.ex;


/**
 * Created by cheri on 2018/8/7.
 */
public class SpkSosInfoBean extends SpkSosBean{
    private int confirm;//0未确认1已确认
    private int operate;//0误操作1危险事故2电话无法接通
    private String content;//备注
    private int inform;//0未通知1.已通知

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getInform() {
        return inform;
    }

    public void setInform(int inform) {
        this.inform = inform;
    }
}
