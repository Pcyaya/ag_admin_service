package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.AgCommonValDao;
import com.runyee.agdhome.dao.anygo.AgGuideValidDao;
import com.runyee.agdhome.dao.anygo.AgSpkSosDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.anygo.*;
import com.runyee.agdhome.entity.ex.GuideValidBean;
import com.runyee.agdhome.entity.ex.GuideValidInfoBean;
import com.runyee.agdhome.entity.form.GuideValidHandleForm;
import com.runyee.agdhome.entity.pagebean.ManValidPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class ValidService {
    @Autowired
    private AgGuideValidDao dao;

    @Autowired
    private AgCommonValDao commonValDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CoinService coinService;


    /**
     * 处理进度 列表
     */
    public String schedules(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("14");
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

    public String list(ManValidPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getName())){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rgv.rgv_visitor  " +
                        "               and (rv_num like  '%"+pageBean.getName()+"%' or rv_name like  '%"+pageBean.getName()+"%') " +
                        "               order by  create_date desc limit 1 ) " ;

            }
            if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rgv.rgv_visitor  " +
                        "               and rv_phone like  '%"+pageBean.getPhone()+"%'  " +
                        "               order by  create_date desc limit 1 ) " ;

            }
            if(pageBean.getSex()>=0){
                filter +="   and exists(select rv.rv_id " +
                        "               from ry_visitor rv " +
                        "               where ifnull(rv_del,0)=0 and rv.rv_id = rgv.rgv_visitor  " +
                        "               and rv_sex = '"+pageBean.getSex()+"' " +
                        "               order by  create_date desc limit 1 ) " ;
            }

            //处理人
            if(!ConvertUtil.isEmpty(pageBean.getHandle())){
                filter += " and exists(select rgvh.* " +
                        "                  from ry_guide_valid_handle rgvh " +
                        "                  where ifnull(rgvh.rgvh_del,0)=0 " +
                        "                  and rgvh.rgvh_valid = rgv.rgv_id and  rgvh.rgvh_user='"+pageBean.getHandle()+"'  )";
            }

            //处理进度
            if(pageBean.getHandle_schedule()>=-1){
                if(pageBean.getHandle_schedule()==-1){
                    filter += " and not exists(select rgvh.* " +
                            "                  from ry_guide_valid_handle rgvh " +
                            "                  where ifnull(rgvh.rgvh_del,0)=0  " +
                            "                  and rgvh.rgvh_valid = rgv.rgv_id  ) ";
                }else{
                    filter += " and exists(select rgvh.* " +
                            "                  from ry_guide_valid_handle rgvh " +
                            "                  where ifnull(rgvh.rgvh_del,0)=0  " +
                            "                  and rgvh.rgvh_valid = rgv.rgv_id  " +
                            "                  and  (rgvh.rgvh_status)='"+(pageBean.getHandle_schedule())+"' ) ";
                }
            }


            //总数
            int total = dao.getGuideValidCount(filter);
            pageBean.setTotal(total);
            List<GuideValidBean> list = dao.getGuideValidBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                String user_ids = "0";
                for(GuideValidBean bean :list ){
                    if(!ConvertUtil.isEmpty(bean.getHandle_user())){
                        user_ids += ",'"+bean.getHandle_user()+"' ";
                    }
                }

                if(!"0".equals(user_ids)){
                    List<AhUser> users = userDao.getUsersKeys(user_ids);
                    if(users!=null && users.size()>0){
                        Map<String,AhUser> userMap = new HashMap<>();
                        for(AhUser user:users){
                            userMap.put(user.getAu_id(),user);
                        }
                        for(GuideValidBean bean:list ){
                            AhUser user = userMap.get(bean.getHandle_user());
                            if(user!=null){
                                bean.setHandle_name(user.getAu_name());
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
            RyGuideValid valid = dao.getGuideValidOneKey(id);
            if(valid!=null && !ConvertUtil.isEmpty(valid.getRgv_id())){
                valid.setRgv_del(1);
                dao.updateGuideValidDel(valid);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_valid_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String info(String id,String view, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            GuideValidInfoBean valid = dao.getGuideValidBeanOneKey(id);
            if(valid!=null && !ConvertUtil.isEmpty(valid.getRgv_id())){

                RyGuideValidHandle handle =this.getGuideValidHandleOnly(valid,ConvertUtil.convertStrToInt(view),user);
                if(handle!=null){
                    int flg = 0 ;
                    if(valid.getHandle_schedule()!=handle.getRgvh_status()){
                        valid.setHandle_schedule(handle.getRgvh_status());
                        flg = 1;
                    }

                    valid.setHandle_user(handle.getRgvh_user());
                    //valid.setHandle_name()
                    valid.setHandle_result(handle.getRgvh_content());

                    if(flg==1){
                        //发送状态变更消息
                        int port = DataUtil.getCurrent().getPort();
                        String url= AppUrl.domain+":"+port;
                        String api=ExternalService.system_msg_api;

                        Map<String,String> params=new HashMap<>();
                        params.put("access_key",valid.getRgv_visitor());
                        params.put("code", APPCode.sk_guide_valid);

                        Map<String,Object> data=new HashMap<>();
                        data.put("status",valid.getHandle_schedule());
                        data.put("content",valid.getHandle_result());

                        params.put("data", JSONObject.fromObject(data).toString());

                        ExternalService.app_api(url,api,params,app);

                    }
                }

                app.setObj(valid);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_valid_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public RyGuideValidHandle getGuideValidHandleOnly(RyGuideValid valid,int view, AhUser user){
        RyGuideValidHandle handle = null;
        if(valid!=null && !ConvertUtil.isEmpty(valid.getRgv_id())){
            Date date = new Date();
            handle = dao.getGuideValidHandleOneValid(valid.getRgv_id());
            if(view!=1){
                if(handle==null || ConvertUtil.isEmpty(handle.getRgvh_id())){
                    handle = new RyGuideValidHandle();
                    handle.setRgvh_valid(valid.getRgv_id());
                    String handle_user= "";//默认系统自己解决
                    if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                        handle_user = user.getAu_id();
                    }
                    handle.setRgvh_user(handle_user);
                    handle.setRgvh_status(0);
                    handle.setRgvh_content("");
                    handle.setRgvh_del(0);
                    handle.setCreate_date(date);

                    dao.insertGuideValidHandle(handle);
                }
            }
        }
        return handle;
    }

    public String audit(GuideValidHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                GuideValidInfoBean  valid = dao.getGuideValidBeanOneKey(form.getId());
                if(valid!=null && !ConvertUtil.isEmpty(valid.getRgv_id())){
                    String access_key = valid.getRgv_visitor();
                    RyGuideValidHandle handle =this.getGuideValidHandleOnly(valid,0,user);
                    if(handle!=null){
                        if(handle.getRgvh_status()!=1){
                            //更新 处理结果
                            int flg = 0;
                            handle.setRgvh_status(form.getStatus());

                            if(valid.getHandle_schedule()!=handle.getRgvh_status()){
                                flg = 1;
                            }
                            handle.setRgvh_user(user.getAu_id());
                            handle.setRgvh_content(form.getContent());
                            dao.updateGuideValidHandle(handle);
                            if(form.getStatus()==1){
                                //审核通过 发放游币
                                int business = -1;
                                String val_key= "coin_task_guide1";
                                RyCommonVal val =  commonValDao.getCommonValOneValKey(val_key);
                                if(val!=null&&!ConvertUtil.isEmpty(val.getRcv_id())){
                                    business = ConvertUtil.convertStrToInt(val.getRcv_val());
                                }
                                //判断是否已经发过游币
                                int count = coinService.transforms_interior_count(access_key,business,"",0,valid.getRgv_id(),"",null);
                                if(count<=0){
                                    coinService.coin_transform(access_key,business,"",0,20,valid.getRgv_id(),"");
                                }
                            }

                            valid.setHandle_schedule(handle.getRgvh_status());
                            valid.setHandle_user(handle.getRgvh_user());
                            valid.setHandle_name(user.getAu_name());
                            valid.setHandle_result(handle.getRgvh_content());


                            if(flg==1){
                                //发送状态变更消息
                                int port = DataUtil.getCurrent().getPort();
                                String url= AppUrl.domain+":"+port;
                                String api=ExternalService.system_msg_api;


                                Map<String,String> params=new HashMap<>();
                                params.put("access_key",valid.getRgv_visitor());
                                params.put("code", APPCode.sk_guide_valid);

                                Map<String,Object> data=new HashMap<>();
                                data.put("status",valid.getHandle_schedule());
                                data.put("content",valid.getHandle_result());

                                params.put("data", JSONObject.fromObject(data).toString());

                                ExternalService.app_api(url,api,params,app);
                            }

                            app.setObj(valid);

                            service_code  = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_valid_handled;
                        }
                    }
                }else{
                    service_code = ServiceCode.fail_man_valid_del;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

}
