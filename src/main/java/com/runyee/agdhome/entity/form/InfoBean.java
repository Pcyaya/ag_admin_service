package com.runyee.agdhome.entity.form;



/**
 * Created by cheri on 2018/3/29.
 */
public class InfoBean {
    private String id;
    private String num;
    private String icon;//头像
    private String name;//昵称
    private int sex;//性别
    private String phone;//手机号
    private int age;//年龄
    private long birth;//生日
    private long phone_flg;//0.不可拨打1.可拨打
    private int friend_flg;//0.非侗友1.侗友

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public long getPhone_flg() {
        return phone_flg;
    }

    public void setPhone_flg(long phone_flg) {
        this.phone_flg = phone_flg;
    }

    public int getFriend_flg() {
        return friend_flg;
    }

    public void setFriend_flg(int friend_flg) {
        this.friend_flg = friend_flg;
    }

    /*public int getGuide_flg() {
        return guide_flg;
    }

    public void setGuide_flg(int guide_flg) {
        this.guide_flg = guide_flg;
    }

    public int getGroup_flg() {
        return group_flg;
    }

    public void setGroup_flg(int group_flg) {
        this.group_flg = group_flg;
    }*/
}
