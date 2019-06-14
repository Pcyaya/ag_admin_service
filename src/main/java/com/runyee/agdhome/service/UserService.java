package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.DepartDao;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.autopage.UserAutoPage;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.ag_home.AhUserDepart;
import com.runyee.agdhome.entity.db.ag_home.AhUserToken;
import com.runyee.agdhome.entity.ex.UserBean;
import com.runyee.agdhome.entity.form.UserForm;
import com.runyee.agdhome.entity.pagebean.ManAutoUserPageBean;
import com.runyee.agdhome.entity.pagebean.ManUserPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class UserService {
    @Autowired
    private UserDao dao;
    @Autowired
    private DepartDao departDao;

    /**
     * 登陆验证
     * @param source 登陆来源0.android,1.ios,2.wx,3web
     */
    public String login(String phone,String pwd,int source, AppJson app) {
        String service_code = ServiceCode.ERRO;
        Date date = DateUtils.getDate();
        if(!ConvertUtil.isEmpty(phone)&&!ConvertUtil.isEmpty(pwd)){
            AhUser user = dao.getUserOnePhone(phone);
            if(user!=null&&!ConvertUtil.isEmpty(user.getAu_id())){
                if(pwd.equals(user.getAu_pwd())){
                    //失效之前的token
                    dao.updateUserTokenInvalid(user.getAu_id(),source);
                    //登陆成功 生成token
                    String token = UUID.randomUUID().toString();
                    AhUserToken userToken = new AhUserToken();
                    userToken.setAut_user(user.getAu_id());
                    userToken.setAut_token(token);
                    userToken.setAut_expire(DateUtils.getDate((date.getTime()+(ServiceCode.valid_hour*DateUtils.HOUR_IN_MILLIS))));
                    userToken.setAut_source(source);
                    userToken.setAut_invalid(0);
                    userToken.setCreate_date(date);
                    dao.insertUserToken(userToken);
                    Map<String,String>  access_info= new HashMap<>();
                    access_info.put("access_key",user.getAu_id());
                    access_info.put("access_name",user.getAu_name());
                    //access_info.put("access_icon",user.getIcon);
                    access_info.put("access_token",token);
                    access_info.put("access_level",user.getAu_level()+"");
                    String android_url = AppUrl.android_test_url;
                    String ios_url = AppUrl.ios_test_url;
                    if(DataUtil.getCurrent().getPort()==80){
                        android_url = AppUrl.android_url;
                        ios_url = AppUrl.ios_url;
                    }
                    access_info.put("android_url",android_url );
                    access_info.put("ios_url", ios_url);
                    app.setObj(access_info);
                    service_code = ServiceCode.SUCCESS;
                }else{
                    service_code = ServiceCode.fail_man_login_pwderror;
                }
            }else{
                service_code = ServiceCode.fail_man_login_unfind;
            }
        }else{
            service_code = ServiceCode.fail_man_login_empty;
            List<String> msgs = new ArrayList<>();
            String msg = "手机号";
            if(ConvertUtil.isEmpty(pwd)){
                msg = "密码";
            }
            msgs.add(msg);
            DataUtil.getCurrent().setMsgs(msgs);

        }
        return service_code;
    }

    //token 检测
    public String authVisitorToken(String access_token,String uri){
        String service_code = ServiceCode.SUCCESS;
        Date date = new Date();
        if(!ConvertUtil.isEmpty(access_token)){
            AhUserToken token = this.getUserTokenOne(access_token);//visitorDao.getVisitorTokenOne(access_token);getUserTokenOne
            if(token!=null && !ConvertUtil.isEmpty(token.getAut_id())){

                if(ServiceCode.SUCCESS.equals(service_code)){
                    AhUser user = dao.getUserOne(token.getAut_user());
                    if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
                        if(token.getAut_invalid()<=0){
                            if(token.getAut_expire()!= null && token.getAut_expire().getTime() >= date.getTime()){
                                //service_code = this.sync_visitor_interface(token,uri);
                                if(ServiceCode.SUCCESS.equals(service_code)){
                                    //更新 过期时间
                                    token.setAut_expire(DateUtils.getDate((date.getTime()+(ServiceCode.valid_hour*DateUtils.HOUR_IN_MILLIS))));
                                    token.setAut_acs_uri(uri);
                                    token.setAut_acs_date(date);

                                    dao.updateUserTokenExpire(token);

                                    DataUtil.getCurrent().setAccess_token(access_token);
                                    DataUtil.getCurrent().setUser(user);
                                    //游客端
                                    //DataUtil.getCurrent().setAccess_flg(0);
                                }
                            }else{
                                service_code = APPCode.ACCESS_TOKEN_EXPIRE;
                            }
                        }else{
                            service_code = APPCode.ACCESS_TOKEN_INVALID;
                        }
                    }else{
                        service_code = APPCode.ACCESS_TOKEN_LOGIN;
                    }
                }

            }else{
                service_code = APPCode.ACCESS_TOKEN_ERROR;
            }
        }else{
            service_code = APPCode.ACCESS_TOKEN_ERROR;
        }
        return service_code;
    }

    @Cacheable(value = "token",key="#access_token") //@Cacheable缓存key为person的id数据到缓存people中。
    public AhUserToken getUserTokenOne(String access_token){
        AhUserToken token = dao.getUserTokenOne(access_token);
        return token;
    }

    /**
     * 修改密码
     */
    public String pwd(String old_pwd,String new_pwd,String repeat_pwd, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(old_pwd)&&!ConvertUtil.isEmpty(new_pwd)&&!ConvertUtil.isEmpty(repeat_pwd)){
            if(!old_pwd.equals(new_pwd)){
                if(new_pwd.equals(repeat_pwd)){
                    AhUser user = DataUtil.getCurrent().getUser();
                    if(user!=null&&!ConvertUtil.isEmpty(user.getAu_id())){
                        if(old_pwd.equals(user.getAu_pwd())){
                            //更新pwd
                            user.setAu_pwd(new_pwd);
                            dao.updateUserPwd(user);
                            service_code = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_man_pwd_pwderror;
                        }
                    }else{
                        service_code = ServiceCode.fail_man_pwd_unfind;
                    }
                }else{
                    service_code = ServiceCode.fail_man_pwd_unlikeliness;
                }
            }else{
                service_code = ServiceCode.fail_man_pwd_equally;
            }

        }else{
            service_code = ServiceCode.fail_man_pwd_empty;
            List<String> msgs = new ArrayList<>();
            String msg = "旧密码";
            if(ConvertUtil.isEmpty(new_pwd)){
                msg = "新密码";
            }
            if(ConvertUtil.isEmpty(repeat_pwd)){
                msg = "确认密码";
            }
            msgs.add(msg);
            DataUtil.getCurrent().setMsgs(msgs);

        }
        return service_code;
    }

    /**
     *  用户基本信息
     */
    public String base_info(AppJson app) {
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            Map<String,String> base_info = new HashMap<>();
            base_info.put("access_key",user.getAu_id());
            base_info.put("name",user.getAu_name());
            base_info.put("email",user.getAu_email());
            base_info.put("descript",user.getAu_descript());
            app.setObj(base_info);
        }else{
            service_code = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    /**
     * 用户 autocomplete 列表
     */
    public String auto(ManAutoUserPageBean pageBean,AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            if(!ConvertUtil.isEmpty(pageBean.getSearch())){
                filter +="   and au.au_name like '%"+pageBean.getSearch()+"%' " ;
            }
            List<AhUser> usres = dao.getUsers(filter,pageBean.getCurrPage(),pageBean.getRows());
            if(usres!=null && usres.size()>0){
                List<UserAutoPage> autoUsers  =  new ArrayList<>();
                for(AhUser user:usres){
                    UserAutoPage autoUser = new UserAutoPage();
                    autoUser.setId(user.getAu_id());
                    autoUser.setName(user.getAu_name());
                    autoUsers.add(autoUser);
                }
                pageBean.setData(autoUsers);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code = APPCode.ERRO;
        }
        return service_code;
    }


    /**
     * 用户列表
     */
    public String list(ManUserPageBean pageBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
                String filter = "";

                if(!ConvertUtil.isEmpty(pageBean.getDepart())){
                    //部门
                    filter +="   and  exists(select * " +
                            "                   from  ah_user_depart aud " +
                            "                   where ifnull(aud.del,0)=0 and aud.aud_user = au.au_id " +
                            "                   and exists(select * " +
                            "                               from ah_depart ad " +
                            "                               where ifnull(ad.del,0)=0 and ad.ad_id = aud.aud_depart " +
                            "                               and ad.ad_name like '%"+pageBean.getDepart()+"%' ) )" ;
                }

                if(!ConvertUtil.isEmpty(pageBean.getNo())){
                    filter += " and au.au_no like '%"+pageBean.getNo()+"%' ";
                }
                if(!ConvertUtil.isEmpty(pageBean.getName())){
                    filter += " and au.au_name like '%"+pageBean.getName()+"%' ";
                }
                if(!ConvertUtil.isEmpty(pageBean.getPhone())){
                    filter += " and au.au_phone like '%"+pageBean.getPhone()+"%' ";
                }
                if(!ConvertUtil.isEmpty(pageBean.getPosition())){
                    filter += " and au.au_position like '%"+pageBean.getPosition()+"%' ";
                }
                int total = dao.getUserBeansCount(filter);
                pageBean.setTotal(total);
                List<UserBean> usres = dao.getUserBeans(filter,pageBean.getCurrPage(),pageBean.getRows());
                if(usres!=null && usres.size()>0){
                    pageBean.setData(usres);
                }
                app.setObj(pageBean);
                service_code  = ServiceCode.SUCCESS;

        }else{
            service_code = APPCode.ERRO;
        }
        return service_code;
    }

    public String issue(UserForm formBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(formBean!=null){
                service_code = this.valid_user(formBean);
                if(ServiceCode.ERRO.equals(service_code)){
                    List<String> departs =null;
                    if(!ConvertUtil.isEmpty(formBean.getDeparts())){
                        JSONArray jArray = JSONArray.fromObject(formBean.getDeparts());
                        if(jArray!=null){
                            departs = JSONArray.toList(jArray,String.class);
                        }
                    }

                    AhUser ahUser = null;
                    if(!ConvertUtil.isEmpty(formBean.getId())){
                        //更新
                        ahUser = dao.getUserOneKey(formBean.getId());
                        if(ahUser!=null && !ConvertUtil.isEmpty(ahUser.getAu_id())){
                            ahUser.setAu_no(formBean.getNo());
                            ahUser.setAu_name(formBean.getName());
                            ahUser.setAu_phone(formBean.getPhone());
                            if(!ConvertUtil.isEmpty(formBean.getPwd())){
                                ahUser.setAu_pwd(formBean.getPwd());
                            }
                            ahUser.setAu_position(formBean.getPosition());
                            ahUser.setAu_email(formBean.getEmail());
                            ahUser.setAu_descript(formBean.getDescript());
                            ahUser.setAu_sex(formBean.getSex());
                            dao.updateUserInfo(ahUser);
                            service_code = ServiceCode.SUCCESS;
                        }else{
                            service_code = ServiceCode.fail_param_error;
                        }

                    }else{
                        //新增
                        ahUser =new AhUser();
                        ahUser.setAu_no(formBean.getNo());
                        ahUser.setAu_name(formBean.getName());
                        ahUser.setAu_phone(formBean.getPhone());
                        ahUser.setAu_pwd(formBean.getPwd());
                        ahUser.setAu_position(formBean.getPosition());
                        ahUser.setAu_email(formBean.getEmail());
                        ahUser.setAu_descript(formBean.getDescript());
                        ahUser.setAu_sex(formBean.getSex());
                        ahUser.setDel(0);
                        ahUser.setCreate_date(DateUtils.getDate());
                        dao.insertUser(ahUser);
                        service_code = ServiceCode.SUCCESS;
                    }
                    if(ahUser!=null&&!ConvertUtil.isEmpty(ahUser.getAu_id())){
                        if(departs!=null&&departs.size()>0){
                            //清除管理人员部门
                            String filter = "";
                            String ids = "''";// StringUtils.join(departs.toArray(), ",");
                            for(String depart:departs){
                                ids+=",'"+depart+"'";
                            }
                            if(!ConvertUtil.isEmpty(ids)){
                                filter += " and aud_depart not in("+ids+") ";
                            }
                            departDao.updateUserDepartDelAll(ahUser.getAu_id(),filter);
                            Map<String,AhUserDepart>  d_map = new HashMap<>();
                            List<AhUserDepart> userDeparts = departDao.getUserDeparts(ahUser.getAu_id());
                            if(userDeparts!=null&&userDeparts.size()>0){
                                for(AhUserDepart userDepart:userDeparts){
                                    d_map.put(userDepart.getAud_depart(),userDepart);
                                }
                            }
                            for(String depart:departs){
                                AhUserDepart userDepart = d_map.get(depart);
                                if(userDepart!=null&&!ConvertUtil.isEmpty(userDepart.getAud_id())){
                                    //更新
                                    if(userDepart.getDel()==1){
                                        userDepart.setDel(0);
                                        departDao.updateUserDepartDel(userDepart);
                                    }
                                }else{
                                    //新增
                                    userDepart = new AhUserDepart();
                                    userDepart.setAud_user(ahUser.getAu_id());
                                    userDepart.setAud_depart(depart);
                                    userDepart.setDel(0);
                                    userDepart.setCreate_date(DateUtils.getDate());

                                    departDao.insertUserDepart(userDepart);
                                }
                            }
                        }
                    }
                }
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    public String valid_user(UserForm formBean){
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(formBean.getName())){
            String filter = "";
            if(!ConvertUtil.isEmpty(formBean.getId())){
                filter += " and  au.au_id <> '"+formBean.getId()+"' ";
            }
            AhUser ahUser = dao.getUserOneName(formBean.getName(),filter);
            if(ahUser!=null&&!ConvertUtil.isEmpty(ahUser.getAu_id())){
                service_code  = ServiceCode.fail_man_user_name_repeat;
            }
        }else{
            service_code  = ServiceCode.fail_man_user_name_empty;
        }
        return service_code;
    }


    public String del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(!ConvertUtil.isEmpty(id)){
                AhUser userPer = dao.getUserOneKey(id);
                if(userPer!=null && !ConvertUtil.isEmpty(userPer.getAu_id())){
                    if(userPer.getAu_level() == 999){
                        service_code = ServiceCode.fail_user_max_noexists;
                    }else{
                        userPer.setDel(1);
                        dao.updateDepartDel(userPer);
                        service_code = ServiceCode.SUCCESS;
                    }
                }else{
                    service_code = ServiceCode.fail_user_noexists;
                }
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }
}
