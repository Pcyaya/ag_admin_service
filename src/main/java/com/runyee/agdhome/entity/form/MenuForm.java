package com.runyee.agdhome.entity.form;


/**
 * Created by cheri on 2018/8/7.
 */
public class MenuForm {
    private     String  id;//唯一标识
    private 	String	name;	 //联系名称
    private 	String	url;	 //路由
    private 	String	pid;	 //父级菜单
    private     String  icon;    //图标
    private     int view;       // 1所有人可见
    private 	int	right_flg;	 //1.关联权限

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getRight_flg() {
        return right_flg;
    }

    public void setRight_flg(int right_flg) {
        this.right_flg = right_flg;
    }

}
