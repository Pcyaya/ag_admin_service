package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.OrderDao;
import com.runyee.agdhome.dao.ag_home.RecruitDao;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.ex.ManAhAgentBean;
import com.runyee.agdhome.entity.ex.OrderBean;
import com.runyee.agdhome.entity.form.FreebackHandleForm;
import com.runyee.agdhome.entity.form.OrderForm;
import com.runyee.agdhome.entity.form.OrderHandleForm;
import com.runyee.agdhome.entity.form.RecruitApplyForm;
import com.runyee.agdhome.entity.page.RecruitBusinessPage;
import com.runyee.agdhome.entity.page.RecruitDetailPage;
import com.runyee.agdhome.entity.page.RecruitPage;
import com.runyee.agdhome.entity.pagebean.ManAppFreeBackPageBean;
import com.runyee.agdhome.entity.pagebean.ManOrderPageBean;
import com.runyee.agdhome.entity.pagebean.RecruitPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao dao;
    @Autowired
    private UserDao userDao;

    public String apply(OrderForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getName())
                    &&!ConvertUtil.isEmpty(form.getPhone())
                    &&!ConvertUtil.isEmpty(form.getAddr())
                    &&!ConvertUtil.isEmpty(form.getContent())){
                Date date = new Date();

                String filter = "";
                filter += "   and timestampdiff(hour,create_date,'" + DateUtils.getTimestamp(date) + "') between  0 and 24     ";
                List<AhOrder> orders = dao.getOrdersPhone(form.getPhone(),filter);
                if(orders!=null&&orders.size()>0){
                    //每天最多5次
                    if(orders.size()<5){
                        AhOrder order = orders.get(0);
                        if(order!=null &&
                                !ConvertUtil.isEmpty(order.getAo_id())&&
                                order.getCreate_date()!=null){
                            //重复操作
                            long interval = date.getTime()-order.getCreate_date().getTime();
                            if(interval <= (DateUtils.SECOND_IN_MILLIS)){
                                service_code = ServiceCode.fail_order_second;
                            }else{
                                //五分钟内只能有一次
                                if(interval <= (5*DateUtils.MINUTE_IN_MILLIS)){
                                    service_code = ServiceCode.fail_order_minute;
                                }else{
                                    service_code = ServiceCode.SUCCESS;
                                }
                            }
                        }

                    }else{
                        service_code = ServiceCode.fail_order_day;
                    }
                }else{
                    service_code = ServiceCode.SUCCESS;
                }

                if(ServiceCode.SUCCESS.equals(service_code)){
                    AhOrder order = new AhOrder();
                    order.setAo_name(form.getName());
                    order.setAo_phone(form.getPhone());
                    order.setAo_addr(form.getAddr());
                    order.setAo_content(form.getContent());
                    order.setDel(0);
                    order.setCreate_date(date);
                    dao.insertOrder(order);
                    service_code = ServiceCode.success_apply;
                }
            }else{
                service_code = ServiceCode.fail_apply_necessary;
                List<String> msgs = new ArrayList<>();
                String msg = "您的姓名";
                if(!ConvertUtil.isEmpty(form.getName())){
                    if(ConvertUtil.isEmpty(form.getPhone())){
                        msg = "您的电话";
                    }else if(ConvertUtil.isEmpty(form.getAddr())){
                        msg = "您的地址";
                    }else if(ConvertUtil.isEmpty(form.getContent())){
                        msg = "您的产品描述";
                    }
                }
                msgs.add(msg);
                DataUtil.getCurrent().setMsgs(msgs);
            }

        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    // 订购 列表
    public String list(ManOrderPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getDate())){
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getDate()+"' ";
            }
            //处理人
            if(!ConvertUtil.isEmpty(pageBean.getDeal())){
                filter += " and exists(select aoh.* " +
                        "                  from ah_order_handle aoh " +
                        "                  where ifnull(aoh.del,0)=0  " +
                        "                  and aoh.aoh_order = ao.ao_id and  aoh.aoh_user='"+pageBean.getDeal()+"' )";
            }
            //手机号
            if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                filter +="   and ao_phone like  '%"+pageBean.getPhone()+"%'  " ;

            }
            //颜色
            if(!ConvertUtil.isEmpty(pageBean.getColor())){
                filter += " and exists(select aoh.* " +
                        "                  from ah_order_handle aoh " +
                        "                  where ifnull(aoh.del,0)=0  " +
                        "                  and aoh.aoh_order = ao.ao_id and  aoh.aoh_color='"+pageBean.getColor()+"' )";
            }
            //付款类型
            if(pageBean.getPaytype()>=0){
                filter += " and exists(select aoh.* " +
                        "                  from ah_order_handle aoh " +
                        "                  where ifnull(aoh.del,0)=0  " +
                        "                  and aoh.aoh_order = ao.ao_id and  aoh.aoh_paytype='"+(pageBean.getPaytype())+"' ) ";
            }
            //付款方式
            if(pageBean.getPayflat()>=0){
                filter += " and exists(select aoh.* " +
                        "                  from ah_order_handle aoh " +
                        "                  where ifnull(aoh.del,0)=0  " +
                        "                  and aoh.aoh_order = ao.ao_id and  aoh.aoh_payflat='"+(pageBean.getPayflat())+"' ) ";
            }
            //处理进度
            if(pageBean.getHandle_schedule()>=0){
                if(pageBean.getHandle_schedule()==0){
                    filter += " and not exists(select aoh.* " +
                            "                  from ah_order_handle aoh " +
                            "                  where ifnull(aoh.del,0)=0 " +
                            "                  and aoh.aoh_order = ao.ao_id ) ";
                }else{
                    filter += " and exists(select aoh.* " +
                            "                  from ah_order_handle aoh " +
                            "                  where ifnull(aoh.del,0)=0  " +
                            "                  and aoh.aoh_order = ao.ao_id and  aoh.aoh_status='"+(pageBean.getHandle_schedule()-1)+"' ) ";
                }
            }


            //总数
            int total = dao.getOrderCount(filter);
            pageBean.setTotal(total);
            List<OrderBean> list = dao.getOrderBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

