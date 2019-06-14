package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.ContactDao;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.AgCommonValDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.db.ag_home.AhContact;
import com.runyee.agdhome.entity.db.ag_home.AhContactHandle;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.anygo.RyCommonVal;
import com.runyee.agdhome.entity.db.anygo.RyReport;
import com.runyee.agdhome.entity.ex.ContactInfoBean;
import com.runyee.agdhome.entity.form.ContactForm;
import com.runyee.agdhome.entity.form.ReportHandleForm;
import com.runyee.agdhome.entity.pagebean.ManContactPageBean;
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
public class ContactService {
    @Autowired
    private ContactDao dao;
    @Autowired
    private AgCommonValDao commonValDao;
    @Autowired
    private UserDao userDao;

    public String issue(ContactForm form,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getName())
                    &&!ConvertUtil.isEmpty(form.getPhone())
                    &&!ConvertUtil.isEmpty(form.getBusiness())
                    &&!ConvertUtil.isEmpty(form.getContent())){
                Date date = new Date();
                String filter = "";
                //24小时 内
                filter += "   and timestampdiff(hour,create_date,'" + DateUtils.getTimestamp(date) + "') between  0 and 24    ";
                List<AhContact> contacts = dao.getContactsPhone(form.getPhone(),filter);
                if(contacts!=null&&contacts.size()>0){
                    //每天最多3次
                    if(contacts.size()<3){
                        AhContact contact = contacts.get(0);
                        if(contact!=null &&
                                !ConvertUtil.isEmpty(contact.getAh_id())&&
                                contact.getCreate_date()!=null){
                            //重复操作
                            long interval = date.getTime()-contact.getCreate_date().getTime();
                            if(interval <= (DateUtils.SECOND_IN_MILLIS)){
                                service_code = ServiceCode.fail_contact_second;
                            }else{
                                //五分钟内只能有一次
                                if(interval <= (5*DateUtils.MINUTE_IN_MILLIS)){
                                    service_code = ServiceCode.fail_contact_minute;
                                }else{
                                    service_code = ServiceCode.SUCCESS;
                                }
                            }
                        }
                    }else{
                        service_code = ServiceCode.fail_contact_day;
                    }
                }else{
                    service_code = ServiceCode.SUCCESS;
                }
                if(ServiceCode.SUCCESS.equals(service_code)){
                    AhContact contact = new AhContact();
                    contact.setAc_name(form.getName());
                    contact.setAc_phone(form.getPhone());
                    contact.setAc_business(form.getBusiness());
                    contact.setAc_email(form.getEmail());
                    contact.setAc_sex(form.getSex());
                    contact.setAc_content(form.getContent());
                    contact.setDel(0);
                    contact.setCreate_date(date);
                    dao.insertContact(contact);
                    service_code = ServiceCode.success_contact;
                }
            }else{
                service_code = ServiceCode.fail_contact_necessary;
                List<String> msgs = new ArrayList<>();
                String msg = "您的姓名";
                if(!ConvertUtil.isEmpty(form.getName())){
                    if(ConvertUtil.isEmpty(form.getPhone())){
                        msg = "您的联系方式";
                    }else if(ConvertUtil.isEmpty(form.getBusiness())){
                        msg = "您的合作业务";
                    }else if(ConvertUtil.isEmpty(form.getContent())){
                        msg = "您的需求描述";
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


    //投诉建议 列表
    public String list(ManContactPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            //留言日期
            if(!ConvertUtil.isEmpty(pageBean.getDate())){
                filter += " and date_format(create_date,'%Y-%m-%d') = '"+pageBean.getDate()+"' ";
            }
            if(!ConvertUtil.isEmpty(pageBean.getType())){
                filter += " and ac.ac_business = '" +pageBean.getType()+"' ";
            }
            //处理人
            if(!ConvertUtil.isEmpty(pageBean.getDeal())){
                filter += " and exists(select ach.* " +
                        "                  from ah_contact_handle ach " +
                        "                  where ifnull(ach.del,0)=0 " +
                        "                  and ach.ach_contact = ac.ah_id and  ach.ach_user='"+pageBean.getDeal()+"'  )";
            }
            //处理进度
            if(pageBean.getHandle_schedule()>=0){
                if(pageBean.getHandle_schedule()==0){
                    filter += " and not exists(select ach.* " +
                            "                  from ah_contact_handle ach " +
                            "                  where ifnull(ach.del,0)=0 " +
                            "                  and ach.ach_contact = ac.ah_id ) ";
                }else{
                    filter += " and exists(select ach.* " +
                            "                  from ah_contact_handle ach " +
                            "                  where ifnull(ach.del,0)=0  " +
                            "                  and ach.ach_contact = ac.ah_id and  ach.ach_status='"+(pageBean.getHandle_schedule()-1)+"' ) ";
                }
            }


            //总数
            int total = dao.getManContactCount(filter);
            pageBean.setTotal(total);
            List<AhContact> list = dao.getManContactList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                String user_ids = "0";
                for(AhContact report :list ){
                    if(!ConvertUtil.isEmpty(report.getAc_name())){
                        user_ids += ",'"+report.getAc_name()+"' ";
                    }
                }
                if(!"0".equals(user_ids)){
                    List<AhUser> users = userDao.getUsersKeys(user_ids);
                    if(users!=null && users.size()>0){
                        Map<String,AhUser> userMap = new HashMap<>();
                        for(AhUser user:users){
                            userMap.put(user.getAu_id(),user);
                        }
                        for(AhContact report :list ){
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
            AhContact contact = dao.getContactBeanOneKey(id);
            if(contact!=null && !ConvertUtil.isEmpty(contact.getAh_id())){
                contact.setDel(1);
                dao.updateContactDel(contact);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    /**
     *  app问题反馈进度 列表
     */
    public String schedules(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("17");
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

    public String info(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            AhContact contact = dao.getContactOneKey(id);
            if(contact!=null && !ConvertUtil.isEmpty((contact.getAh_id()))){
                AhContactHandle contactHandle =this.getContactHandleOnly(contact,user);
                if(contactHandle!=null){
                    contact.setStatus(contactHandle.getAch_status());
                    contact.setContent(contactHandle.getAch_content());
                }
                app.setObj(contact);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public AhContactHandle getContactHandleOnly(AhContact contact, AhUser user){
        AhContactHandle contactHandle = null;
        if(contact!=null && !ConvertUtil.isEmpty(contact.getAh_id())){
            Date date = new Date();
            contactHandle = dao.gethContactHandleOneKey(contact.getAh_id());
            if(contactHandle==null || ConvertUtil.isEmpty(contactHandle.getAch_id())){
                contactHandle = new AhContactHandle();
                contactHandle.setAch_contact(contact.getAh_id());
                String handle_user= "";//默认系统自己解决
                if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                    handle_user = user.getAu_id();
                }
                contactHandle.setAch_user(handle_user);
                contactHandle.setAch_status(0);
                contactHandle.setAch_content("");
                contactHandle.setDel(0);
                contactHandle.setCreate_date(date);

                dao.insertContactHandle(contactHandle);
            }
        }
        return contactHandle;
    }

    public String corfirm(ReportHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                AhContact contact = dao.getContactOneKey(form.getId());
                if(contact!=null && !ConvertUtil.isEmpty(contact.getAh_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    AhContactHandle contactHandle =this.getContactHandleOnly(contact,user);
                    if(contactHandle!=null){
                        if(contactHandle.getAch_status()<1){
                            contactHandle.setAch_status(form.getStatus());
                            contactHandle.setAch_content(form.getContent());
                            contactHandle.setAch_user(access_key);

                            //更新 处理结果
                            dao.updateContactHandle(contactHandle);
                            contact.setStatus(contactHandle.getAch_status());
                            contact.setContent(contactHandle.getAch_content());

                            app.setObj(contact);
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
