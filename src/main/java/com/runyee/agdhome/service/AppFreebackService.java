package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.FreebackDao;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.AgCommonValDao;
import com.runyee.agdhome.dao.anygo.AgFreebackDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.db.anygo.*;
import com.runyee.agdhome.entity.ex.*;
import com.runyee.agdhome.entity.form.FreeBackBean;
import com.runyee.agdhome.entity.form.FreeBackDetailBean;
import com.runyee.agdhome.entity.form.FreebackHandleForm;
import com.runyee.agdhome.entity.form.ReportHandleForm;
import com.runyee.agdhome.entity.pagebean.ManAppFreeBackPageBean;
import com.runyee.agdhome.entity.pagebean.ManFreeBackPageBean;
import com.runyee.agdhome.entity.pagebean.ManReportPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class AppFreebackService {
    @Autowired
    private AgFreebackDao dao;
    @Autowired
    private AgCommonValDao commonValDao;
    @Autowired
    private UserDao userDao;

    public String tags(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("2");
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

    /**
     *  app问题反馈进度 列表
     */
    public String schedules(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("15");
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

    //app问题反馈 列表
    public String list(ManAppFreeBackPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getName())){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rf.rf_visitor  " +
                        "               and (rv_num like  '%"+pageBean.getName()+"%' or rv_name like  '%"+pageBean.getName()+"%') " +
                        "               order by  create_date desc limit 1 ) " ;

            }
            if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                filter +="  and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rf.rf_visitor  " +
                        "               and  rv_phone like  '%"+pageBean.getPhone()+"%'  " +
                        "               order by  create_date desc limit 1 ) " ;

            }
            if(pageBean.getSex()>=0){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rf.rf_visitor  " +
                        "               and rv_sex = '"+pageBean.getSex()+"' " +
                        "               order by  create_date desc limit 1 ) " ;
            }
            if(pageBean.getType()>=0){
                filter +="  and rf.rf_type = '"+pageBean.getType()+"' ";
            }

            //处理人
            if(!ConvertUtil.isEmpty(pageBean.getDeal())){
                filter += " and exists(select rfh.* " +
                        "                  from ry_freeback_handle rfh " +
                        "                  where ifnull(rfh.rfh_del,0)=0 " +
                        "                  and rfh.rfh_freeback = rf.rf_id and  rfh.rfh_user='"+pageBean.getDeal()+"'  )";
            }

            //处理进度
            if(pageBean.getHandle_schedule()>=-1){
                if(pageBean.getHandle_schedule()==-1){
                    filter += " and not exists(select rfh.* " +
                            "                  from ry_freeback_handle rfh " +
                            "                  where ifnull(rfh.rfh_del,0)=0 " +
                            "                  and rfh.rfh_freeback = rf.rf_id ) ";
                }else{
                    filter += " and exists(select rfh.* " +
                            "                  from ry_freeback_handle rfh " +
                            "                  where ifnull(rfh.rfh_del,0)=0  " +
                            "                  and rfh.rfh_freeback = rf.rf_id and  rfh.rfh_status='"+(pageBean.getHandle_schedule())+"' ) ";
                }
            }

            //总数
            int total = dao.getFreebackCount(filter);
            pageBean.setTotal(total);
            List<AppFreebackBean> list = dao.getFreebackBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                String user_ids = "0";
                for(AppFreebackBean report :list ){
                    if(!ConvertUtil.isEmpty(report.getHandle_user())){
                        user_ids += ",'"+report.getHandle_user()+"' ";
                    }
                }
                if(!"0".equals(user_ids)){
                    List<AhUser> users = userDao.getUsersKeys(user_ids);
                    if(users!=null && users.size()>0){
                        Map<String,AhUser> userMap = new HashMap<>();
                        for(AhUser user:users){
                            userMap.put(user.getAu_id(),user);
                        }
                        for(AppFreebackBean report :list ){
                            AhUser user = userMap.get(report.getHandle_user());
                            if(user!=null){
                                report.setHandle_name(user.getAu_name());
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

    public String del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyFreeback freeback  = dao.getFreebackOneKey(id);
            if(freeback!=null && !ConvertUtil.isEmpty(freeback.getRf_id())){
                freeback.setRf_del(1);
                dao.updateFreebackDel(freeback);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String info(String id, String view,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            AppFreebackInfoBean freeback = dao.getFreebackBeanOneKey(id);
            if(freeback!=null && !ConvertUtil.isEmpty(freeback.getRf_id())){
                RyFreebackHandle handle =this.getHandleOnly(freeback,ConvertUtil.convertStrToInt(view),user);
                if(handle!=null){
                    freeback.setHandle_schedule(handle.getRfh_status());
                    freeback.setHandle_results(handle.getRfh_content());
                }
                //详情
                List<RyFreebackDetail> details= dao.getFreebackDetails(id);
                if(details!=null&&details.size()>0){
                    freeback.setDetails(details);
                }
                app.setObj(freeback);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }


    public RyFreebackHandle getHandleOnly(RyFreeback freeback,int view, AhUser user){
        RyFreebackHandle handle = null;
        if(freeback!=null && !ConvertUtil.isEmpty(freeback.getRf_id())){
            Date date = new Date();
            handle = dao.getHandleOneFreeback(freeback.getRf_id());
            if(view!=1){//1.查看
                if(handle==null || ConvertUtil.isEmpty(handle.getRfh_id())){
                    handle = new RyFreebackHandle();
                    handle.setRfh_freeback(freeback.getRf_id());
                    String handle_user= "";//默认系统自己解决
                    if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                        handle_user = user.getAu_id();
                    }
                    handle.setRfh_user(handle_user);
                    handle.setRfh_status(0);
                    handle.setRfh_content("");
                    handle.setRfh_del(0);
                    handle.setCreate_date(date);

                    dao.insertFreebackHandle(handle);
                }
            }
        }
        return handle;
    }

    //处理
    public String handle(FreebackHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                AppFreebackInfoBean  freeback = dao.getFreebackBeanOneKey(form.getId());
                if(freeback!=null && !ConvertUtil.isEmpty(freeback.getRf_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    RyFreebackHandle handle =this.getHandleOnly(freeback,0,user);
                    if(handle!=null){
                        if(handle.getRfh_status()<1){
                            handle.setRfh_status(form.getStatus());
                            handle.setRfh_content(form.getContent());
                            handle.setRfh_user(access_key);

                            //更新 处理结果
                            dao.updateFreebackHandle(handle);
                            freeback.setHandle_schedule(handle.getRfh_status());
                            freeback.setHandle_results(handle.getRfh_content());
                            freeback.setHandle_user(access_key);
                            freeback.setHandle_name(user.getAu_name());

                            app.setObj(freeback);
                            service_code  = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_report_handled;
                        }
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

}
