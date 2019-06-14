package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.dao.anygo.*;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.anygo.*;
import com.runyee.agdhome.entity.ex.*;
import com.runyee.agdhome.entity.form.ReportHandleForm;
import com.runyee.agdhome.entity.pagebean.ManReportPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class ReportService {
    @Autowired
    private ReportDao dao;

    @Autowired
    private AgCommonValDao commonValDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AgVisitorDao friendDao;

    @Autowired
    private AgGroupDao groupDao;

    @Autowired
    private AgVisitorDao noteDao;

    @Autowired
    private  AgTravelNoteDao travelNoteDao;

    /**
     * 举报标签 列表
     */
    public String tags(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("6");
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
     *  举报进度 列表
     */
    public String report_schedule(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("12");
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
     *  获取所有的举报 列表
     * @param pageBean 查询条件
     * @param apj json
     * @return String
     */
    public String list(ManReportPageBean pageBean, AppJson apj){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String fi = "";
            //条件查询
            if(!ConvertUtil.isEmpty(pageBean.getName())){
                fi += " and (rv.rv_num like  '%"+pageBean.getName()+"%' or rv.rv_name like  '%"+pageBean.getName()+"%') ";
            }
            if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                fi += " and rv.rv_phone like  '%"+pageBean.getPhone()+"%' ";
            }

            if(pageBean.getSex()>=0){
                fi += " and rv.rv_sex = '"+pageBean.getSex()+"' ";
            }
            String rv_ids =null;
            if(!ConvertUtil.isEmpty(fi)){
                List<VisitorBean> visitorBeans = friendDao.getVisitorBeansByCondition(fi);
                if(visitorBeans!=null && visitorBeans.size()>0){
                    for (VisitorBean visitorBean : visitorBeans){
                        rv_ids += "\""+visitorBean.getRv_id()+",\"";
                    }
                }
            }

            String filter = "";
            if(rv_ids!=null){
                filter += "  and rr.rr_visitor in ("+rv_ids+")";
            }

            if(pageBean.getType()>=0){
                filter +="  and rr.rr_type = '"+pageBean.getType()+"' ";
            }
            //举报原因
            if(!ConvertUtil.isEmpty(pageBean.getReasons())){
                filter+= " and exists(select rrt.* " +
                        "               from ry_report_tag rrt " +
                        "               where ifnull(rrt.rrt_del,0)=0 " +
                        "               and rrt.rrt_report = rr.rr_id and rrt.rrt_val='"+pageBean.getReasons()+"' )";
            }
            //处理人
            if(!ConvertUtil.isEmpty(pageBean.getDeal())){
                filter += " and exists(select rrh.* " +
                        "                  from ry_report_handle rrh " +
                        "                  where ifnull(rrh.rrh_del,0)=0 " +
                        "                  and rrh.rrh_report = rr.rr_id and  rrh.rrh_user='"+pageBean.getDeal()+"'  )";
            }

            //处理进度
            if(pageBean.getHandle_schedule()>=0){
                if(pageBean.getHandle_schedule()==0){
                    filter += " and not exists(select rrh.* " +
                            "                  from ry_report_handle rrh " +
                            "                  where ifnull(rrh.rrh_del,0)=0 " +
                            "                  and rrh.rrh_report = rr.rr_id ) ";
                }else{
                    filter += " and exists(select rrh.* " +
                            "                  from ry_report_handle rrh " +
                            "                  where ifnull(rrh.rrh_del,0)=0  " +
                            "                  and rrh.rrh_report = rr.rr_id and  rrh.rrh_status='"+(pageBean.getHandle_schedule()-1)+"' ) ";
                }
            }

            //总数
            int total = dao.getReportCount(filter);
            pageBean.setTotal(total);
            List<ReportBean> list = dao.getReportBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            if(list!=null && list.size()> 0){
                String user_ids = "0";
                StringBuilder obj_ids = new StringBuilder("\'\'");
                StringBuilder types = new StringBuilder();
                for(ReportBean report :list ){
                    if(!ConvertUtil.isEmpty(report.getHandle_user())){
                        user_ids += ",'"+report.getHandle_user()+"' ";
                        obj_ids.append(",\'"+report.getRr_obj()+"\'");
                        types.append(report.getRr_type()+",");
                    }
                }
                //取得用户
                Map<String,RyVisitor> vi_map = new HashMap<>();
                if(types.toString().contains("0")){
                    List<RyVisitor> visitors = friendDao.getVisitorsByKeys(obj_ids.toString());
                    if(visitors!=null && visitors.size()>0){
                        for (RyVisitor visitor : visitors){
                            vi_map.put(visitor.getRv_num(),visitor);
                        }
                    }
                }
                //取得团
                Map<String,RyGroup> rg_map = new HashMap<>();
                if(types.toString().contains("1")){
                    List<RyGroup> groups = groupDao.getGroupNameorNumByids(obj_ids.toString());
                    if(groups!= null && groups.size()>0){
                        for (RyGroup group : groups){
                            rg_map.put(group.getRg_num(),group);
                        }
                    }
                }
                //取得游记
                Map<String,RyTravelNote> note_map = new HashMap<>();
                if(types.toString().contains("2")){
                    List<RyTravelNote> notes = travelNoteDao.getTravelNoteContentByIds(obj_ids.toString());
                    if(notes!=null && notes.size()>0){
                        for (RyTravelNote note : notes){
                            note_map.put(note.getRtn_num(),note);
                        }
                    }
                }
                //取得用户
                Map<String,AhUser> userMap = new HashMap<>();
                if(!"0".equals(user_ids)){
                    List<AhUser> users = userDao.getUsersKeys(user_ids);
                    if(users!=null && users.size()>0){
                        for(AhUser user:users){
                            userMap.put(user.getAu_id(),user);
                        }
                    }
                }
                //给list赋值
                for(ReportBean report :list ){
                    AhUser user = userMap.get(report.getHandle_user());
                    if(user!=null){
                        report.setHandle_name(user.getAu_name());
                    }
                    //举报原因赋值
                    this.setData(report);

                    //被举报的内容显示
                    if("0".equals(report.getRr_type())){//被举报的是人
                        RyVisitor visitor = vi_map.get(report.getRr_obj());
                        if(visitor!=null){
                            if(!ConvertUtil.isEmpty(visitor.getRv_name())){
                                report.setReported_info(visitor.getRv_name());
                            }else {
                                report.setReported_info(visitor.getRv_num());
                            }
                        }
                    }else if("1".equals(report.getRr_type())){//被举报的是团
                        RyGroup group = rg_map.get(report.getRr_obj());
                        if(group!=null){
                            if(!ConvertUtil.isEmpty(group.getRg_name())){
                                report.setReported_info(group.getRg_name());
                            }else {
                                report.setReported_info(group.getRg_num());
                            }
                        }
                    }else {//被举报的是游记
                        RyTravelNote note = note_map.get(report.getRr_obj());
                        if(note!=null){
                            if(!ConvertUtil.isEmpty(note.getRtn_content())){
                                report.setReported_info(note.getRtn_content());
                            }
                        }
                    }
                }
                pageBean.setData(list);
            }
            apj.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }
        return service_code;
    }

    public String del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyReport report = dao.getReportBeanOneKey(id);
            if(report!=null && !ConvertUtil.isEmpty(report.getRr_id())){
                report.setRr_del(1);
                dao.updateReportDel(report);
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
     * 获取单条举报明细
     * @param id 举报id
     * @param apj json
     * @return String
     */
    public String info(String id, AppJson apj){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AhUser user = DataUtil.getCurrent().getUser();
            ReportInfoBean report = dao.getReportOneKey(id);
            AhUser user1 = userDao.getUserOneKey(report.getHandle_user());
            if(user1!=null){
                report.setHandle_name(user.getAu_name());
            }
            String type = report.getRr_type()+"";
            //System.out.println("type===="+type+" ,");
            if("0".equals(type)){
                RyVisitor visitor = friendDao.getVisitorOneKey(report.getRr_obj());
                if(visitor!=null){
                    if(!ConvertUtil.isEmpty(visitor.getRv_name())){
                        report.setReported_info(visitor.getRv_name());
                    }else {
                        report.setReported_info(visitor.getRv_num());
                    }
                }
            }else if("1".equals(type)){
                RyGroup group = groupDao.getGroupOneNum(report.getRr_obj());
                if(group!=null){
                    if(!ConvertUtil.isEmpty(group.getRg_name())){
                        report.setReported_info(group.getRg_name());
                    }else {
                        report.setReported_info(group.getRg_num());
                    }
                }
            }else if("2".equals(type)){
                RyTravelNote note = travelNoteDao.getTravelNoteByNum(report.getRr_obj());
                if(note!=null){
                    if(!ConvertUtil.isEmpty(note.getRtn_content())){
                        report.setReported_info(note.getRtn_content());
                    }
                }
            }
            //举报原因
            this.setData(report);

            if(!ConvertUtil.isEmpty(report.getRr_id())){
                RyReportHandle reportHandle =this.getReportHandleOnly(report,user);
                if(reportHandle!=null){
                    report.setStatus(reportHandle.getRrh_status());
                    report.setContent(reportHandle.getRrh_content());
                }
                apj.setObj(report);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_man_sos_del;
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }
        return service_code;
    }

    public void setData(ReportBean report){
        //获取CommonVal
        List<RyCommonVal> commonVals = commonValDao.getCommonValsByConditions();
        String tags = "";
        for (RyCommonVal val : commonVals){
            if(!ConvertUtil.isEmpty(report.getTags())){
                if(report.getTags().contains(val.getRcv_id()) &&
                        "6".equals(val.getRcv_group())){
                    tags+= val.getRcv_name()+",";
                }
            }
            //处理进度名称和处理结果
            if(val.getRcv_val().equals(report.getHandle_schedule()+"") &&
                    "12".equals(val.getRcv_group())){
                report.setSchedule_name(val.getRcv_name());
            }
            if(val.getRcv_val().equals(report.getHandle_results()+"") &&
                    "13".equals(val.getRcv_group())){
                report.setResults_name(val.getRcv_name());
            }
        }
        if(!ConvertUtil.isEmpty(tags)){
            report.setTags(tags.substring(0,tags.length()-1));
        }

    }


    public RyReportHandle getReportHandleOnly(RyReport report, AhUser user){
        RyReportHandle reportHandle = null;
        if(report!=null && !ConvertUtil.isEmpty(report.getRr_id())){
            Date date = new Date();
            reportHandle = dao.getReportHandleOneKey(report.getRr_id());
            if(reportHandle==null || ConvertUtil.isEmpty(reportHandle.getRrh_id())){
                reportHandle = new RyReportHandle();
                reportHandle.setRrh_report(report.getRr_id());
                String handle_user= "";//默认系统自己解决
                if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                    handle_user = user.getAu_id();
                }
                reportHandle.setRrh_user(handle_user);
                reportHandle.setRrh_status(0);
                reportHandle.setRrh_content("");
                reportHandle.setRrh_val(0);
                reportHandle.setRrh_del(0);
                reportHandle.setCreate_date(date);

                dao.insertReportHandle(reportHandle);
            }
        }
        return reportHandle;
    }

    public String corfirm(ReportHandleForm form, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(form!=null){
            if(!ConvertUtil.isEmpty(form.getId())){
                AhUser user = DataUtil.getCurrent().getUser();
                ReportInfoBean  report = dao.getReportOneKey(form.getId());
                if(report!=null && !ConvertUtil.isEmpty(report.getRr_id())){
                    String access_key = DataUtil.getCurrent().getUser().getAu_id();
                    RyReportHandle reportHandle =this.getReportHandleOnly(report,user);
                    if(reportHandle!=null){
                        if(reportHandle.getRrh_status()<1){
                            System.out.print(form.getVal());
                            reportHandle.setRrh_val(form.getVal());
                            reportHandle.setRrh_status(form.getStatus());
                            reportHandle.setRrh_content(form.getContent());
                            reportHandle.setRrh_user(access_key);

                            //更新 处理结果
                            dao.updateReportHandle(reportHandle);
                            report.setVal(reportHandle.getRrh_val());
                            report.setStatus(reportHandle.getRrh_status());
                            report.setContent(reportHandle.getRrh_content());

                            app.setObj(report);
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
