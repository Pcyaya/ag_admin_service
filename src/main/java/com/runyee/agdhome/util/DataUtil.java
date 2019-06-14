package com.runyee.agdhome.util;


import com.runyee.agdhome.entity.db.ag_home.AhUser;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    private static final ThreadLocal LOCAL = new ThreadLocal() {
        //重写 initialValue
        protected synchronized Object initialValue() {
          return new DataUtil();
        }
    };

    private DataUtil() {
    }
    
    public static DataUtil getCurrent() {
        return (DataUtil) LOCAL.get();
    }

    private String access_token;
    private AhUser user;
    private List<String> msgs = new ArrayList<>();
    private int port;//端口号
    private String db;


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public AhUser getUser() {
        return user;
    }

    public void setUser(AhUser user) {
        this.user = user;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
