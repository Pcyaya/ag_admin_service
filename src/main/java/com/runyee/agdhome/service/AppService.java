package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.anygo.*;
import com.runyee.agdhome.dao.spk_server.SpkDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.CommonValPage;
import com.runyee.agdhome.entity.db.ag_home.AhDepart;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.ag_home.AhUserDepart;
import com.runyee.agdhome.entity.db.anygo.*;
import com.runyee.agdhome.entity.ex.AppFreebackBean;
import com.runyee.agdhome.entity.ex.SpkSosBean;
import com.runyee.agdhome.entity.ex.SpkSosInfoBean;
import com.runyee.agdhome.entity.form.AppVersionForm;
import com.runyee.agdhome.entity.page.*;
import com.runyee.agdhome.entity.pagebean.ManAppFreeBackPageBean;
import com.runyee.agdhome.entity.pagebean.ManAppVersionPageBean;
import com.runyee.agdhome.util.AgeUtil;
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
public class AppService {
    @Autowired
    private AgAppVersionDao dao;
    @Autowired
    private AgVisitorDao visitorDao;
    @Autowired
    private AgGroupDao groupDao;
    @Autowired
    private SpkDao spkDao;
    @Autowired
    private AgSpkSosDao sosDao;
    @Autowired
    private AgCommonValDao commonValDao;

    /**
     * App 下载url
     * */
    public String downloads(AppJson app){
        String service_code = ServiceCode.ERRO;

        List<AppVersion> versions = new ArrayList<>();//dao.getAppVersionNews();
        //android
        AppVersion android = dao.getAppVersionOnePlat(0);
        if(android!=null&&!ConvertUtil.isEmpty(android.getId())){
            versions.add(android);
        }
        //ios
        AppVersion ios = dao.getAppVersionOnePlat(1);
        if(ios!=null&&!ConvertUtil.isEmpty(ios.getId())){
            versions.add(ios);
        }
        if(versions!=null && versions.size()>0){
            service_code = ServiceCode.success_app_download_url;
            app.setObj(versions);
        }
        return service_code;
    }

