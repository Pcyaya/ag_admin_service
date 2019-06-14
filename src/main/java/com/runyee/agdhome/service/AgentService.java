package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.AgentDao;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.ex.ManAhAgentBean;
import com.runyee.agdhome.entity.form.AgentApplyBean;
import com.runyee.agdhome.entity.form.FreebackHandleForm;
import com.runyee.agdhome.entity.pagebean.ManAppFreeBackPageBean;
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
public class AgentService {
    @Autowired
    private AgentDao dao;
    @Autowired
    private UserDao userDao;

    public String apply(AgentApplyBean form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getName())&&
                    !ConvertUtil.isEmpty(form.getPhone())&&
                    !ConvertUtil.isEmpty(form.getContent())&&
                    !ConvertUtil.isEmpty(form.getAddr())){
                //同一手机每天只能提交一次
                Date date = new Date();

                String filter = "";
                //24小时 内
                filter += "   and timestampdiff(hour,create_date,'" + DateUtils.getTimestamp(date) + "') between  0 and 24    ";
                List<AhAgentApply> applys = dao.getAgentApplysPhone(form.getPhone(),filter);
                if(applys!=null&&applys.size()>0){
                    //每天最多1次
                    if(applys.size()<1){
                        AhAgentApply apply = applys.get(0);
                        if(apply!=null &&
                                !ConvertUtil.isEmpty(apply.getAaa_id())&&
                                apply.getCreate_date()!=null){
                            //重复操作
                            long interval = date.getTime()-apply.getCreate_date().getTime();
                            if(interval <= (DateUtils.SECOND_IN_MILLIS)){
                                service_code = ServiceCode.fail_agentapply_second;
                            }else{
                                //五分钟内只能有一次
                                if(interval <= (5*DateUtils.MINUTE_IN_MILLIS)){
                                    service_code = ServiceCode.fail_agentapply_minute;
                                }else{
                                    service_code = ServiceCode.SUCCESS;
                                }
                            }
                        }
                    }else{
                        service_code = ServiceCode.fail_agentapply_day;
                    }
                }else{
                    service_code = ServiceCode.SUCCESS;
                }


                if(ServiceCode.SUCCESS.equals(service_code)){
                    service_code = ServiceCode.success_agent_apply;
                    AhAgentApply apply = new AhAgentApply();
                    apply.setAaa_name(form.getName());
                    apply.setAaa_phone(form.getPhone());
                    apply.setAaa_content(form.getContent());
                    apply.setAaa_addr(form.getAddr());
                    apply.setAaa_provinceid(form.getProvinceid());
                    apply.setAaa_province(form.getProvince());
                    apply.setAaa_cityid(form.getCityid());
                    apply.setAaa_city(form.getCity());
                    apply.setAaa_areaid(form.getAreaid());
                    apply.setAaa_area(form.getArea());
                    apply.setDel(0);
                    apply.setCreate_date(date);

                    dao.insertAgentApply(apply);
                }

            }else{
                service_code = ServiceCode.fail_agentapply_empty;
                List<String> msgs = new ArrayList<>();
                String msg = "您的姓名";
                if(!ConvertUtil.isEmpty(form.getName())){
                    if(ConvertUtil.isEmpty(form.getPhone())){
                        msg = "您的电话";
                    }else if(ConvertUtil.isEmpty(form.getContent())){
                        msg = "您的需求描述";
                    }else if(ConvertUtil.isEmpty(form.getAddr())){
                        msg = "您的营业地址";
                    }
                }
                msgs.add(msg);
                DataUtil.getCurrent().setMsgs(msgs);

            }
        }
        return service_code;
    }

    // 代理商 列表
    public String list(ManAppFreeBackPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getDate())){
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getDate()+"' ";
            }
            //处理人
            if(!ConvertUtil.isEmpty(pageBean.getDeal())){
                filter += " and exists(select aah.* " +
                        "                  from ah_apply_handle aah " +
                        "                  where ifnull(aah.del,0)=0  " +
                        "                  and aah.aah_apply = aaa.aaa_id and  aah.aah_user='"+pageBean.getDeal()+"' )";
            }
            //处理进度
            if(pageBean.getHandle_schedule()>=0){
                if(pageBean.getHandle_schedule()==0){
                    filter += " and not exists(select aah.* " +
                            "                  from ah_apply_handle aah " +
                            "                  where ifnull(aah.del,0)=0 " +
                            "                  and aah.aah_apply = aaa.aaa_id ) ";
                }else{
                    filter += " and exists(select aah.* " +
                            "                  from ah_apply_handle aah " +
                            "                  where ifnull(aah.del,0)=0  " +
                            "                  and aah.aah_apply = aaa.aaa_id and  aah.aah_status='"+(pageBean.getHandle_schedule()-1)+"' ) ";
                }
            }


            //总数
            int total = dao.getManAhAgentCount(filter);
            pageBean.setTotal(total);
            List<ManAhAgentBean> list = dao.getManAhAgentBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                String user_ids = "0";
                for(ManAhAgentBean report :list ){
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
                        for(ManAhAgentBean report :list ){
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
            AhAgentApply Agent  = dao.getAhAgentOneKey(id);
            if(Agent!=null && !ConvertUtil.isEmpty(Agent.getAaa_id())){
                Agent.setDel(1);
                dao.updateFreebackDel(Agent);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }


    public String info(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            ManAhAgentBean agent = dao.getAgentBeanOneKey(id);
            if(agent!=null && !ConvertUtil.isEmpty(agent.getAaa_id())){
                AhAgentApplyHandle handle =this.getHandleOnly(agent,user);
                if(handle!=null){
                    agent.setHandle_schedule(handle.getAah_status());
                    agent.setHandle_results(handle.getAah_content());
                }
                app.setObj(agent);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }


    public AhAgentApplyHandle getHandleOnly(AhAgentApply agent, AhUser user){
        AhAgentApplyHandle handle = null;
        if(agent!=null && !ConvertUtil.isEmpty(agent.getAaa_id())){
            Date date = new Date();
            handle = dao.getHandleOneAgent(agent.getAaa_id());
            if(handle==null || ConvertUtil.isEmpty(handle.getAah_id())){
                if(handle==null || ConvertUtil.isEmpty(handle.getAah_id())){
                    handle = new AhAgentApplyHandle();
                    handle.setAah_apply(agent.getAaa_id());
                    String handle_user= "";//默认系统自己解决
                    if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                        handle_user = user.getAu_id();
                    }
                    handle.setAah_user(handle_user);
                    handle.setAah_status(0);
                    handle.setAah_content("");
                    handle.setDel(0);
                    handle.setCreate_date(date);

                    dao.insertAgentHandle(handle);
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
                AhAgentApply agent = dao.getAgentBeanOneKey(form.getId());
                if(agent!=null && !ConvertUtil.isEmpty(agent.getAaa_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    AhAgentApplyHandle handle =this.getHandleOnly(agent,user);
                    if(handle!=null){
                        if(handle.getAah_status()<1){
                            handle.setAah_status(form.getStatus());
                            handle.setAah_content(form.getContent());
                            handle.setAah_user(access_key);

                            //更新 处理结果
                            dao.updateAgentHandle(handle);
                            agent.setStatus(handle.getAah_status());
                            agent.setContent(handle.getAah_content());

                            app.setObj(agent);
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
