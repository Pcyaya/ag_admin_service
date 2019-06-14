package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.FreebackDao;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.AgCommonValDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.db.anygo.RyCommonVal;
import com.runyee.agdhome.entity.ex.AppManFreebackBean;
import com.runyee.agdhome.entity.form.FreeBackBean;
import com.runyee.agdhome.entity.form.FreeBackDetailBean;
import com.runyee.agdhome.entity.form.FreebackHandleForm;
import com.runyee.agdhome.entity.pagebean.ManAppFreeBackPageBean;
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
public class FreebackService {
    @Autowired
    private FreebackDao dao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AgCommonValDao commonValDao;

    public String issue(FreeBackBean form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getName())&&
                    !ConvertUtil.isEmpty(form.getPhone())&&
                    !ConvertUtil.isEmpty(form.getEmail())&&
                    !ConvertUtil.isEmpty(form.getTitle())&&
                    !ConvertUtil.isEmpty(form.getContent())){

                Date date = new Date();

                String filter = "";
                filter += "   and timestampdiff(hour,create_date,'" + DateUtils.getTimestamp(date) + "') between  0 and 24     ";
                List<AhFreeback> freebacks = dao.getFreebacksPhone(form.getPhone(),filter);
                if(freebacks!=null&&freebacks.size()>0){
                    //每天最多3次
                    if(freebacks.size()<3){
                        AhFreeback freeback = freebacks.get(0);
                        if(freeback!=null &&
                                !ConvertUtil.isEmpty(freeback.getAf_id())&&
                                freeback.getCreate_date()!=null){
                            //重复操作
                            long interval = date.getTime()-freeback.getCreate_date().getTime();
                            if(interval <= (DateUtils.SECOND_IN_MILLIS)){
                                service_code = ServiceCode.fail_freeback_second;
                            }else{
                                //五分钟内只能有一次
                                if(interval <= (5*DateUtils.MINUTE_IN_MILLIS)){
                                    service_code = ServiceCode.fail_freeback_minute;
                                }else{
                                    service_code = ServiceCode.SUCCESS;
                                }
                            }
                        }

                    }else{
                        service_code = ServiceCode.fail_freeback_day;
                    }
                }else{
                    service_code = ServiceCode.SUCCESS;
                 }

                if(ServiceCode.SUCCESS.equals(service_code)){
                    //图片
                    List<FreeBackDetailBean> imgs = null;
                    if(!ConvertUtil.isEmpty(form.getImgs())){
                        JSONArray json = JSONArray.fromObject(form.getImgs());
                        imgs = (List<FreeBackDetailBean>)JSONArray.toList(json,FreeBackDetailBean.class);
                    }
                    if(imgs==null || imgs.size()< 6){
                        service_code = ServiceCode.success_freeback;
                        AhFreeback freeback = new AhFreeback();
                        freeback.setAf_name(form.getName());
                        freeback.setAf_phone(form.getPhone());
                        freeback.setAf_email(form.getEmail());
                        freeback.setAf_title(form.getTitle());
                        freeback.setAf_content(form.getContent());
                        freeback.setDel(0);
                        freeback.setCreate_date(date);

                        dao.insertFreeback(freeback);

                        if(imgs!=null && imgs.size()>0){
                            for(FreeBackDetailBean img :imgs){
                                AhFreebackDetail detail = new AhFreebackDetail();
                                detail.setAfd_freeback(freeback.getAf_id());
                                detail.setAfd_content(img.getContent());
                                detail.setAfd_type(1);
                                detail.setAfd_url(img.getUrl());
                                detail.setDel(0);
                                detail.setCreate_date(date);
                                dao.insertFreebackDetail(detail);
                            }
                        }
                    }else{
                        service_code = ServiceCode.fail_freeback_limit;
                        List<String> msgs = new ArrayList<>();
                        msgs.add("6");
                        if (imgs != null && imgs.size() > 6) {
                            msgs.add("张图片");
                        }
                        DataUtil.getCurrent().setMsgs(msgs);
                    }
                }
            }else{
                service_code = ServiceCode.fail_freeback_empty;
                List<String> msgs = new ArrayList<>();
                String msg = "您的姓名";
                if(!ConvertUtil.isEmpty(form.getName())){
                    if(ConvertUtil.isEmpty(form.getPhone())){
                        msg = "您的电话";
                    }else if(ConvertUtil.isEmpty(form.getEmail())){
                        msg = "您的邮箱";
                    }else if(ConvertUtil.isEmpty(form.getTitle())){
                        msg = "意见标题";
                    }else if(ConvertUtil.isEmpty(form.getContent())){
                        msg = "意见描述";
                    }
                }
                msgs.add(msg);
                DataUtil.getCurrent().setMsgs(msgs);

            }
        }
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

    //投诉建议 列表
    public String manlist(ManAppFreeBackPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getDate())){
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getDate()+"' ";
            }
            //处理人
            if(!ConvertUtil.isEmpty(pageBean.getDeal())){
                filter += " and exists(select afh.* " +
                        "                  from ah_freeback_handle afh " +
                        "                  where ifnull(afh.del,0)=0  " +
                        "                  and afh.afh_freeback = af.af_id and  afh.afh_user='"+pageBean.getDeal()+"' )";
            }
            //处理进度
            if(pageBean.getHandle_schedule()>=0){
                if(pageBean.getHandle_schedule()==0){
                    filter += " and not exists(select afh.* " +
                            "                  from ah_freeback_handle afh " +
                            "                  where ifnull(afh.del,0)=0 " +
                            "                  and afh.afh_freeback = af.af_id ) ";
                }else{
                    filter += " and exists(select afh.* " +
                            "                  from ah_freeback_handle afh " +
                            "                  where ifnull(afh.del,0)=0  " +
                            "                  and afh.afh_freeback = af.af_id and  afh.afh_status='"+(pageBean.getHandle_schedule()-1)+"' ) ";
                }
            }


            //总数
            int total = dao.getManFreebackCount(filter);
            pageBean.setTotal(total);
            List<AppManFreebackBean> list = dao.getManFreebackBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                String user_ids = "0";
                for(AppManFreebackBean report :list ){
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
                        for(AppManFreebackBean report :list ){
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

    public String mandel(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhFreeback freeback  = dao.getFreebackOneKey(id);
            if(freeback!=null && !ConvertUtil.isEmpty(freeback.getAf_id())){
                freeback.setDel(1);
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

    public String maninfo(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            AppManFreebackBean freeback = dao.getFreebackBeanOneKey(id);
            if(freeback!=null && !ConvertUtil.isEmpty(freeback.getAf_id())){
                AhFreebackHandle handle =this.getManHandleOnly(freeback,user);
                if(handle!=null){
                    freeback.setHandle_schedule(handle.getAfh_status());
                    freeback.setHandle_results(handle.getAfh_content());
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


    public AhFreebackHandle getManHandleOnly(AhFreeback freeback, AhUser user){
        AhFreebackHandle handle = null;
        if(freeback!=null && !ConvertUtil.isEmpty(freeback.getAf_id())){
            Date date = new Date();
            handle = dao.getHandleOneFreeback(freeback.getAf_id());
            if(handle==null || ConvertUtil.isEmpty(handle.getAfh_id())){
                if(handle==null || ConvertUtil.isEmpty(handle.getAfh_id())){
                    handle = new AhFreebackHandle();
                    handle.setAfh_freeback(freeback.getAf_id());
                    String handle_user= "";//默认系统自己解决
                    if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                        handle_user = user.getAu_id();
                    }
                    handle.setAfh_user(handle_user);
                    handle.setAfh_status(0);
                    handle.setAfh_content("");
                    handle.setDel(0);
                    handle.setCreate_date(date);

                    dao.insertFreebackHandle(handle);
                }
            }
        }
        return handle;
    }

    //处理
    public String manhandle(FreebackHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                AhFreeback  freeback = dao.getFreebackBeanOneKey(form.getId());
                if(freeback!=null && !ConvertUtil.isEmpty(freeback.getAf_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    AhFreebackHandle handle =this.getManHandleOnly(freeback,user);
                    if(handle!=null){
                        if(handle.getAfh_status()<1){
                            handle.setAfh_status(form.getStatus());
                            handle.setAfh_content(form.getContent());
                            handle.setAfh_user(access_key);

                            //更新 处理结果
                            dao.updateFreebackHandle(handle);
                            freeback.setStatus(handle.getAfh_status());
                            freeback.setContent(handle.getAfh_content());

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
