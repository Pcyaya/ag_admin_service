package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.FreebackDao;
import com.runyee.agdhome.dao.anygo.AgGroupDao;
import com.runyee.agdhome.dao.anygo.AgVisitorDao;
import com.runyee.agdhome.dao.spk_server.SpkDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.AhFreeback;
import com.runyee.agdhome.entity.db.ag_home.AhFreebackDetail;
import com.runyee.agdhome.entity.db.anygo.*;
import com.runyee.agdhome.entity.ex.SpkBean;
import com.runyee.agdhome.entity.ex.SpkSosBean;
import com.runyee.agdhome.entity.ex.SpkSyncBean;
import com.runyee.agdhome.entity.ex.VisitorBean;
import com.runyee.agdhome.entity.form.FreeBackBean;
import com.runyee.agdhome.entity.form.FreeBackDetailBean;
import com.runyee.agdhome.entity.form.GroupInfoBean;
import com.runyee.agdhome.entity.pagebean.ManSpeakerPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class SpeakerService {
    @Autowired
    private SpkDao dao;
    @Autowired
    private AgVisitorDao visitorDao;
    @Autowired
    private AgGroupDao groupDao;
    @Autowired
    private GroupService groupService;

    public String list(ManSpeakerPageBean pageBean, AppJson app){
        String service_code = ServiceCode.SUCCESS;
        String db = DataUtil.getCurrent().getDb();
        if(pageBean!=null){
            String filter = "";
            //音箱编码
            if(!ConvertUtil.isEmpty(pageBean.getSpeak_num())){
                filter +="  and ris.ris_num like '%"+pageBean.getSpeak_num()+"%'  ";
            }
            //sim卡号
            if(!ConvertUtil.isEmpty(pageBean.getCard())){
                filter +="  and ris.ris_card like '%"+pageBean.getCard()+"%'  ";
            }
            //绑定人
            if(!ConvertUtil.isEmpty(pageBean.getVisitor())){
                filter +="  and exists(select * " +
                        "               from  ry_intell_spk_bind risb " +
                        "               where ifnull(risb.risb_del,0)=0 " +
                        "               and  risb.risb_id =(select risb1.risb_id " +
                        "                                       from ry_intell_spk_bind risb1 " +
                        "                                       where ifnull(risb1.risb_del,0)=0 and ifnull(risb1.risb_state,0)=0 and risb1.risb_intell_spk = ris.ris_id  " +
                        "                                       order by  create_date desc limit 1 )" +
                        "               and  risb.risb_guide like  '%"+pageBean.getVisitor()+"%') " ;
            }

            //是否被管理
            if(pageBean.getManage()>=0){
                //未管理
                if(pageBean.getManage() == 0){
                    filter += " and not exists(select *" +
                            "                   from ry_intell_spk_man rism" +
                            "                   where ifnull(rism.rism_del,0)=0 and ifnull(rism.rism_state,0)=0 "+
                            "                   and rism.rism_intell_spk = ris.ris_id )";
                }else {//已管理
                    filter +="  and exists(select *" +
                            "                   from ry_intell_spk_man rism" +
                            "                   where ifnull(rism.rism_del,0)=0 and ifnull(rism.rism_state,0)=0 and rism.rism_db='"+db+"'"+
                            "                   and rism.rism_intell_spk = ris.ris_id)";
                }

            }

            //管理者
            if(!ConvertUtil.isEmpty(pageBean.getManager())){
                filter += " and exists(select * " +
                        "                   from ry_intell_spk_man rism " +
                        "                   where ifnull(rism.rism_del,0)=0 and ifnull(rism.rism_state,0)=0" +
                        "                   and rism.rism_guide like '%"+pageBean.getManager()+"%' " +
                        "                   and rism.rism_intell_spk = ris.ris_id )";
                /*//查出这个人的id
                String vi_filter = " and rv.rv_name like '%"+pageBean.getManager()+"%'";
                List<VisitorBean> visitors = visitorDao.getVisitorBeansByCondition(vi_filter);
                StringBuffer buffer = new StringBuffer("\'\'");
                if(visitors!=null&&visitors.size()>0){
                    for (VisitorBean visitorBean : visitors){
                        buffer.append(",\'"+visitorBean+"\'");
                    }
                }
                if(!ConvertUtil.isEmpty(buffer.toString())){
                    filter += " and exists(select * " +
                            "                   from ry_intell_spk_man rism " +
                            "                   where ifnull(rism.rism_del,0)=0 and ifnull(rism.rism_state,0)=0" +
                            "                   and rism.rism_guide like '%"+buffer.toString()+"%')";
                }*/
            }
            if(pageBean.getBind()>=0){

                if(pageBean.getBind() == 0){
                    filter += " and not exists(select ris.* " +
                            "                  from ry_intell_spk_bind risb " +
                            "                  where ifnull(risb.risb_del,0)=0 " +
                            "                  and risb.risb_intell_spk = ris.ris_id and risb.risb_state=0 ) ";
                }else {//已绑定
                    filter += " and exists(select ris.* " +
                            "                  from ry_intell_spk_bind risb " +
                            "                  where ifnull(risb.risb_del,0)=0  " +
                            "                  and risb.risb_intell_spk = ris.ris_id and  risb.risb_state=0 and risb.risb_db='"+db+"' ) ";
                }
            }/*else{
                filter += " and (not exists(select ris.* " +
                        "                  from ry_intell_spk_bind risb " +
                        "                  where ifnull(risb.risb_del,0)=0 " +
                        "                  and risb.risb_intell_spk = ris.ris_id and risb.risb_state=0 )" +
                        "       or exists(select ris.* " +
                        "                  from ry_intell_spk_bind risb " +
                        "                  where ifnull(risb.risb_del,0)=0  " +
                        "                  and risb.risb_intell_spk = ris.ris_id and  risb.risb_state=0 and risb.risb_db='"+db+"' )" +
                        "   ) ";
            }*/
            //艾侗游网络名称
            if (!ConvertUtil.isEmpty(pageBean.getWifi())) {
                filter += " and ris.ris_wifi_name like'%"+pageBean.getWifi()+"%'";
            }

            //总数
            int total = dao.getSpkCount(filter);
            pageBean.setTotal(total);
            List<SpkBean> list = dao.getSpkBeanList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());
            if(list!=null&&list.size()>0){
                String ids = "0";
                String nums = "0";
                for(SpkBean spk :list ){
                    if(!ConvertUtil.isEmpty(spk.getVisitor())){
                        ids += ",'"+spk.getVisitor()+"' ";
                    }
                    nums += ",'"+spk.getRis_num()+"'";
                    if(!ConvertUtil.isEmpty(spk.getSpk_manage())){
                        ids += ",'"+spk.getSpk_manage()+"'";
                    }
                }
                //取得音箱版本号
                Map<String, RySpkSync> spkSyncMap = new HashMap<>();
                if(!"0".equals(nums)) {
                    List<RySpkSync> spkSyncs = dao.getIntelligentSpeakerVersion(nums);
                    if (spkSyncs != null && spkSyncs.size() > 0) {
                        for (RySpkSync spkSync : spkSyncs) {
                            spkSyncMap.put(spkSync.getRss_spk_num(), spkSync);
                        }
                    }
                }
                //取得音箱绑定人和音箱管理人
                Map<String,RyVisitor> visitorMap = new HashMap<>();
                if(!"0".equals(ids)){
                    List<RyVisitor> visitors = visitorDao.getVisitorsKeys(ids);
                    if(visitors!=null && visitors.size()>0){
                        for(RyVisitor visitor:visitors){
                            visitorMap.put(visitor.getRv_id(),visitor);
                        }
                    }
                }

                for(SpkBean spk :list ){
                    if(!ConvertUtil.isEmpty(spk.getVisitor())){
                        RyVisitor  visitor = visitorMap.get(spk.getVisitor());
                        if(visitor!=null){
                            spk.setVisitor(visitor.getRv_id());
                            spk.setName(visitor.getRv_name());
                            spk.setAge(visitor.getRv_age());
                            spk.setPhone(visitor.getRv_phone());
                            spk.setSex(visitor.getRv_sex());
                        }
                    }
                    //音箱管理者，默认没有被管理
                    if(!ConvertUtil.isEmpty(spk.getSpk_manage())){
                        RyVisitor visitor = visitorMap.get(spk.getSpk_manage());
                        if(visitor!=null){
                            spk.setSpk_manager_name(visitor.getRv_name());
                        }
                    }
                    //音箱版本
                    if(!ConvertUtil.isEmpty(spk.getRis_num())){
                        RySpkSync spkSync = spkSyncMap.get(spk.getRis_num());
                        if(spkSync!=null){
                            spk.setSpk_version(spkSync.getRss_version());
                        }
                    }
                    //绑定时间格式转换
                    if (!ConvertUtil.isEmpty(spk.getBinddate())){
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try{
                            Date date = format.parse(spk.getBinddate());
                            spk.setBind_date(date);
                        }catch (ParseException e){
                            e.printStackTrace();
                            service_code = ServiceCode.EXCEPTION;
                        }
                    }
                }
            }
            pageBean.setData(list);
            app.setObj(pageBean);
        }
        return service_code;
    }

    public String active(String  id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyIntelligentSpeaker speaker = dao.getSpkOneKey(id);
            if(speaker!=null&&!ConvertUtil.isEmpty(speaker.getRis_id())){
                speaker.setRis_active(1);
                dao.updateSpkActive(speaker);
                service_code = ServiceCode.SUCCESS;
            }else {
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String server(String  id,String server, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyIntelligentSpeaker speaker = dao.getSpkOneKey(id);
            if(speaker!=null&&!ConvertUtil.isEmpty(speaker.getRis_id())){
                int test = ConvertUtil.convertStrToInt(server);
                speaker.setRis_test(test);
                dao.updateSpkEnv(speaker);
                service_code = ServiceCode.SUCCESS;
            }else {
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String unbind(String  id,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            //解绑设备
            RyIntellSpkBind bind = dao.getIntellSpkBindOnSpk(id);
            if(bind != null && !ConvertUtil.isEmpty(bind.getRisb_intell_spk())){
                //查看是否有旅行团
                List<RyGroup> groups = groupDao.getGroupSpeakerKey(id);
                if(groups!=null&& groups.size()>0){
                    int rg_type = 0;
                    RyGroup group  = null;

                    for(RyGroup cg : groups){
                        if(cg!=null){
                            //cg.setSpeaker_num(bean.getSpeaker_num());
                            rg_type = cg.getRg_type();
                            if(rg_type==1){
                                group = cg;
                                break;
                            }
                        }
                    }
                    if(rg_type==0){
                        //清除团设备
                        groupDao.updateGroupTravelSpk(id);
                    }else if(group!=null ){
                        //强制结束旅行(暂定)
                        service_code = ServiceCode.fail_group_traveling;
                        List<String> msgs = new ArrayList<>();
                        GroupInfoBean groupInfo = groupService.groupInfo(group);
                        msgs.add(groupInfo.getName());
                        DataUtil.getCurrent().setMsgs(msgs);
                    }else{
                        service_code = ServiceCode.fail_param_error;
                    }
                }

                if(ServiceCode.ERRO.equals(service_code)){
                    //清除绑定设备
                    dao.updateIntellSpkBindsSpk(id);
                    service_code = ServiceCode.SUCCESS;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String bindcode(String  id,String code, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            RyIntelligentSpeaker speaker = dao.getSpkOneKey(id);
            if(speaker!=null&&!ConvertUtil.isEmpty(speaker.getRis_id())){

                speaker.setRis_bind(code);
                dao.updateSpkBindCode(speaker);
                service_code = ServiceCode.SUCCESS;
            }else {
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    /**
     * 获取设备路径
     * @param bean  bean
     * @param json  json
     * @return  String
     */
    public String show_speaker_path(SpkSyncBean bean,AppJson json){
        String service_code = ServiceCode.SUCCESS;
        if(bean != null && !ConvertUtil.isEmpty(bean.getSpk_num())){
            Date start = Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

            if(!"0".equals(bean.getStartDate()+"")  && !"0".equals(bean.getEndDate()+"")){
                start = DateUtils.getDate(bean.getStartDate());
                end = DateUtils.getDate(bean.getEndDate());
            }else {
                service_code = ServiceCode.fail_param_error;
            }

            String filter = " and rss.create_date >= '"+DateUtils.gettimestamp(start) +"' and rss.create_date <='"+DateUtils.getTimestamp(end)+"'";
            List<SpkSyncBean> list = dao.getRySpkSyncpositionList(bean.getSpk_num(),filter);
            json.setObj(list);
        }
        return service_code;
    }
}
