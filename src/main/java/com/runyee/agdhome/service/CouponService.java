package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.AgCouponDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.anygo.*;
import com.runyee.agdhome.entity.ex.CouponTmpBean;
import com.runyee.agdhome.entity.form.CouponTmpForm;
import com.runyee.agdhome.entity.pagebean.ManCouponTmpPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class CouponService {
    @Autowired
    private AgCouponDao dao;
    @Autowired
    private UserDao userDao;


    //优惠券模板 列表
    public String tmp_list(ManCouponTmpPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";

            if(pageBean.getRct_type()>=0){
                filter +="   and rct_type = '"+pageBean.getRct_type()+"' ";
            }
            if(!ConvertUtil.isEmpty(pageBean.getDate())){
                //创建时间日期
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getDate()+"' ";//:%s

            }

            //总数
            int total = dao.getCouponTmpBeanCount(filter);
            pageBean.setTotal(total);
            List<CouponTmpBean> list = dao.getCouponTmpBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                pageBean.setData(list);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }
        return service_code;
    }

    public String tmp_del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyCouponTmp tmp  = dao.getCouponTmpOneKey(id);
            if(tmp!=null && !ConvertUtil.isEmpty(tmp.getRct_id())){
                if(ConvertUtil.isEmpty(tmp.getRct_backup())){
                    tmp.setRct_del(1);
                    dao.updateCouponTmpDel(tmp);
                    service_code  = ServiceCode.SUCCESS;
                }else{
                    service_code = ServiceCode.fail_man_tmpbackup_undel;
                }
            }else{
                service_code = ServiceCode.fail_man_tmp_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String tmp_issue(CouponTmpForm formBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(formBean!=null){
            service_code = this.coupon_tmp_valid(formBean);
            if(ServiceCode.SUCCESS.equals(service_code)){
                String id = formBean.getId();
                RyCouponTmp tmp = null;
                if(!ConvertUtil.isEmpty(id)){
                    //更新
                    tmp = dao.getCouponTmpOneKey(id);
                    if(tmp!=null && !ConvertUtil.isEmpty(tmp.getRct_id())){
                        if(ConvertUtil.isEmpty(tmp.getRct_backup())){
                            tmp.setRct_den(formBean.getDen());
                            tmp.setRct_unit(formBean.getUnit());
                            tmp.setRct_type(formBean.getType());
                            tmp.setRct_inteval(formBean.getInterval());
                            if(tmp.getRct_inteval()==-2){
                                tmp.setRct_start(DateUtils.getDate(formBean.getStart()));
                                tmp.setRct_end(DateUtils.getDate(formBean.getEnd()));
                            }
                            dao.updateCouponTmp(tmp);
                            service_code  = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_tmpbackup_undel;
                        }
                    }else{
                        service_code = ServiceCode.fail_man_tmp_del;
                    }
                }else{
                    //新增
                    tmp = new RyCouponTmp();
                    tmp.setRct_den(formBean.getDen());
                    tmp.setRct_unit(formBean.getUnit());
                    tmp.setRct_type(formBean.getType());
                    tmp.setRct_inteval(formBean.getInterval());
                    if(tmp.getRct_inteval()==-2){
                        tmp.setRct_start(DateUtils.getDate(formBean.getStart()));
                        tmp.setRct_end(DateUtils.getDate(formBean.getEnd()));
                    }
                    tmp.setRct_backup("");
                    tmp.setRct_del(0);
                    tmp.setCreate_date(DateUtils.getDate());

                    dao.insertCouponTmp(tmp);
                    service_code = ServiceCode.SUCCESS;
                }
            }

        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String coupon_tmp_valid(CouponTmpForm formBean){
        String service_code = ServiceCode.ERRO;
        if(formBean!=null){
            //验证面额
            if(formBean.getDen()>0){
                //验证时效
                if(formBean.getInterval()>=-2&&formBean.getInterval()!=0){
                    //类型单位
                    if(formBean.getType()==0){//0流量券
                        if("G".equals(formBean.getUnit())||"g".equals(formBean.getUnit())){
                            service_code = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_tmp_unit;
                        }
                    }else if(formBean.getType()==1){//1.金额券
                        if("元".equals(formBean.getUnit())){
                            service_code = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_tmp_unit;
                        }
                    }else{
                        service_code = ServiceCode.fail_man_tmp_type;
                    }
                }else{
                    service_code = ServiceCode.fail_man_tmp_interval;
                }
            }else{
                service_code = ServiceCode.fail_man_tmp_den;
            }
        }
        return service_code;
    }

    /**
     * 生成备份模板
     *
     * */
    public RyCouponTmp coupon_backup(RyCouponTmp tmp){
        RyCouponTmp backup = null;
        if(tmp!=null&&!ConvertUtil.isEmpty(tmp.getRct_id())){
            backup = new RyCouponTmp();

            backup.setRct_den(tmp.getRct_den());
            backup.setRct_unit(tmp.getRct_unit());
            backup.setRct_type(tmp.getRct_type());
            backup.setRct_inteval(tmp.getRct_inteval());
            backup.setRct_start(tmp.getRct_start());
            backup.setRct_end(tmp.getRct_end());
            backup.setRct_backup(tmp.getRct_id());
            backup.setRct_del(0);
            backup.setCreate_date(DateUtils.getDate());

            dao.insertCouponTmp(backup);
        }
        return backup;
    }

}