    public String card(String num,String tag,AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(num) && !ConvertUtil.isEmpty(tag)){
            CardPage card = null;
            service_code = ServiceCode.SUCCESS;
            //tag  1-团 2-侗友 3-设备 4-登陆二维码
            switch (tag){
                case "1":
                    //团
                    RyGroup group = groupDao.getGroupOneNum(num);
                    if(group!=null){
                        GroupCardPage group_card = new GroupCardPage();
                        group_card.setId(group.getRg_id());
                        group_card.setNum(group.getRg_num());
                        group_card.setName(group.getRg_name());
                        if(ConvertUtil.isEmpty(group_card.getName())){
                            group_card.setName(group_card.getNum());
                        }

                        if(!ConvertUtil.isEmpty(group.getRg_icon())){
                            String icon = AppUrl.icon_img_url+group.getRg_icon();
                            group_card.setIcon(icon);
                        }
                        group_card.setType(group.getRg_type());
                        card = group_card;
                    }
                    break;
                case "2":
                    //侗友
                    RyVisitor visitor = visitorDao.getVisitorOneNum(num);
                    if(visitor!=null){
                        VisitorCardPage visitor_card = new VisitorCardPage();
                        visitor_card.setId(visitor.getRv_id());
                        visitor_card.setNum(visitor.getRv_num());
                        visitor_card.setName(visitor.getRv_name());
                        if(ConvertUtil.isEmpty(visitor_card.getName())){
                            visitor_card.setName(visitor_card.getNum());
                        }
                        if(!ConvertUtil.isEmpty(visitor.getRv_icon())){
                            String icon = AppUrl.icon_img_url+visitor.getRv_icon();
                            visitor_card.setIcon(icon);
                        }
                        try {
                            int age = AgeUtil.getAge(visitor.getRv_age());
                            visitor_card.setAge(age);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        visitor_card.setSex(visitor.getRv_sex());

                        //手机号
                        String tem_phone = "****";
                        String phone = visitor.getRv_phone();
                        if(!ConvertUtil.isEmpty(phone)&&phone.length()>7){
                            tem_phone = phone.substring(0,3)+tem_phone+ phone.substring(7,phone.length());
                        }
                        visitor_card.setPhone(tem_phone);
                        card = visitor_card;
                    }
                    break;
                case "3":
                    //设备
                    RyIntellSpkBind bind = spkDao.getIntellSpkBindOneNum(num);
                    if(bind!=null&&!ConvertUtil.isEmpty(bind.getRisb_id())){
                        visitor = visitorDao.getVisitorOneKey(bind.getRisb_guide());
                        if(visitor!=null){
                            VisitorCardPage visitor_card = new VisitorCardPage();
                            visitor_card.setId(visitor.getRv_id());
                            visitor_card.setNum(visitor.getRv_num());
                            visitor_card.setName(visitor.getRv_name());
                            if(ConvertUtil.isEmpty(visitor_card.getName())){
                                visitor_card.setName(visitor_card.getNum());
                            }
                            if(!ConvertUtil.isEmpty(visitor.getRv_icon())){
                                String icon = AppUrl.icon_img_url+visitor.getRv_icon();
                                visitor_card.setIcon(icon);
                            }
                            try {
                                int age = AgeUtil.getAge(visitor.getRv_age());
                                visitor_card.setAge(age);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            visitor_card.setSex(visitor.getRv_sex());

                            //手机号
                            String tem_phone = "****";
                            String phone = visitor.getRv_phone();
                            if(!ConvertUtil.isEmpty(phone)&&phone.length()>7){
                                tem_phone = phone.substring(0,3)+tem_phone+ phone.substring(7,phone.length());
                            }
                            visitor_card.setPhone(tem_phone);
                            card = visitor_card;
                        }
                    }
                    break;
                case "4":
                    service_code = ServiceCode.fail_card_type;
                    break;
                default:
                    service_code = ServiceCode.fail_card_type;
            }

            if(card!=null){
                int port = DataUtil.getCurrent().getPort();
                String download =AppUrl.domain+":"+port+AppUrl.download_url;
                card.setDownload(download);
            }
            app.setObj(card);

        }
        return service_code;
    }

    /**
     * 注册app接口
     * @param  tag 1-团 2-侗友 3-设备 4-登陆二维码
     * @param source 0.android,1.ios 2.wx 3.web
     * */
    public String register(String num,String tag,String phone,int source, AppJson app){
        String service_code =  ServiceCode.SUCCESS;
        Date date = new Date();
        RegisterPage register = new RegisterPage();
        RyVisitor access = visitorDao.getVisitorOnePhoneDel(phone);
        if(access==null || ConvertUtil.isEmpty(access.getRv_id())){
            if(!ConvertUtil.isEmpty(num) && !ConvertUtil.isEmpty(tag)){
                //tag  1-团 2-侗友 3-设备 4-登陆二维码
                RyVisitor visitor = null;
                switch (tag){
                    case "1":
                        service_code = ServiceCode.fail_card_type;
                        break;
                    case "2":
                        //侗友
                        visitor = visitorDao.getVisitorOneNum(num);
                        break;
                    case "3":
                        //设备
                        RyIntellSpkBind bind = spkDao.getIntellSpkBindOneNum(num);
                        if(bind!=null&&!ConvertUtil.isEmpty(bind.getRisb_id())){
                            visitor = visitorDao.getVisitorOneKey(bind.getRisb_guide());
                        }
                        break;
                    case "4":
                        service_code = ServiceCode.fail_card_type;
                        break;
                    default:
                        service_code = ServiceCode.fail_card_type;
                }
                if(ServiceCode.SUCCESS.equals(service_code)){
                    access = new RyVisitor();
                    access.setRv_phone(phone);
                    access.setRv_name("");
                    access.setRv_passwd("");

                    if(visitor!=null&&!ConvertUtil.isEmpty(visitor.getRv_id())){
                        //邀请人
                        access.setRv_invite(visitor.getRv_num());
                    }
                    access.setRv_del(0);
                    visitor.setCreate_by(0);
                    visitor.setUpdate_by(0);

                    access.setCreate_date(date);
                    visitorDao.insertVisitor(access);
                }
            }
        }else{
            //已注册
            register.setRegister_flg(1);
        }
        if(ServiceCode.SUCCESS.equals(service_code)){

            if(register!=null){
                register.setDownload(AppUrl.download_url);
            }
            //下载地址
            List<AppVersion> versions = dao.getAppVersionNews();
            if(versions!=null && versions.size()>0){
                for(AppVersion appVersion :versions){
                    if(appVersion.getFlat_form()==source){
                        register.setApp_url(appVersion.getApk_file_url());
                    }
                }
            }
            app.setObj(register);
        }
        return service_code;
    }

    public String sosinfo(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            SpkSosBean sos = sosDao.getSpkSosBeanOneKey(id);
            if(sos!=null && !ConvertUtil.isEmpty(sos.getRss_id())){
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

    //应用平台
    public String plats(AppJson app) {
        String service_code = ServiceCode.ERRO;
        List<RyCommonVal> commons = commonValDao.getCommons("16");
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

    //app 版本 列表
    public String list(ManAppVersionPageBean pageBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getVersion())){
                filter +="  and appv.new_version like '%"+pageBean.getVersion()+"%' " ;

            }
            if(pageBean.getPlat()>=0){
                filter +="  and appv.flat_form = '"+pageBean.getPlat()+"' ";
            }

            if(pageBean.getConstraint_flg()>=0){
                filter +="  and appv.constraint_flg = '"+pageBean.getConstraint_flg()+"' ";
            }

            if(!ConvertUtil.isEmpty(pageBean.getDate())){
                filter +="  and date_format(appv.create_date,'%Y-%m-%d') = '"+pageBean.getDate()+"' ";
            }


            //总数
            int total = dao.getAppVersionCount(filter);
            pageBean.setTotal(total);
            pageBean.setField(" appv.new_version ");
            //order by create_date desc
            List<AppVersion> list = dao.getAppVersionList(filter,pageBean.getOrder(),pageBean.getCurrPage(),pageBean.getRows());

            pageBean.setData(list);
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }
        return service_code;
    }

    public String del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            AppVersion appVersion  = dao.getAppVersionOneKey(id);
            if(appVersion!=null && !ConvertUtil.isEmpty(appVersion.getId())){
                appVersion.setApp_type(1);
                dao.updateAppVersionDel(appVersion);
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
     *  app版本 新增
     */
    public String issue(AppVersionForm formBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(formBean!=null){
            service_code = this.valid_form(formBean);
            if(ServiceCode.SUCCESS.equals(service_code)){
                if(!ConvertUtil.isEmpty(formBean.getId())){
                    //修改
                    AppVersion version = dao.getAppVersionOneKey(formBean.getId());
                    version.setNew_version(formBean.getVersion());
                    version.setApk_file_url(formBean.getUrl());
                    version.setUpdate_log(formBean.getUpdate_log());
                    version.setTarget_size(formBean.getSize());
                    version.setConstraint_flg(formBean.getConstraint_flg());
                    version.setFlat_form(formBean.getFlat_form());
                    dao.updateAppVersion(version);
                    service_code = ServiceCode.SUCCESS;
                }else{
                    //新增
                    AppVersion version = new AppVersion();
                    version.setNew_version(formBean.getVersion());
                    version.setApk_file_url(formBean.getUrl());
                    version.setUpdate_log(formBean.getUpdate_log());
                    version.setTarget_size(formBean.getSize());
                    version.setNew_md5("");
                    version.setConstraint_flg(formBean.getConstraint_flg());
                    version.setFlat_form(formBean.getFlat_form());
                    version.setApp_type(0);
                    version.setCreate_date(DateUtils.getDate());
                    dao.insertAppVersion(version);
                    service_code = ServiceCode.SUCCESS;
                }
            }
        }else{
            service_code = ServiceCode.fail_param_error;
        }

        return service_code;
    }

    public String valid_form(AppVersionForm formBean){
        String service_code = ServiceCode.ERRO;
        List<String> msgs = new ArrayList<>();
        String msg = "app版本号";
        if(!ConvertUtil.isEmpty(formBean.getVersion())){
            if(!ConvertUtil.isEmpty(formBean.getUrl())){
                if(!ConvertUtil.isEmpty(formBean.getSize())){
                    service_code = ServiceCode.SUCCESS;
                }else{
                    msg = "app大小";
                    service_code = ServiceCode.fail_appversion_empty;
                }
            }else{
                msg = "app下载地址";
                service_code = ServiceCode.fail_appversion_empty;
            }
        }else{
            service_code = ServiceCode.fail_appversion_empty;
        }
        if(ServiceCode.fail_appversion_empty.equals(service_code)){
            msgs.add(msg);
            DataUtil.getCurrent().setMsgs(msgs);
        }
        return service_code;
    }
}