//            if(list!=null && list.size()> 0){
//                String user_ids = "0";
//                for(OrderBean order :list ){
//                    if(!ConvertUtil.isEmpty(order.getHandle_user())){
//                        user_ids += ",'"+order.getHandle_user()+"' ";
//                    }
//                }
//                if(!"0".equals(user_ids)){
//                    List<AhUser> users = userDao.getUsersKeys(user_ids);
//                    if(users!=null && users.size()>0){
//                        Map<String,AhUser> userMap = new HashMap<>();
//                        for(AhUser user:users){
//                            userMap.put(user.getAu_id(),user);
//                        }
//                        for(OrderBean order :list ){
//                            AhUser user = userMap.get(order.getHandle_user());
//                            if(user!=null){
//                                order.setHandle_name(user.getAu_name());
//                            }
//                        }
//                    }
//                }
                pageBean.setData(list);
//            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }
        return service_code;
    }

    public String info(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            OrderBean order = dao.getOrderBeanOneKey(id);
            if(order!=null && !ConvertUtil.isEmpty(order.getAo_id())){
                AhOrderHandle handle =this.getHandleOnly(order,user);
                if(handle!=null){
                    order.setHandle_schedule(handle.getAoh_status());
                    order.setHandle_results(handle.getAoh_content());
                }
                app.setObj(order);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public AhOrderHandle getHandleOnly(AhOrder order, AhUser user){
        AhOrderHandle handle = null;
        if(order!=null && !ConvertUtil.isEmpty(order.getAo_id())){
            Date date = new Date();
            handle = dao.getHandleOrderOneKey(order.getAo_id());
            if(handle==null || ConvertUtil.isEmpty(handle.getAoh_id())){
                if(handle==null || ConvertUtil.isEmpty(handle.getAoh_id())){
                    handle = new AhOrderHandle();
                    handle.setAoh_order(order.getAo_id());
                    String handle_user= "";//默认系统自己解决
                    if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                        handle_user = user.getAu_id();
                    }
                    handle.setAoh_user(handle_user);
                    handle.setAoh_status(0);
                    handle.setAoh_content("");
                    handle.setDel(0);
                    handle.setCreate_date(date);

                    dao.insertAgentHandle(handle);
                }
            }
        }
        return handle;
    }

    //处理
    public String handle(OrderHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                OrderBean order = dao.getOrderBeanOneKey(form.getId());
                if(order!=null && !ConvertUtil.isEmpty(order.getAo_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    AhOrderHandle handle =this.getHandleOnly(order,user);
                    if(handle!=null){
                        handle.setAoh_status(form.getStatus());
                        handle.setAoh_content(form.getAoh_content());
                        handle.setAoh_name(form.getAoh_name());
                        handle.setAoh_color(form.getAoh_color());
                        handle.setAoh_price(form.getAoh_price());
                        handle.setAoh_amount(form.getAoh_amount());
                        handle.setAoh_total(form.getAoh_total());
                        handle.setAoh_paytype(form.getAoh_paytype());
                        handle.setAoh_payflat(form.getAoh_payflat());
                        handle.setAoh_trans(form.getAoh_trans());
                        handle.setAoh_transno(form.getAoh_transno());
                        handle.setAoh_user(access_key);
                        //更新 处理结果
                        dao.updateOrderHandle(handle);

                        order.setAo_name(form.getAo_name());
                        order.setAo_addr(form.getAo_addr());
                        order.setAo_content(form.getAo_content());
                        //更新订单人信息
                        dao.updateOrderInfo(order);

                        app.setObj(order);
                        service_code  = ServiceCode.SUCCESS;
                    }
                }else{
                    service_code = ServiceCode.fail_man_report_del;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhOrder Order  = dao.getOrderOneKey(id);
            if(Order!=null && !ConvertUtil.isEmpty(Order.getAo_id())){
                Order.setDel(1);
                dao.updateOrderDel(Order);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

}
