package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.AgCommonValDao;
import com.runyee.agdhome.dao.anygo.AgVisitorDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.autopage.UserAutoPage;
import com.runyee.agdhome.entity.autopage.VisitorAutoPage;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.ag_home.AhUserToken;
import com.runyee.agdhome.entity.db.anygo.RyCommonVal;
import com.runyee.agdhome.entity.db.anygo.RyUrgentPhone;
import com.runyee.agdhome.entity.db.anygo.RyVisitor;
import com.runyee.agdhome.entity.ex.VisitorBean;
import com.runyee.agdhome.entity.pagebean.ManAutoUserPageBean;
import com.runyee.agdhome.entity.pagebean.ManAutoVisitorPageBean;
import com.runyee.agdhome.entity.pagebean.ManVisitorPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class VisitorService {
    @Autowired
    private AgVisitorDao dao;
    @Autowired
    private AgCommonValDao commonValDao;

    /**
     * visitor autocomplete 列表
     */
    public String auto(ManAutoVisitorPageBean pageBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getSearch())){
                filter +="   and (rv.rv_num like '%"+pageBean.getSearch()+"%' or rv.rv_name like '%"+pageBean.getSearch()+"%' or rv.rv_phone like '%"+pageBean.getSearch()+"%'  ) " ;
            }
            List<RyVisitor> visitors = dao.getVisitors(filter,pageBean.getCurrPage(),pageBean.getRows());
            if(visitors!=null && visitors.size()>0){
                List<VisitorAutoPage> autos  =  new ArrayList<>();
                for(RyVisitor visitor:visitors){
                    VisitorAutoPage auto = new VisitorAutoPage();
                    auto.setId(visitor.getRv_id());
                    auto.setName(visitor.getRv_name());
                    auto.setNum(visitor.getRv_num());
                    auto.setPhone(visitor.getRv_phone());
                    autos.add(auto);
                }
                pageBean.setData(autos);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code = APPCode.ERRO;
        }
        return service_code;
    }


    public String list(ManVisitorPageBean pageBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getName())){
                filter += " and rv_name like '%"+pageBean.getName()+"%'";
            }
            if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                filter += " and rv_phone like '%"+pageBean.getPhone()+"%'";
            }
            if(pageBean.getSex()>=0){
                filter += " and rv_sex ='"+pageBean.getSex()+"'";
            }
            if(pageBean.getSource()>=0){
                filter += " and rv_source ='"+pageBean.getSource()+"'";
            }
            if(!ConvertUtil.isEmpty(pageBean.getReg_date())){
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getReg_date()+"' ";
            }
            //-1.全部0.未绑定1.已绑定
            if(pageBean.getBind_flg()>=0){
                if(pageBean.getBind_flg()==0){
                    filter += " and ifnull(rv_spk_num,'')='' ";
                }else{
                    filter += " and ifnull(rv_spk_num,'')<>'' ";
                }
            }

            if(!ConvertUtil.isEmpty(pageBean.getBind_num())){
                filter += " and rv_spk_num like '%"+pageBean.getBind_num()+"%'";
            }
            //认证状态 -2.未认证 -1.认证中 0.验证中 1.认证通过 2.认证失败 3.认证失效
            if(pageBean.getValid_schedule()>=-2){
                if(pageBean.getValid_schedule()==-2){
                    filter += " and not exists(select * " +
                            "                   from ry_guide_valid rgv " +
                            "                   where ifnull(rgv.rgv_del,0)=0 and rgv.rgv_visitor = rv.rv_id )";
                }else if(pageBean.getValid_schedule()==-1){
                    filter += " and  exists(select * " +
                            "                  from ry_guide_valid rgv " +
                            "                  where ifnull(rgv.rgv_del,0)=0 " +
                            "                  and rgv.rgv_id = (select rgv1.rgv_id " +
                            "                                       from ry_guide_valid rgv1 " +
                            "                                       where ifnull(rgv1.rgv_del,0)=0 and rgv1.rgv_visitor = rv.rv_id " +
                            "                                       order by create_date limit 1 ) " +
                            "                  and not exists(select * " +
                            "                                   from ry_guide_valid_handle rgvh " +
                            "                                   where ifnull(rgvh.rgvh_del,0)=0 and rgvh.rgvh_valid = rgv.rgv_id ))";
                }else{
                    filter += " and  exists(select * " +
                            "                  from ry_guide_valid rgv " +
                            "                  where ifnull(rgv.rgv_del,0)=0 " +
                            "                  and rgv.rgv_id = (select rgv1.rgv_id " +
                            "                                       from ry_guide_valid rgv1 " +
                            "                                       where ifnull(rgv1.rgv_del,0)=0 and rgv1.rgv_visitor = rv.rv_id " +
                            "                                       order by create_date limit 1 ) " +
                            "                  and  exists(select * " +
                            "                                   from ry_guide_valid_handle rgvh " +
                            "                                   where ifnull(rgvh.rgvh_del,0)=0 and rgvh.rgvh_valid = rgv.rgv_id " +
                            "                                   and rgvh.rgvh_status = '"+pageBean.getValid_schedule()+"' ))";
                }
            }

            if(pageBean.getFreeze()>=0){
                filter += " and rv_freeze = '"+pageBean.getFreeze()+"' ";
            }
            int total = dao.getVisitorBeanCount(filter);
            pageBean.setTotal(total);
            List<VisitorBean> visitors = dao.getVisitorBeans(filter,pageBean.getCurrPage(),pageBean.getRows());

            if(visitors!=null && visitors.size()>0){
                pageBean.setData(visitors);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code = APPCode.ERRO;
        }
        return service_code;
    }


    //冻结/解冻
    public String freeze(String id, String opt, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyVisitor visitor= dao.getVisitorOneKey(id);
            if(visitor!=null && !ConvertUtil.isEmpty(visitor.getRv_id())){
                visitor.setRv_freeze(ConvertUtil.convertStrToInt(opt));
                dao.updateVisitorFreeze(visitor);

                //发送账号冻结消息
                int port = DataUtil.getCurrent().getPort();
                String url= AppUrl.domain+":"+port;
                String api=ExternalService.system_msg_api;

                Map<String,String> params=new HashMap<>();
                params.put("access_key",visitor.getRv_id());
                params.put("code", APPCode.sk_visitor_freeze);

                Map<String,Object> data=new HashMap<>();
                data.put("opt",opt);
                String content = "您的账号已被冻结,请联系管理员解冻";
                if("0".equals(opt)){
                    content = "您的账号已解冻";
                }
                data.put("content",content);
                params.put("data", JSONObject.fromObject(data).toString());

                ExternalService.app_api(url,api,params,app);


                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    /**
     * 导游认证状态 列表
     */
    public String validstatus(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("19");
        if(commons!=null && commons.size()>0){
            List<CommonValPage> autos  =  new ArrayList<>();
            for(RyCommonVal common:commons){
                CommonValPage auto = new CommonValPage();
                auto.setId(common.getRcv_id());
                auto.setName(common.getRcv_name());
                auto.setVal(common.getRcv_val());
                autos.add(auto);
            }
            app.setObj(autos);
        }
        service_code  = ServiceCode.SUCCESS;
        return service_code;
    }

}
