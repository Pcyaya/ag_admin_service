
package com.runyee.agdhome.entity.ex;

import com.runyee.agdhome.entity.db.ag_home.AhAgentApply;
import com.runyee.agdhome.entity.db.ag_home.AhFreeback;

import java.util.Date;


/**
 * Created by cheri on 2018/8/7.
 */
public class ManAhAgentBean extends AhAgentApply {

    private String handle_user;//处理人id
    private String handle_name;//处理人
    private int handle_schedule;//处理进度
    private String schedule_name;//处理进度名称
    private String  handle_results;//处理结果
    private String results_name;//处理结果名称
    private Date date_handle;//处理时间
    private String tags;//标签


    public String getResults_name() {
        return results_name;
    }

    public void setResults_name(String results_name) {
        this.results_name = results_name;
    }

    public String getHandle_user() {
        return handle_user;
    }

    public void setHandle_user(String handle_user) {
        this.handle_user = handle_user;
    }

    public String getHandle_name() {
        return handle_name;
    }

    public void setHandle_name(String handle_name) {
        this.handle_name = handle_name;
    }

    public int getHandle_schedule() {
        return handle_schedule;
    }

    public void setHandle_schedule(int handle_schedule) {
        this.handle_schedule = handle_schedule;
    }

    public String getSchedule_name() {
        return schedule_name;
    }

    public void setSchedule_name(String schedule_name) {
        this.schedule_name = schedule_name;
    }

    public String  getHandle_results() {
        return handle_results;
    }

    public void setHandle_results(String handle_results) {
        this.handle_results = handle_results;
    }

    public Date getDate_handle() {
        return date_handle;
    }

    public void setDate_handle(Date date_handle) {
        this.date_handle = date_handle;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


}
