package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.anygo.AgSurvivalDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.PageBean;
import com.runyee.agdhome.entity.db.anygo.RySurvival;
import com.runyee.agdhome.entity.db.anygo.RySurvivalCapsule;
import com.runyee.agdhome.entity.db.anygo.RyUrgent;
import com.runyee.agdhome.entity.db.anygo.RyUrgentPhone;
import com.runyee.agdhome.entity.form.SurvivalForm;
import com.runyee.agdhome.entity.form.UPhoneForm;
import com.runyee.agdhome.entity.form.UrgentForm;
import com.runyee.agdhome.entity.page.*;
import com.runyee.agdhome.entity.pagebean.SurvivalCapsulePageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class SurvivalCapsuleService {

    @Autowired
    private AgSurvivalDao dao;

    public String list(SurvivalCapsulePageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String search = pageBean.getSearch();
            String filter = "";
            if(!ConvertUtil.isEmpty(search)){
                filter += " and rs_title like '%"+search+"%' ";
            }
            int total = dao.getSurvivalCount(filter);
            pageBean.setTotal(total);
            List<RySurvival>  survivals = dao.getSurvivals(filter,pageBean.getCurrPage(),pageBean.getRows());
            if (survivals!=null && survivals.size()>0){
                List<SurvivalPage> pages = new ArrayList<>();
                for(RySurvival survival:survivals){
                    SurvivalPage page = new SurvivalPage();
                    page.setId(survival.getRs_id());
                    page.setTitle(survival.getRs_title());
                    page.setContent(survival.getRs_content());
                    page.setSync(survival.getRs_sync());
                    pages.add(page);
                }
                pageBean.setData(pages);
            }
            app.setObj(pageBean);
            service_code = ServiceCode.SUCCESS;
        }else {
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }


    public String info(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RySurvival  survival = dao.getSurvivalOneKey(id);
            if(survival!=null && !ConvertUtil.isEmpty(survival.getRs_id())){
                SvPage svpage = new SvPage();
                svpage.setId(survival.getRs_id());
                svpage.setTitle(survival.getRs_title());
                svpage.setContent(survival.getRs_content());
                svpage.setSync(survival.getRs_sync());
                List<RySurvivalCapsule>  capsules = dao.getSurvivalCapsules(survival.getRs_id());
                if(capsules!=null&&capsules.size()>0){
                    List<SvCapPage> caps = new ArrayList<>();
                    for(RySurvivalCapsule capsule :capsules){
                        SvCapPage cap = new SvCapPage();
                        cap.setTitle(capsule.getRsc_title());
                        cap.setContent(capsule.getRsc_content());
                        caps.add(cap);
                    }

                    svpage.setCapsules(caps);
                }
                app.setObj(svpage);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }else {
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public String issue(SurvivalForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            service_code = this.valid_form(form);
            if(ServiceCode.SUCCESS.equals(service_code)){
                RySurvival survival = null;
                if(!ConvertUtil.isEmpty(form.getId())){
                    //更新
                    survival = dao.getSurvivalOneKey(form.getId());
                    if(survival!=null && !ConvertUtil.isEmpty(survival.getRs_id())){
                        //清除详情
                        dao.updateSurvivalCapsuleDelAll(survival.getRs_id());
                        survival.setRs_title(form.getTitle());
                        survival.setRs_content(form.getContent());
                        dao.updateSurvival(survival);
                        service_code = ServiceCode.SUCCESS;
                    }else{
                        service_code = ServiceCode.fail_param_error;
                    }
                }else{
                    //新增
                    survival = new RySurvival();
                    survival.setRs_title(form.getTitle());
                    survival.setRs_content(form.getContent());
                    survival.setRs_sync(form.getSync());
                    survival.setRs_del(0);
                    survival.setCreate_date(DateUtils.getDate());
                    dao.insertSurvival(survival);
                    service_code = ServiceCode.SUCCESS;
                }
            }
        }
        return service_code;
    }

    public String valid_form(SurvivalForm form){
        String service_code = ServiceCode.ERRO;
        String msg = "生存锦囊标题";
        if(!ConvertUtil.isEmpty(form.getTitle())){
            if(!ConvertUtil.isEmpty(form.getContent())){
                String filter = " and rs_title = '"+form.getTitle()+"' ";
                if(!ConvertUtil.isEmpty(form.getId())){
                    filter += " and rs_id <> '"+form.getId()+"' ";
                }
                int count = dao.getSurvivalCount(filter);
                if(count>0){
                    msg = "生存锦囊标题:"+form.getTitle();
                    service_code = ServiceCode.fail_survival_exists;
                }else{
                    service_code = ServiceCode.SUCCESS;
                }
            }else{
                msg = "生存锦囊内容";
                service_code = ServiceCode.fail_survival_empty;
            }
        }else{
            service_code = ServiceCode.fail_survival_empty;
        }
        if(!ServiceCode.SUCCESS.equals(service_code)){
            List<String> msgs = new ArrayList<>();
            msgs.add(msg);
            DataUtil.getCurrent().setMsgs(msgs);
        }
        return service_code;
    }

    public String del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RySurvival survival = dao.getSurvivalOneKey(id);
            if(survival!=null && !ConvertUtil.isEmpty(survival.getRs_id())){
                //清除详情
                dao.updateSurvivalCapsuleDelAll(survival.getRs_id());
                survival.setRs_del(1);
                dao.updateSurvivalDel(survival);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String sync(String id, String opt, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RySurvival survival = dao.getSurvivalOneKey(id);
            if(survival!=null && !ConvertUtil.isEmpty(survival.getRs_id())){
                //清除详情
                //dao.updateSurvivalCapsuleDelAll(survival.getRs_id());
                survival.setRs_sync(ConvertUtil.convertStrToInt(opt));
                dao.updateSurvivalSync(survival);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String uphones(PageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            int total = dao.getUrgentCount(filter);
            pageBean.setTotal(total);
            List<RyUrgent>  urgents = dao.getUrgentList(filter,pageBean.getCurrPage(),pageBean.getRows());
            if (urgents!=null && urgents.size()>0){
                List<UrgentPage> pages = new ArrayList<>();
                for(RyUrgent urgent:urgents){
                    UrgentPage page = new UrgentPage();
                    page.setId(urgent.getRu_id());
                    page.setTitle(urgent.getRu_name());
                    page.setSync(urgent.getRu_sync());

                    List<RyUrgentPhone> uphones = dao.getUrgentPhones(urgent.getRu_id());
                    if(uphones!=null && uphones.size()>0){
                        List<UrgentPhonePage> details = new ArrayList();
                        for(RyUrgentPhone uphone:uphones){
                            UrgentPhonePage phone = new UrgentPhonePage();
                            phone.setId(uphone.getRup_id());
                            phone.setTitle(uphone.getRup_title());
                            phone.setPhone(uphone.getRup_phone());
                            phone.setSync(uphone.getRup_sync());

                            details.add(phone);
                        }
                        page.setDetails(details);
                    }
                    pages.add(page);
                }
                pageBean.setData(pages);
            }
            app.setObj(pageBean);
            service_code = ServiceCode.SUCCESS;
        }else {
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }


    public String urgent_issue(UrgentForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            service_code = this.urgent_valid_form(form);
            if(ServiceCode.SUCCESS.equals(service_code)){
                RyUrgent urgent = null;
                if(!ConvertUtil.isEmpty(form.getId())){
                    //更新
                    urgent = dao.getUrgentOneKey(form.getId());
                    if(urgent!=null && !ConvertUtil.isEmpty(urgent.getRu_id())){
                        urgent.setRu_name(form.getName());
                        dao.updateUrgent(urgent);
                        service_code = ServiceCode.SUCCESS;
                    }else{
                        service_code = ServiceCode.fail_param_error;
                    }
                }else{
                    //新增
                    urgent = new RyUrgent();
                    urgent.setRu_name(form.getName());
                    urgent.setRu_sync(form.getSync());
                    urgent.setRu_del(0);
                    urgent.setCreate_date(DateUtils.getDate());
                    dao.insertUrgent(urgent);
                    service_code = ServiceCode.SUCCESS;
                }
            }
        }
        return service_code;
    }

    public String urgent_valid_form(UrgentForm form){
        String service_code = ServiceCode.ERRO;
        String msg = "国家名称";
        if(!ConvertUtil.isEmpty(form.getName())){
            String filter = " and ru_name = '"+form.getName()+"' ";
            if(!ConvertUtil.isEmpty(form.getId())){
                filter += " and ru_id <> '"+form.getId()+"' ";
            }
            int count = dao.getUrgentCount(filter);
            if(count>0){
                msg = "国家名称:"+form.getName();
                service_code = ServiceCode.fail_urgent_exists;
            }else{
                service_code = ServiceCode.SUCCESS;
            }
        }else{
            service_code = ServiceCode.fail_urgent_empty;
        }

        if(!ServiceCode.SUCCESS.equals(service_code)){
            List<String> msgs = new ArrayList<>();
            msgs.add(msg);
            DataUtil.getCurrent().setMsgs(msgs);
        }
        return service_code;
    }

    public String urgent_del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyUrgent urgent = dao.getUrgentOneKey(id);
            if(urgent!=null && !ConvertUtil.isEmpty(urgent.getRu_id())){
                urgent.setRu_del(1);
                dao.updateUrgentDel(urgent);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String urgent_sync(String id, String opt, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyUrgent urgent = dao.getUrgentOneKey(id);
            if(urgent!=null && !ConvertUtil.isEmpty(urgent.getRu_id())){
                urgent.setRu_sync(ConvertUtil.convertStrToInt(opt));
                dao.updateUrgentSync(urgent);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String uphone_issue(UPhoneForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            service_code = this.uphone_valid_form(form);
            if(ServiceCode.SUCCESS.equals(service_code)){
                RyUrgentPhone uphone = null;
                if(!ConvertUtil.isEmpty(form.getId())){
                    //更新
                    uphone = dao.getUrgentPhoneOneKey(form.getId());
                    if(uphone!=null && !ConvertUtil.isEmpty(uphone.getRup_id())){
                        uphone.setRup_title(form.getTitle());
                        uphone.setRup_phone(form.getPhone());
                        dao.updateUrgentPhone(uphone);

                    }else{
                        service_code = ServiceCode.fail_param_error;
                    }
                }else{
                    //新增
                    uphone = new RyUrgentPhone();
                    uphone.setRup_urgent(form.getUrgent());
                    uphone.setRup_title(form.getTitle());
                    uphone.setRup_phone(form.getPhone());
                    uphone.setRup_sync(form.getSync());
                    uphone.setRup_del(0);
                    uphone.setCreate_date(DateUtils.getDate());
                    dao.insertUrgentPhone(uphone);

                }
            }
        }
        return service_code;
    }

    public String uphone_valid_form(UPhoneForm form){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(form.getUrgent())){
            RyUrgent urgent = dao.getUrgentOneKey(form.getUrgent());
            if(urgent!=null&&!ConvertUtil.isEmpty(urgent.getRu_id())){
                String msg = "类别";
                if(!ConvertUtil.isEmpty(form.getTitle())){
                    if(!ConvertUtil.isEmpty(form.getPhone())){
                        String filter = " and rup_urgent = '"+form.getUrgent()+"' ";
                        filter+=" and rup_title = '"+form.getTitle()+"' ";
                        if(!ConvertUtil.isEmpty(form.getId())){
                            filter += " and rup_id <> '"+form.getId()+"' ";
                        }
                        int count = dao.getUrgentPhoneCount(filter);
                        if(count>0){
                            msg = urgent.getRu_name()+" 的 "+form.getTitle()+" 类别";
                            service_code = ServiceCode.fail_uphone_exists;
                        }else{
                            service_code = ServiceCode.SUCCESS;
                        }
                    }else {
                        msg="紧急电话";
                        service_code = ServiceCode.fail_uphone_empty;
                    }
                }else {
                    service_code = ServiceCode.fail_uphone_empty;
                }
                if(!ServiceCode.SUCCESS.equals(service_code)){
                    List<String> msgs = new ArrayList<>();
                    msgs.add(msg);
                    DataUtil.getCurrent().setMsgs(msgs);
                }

            }else{
                service_code = ServiceCode.fail_uphone_urgentdel;
            }
        }else{
            service_code = ServiceCode.fail_uphone_urgent;
        }

        return service_code;
    }

    public String uphone_del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyUrgentPhone uphone = dao.getUrgentPhoneOneKey(id);
            if(uphone!=null && !ConvertUtil.isEmpty(uphone.getRup_id())){
                uphone.setRup_del(1);
                dao.updateUrgentPhoneDel(uphone);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String uphone_sync(String id, String opt, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyUrgentPhone uphone = dao.getUrgentPhoneOneKey(id);
            if(uphone!=null && !ConvertUtil.isEmpty(uphone.getRup_id())){
                uphone.setRup_sync(ConvertUtil.convertStrToInt(opt));
                dao.updateUrgentPhoneSync(uphone);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }


}
