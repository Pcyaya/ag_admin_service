package com.runyee.agdhome.entity.page;


import java.util.List;

/**
 * Created by cheri on 2018/3/29.
 */
public class UseHelpCategoryPage {
    private String id;//唯一标识
    private String icon;//图标
    private String name;//名称
    private int sync;//app同步
    //private List<UseHelpPage> usehelps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    /*public List<UseHelpPage> getUsehelps() {
        return usehelps;
    }

    public void setUsehelps(List<UseHelpPage> usehelps) {
        this.usehelps = usehelps;
    }*/
}
