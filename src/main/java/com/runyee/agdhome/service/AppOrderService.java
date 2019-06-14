package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.anygo.AgOrderDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.ex.AppOrderBean;
import com.runyee.agdhome.entity.pagebean.ManAppOrderPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class AppOrderService {
    @Autowired
    private AgOrderDao dao;

    // 商品模板 列表
    public String list(ManAppOrderPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";

            if(!ConvertUtil.isEmpty(pageBean.getNum())){
                filter +="   and ro_num like '%"+pageBean.getNum()+"%' ";
            }

            if(!ConvertUtil.isEmpty(pageBean.getVisitor())){
                filter +="   and ro_visitor = '"+pageBean.getVisitor()+"' ";
            }

            if(!ConvertUtil.isEmpty(pageBean.getCommodity_num())){
                filter +="   and ro_visitor = '"+pageBean.getVisitor()+"' ";
            }

            if(!ConvertUtil.isEmpty(pageBean.getTransaction_date())){
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getTransaction_date()+"' ";
            }

            if(pageBean.getBusiness()>=0){
                filter +="   and ro_status = '"+pageBean.getBusiness()+"' ";
            }


            if(pageBean.getTransaction_type()>0){

            }

            if(!ConvertUtil.isEmpty(pageBean.getTransaction_date())){

            }

            if(pageBean.getStatus()>=0){
                filter +="   and ro_status = '"+pageBean.getBusiness()+"' ";
            }

            //总数
            int total = dao.getOrderBeanCount(filter);
            pageBean.setTotal(total);
            List<AppOrderBean> list = dao.getOrderBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                pageBean.setData(list);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }
        return service_code;
    }
}
