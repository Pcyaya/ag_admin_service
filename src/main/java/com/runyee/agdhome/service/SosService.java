package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.AgCommonValDao;
import com.runyee.agdhome.dao.anygo.AgSpkSosDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.anygo.RyCommonVal;
import com.runyee.agdhome.entity.db.anygo.RySpkSos;
import com.runyee.agdhome.entity.db.anygo.RySpkSosHandle;
import com.runyee.agdhome.entity.ex.SpkSosBean;
import com.runyee.agdhome.entity.ex.SpkSosInfoBean;
import com.runyee.agdhome.entity.form.SosHandleForm;
import com.runyee.agdhome.entity.pagebean.ManSosPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class SosService {
    @Autowired
    private AgSpkSosDao dao;

    @Autowired
    private AgCommonValDao commonValDao;

    @Autowired
    private UserDao userDao;


    /**
     * 处理进度 列表
     */
    public String schedules(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("5");
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

    public String list(ManSosPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getName())){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rss.rss_visitor  " +
                        "               and (rv_num like  '%"+pageBean.getName()+"%' or rv_name like  '%"+pageBean.getName()+"%') " +
                        "               order by  create_date desc limit 1 ) " ;

            }
            if(pageBean.getSex()>=0){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rss.rss_visitor  " +
                        "               and rv_sex = '"+pageBean.getSex()+"' " +
                        "               order by  create_date desc limit 1 ) " ;
            }
            if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rss.rss_visitor  " +
                        "               and rv_phone like '%"+pageBean.getPhone()+"%' " +
                        "               order by  create_date desc limit 1 ) " ;
            }
            if(!ConvertUtil.isEmpty(pageBean.getOccupation())){
                filter +="   and exists(select rgi.rgi_id " +
                        "               from ry_guide_info rgi  " +
                        "               where ifnull(rgi.rgi_del,0)= 0 and rgi.rgi_guide = rss.rss_visitor " +
                        "               and rgi_occupation like '%"+pageBean.getOccupation()+"%' " +
                        "               order by  create_date desc limit 1 ) " ;
            }
            if(!ConvertUtil.isEmpty(pageBean.getSpeak_num())){
                filter += " and rss_speak_num  like '%"+pageBean.getSpeak_num()+"%' ";
            }

            if(!ConvertUtil.isEmpty(pageBean.getDate())){
                //求救日期
                filter += " and date_format(rss.create_date,'%Y-%m-%d') = '"+pageBean.getDate()+"' ";//:%s

            }

            if(!ConvertUtil.isEmpty(pageBean.getHandle_schedule())){
                if("-000".equals(pageBean.getHandle_schedule())){
                    filter +=" and not exists(select  rssh.rssh_id  " +
                            "               from ry_spk_sos_handle rssh " +
                            "               where  ifnull(rssh.rssh_del,0)=0 and  rssh.rssh_sos =rss.rss_id " +
                            //"               and  concat(ifnull(rssh_confirm,0),ifnull(rssh_operate,0),ifnull(rssh_inform,0)) = '"+pageBean.getHandle_schedule()+"' "+
                            "               order by create_date desc limit 1 ) " ;
                }else{
                    filter +=" and exists(select  rssh.rssh_id  " +
                            "               from ry_spk_sos_handle rssh " +
                            "               where  ifnull(rssh.rssh_del,0)=0 and  rssh.rssh_sos =rss.rss_id " +
                            "               and  concat(ifnull(rssh_confirm,0),ifnull(rssh_operate,0),ifnull(rssh_inform,0)) = '"+pageBean.getHandle_schedule()+"' "+
                            "               order by create_date desc limit 1 ) " ;
                }
            }

            //总数
            int total = dao.getSpkSosCount(filter);
            pageBean.setTotal(total);
            List<SpkSosBean> list = dao.getSpkSosBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                String user_ids = "0";
                for(SpkSosBean spkSos :list ){
                    if(!ConvertUtil.isEmpty(spkSos.getHandle_user())){
                        user_ids += ",'"+spkSos.getHandle_user()+"' ";
                    }
                }

                if(!"0".equals(user_ids)){
                    List<AhUser> users = userDao.getUsersKeys(user_ids);
                    if(users!=null && users.size()>0){
                        Map<String,AhUser> userMap = new HashMap<>();
                        for(AhUser user:users){
                            userMap.put(user.getAu_id(),user);
                        }
                        for(SpkSosBean spkSos  :list ){
                            AhUser user = userMap.get(spkSos.getHandle_user());
                            if(user!=null){
                                spkSos.setHandle_name(user.getAu_name());
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
            //String access_key = DataUtil.getCurrent().getUser().getAu_id();
            SpkSosInfoBean sos = dao.getSpkSosBeanOneKey(id);
            if(sos!=null && !ConvertUtil.isEmpty(sos.getRss_id())){
                sos.setRss_del(1);
                dao.updateSpkSosDel(sos);
                service_code  = ServiceCode.SUCCESS;
                /*if(ConvertUtil.isEmpty(sos.getHandle_user())|| access_key.equals(sos.getHandle_user())){

                }else{
                    service_code = ServiceCode.fail_man_sos_unhandle;
                }*/
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String info(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            SpkSosInfoBean  sos = dao.getSpkSosBeanOneKey(id);
            if(sos!=null && !ConvertUtil.isEmpty(sos.getRss_id())){
                RySpkSosHandle sosHandle =this.getSosHandleOnly(sos,user);
                if(sosHandle!=null){
                    sos.setConfirm(sosHandle.getRssh_confirm());
                    sos.setOperate(sosHandle.getRssh_operate());
                    sos.setContent(sosHandle.getRssh_content());
                    sos.setInform(sosHandle.getRssh_inform());
                }
                app.setObj(sos);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public RySpkSosHandle getSosHandleOnly(RySpkSos sos,AhUser user){
        RySpkSosHandle sosHandle = null;
        if(sos!=null && !ConvertUtil.isEmpty(sos.getRss_id())){
            Date date = new Date();
            sosHandle = dao.getSpkSosHandleOneKey(sos.getRss_id());
            if(sosHandle==null || ConvertUtil.isEmpty(sosHandle.getRssh_id())){
                sosHandle = new RySpkSosHandle();
                sosHandle.setRssh_sos(sos.getRss_id());
                String handle_user= "";//默认系统自己解决
                if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                    handle_user = user.getAu_id();
                }
                sosHandle.setRssh_user(handle_user);
                sosHandle.setRssh_confirm(0);
                sosHandle.setRssh_operate(0);
                sosHandle.setRssh_content("");
                sosHandle.setRssh_inform(0);
                sosHandle.setRssh_del(0);
                sosHandle.setCreate_date(date);

                dao.insertSpkSosHandle(sosHandle);
            }
        }
        return sosHandle;
    }


    public String corfirm(SosHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                SpkSosInfoBean  sos = dao.getSpkSosBeanOneKey(form.getId());
                if(sos!=null && !ConvertUtil.isEmpty(sos.getRss_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    RySpkSosHandle sosHandle =this.getSosHandleOnly(sos,user);
                    if(sosHandle!=null){
                        if(sosHandle.getRssh_inform()<=0){
                            sosHandle.setRssh_confirm(form.getStatus());
                            sosHandle.setRssh_operate(form.getOperate());
                            sosHandle.setRssh_content(form.getContent());
                            sosHandle.setRssh_user(access_key);

                            //更新 处理结果
                            dao.updateSpkSosHandle(sosHandle);
                            sos.setConfirm(sosHandle.getRssh_confirm());
                            sos.setOperate(sos.getOperate());
                            sos.setContent(sos.getContent());
                            sos.setInform(sos.getInform());
                            app.setObj(sos);
                            service_code  = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_sos_informed;
                        }
                    }
                }else{
                    service_code = ServiceCode.fail_man_sos_del;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String inform(SosHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                SpkSosInfoBean  sos = dao.getSpkSosBeanOneKey(form.getId());
                if(sos!=null && !ConvertUtil.isEmpty(sos.getRss_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    RySpkSosHandle sosHandle =this.getSosHandleOnly(sos,user);
                    if(sosHandle!=null){
                        if(sosHandle.getRssh_confirm()>0){
                            if(sosHandle.getRssh_operate()>0){
                                if(sosHandle.getRssh_inform()<=0){

                                    //更新 处理结果
                                    sosHandle.setRssh_inform(form.getStatus());
                                    dao.updateSpkSosHandle(sosHandle);

                                    sos.setConfirm(sosHandle.getRssh_confirm());
                                    sos.setOperate(sos.getOperate());
                                    sos.setContent(sos.getContent());
                                    sos.setInform(sos.getInform());
                                    app.setObj(sos);
                                    service_code  = ServiceCode.SUCCESS;
                                }else{
                                    service_code = ServiceCode.fail_man_sos_reinform;
                                }
                            }else{
                                service_code = ServiceCode.fail_man_sos_operate;
                            }
                        }else{
                            service_code = ServiceCode.fail_man_sos_confirm;
                        }
                    }
                }else{
                    service_code = ServiceCode.fail_man_sos_del;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

}
