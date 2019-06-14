package com.runyee.agdhome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.runyee.agdhome.util.ConvertUtil;

/**
 * Created by cheri on 2018/3/16.
 */
public class PageBean {
    private int page;
    private int rows;
    private int total;
    private int totalpage;
    private Object data;
    private String field;// 排序字段名
    private String sort;// 按什么排序(asc,desc)
    public int getPage() {
        if(page<=0){
            page = 1;
        }
        return page;
    }

    public int getCurrPage() {
        int currpage = 0;
        currpage = ((this.getPage()-1)*this.getRows());
        return currpage;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        if(rows<=0){
            rows = 10;
            if(rows<0){
                rows = 1000;
            }
        }else{
            if(rows>1000){
                rows = 1000;
            }
        }
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalpage() {
        if(totalpage<=0){
            totalpage = (int) Math.ceil((this.getTotal()/(this.getRows()* 1.0f)));
        }
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @JsonIgnore
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @JsonIgnore
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder(){
        String order = "";
        if(!ConvertUtil.isEmpty(this.field)){
            order += " order by "+this.field;
            if(ConvertUtil.isEmpty(this.sort)){
                this.sort = "desc";
            }
            order += " "+this.sort;
        }
        if(ConvertUtil.isEmpty(order)){
            order = this.getDefaultOrder();
        }
        return order;
    }

    public String getDefaultOrder(){
        return " order by create_date desc ";
    }

}
