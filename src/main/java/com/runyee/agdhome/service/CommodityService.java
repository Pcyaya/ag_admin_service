package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.anygo.AgCommodityDao;
import com.runyee.agdhome.dao.anygo.AgCouponDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.anygo.RyCommodity;
import com.runyee.agdhome.entity.db.anygo.RyCommodityTmp;
import com.runyee.agdhome.entity.db.anygo.RyCouponTmp;
import com.runyee.agdhome.entity.ex.CommodityTmpBean;
import com.runyee.agdhome.entity.form.CommodityTmpForm;
import com.runyee.agdhome.entity.pagebean.ManCommodityTmpPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class CommodityService {
    @Autowired
    private AgCommodityDao dao;
    @Autowired
    private AgCouponDao couponDao;
    @Autowired
    private CouponService couponService;
    @Autowired
    private NumGenService numGenService;


    // 商品模板 列表
    public String tmp_list(ManCommodityTmpPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";

            if(pageBean.getBusiness()>=0){
                filter +="   and rct_business = '"+pageBean.getBusiness()+"' ";
            }

            if(!ConvertUtil.isEmpty(pageBean.getName())){
                filter +="   and rct_name like '%"+pageBean.getName()+"%' ";
            }

            if(pageBean.getOnline()>=-1){
                if(pageBean.getOnline()==-1){
                    filter +="   and not exists(select * " +
                            "                       from ry_commodity rc " +
                            "                       where ifnull(rc_del,0)=0 and rc.rc_tmp = rct.rct_id ) ";
                }else {
                    filter +="   and  exists(select * " +
                            "                       from ry_commodity rc " +
                            "                       where ifnull(rc_del,0)=0 and rc.rc_tmp = rct.rct_id ) ";
                    filter +="   and rct_online = '"+pageBean.getOnline()+"' ";
                }
            }

            if(!ConvertUtil.isEmpty(pageBean.getOn_date())){
                //上架时间
                filter += " and date_format(rct_ondate,'%Y-%m-%d') = '"+pageBean.getOn_date()+"' ";
            }

            if(!ConvertUtil.isEmpty(pageBean.getUnder_date())){
                //下架时间
                filter += " and date_format(rct_underdate,'%Y-%m-%d') = '"+pageBean.getUnder_date()+"' ";//:%s
            }

            //总数
            int total = dao.getCommodityTmpBeanCount(filter);
            pageBean.setTotal(total);
            List<CommodityTmpBean> list = dao.getCommodityTmpBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                //封装优惠券
                String ids = "''";
                for(CommodityTmpBean tmp:list){
                    //0.商品，1.优惠券
                    if(tmp.getRct_business()==1){
                        ids += ","+"'"+tmp.getRct_rel()+"'";
                    }
                }
                if(!"''".equals(ids)){
                    List<RyCouponTmp> coupon_tmps = couponDao.getCouponTmpsKeys(ids);
                    if(coupon_tmps!=null&&coupon_tmps.size()>0){
                        Map<String,RyCouponTmp> couponTmpMap = new HashMap<>();
                        for(RyCouponTmp couponTmp:coupon_tmps){
                            couponTmpMap.put(couponTmp.getRct_id(),couponTmp);
                        }

                        for(CommodityTmpBean tmp:list){
                            //0.商品，1.优惠券
                            if(tmp.getRct_business()==1){
                                RyCouponTmp couponTmp = couponTmpMap.get(tmp.getRct_rel());
                                if(couponTmp!=null&&!ConvertUtil.isEmpty(couponTmp.getRct_id())){
                                    tmp.setCoupon_den(couponTmp.getRct_den());
                                    tmp.setCoupon_unit(couponTmp.getRct_unit());
                                    tmp.setCoupon_interval(couponTmp.getRct_inteval());
                                    tmp.setCoupon_type(couponTmp.getRct_type());
                                    if(tmp.getCoupon_interval()==-2){
                                        //时限券
                                        tmp.setCoupon_start(couponTmp.getRct_start());
                                        tmp.setCoupon_end(couponTmp.getRct_end());
                                        if(tmp.getCoupon_end()!=null){
                                            if(DateUtils.getMillis()>=tmp.getCoupon_end().getTime()){
                                                tmp.setCoupon_valid(1);
                                            }
                                        }
                                    }

                                }
                            }
                        }

                    }
                }

                pageBean.setData(list);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }
        return service_code;
    }

    // 商品模板 信息
    public String tmp_info(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            CommodityTmpBean tmp  = dao.getCommodityTmpBeanOneKey(id);
            if(tmp!=null && !ConvertUtil.isEmpty(tmp.getRct_id())){
                // 0.商品，1.优惠券
                if(tmp.getRct_business()==1){
                    RyCouponTmp  couponTmp = couponDao.getCouponTmpOneKey(id);
                    if(couponTmp!=null&&!ConvertUtil.isEmpty(couponTmp.getRct_id())){
                        tmp.setCoupon_den(couponTmp.getRct_den());
                        tmp.setCoupon_unit(couponTmp.getRct_unit());
                        tmp.setCoupon_interval(couponTmp.getRct_inteval());
                        tmp.setCoupon_type(couponTmp.getRct_type());
                        if(tmp.getCoupon_interval()==-2){
                            //时限券
                            tmp.setCoupon_start(couponTmp.getRct_start());
                            tmp.setCoupon_end(couponTmp.getRct_end());
                            if(tmp.getCoupon_end()!=null){
                                if(DateUtils.getMillis()>=tmp.getCoupon_end().getTime()){
                                    tmp.setCoupon_valid(1);
                                }
                            }
                        }

                    }
                }
                app.setObj(tmp);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_commodity_tmp_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String tmp_del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyCommodityTmp tmp  = dao.getCommodityTmpOneKey(id);
            if(tmp!=null && !ConvertUtil.isEmpty(tmp.getRct_id())){
                if(tmp.getRct_online()==0){
                    tmp.setRct_del(1);
                    dao.updateCommodityTmpDel(tmp);
                    service_code  = ServiceCode.SUCCESS;
                }else{
                    service_code = ServiceCode.fail_man_commodity_tmp_online;
                }
            }else{
                service_code = ServiceCode.fail_man_commodity_tmp_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String tmp_issue(CommodityTmpForm formBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(formBean!=null){
            service_code = this.coupon_tmp_valid(formBean);
            if(ServiceCode.SUCCESS.equals(service_code)){
                String id = formBean.getId();
                RyCommodityTmp tmp = null;
                if(!ConvertUtil.isEmpty(id)){
                    //更新
                    tmp = dao.getCommodityTmpOneKey(id);
                    if(tmp!=null && !ConvertUtil.isEmpty(tmp.getRct_id())){
                        tmp.setRct_name(formBean.getName());
                        tmp.setRct_icon(formBean.getIcon());
                        tmp.setRct_hot(formBean.getHot());
                        tmp.setRct_recommend(formBean.getRecommend());
                        tmp.setRct_business(formBean.getBusiness());
                        //模板变化，做备份模板
                        if(formBean.getRel()==null){
                            formBean.setRel("");
                        }
                        if(!formBean.getRel().equals(tmp.getRct_rel())){
                            //生成备份模板
                            RyCouponTmp couponTmp = couponDao.getCouponTmpOneKey(formBean.getRel());
                            if(couponTmp!=null&&!ConvertUtil.isEmpty(couponTmp.getRct_id())){
                                RyCouponTmp backup = couponService.coupon_backup(couponTmp);
                                if(backup!=null&&!ConvertUtil.isEmpty(backup.getRct_id())){
                                    tmp.setRct_rel(backup.getRct_id());
                                }
                            }
                        }
                        tmp.setRct_vprice(formBean.getVprice());
                        tmp.setRct_rprice(formBean.getRprice());
                        tmp.setRct_coin(formBean.getCoin());
                        tmp.setRct_limit(formBean.getLimit_amount());

                        dao.updateCommodityTmp(tmp);
                        //上架
                        if(formBean.getOnline()!=tmp.getRct_online()){
                            service_code = this.commodity_up(tmp,formBean.getOnline());
                        }else{
                            service_code = ServiceCode.SUCCESS;
                        }
                    }else{
                        service_code = ServiceCode.fail_man_commodity_tmp_del;
                    }
                }else{
                    //新增
                    tmp = new RyCommodityTmp();
                    String num = numGenService.generateNum("RCTN",5);
                    tmp.setRct_num(num);
                    tmp.setRct_name(formBean.getName());
                    tmp.setRct_icon(formBean.getIcon());
                    tmp.setRct_hot(formBean.getHot());
                    tmp.setRct_recommend(formBean.getRecommend());
                    tmp.setRct_business(formBean.getBusiness());
                    //生成备份模板
                    if(formBean.getRel()!=null&&!ConvertUtil.isEmpty(formBean.getRel())){
                        RyCouponTmp couponTmp = couponDao.getCouponTmpOneKey(formBean.getRel());
                        if(couponTmp!=null&&!ConvertUtil.isEmpty(couponTmp.getRct_id())){
                            RyCouponTmp backup = couponService.coupon_backup(couponTmp);
                            if(backup!=null&&!ConvertUtil.isEmpty(backup.getRct_id())){
                                tmp.setRct_rel(backup.getRct_id());
                            }
                        }
                    }
                    tmp.setRct_vprice(formBean.getVprice());
                    tmp.setRct_rprice(formBean.getRprice());
                    tmp.setRct_coin(formBean.getCoin());
                    tmp.setRct_limit(formBean.getLimit_amount());
                    tmp.setRct_del(0);
                    tmp.setCreate_date(DateUtils.getDate());

                    dao.insertCommodityTmp(tmp);
                    //上架
                    if(formBean.getOnline()==1){
                        service_code = this.commodity_up(tmp,formBean.getOnline());
                    }else{
                        service_code = ServiceCode.SUCCESS;
                    }
                }
            }

        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String coupon_tmp_valid(CommodityTmpForm formBean){
        String service_code = ServiceCode.ERRO;
        if(formBean!=null){
            //通用属性验证
            String msg = "商品名称";
            RyCommodityTmp tmp = null;

            if(formBean.getVprice()>0){
                if((formBean.getRprice()+formBean.getCoin())>0){
                    if(!ConvertUtil.isEmpty(formBean.getId())){
                        tmp = dao.getCommodityTmpOneKey(formBean.getId());
                        if(tmp!=null&&!ConvertUtil.isEmpty(tmp.getRct_id())){
                            if(tmp.getRct_online()==1){
                                service_code = ServiceCode.fail_man_commodity_tmp_online;
                            }else{
                                if(formBean.getLimit_amount()!=-1){
                                    if(formBean.getLimit_amount()<tmp.getRct_sell()){
                                        service_code = ServiceCode.fail_man_commodity_tmp_amount;
                                    }
                                }
                            }
                        }else{
                            service_code = ServiceCode.fail_man_commodity_tmp_del;
                        }
                    }
                }else{
                    msg = "现价";
                    service_code = ServiceCode.fail_man_commodity_tmp_sure;
                }
            }else{
                msg = "商品原价";
                service_code = ServiceCode.fail_man_commodity_tmp_sure;
            }

            if(ServiceCode.ERRO.equals(service_code)){
                //商品类型
                if(formBean.getBusiness()==0){
                    //商品
                    if(!ConvertUtil.isEmpty(formBean.getName())){
                        if(!ConvertUtil.isEmpty(formBean.getIcon())){
                            service_code = ServiceCode.SUCCESS;
                        }else{
                            msg = "商品图片";
                            service_code = ServiceCode.fail_man_commodity_tmp_empty;
                        }
                    }else{
                        service_code = ServiceCode.fail_man_commodity_tmp_empty;
                    }
                }else if(formBean.getBusiness()==1){
                    //优惠券
                    if(!ConvertUtil.isEmpty(formBean.getRel())){
                        RyCouponTmp couponTmp = couponDao.getCouponTmpOneKey(formBean.getRel());
                        if(couponTmp!=null&&!ConvertUtil.isEmpty(couponTmp.getRct_id())){
                            service_code = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_commodity_tmp_coupon_del;
                        }
                    }else{
                        service_code = ServiceCode.fail_man_commodity_tmp_coupon;
                    }
                }else{
                    //不支持
                    service_code = ServiceCode.fail_man_commodity_tmp_unsupport;
                }

            }
            if(!ServiceCode.SUCCESS.equals(service_code)){
                List<String> msgs = new ArrayList<>();
                msgs.add(msg);
                DataUtil.getCurrent().setMsgs(msgs);
            }
        }
        return service_code;
    }


    /**
     * 上架/下架商品 每次上架都生成商品
     * @param tmp 商品模板
     * @param opt 1.上架
     * */
    public String commodity_up(RyCommodityTmp tmp,int opt){
        String service_code = ServiceCode.ERRO;
        //更新所有商品状态
        if(tmp!=null&&!ConvertUtil.isEmpty(tmp.getRct_id())){
            if(tmp.getRct_online()!=opt){
                if(opt==1||opt==0){
                    //更新所有商品状态
                    dao.updateCommodityOnline(tmp.getRct_id(),0);
                    if(opt==1){
                        //上架
                        //生成商品副本
                        RyCommodity commodity = new RyCommodity();
                        commodity.setRc_tmp(tmp.getRct_id());
                        commodity.setRc_name(tmp.getRct_name());
                        commodity.setRc_icon(tmp.getRct_icon());
                        commodity.setRc_hot(tmp.getRct_hot());
                        commodity.setRc_recommend(tmp.getRct_recommend());
                        commodity.setRc_business(tmp.getRct_business());
                        commodity.setRc_rel(tmp.getRct_rel());
                        commodity.setRc_vprice(tmp.getRct_vprice());
                        commodity.setRc_rprice(tmp.getRct_rprice());
                        commodity.setRc_coin(tmp.getRct_coin());
                        int calculate = 0;
                        if((commodity.getRc_rprice()>0)&&(commodity.getRc_coin()>0)){
                                calculate = 2;
                        }else{
                            if(commodity.getRc_coin()>0){
                                calculate = 1;
                            }
                        }
                        commodity.setRc_calculate(calculate);
                        commodity.setRc_online(opt);
                        commodity.setRc_del(0);
                        commodity.setCreate_date(DateUtils.getDate());
                        dao.insertCommodity(commodity);

                        tmp.setRct_online(opt);
                        tmp.setRct_ondate(DateUtils.getDate());
                    }else{
                        //下架
                        tmp.setRct_online(opt);
                        tmp.setRct_underdate(DateUtils.getDate());
                    }
                }else{
                    service_code = ServiceCode.fail_param_error;
                }

                if(ServiceCode.ERRO.equals(service_code)){
                    //更新模板上下线状态
                    dao.updateCommodityTmpOnline(tmp);
                    service_code = ServiceCode.SUCCESS;
                }
            }else{
                service_code = ServiceCode.fail_man_commodity_tmp_reonline;
            }
        }
        return service_code;
    }


    /**
     * 上架/下架商品 每次上架都生成商品
     * @param id 商品模板id
     * @param opt 1.上架
     * */
    public String tmp_rack(String  id,int opt,AppJson app){
        String service_code = ServiceCode.ERRO;
        //更新所有商品状态
        if(!ConvertUtil.isEmpty(id)){
            RyCommodityTmp tmp = dao.getCommodityTmpOneKey(id);
            if(tmp!=null&&!ConvertUtil.isEmpty(tmp.getRct_id())){
                if(tmp.getRct_online()!=opt){
                    service_code = this.commodity_up(tmp,opt);
                    if(ServiceCode.SUCCESS.equals(service_code)){
                        //更新模板上下线状态
                        dao.updateCommodityTmpOnline(tmp);
                        service_code = ServiceCode.SUCCESS;
                    }
                }else{
                    service_code = ServiceCode.fail_man_commodity_tmp_reonline;
                }
            }else{
                service_code = ServiceCode.fail_man_commodity_tmp_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

}
