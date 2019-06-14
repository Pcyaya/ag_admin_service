package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.NewsDao;
import com.runyee.agdhome.dao.ag_home.RecruitDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.ex.NewsBean;
import com.runyee.agdhome.entity.ex.RecruitApplyBean;
import com.runyee.agdhome.entity.ex.RecruitBean;
import com.runyee.agdhome.entity.form.*;
import com.runyee.agdhome.entity.page.*;
import com.runyee.agdhome.entity.pagebean.ManRecruitApplyPageBean;
import com.runyee.agdhome.entity.pagebean.ManRecruitPageBean;
import com.runyee.agdhome.entity.pagebean.NewsPageBean;
import com.runyee.agdhome.entity.pagebean.RecruitPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class RecruitService {
    @Autowired
    private RecruitDao dao;

    public String list(RecruitPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            //业务类型
            String business = pageBean.getBusiness();
            if(ConvertUtil.isEmpty(business)){
                business = "arb_000000";
            }
            List<AhRecruitBusiness>  blist = dao.getRecruitBusinessAllList(0,5);
            if(blist!=null && blist.size()>0){
                List<RecruitBusinessPage> blist_pages = new ArrayList<>();
                for(AhRecruitBusiness busi:blist){
                    RecruitBusinessPage b_page = new RecruitBusinessPage();
                    b_page.setId(busi.getArb_id());
                    b_page.setName(busi.getArb_name());
                    if(business.equals(b_page.getId())){
                        b_page.setShow_flg(1);
                    }
                    blist_pages.add(b_page);

                }
                pageBean.setBlist(blist_pages);
            }
            //招聘类型
            String filter = " and ar_audit = 1 ";
            filter += " and ar_business = '"+business+"'";
            List<AhRecruit> list = dao.getRecruitList(filter,pageBean.getCurrPage(),pageBean.getRows());
            if(list!=null && list.size()> 0){
                List<RecruitPage> pages = new ArrayList<>();//新闻列表
                for(AhRecruit recruit:list){
                    RecruitPage recruitPage = new RecruitPage();

                    recruitPage.setId(recruit.getAr_id());
                    recruitPage.setName(recruit.getAr_name());
                    recruitPage.setAddr(recruit.getAr_addr());
                    String exper = "";
                    if(recruit.getAr_exper()>0){
                        exper +=recruit.getAr_exper();
                    }
                    if(recruit.getAr_exper_end()>0){
                        if(!ConvertUtil.isEmpty(exper)){
                            exper +="-"+recruit.getAr_exper_end();
                        }else{
                            exper +=recruit.getAr_exper_end();
                        }
                    }

                    if(!ConvertUtil.isEmpty(exper)){
                        exper +="年";
                    }else{
                        exper +="无";
                    }

                    recruitPage.setExper(exper);
                    recruitPage.setEdu(recruit.getAr_edu());
                    recruitPage.setDate(recruit.getCreate_date());

                    List<AhRecruitDetail> contents = dao.getRecruitDetailList(recruit.getAr_id());
                    if(contents!=null&&contents.size()>0){
                        List<RecruitDetailPage> details = new ArrayList<>();
                        for(AhRecruitDetail content:contents){
                            RecruitDetailPage detail = new RecruitDetailPage();
                            detail.setTitle(content.getArd_title());
                            detail.setContent(content.getArd_content());
                            detail.setType(content.getArd_type());
                            detail.setUrl(content.getArd_url());

                            details.add(detail);
                        }
                        recruitPage.setDetails(details);
                    }

                    pages.add(recruitPage);
                }
                pageBean.setData(pages);
            }


            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }

        return service_code;
    }

    public String apply(RecruitApplyForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getRecruit())){
                AhRecruit recruit = dao.getRecruitOneKey(form.getRecruit());
                if(recruit!=null && !ConvertUtil.isEmpty(recruit.getAr_id())){
                    if(!ConvertUtil.isEmpty(form.getName())
                            &&!ConvertUtil.isEmpty(form.getPhone())
                            &&!ConvertUtil.isEmpty(form.getContent())){
                        Date date = new Date();

                        String filter = "";
                        filter += "   and timestampdiff(hour,create_date,'" + DateUtils.getTimestamp(date) + "') between  0 and 24     ";
                        List<AhRecruitApply> applys = dao.getRecruitApplysPhone(form.getPhone(),filter);
                        if(applys!=null&&applys.size()>0){
                            //每天最多3次
                            if(applys.size()<3){
                                AhRecruitApply apply = applys.get(0);
                                if(apply!=null &&
                                        !ConvertUtil.isEmpty(apply.getAra_id())&&
                                        apply.getCreate_date()!=null){
                                    //重复操作
                                    long interval = date.getTime()-apply.getCreate_date().getTime();
                                    if(interval <= (DateUtils.SECOND_IN_MILLIS)){
                                        service_code = ServiceCode.fail_recruit_apply_second;
                                    }else{
                                        //五分钟内只能有一次
                                        if(interval <= (5*DateUtils.MINUTE_IN_MILLIS)){
                                            service_code = ServiceCode.fail_recruit_apply_minute;
                                        }else{
                                            service_code = ServiceCode.SUCCESS;
                                        }
                                    }
                                }

                            }else{
                                service_code = ServiceCode.fail_recruit_apply_day;
                            }
                        }else{
                            service_code = ServiceCode.SUCCESS;
                        }

                        if(ServiceCode.SUCCESS.equals(service_code)){
                            AhRecruitApply apply = new AhRecruitApply();
                            apply.setAra_recruit(recruit.getAr_id());
                            apply.setAra_name(form.getName());
                            apply.setAra_phone(form.getPhone());
                            apply.setAra_email(form.getEmail());
                            apply.setAra_sex(form.getSex());
                            apply.setAra_content(form.getContent());
                            apply.setDel(0);
                            apply.setCreate_date(date);
                            dao.insertRecruitApply(apply);
                            if(!ConvertUtil.isEmpty(form.getResume())){
                                AhRecruitApplyDetail detail = new AhRecruitApplyDetail();
                                detail.setArad_apply(apply.getAra_id());
                                detail.setArad_title("");
                                detail.setArad_content("");
                                detail.setArad_sort(0);
                                detail.setArad_type(4);
                                detail.setArad_url(form.getResume());
                                detail.setDel(0);
                                detail.setCreate_date(date);

                                dao.insertRecruitApplyDetail(detail);
                            }
                            service_code = ServiceCode.success_apply;
                        }

                    }else{
                        service_code = ServiceCode.fail_apply_necessary;
                        List<String> msgs = new ArrayList<>();
                        String msg = "您的姓名";
                        if(!ConvertUtil.isEmpty(form.getName())){
                            if(ConvertUtil.isEmpty(form.getPhone())){
                                msg = "您的电话";
                            }else if(ConvertUtil.isEmpty(form.getContent())){
                                msg = "您的描述";
                            }
                        }
                        msgs.add(msg);
                        DataUtil.getCurrent().setMsgs(msgs);
                    }
                }else{
                    service_code = ServiceCode.fail_apply_noexists;
                }
            }else {
               service_code = ServiceCode.fail_param_error;
            }

        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }


    public String man_business_list(AppJson app){
        String service_code = ServiceCode.ERRO;
        List<AhRecruitBusiness> blist = dao.getRecruitBusinessAllList(0,5);
        List<RecruitBusinessPage> blist_pages = new ArrayList<>();
        if(blist!=null && blist.size()>0){
            for(AhRecruitBusiness busi:blist){
                RecruitBusinessPage b_page = new RecruitBusinessPage();
                b_page.setId(busi.getArb_id());
                b_page.setName(busi.getArb_name());
                blist_pages.add(b_page);
            }
        }
        app.setObj(blist_pages);
        service_code  = ServiceCode.SUCCESS;
        return service_code;
    }

    public String man_recruit_list(ManRecruitPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            String business = pageBean.getBusiness();
            if(!ConvertUtil.isEmpty(business)){
                //招聘类型
                filter += " and ar_business = '"+business+"'";
            }
            if(!ConvertUtil.isEmpty(pageBean.getName())){
                filter += " and ar_name like '%"+pageBean.getName()+"%'";
            }
            if(pageBean.getExper()>0){
                /*if(pageBean.getExper()==0){
                    filter += "and ar_exper = '"+pageBean.getExper()+"' ";
                    filter += "and ar_exper_end = '"+pageBean.getExper()+"' ";
                }else{
                }*/
                filter += " and ar_exper <= '"+pageBean.getExper()+"' ";
                filter += " and ar_exper_end >= '"+pageBean.getExper()+"' ";
            }
            if(!ConvertUtil.isEmpty(pageBean.getEdu())){
                filter += " and ar_edu like '%"+pageBean.getEdu()+"%'";
            }

            if(!ConvertUtil.isEmpty(pageBean.getStart())){
                filter += " and date_format(create_date,'%Y-%m-%d') =  '"+pageBean.getStart()+"'";
            }

            if(pageBean.getAudit()>=0){
                filter += " and ar_audit = '"+pageBean.getAudit()+"' ";
            }

            int total = dao.getManRecruitCount(filter);
            pageBean.setTotal(total);

            List<RecruitBean> list = dao.getManRecruitList(filter,pageBean.getCurrPage(),pageBean.getRows());
            if(list!=null && list.size()> 0){
                List<ManRecruitPage> pages = new ArrayList<>();//新闻列表
                for(RecruitBean recruit:list){
                    ManRecruitPage recruitPage = new ManRecruitPage();

                    recruitPage.setId(recruit.getAr_id());
                    recruitPage.setName(recruit.getAr_name());
                    recruitPage.setAddr(recruit.getAr_addr());
                    recruitPage.setBusiness_name(recruit.getBusiness_name());
                    recruitPage.setApply_num(recruit.getApply_num());
                    recruitPage.setAudit(recruit.getAr_audit());

                    String exper = "";
                    if(recruit.getAr_exper()>0){
                        exper +=recruit.getAr_exper();
                    }
                    if(recruit.getAr_exper_end()>0){
                        if(!ConvertUtil.isEmpty(exper)){
                            if(recruit.getAr_exper()!= recruit.getAr_exper_end() ){
                                exper +="-"+recruit.getAr_exper_end();
                            }
                        }else{
                            exper +=recruit.getAr_exper_end();
                        }
                    }

                    if(!ConvertUtil.isEmpty(exper)){
                        exper +="年";
                    }else{
                        exper +="无";
                    }

                    recruitPage.setExper(exper);
                    recruitPage.setEdu(recruit.getAr_edu());
                    recruitPage.setDate(recruit.getCreate_date());

                    List<AhRecruitDetail> contents = dao.getRecruitDetailList(recruit.getAr_id());
                    if(contents!=null&&contents.size()>0){
                        List<RecruitDetailPage> details = new ArrayList<>();
                        for(AhRecruitDetail content:contents){
                            RecruitDetailPage detail = new RecruitDetailPage();
                            detail.setTitle(content.getArd_title());
                            detail.setContent(content.getArd_content());
                            detail.setType(content.getArd_type());
                            detail.setUrl(content.getArd_url());

                            details.add(detail);
                        }
                        recruitPage.setDetails(details);
                    }

                    pages.add(recruitPage);
                }
                pageBean.setData(pages);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }

        return service_code;
    }


    public String man_recruit_content(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhRecruit recruit = dao.getRecruitOneKey(id);
            if(recruit!=null && !ConvertUtil.isEmpty(recruit.getAr_id())){
                ManRecruitPage recruitPage = new ManRecruitPage();

                recruitPage.setId(recruit.getAr_id());
                recruitPage.setName(recruit.getAr_name());
                recruitPage.setAddr(recruit.getAr_addr());
                /*recruitPage.setBusiness_name(recruit.getBusiness_name());
                recruitPage.setApply_num(recruit.getApply_num());*/
                recruitPage.setAudit(recruit.getAr_audit());

                String exper = "";
                if(recruit.getAr_exper()>0){
                    exper +=recruit.getAr_exper();
                }
                if(recruit.getAr_exper_end()>0){
                    if(!ConvertUtil.isEmpty(exper)){
                        if(recruit.getAr_exper()!= recruit.getAr_exper_end() ){
                            exper +="-"+recruit.getAr_exper_end();
                        }
                    }else{
                        exper +=recruit.getAr_exper_end();
                    }
                }

                if(!ConvertUtil.isEmpty(exper)){
                    exper +="年";
                }else{
                    exper +="无";
                }

                recruitPage.setExper(exper);
                recruitPage.setEdu(recruit.getAr_edu());
                recruitPage.setDate(recruit.getCreate_date());

                List<AhRecruitDetail> contents = dao.getRecruitDetailList(recruit.getAr_id());
                if(contents!=null&&contents.size()>0){
                    List<RecruitDetailPage> details = new ArrayList<>();
                    for(AhRecruitDetail content:contents){
                        RecruitDetailPage detail = new RecruitDetailPage();
                        detail.setTitle(content.getArd_title());
                        detail.setContent(content.getArd_content());
                        detail.setType(content.getArd_type());
                        detail.setUrl(content.getArd_url());

                        details.add(detail);
                    }
                    recruitPage.setDetails(details);
                }

                app.setObj(recruitPage);
            }
            service_code  = ServiceCode.SUCCESS;
        }

        return service_code;
    }

    public String man_del(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        AhRecruit recruit = dao.getRecruitOneKey(id);
        if(recruit!=null && !ConvertUtil.isEmpty(recruit.getAr_id())){
            recruit.setDel(1);
            dao.updateDelRecruit(recruit);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code  = ServiceCode.fail_man_recruit_del;
        }
        return service_code;
    }

    public String man_add_edit(RecruitForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        Date date = new Date();
        if(form!=null){
            if(form.getExper()<=form.getExper_end()){
                if(ConvertUtil.isEmpty(form.getId())){
                    if(!ConvertUtil.isEmpty(form.getBusiness())&&
                            !ConvertUtil.isEmpty(form.getName())&&
                            !ConvertUtil.isEmpty(form.getAddr())&&
                            !ConvertUtil.isEmpty(form.getEdu())&&
                            !ConvertUtil.isEmpty(form.getDetails())){
                        //验证detail
                        List<RecruitDetailForm> details = new ArrayList<>();

                        JSONArray details_json =  JSONArray.fromObject(form.getDetails());
                        if(details_json!=null){
                            details =  JSONArray.toList(details_json,RecruitDetailForm.class);
                        }

                        if(details!=null && details.size()>0){
                            service_code = ServiceCode.SUCCESS;
                            //新增
                            AhRecruit recruit = new AhRecruit();
                            recruit.setAr_business(form.getBusiness());
                            recruit.setAr_name(form.getName());
                            recruit.setAr_addr(form.getAddr());
                            recruit.setAr_exper(form.getExper());
                            recruit.setAr_exper_end(form.getExper_end());
                            recruit.setAr_edu(form.getEdu());
                            //recruit.setAr_audit(form.getAudit());
                            recruit.setAr_audit(form.getAudit());
                            recruit.setDel(0);
                            recruit.setCreate_date(date);

                            dao.insertRecruit(recruit);
                            int i =0;
                            for(RecruitDetailForm detail:details){
                                AhRecruitDetail recruitDetail = new AhRecruitDetail();
                                recruitDetail.setArd_recruit(recruit.getAr_id());
                                recruitDetail.setArd_title(detail.getTitle());
                                recruitDetail.setArd_content(detail.getContent());
                                recruitDetail.setArd_sort(i);
                                recruitDetail.setArd_type(detail.getType());
                                recruitDetail.setArd_url(detail.getUrl());
                                recruitDetail.setDel(0);
                                recruitDetail.setCreate_date(date);

                                dao.insertRecruitDetail(recruitDetail);
                                i++;
                            }

                        }else{
                            service_code = ServiceCode.fail_man_recruit_empty;
                            List<String> msgs = new ArrayList<>();
                            String msg = "招聘详情";
                            msgs.add(msg);
                            DataUtil.getCurrent().setMsgs(msgs);
                        }
                    }else{
                        service_code = ServiceCode.fail_man_recruit_empty;
                        List<String> msgs = new ArrayList<>();
                        String msg = "职位类别";
                        if(ConvertUtil.isEmpty(form.getName())){
                            msg = "职称";
                        }else if(ConvertUtil.isEmpty(form.getAddr())){
                            msg = "工作地点";
                        }else if(ConvertUtil.isEmpty(form.getEdu())){
                            msg = "学历";
                        }else if(ConvertUtil.isEmpty(form.getDetails())){
                            msg = "招聘详情";
                        }
                        msgs.add(msg);
                        DataUtil.getCurrent().setMsgs(msgs);
                    }
                }else{
                    //更新
                    AhRecruit recruit = dao.getRecruitOneKey(form.getId());
                    if(recruit!=null && !ConvertUtil.isEmpty(recruit.getAr_id())){
                        if(!ConvertUtil.isEmpty(form.getBusiness())&&!form.getBusiness().equals(recruit.getAr_business())){
                            service_code = ServiceCode.SUCCESS;
                            recruit.setAr_business(form.getBusiness());
                        }
                        if(!ConvertUtil.isEmpty(form.getName())&&!form.getName().equals(recruit.getAr_name())){
                            service_code = ServiceCode.SUCCESS;
                            recruit.setAr_name(form.getName());
                        }
                        if(!ConvertUtil.isEmpty(form.getAddr())&&!form.getAddr().equals(recruit.getAr_addr())){
                            service_code = ServiceCode.SUCCESS;
                            recruit.setAr_addr(form.getAddr());
                        }

                        if(!ConvertUtil.isEmpty(form.getEdu())&&!form.getEdu().equals(recruit.getAr_edu())){
                            service_code = ServiceCode.SUCCESS;
                            recruit.setAr_edu(form.getEdu());
                        }
                        if(form.getExper()!=recruit.getAr_exper()){
                            service_code = ServiceCode.SUCCESS;
                            recruit.setAr_exper(form.getExper());
                        }

                        if(form.getExper_end()!=recruit.getAr_exper_end()){
                            service_code = ServiceCode.SUCCESS;
                            recruit.setAr_exper_end(form.getExper_end());
                        }

                    /*if(form.getAudit()!=recruit.getAr_audit()){
                        service_code = ServiceCode.SUCCESS;
                        recruit.setAr_audit(form.getAudit());
                    }*/


                        if(ServiceCode.SUCCESS.equals(service_code)){
                            //更新新闻
                            recruit.setCreate_date(date);
                            dao.updateNews(recruit);
                        }
                        service_code = ServiceCode.SUCCESS;
                        if(!ConvertUtil.isEmpty(form.getDetails())){
                            List<NewsDetailForm> details = new ArrayList<>();
                            JSONArray details_json =  JSONArray.fromObject(form.getDetails());
                            if(details_json!=null){
                                details =  JSONArray.toList(details_json,NewsDetailForm.class);
                            }
                            if(details!=null && details.size()>0){
                                //删除之前所有详情
                                dao.updateDelRecruitDetail(recruit.getAr_id());
                                int i =0;
                                for(NewsDetailForm detail:details){
                                    AhRecruitDetail recruitDetail = new AhRecruitDetail();
                                    recruitDetail.setArd_recruit(recruit.getAr_id());
                                    recruitDetail.setArd_title(detail.getTitle());
                                    recruitDetail.setArd_content(detail.getContent());
                                    recruitDetail.setArd_sort(i);
                                    recruitDetail.setArd_type(detail.getType());
                                    recruitDetail.setArd_url(detail.getUrl());
                                    recruitDetail.setDel(0);
                                    recruitDetail.setCreate_date(date);

                                    dao.insertRecruitDetail(recruitDetail);
                                    i++;
                                }
                            }
                        }
                    }else{
                        service_code = ServiceCode.fail_man_recruit_del;
                    }
                }

            }else{

            }
        }
        return service_code;
    }

    public String man_apply_list(ManRecruitApplyPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            if(!ConvertUtil.isEmpty(pageBean.getId())){
                AhRecruit recruit = dao.getRecruitOneKey(pageBean.getId());
                if(recruit!=null && !ConvertUtil.isEmpty(recruit.getAr_id())){
                    String filter = "";
                    filter += " and ara_recruit = '"+pageBean.getId()+"'    ";
                    if(!ConvertUtil.isEmpty(pageBean.getName())){
                        filter += " and ara_name like '%"+pageBean.getName()+"%'    ";
                    }
                    if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                        filter += " and ara_phone like '%"+pageBean.getPhone()+"%'  ";
                    }
                    if(!ConvertUtil.isEmpty(pageBean.getEmail())){
                        filter += " and ara_email like '%"+pageBean.getEmail()+"%'  ";
                    }
                    if(pageBean.getSex()>=0){
                        filter += " and ara_sex = '"+pageBean.getSex()+"'   ";
                    }
                    if(pageBean.getResume_flg()>=0){
                        if(pageBean.getResume_flg()==0){
                            filter += " and not exists(select arad.arad_id " +
                                    "                   from ah_recruit_apply_detail arad " +
                                    "                   where ifnull(arad.del,0)=0 and arad.arad_apply = ara.ara_id " +
                                    "                   and ifnull(arad_type,0)=4  )    ";
                        }else{
                            filter += " and exists(select arad.arad_id " +
                                    "                   from ah_recruit_apply_detail arad " +
                                    "                   where ifnull(arad.del,0)=0 and arad.arad_apply = ara.ara_id " +
                                    "                   and ifnull(arad_type,0)=4  )    ";
                        }
                    }

                    int total = dao.getManRecruitApplyCount(filter);
                    pageBean.setTotal(total);

                    List<RecruitApplyBean> list = dao.getManRecruitApplyList(filter,pageBean.getCurrPage(),pageBean.getRows());
                    if(list!=null && list.size()> 0){
                        List<ManRecruitApplyPage> pages = new ArrayList<>();//
                        for(RecruitApplyBean apply:list){
                            ManRecruitApplyPage applyPage = new ManRecruitApplyPage();
                            applyPage.setRecruit_name(recruit.getAr_name());
                            applyPage.setId(apply.getAra_id());
                            applyPage.setName(apply.getAra_name());
                            applyPage.setPhone(apply.getAra_phone());
                            applyPage.setEmail(apply.getAra_email());
                            applyPage.setSex(apply.getAra_sex());
                            applyPage.setContent(apply.getAra_content());
                            applyPage.setCreate_date(apply.getCreate_date());
                            applyPage.setResume_flg(apply.getResume_flg());

                            List<AhRecruitApplyDetail> apply_details = dao.getRecruitApplyDetailList(apply.getAra_id());
                            if(apply_details!=null&&apply_details.size()>0){
                                List<RecruitApplyDetailPage> details = new ArrayList<>();
                                for(AhRecruitApplyDetail apply_detail:apply_details){
                                    RecruitApplyDetailPage detail = new RecruitApplyDetailPage();
                                    detail.setTitle(apply_detail.getArad_title());
                                    detail.setContent(apply_detail.getArad_content());
                                    detail.setType(apply_detail.getArad_type());
                                    detail.setUrl(apply_detail.getArad_url());

                                    details.add(detail);
                                }
                                applyPage.setDetails(details);
                            }
                            pages.add(applyPage);
                        }
                        pageBean.setData(pages);
                    }
                    app.setObj(pageBean);
                    service_code  = ServiceCode.SUCCESS;
                }else{
                    service_code = ServiceCode.fail_man_recruit_del;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String man_audit(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        AhRecruit recruit = dao.getRecruitOneKey(id);
        if(recruit!=null && !ConvertUtil.isEmpty(recruit.getAr_id())){
            if(recruit.getAr_audit()==0){
                recruit.setAr_audit(1);
            }else{
                recruit.setAr_audit(0);
            }
            dao.updateAuditRecruit(recruit);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code  = ServiceCode.fail_man_recruit_del;
        }
        return service_code;
    }

}
