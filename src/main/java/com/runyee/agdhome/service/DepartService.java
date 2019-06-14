package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.DepartDao;
import com.runyee.agdhome.dao.ag_home.UserDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.AhDepart;
import com.runyee.agdhome.entity.db.ag_home.AhDepartRight;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.ag_home.AhUserDepart;
import com.runyee.agdhome.entity.ex.DepartParentBean;
import com.runyee.agdhome.entity.ex.RightParentBean;
import com.runyee.agdhome.entity.ex.UserBean;
import com.runyee.agdhome.entity.form.DepartForm;
import com.runyee.agdhome.entity.page.DepartTreePage;
import com.runyee.agdhome.entity.page.RightTreePage;
import com.runyee.agdhome.entity.pagebean.ManDepartUserPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class DepartService {
    @Autowired
    private DepartDao dao;
    @Autowired
    private UserDao userDao;

    //部门树
    public String departs(AppJson app){
        String service_code = ServiceCode.ERRO;
        //菜单列表
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            String filter = "";
            List<DepartParentBean> parents  = dao.getFirstDeparts(filter);
            List<DepartParentBean> leafs  = dao.getUnFirstDeparts(filter);
            List<DepartTreePage> departs =  new ArrayList<>();
            if(parents!=null&&parents.size()>0){
                for(DepartParentBean parent:parents){
                    DepartTreePage depart = new DepartTreePage();
                    depart.setId(parent.getAd_id());
                    depart.setName(parent.getAd_name());
                    depart.setIcon(parent.getAd_icon());

                    this.depart_recurrence(depart,leafs);
                    departs.add(depart);
                }
            }

            app.setObj(departs);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }

        return service_code;
    }


    //递归封装
    public void depart_recurrence(DepartTreePage depart,List<DepartParentBean> leafs){
        if(depart!=null&&!ConvertUtil.isEmpty(depart.getId())){
            if(leafs!=null && leafs.size()>0){
                List<DepartTreePage> childrens = new ArrayList<>();
                for(DepartParentBean leaf:leafs){
                    if(leaf!=null&&leaf.getHandle_flg()==0){
                        if(!ConvertUtil.isEmpty(leaf.getAd_id())){
                            if(depart.getId().equals(leaf.getAd_pid())){
                                leaf.setHandle_flg(1);

                                DepartTreePage children = new DepartTreePage();
                                children.setId(leaf.getAd_id());
                                children.setName(leaf.getAd_name());
                                children.setIcon(leaf.getAd_icon());
                                this.depart_recurrence(children,leafs);

                                childrens.add(children);
                            }
                        }
                    }
                }
                depart.setChildren(childrens);
            }
            if(depart.getChildren()==null||depart.getChildren().size()<=0){
                depart.setLeaf_flg(1);
            }
        }
    }


    public String issue(DepartForm formBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(formBean!=null){
                if(!ConvertUtil.isEmpty(formBean.getName())){
                    String pid = "";
                    if(!ConvertUtil.isEmpty(formBean.getPid())){
                        pid = formBean.getPid();
                    }
                    AhDepart depart = null;
                    if(!ConvertUtil.isEmpty(formBean.getId())){
                        //更新
                        depart = dao.getDepartOneKey(formBean.getId());
                        if(depart!=null && !ConvertUtil.isEmpty(depart.getAd_id())){
                            depart.setAd_name(formBean.getName());
                            depart.setAd_icon(formBean.getIcon());
                            //更改父级
                            if(!pid.equals(depart.getAd_pid())&&!ConvertUtil.isEmpty(pid)){
                                AhDepart pdepart = dao.getDepartOneKey(pid);
                                if(pdepart!=null&&!ConvertUtil.isEmpty(pdepart.getAd_id())){
                                    depart.setAd_pid(pid);
                                }else{
                                    service_code = ServiceCode.fail_param_error;
                                }
                            }
                            if(ServiceCode.ERRO.equals(service_code)){
                                dao.updateDepartInfo(depart);
                                service_code  = ServiceCode.SUCCESS;
                            }

                        }else{
                            service_code = ServiceCode.fail_param_error;
                        }

                    }else{
                        if(!ConvertUtil.isEmpty(pid)){
                            AhDepart pdepart = dao.getDepartOneKey(pid);
                            if(pdepart!=null&&!ConvertUtil.isEmpty(pdepart.getAd_id())){
                                //depart.setAd_pid(pid);
                            }else{
                                service_code = ServiceCode.fail_param_error;
                            }
                        }

                        if(ServiceCode.ERRO.equals(service_code)){
                            //新增
                            depart = new AhDepart();
                            depart.setAd_name(formBean.getName());
                            depart.setAd_pid(pid);
                            depart.setAd_icon(formBean.getIcon());

                            depart.setDel(0);
                            depart.setCreate_date(DateUtils.getDate());

                            dao.insertDepart(depart);
                            service_code  = ServiceCode.SUCCESS;
                        }
                    }

                }else {
                    service_code  = ServiceCode.fail_menu_name_empty;
                }
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    /**
     *  添加部门人员
     */
    public String uissue(String did,String uid,String operate, AppJson app) {
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(!ConvertUtil.isEmpty(did)&&!ConvertUtil.isEmpty(uid)){
                AhDepart depart = dao.getDepartOneKey(did);
                AhUser duser = userDao.getUserOneKey(uid);
                if(depart!=null&&!ConvertUtil.isEmpty(depart.getAd_id())&&
                        duser!=null&&!ConvertUtil.isEmpty(duser.getAu_id())){
                    int opt = ConvertUtil.convertStrToInt(operate);
                    AhUserDepart userDepart = dao.getDepartUserOneAssembleKey(did,uid);
                    if(opt==0){
                        //添加
                        if(userDepart!=null&&!ConvertUtil.isEmpty(userDepart.getAud_id())){
                            if(userDepart.getDel()==1){
                                //更新
                                userDepart.setDel(0);
                                dao.updateUserDepartDel(userDepart);
                            }else{
                                service_code = ServiceCode.fail_depart_user_added;
                            }
                        }else{
                            userDepart = new AhUserDepart();
                            userDepart.setAud_depart(did);
                            userDepart.setAud_user(uid);
                            userDepart.setDel(0);
                            userDepart.setCreate_date(DateUtils.getDate());
                            dao.insertUserDepart(userDepart);
                        }

                    }else{
                        //移除
                        if(userDepart!=null&&!ConvertUtil.isEmpty(userDepart.getAud_id())){
                            if(userDepart.getDel()==0){
                                //更新
                                userDepart.setDel(1);
                                dao.updateUserDepartDel(userDepart);
                            }else{
                                service_code = ServiceCode.success_depart_user_deled;
                                dao.updateUserDepartDelUidDid(did,uid);
                            }
                        }else{
                            service_code = ServiceCode.fail_depart_user_deled;
                        }
                    }

                    if(ServiceCode.ERRO.equals(service_code)){
                        service_code  = ServiceCode.SUCCESS;
                    }
                }else{
                    service_code = ServiceCode.fail_param_error;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }

        }else {
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }

        return service_code;
    }

    /**
     * 部门用户列表
     */
    public String ulist(ManDepartUserPageBean pageBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            if(!ConvertUtil.isEmpty(pageBean.getDepart())){
                String filter = "";

                if(pageBean.getFlg()==0){
                    //部门人员
                    filter +="   and  exists(select * " +
                            "                   from  ah_user_depart aud " +
                            "                   where ifnull(aud.del,0)=0 and aud.aud_depart='"+pageBean.getDepart()+"' " +
                            "                   and aud.aud_user = au.au_id )" ;
                }else{
                    //非部门人员
                    filter +="   and  not exists(select * " +
                            "                   from  ah_user_depart aud " +
                            "                   where ifnull(aud.del,0)=0 and aud.aud_depart='"+pageBean.getDepart()+"' " +
                            "                   and aud.aud_user = au.au_id )" ;
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
                int total = userDao.getUserBeansCount(filter);
                pageBean.setTotal(total);
                List<UserBean> usres = userDao.getUserBeans(filter,pageBean.getCurrPage(),pageBean.getRows());
                if(usres!=null && usres.size()>0){
                    pageBean.setData(usres);
                }
                app.setObj(pageBean);
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }else{
            service_code = APPCode.ERRO;
        }
        return service_code;
    }



    public String parent(String id,String pid, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(!ConvertUtil.isEmpty(id)){
                AhDepart depart = dao.getDepartOneKey(id);
                if(depart!=null && !ConvertUtil.isEmpty(depart.getAd_id())){
                    //更改父级
                    if(!pid.equals(depart.getAd_pid())&&!ConvertUtil.isEmpty(pid)){
                        AhDepart pdepart = dao.getDepartOneKey(pid);
                        if(pdepart!=null&&!ConvertUtil.isEmpty(pdepart.getAd_id())){
                            depart.setAd_pid(pid);
                        }else{
                            service_code = ServiceCode.fail_param_error;
                        }
                    }
                    if(ServiceCode.ERRO.equals(service_code)){
                        dao.updateDepartPid(depart);
                    }

                }else{
                    service_code = ServiceCode.fail_param_error;
                }
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    public String del(String id, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(!ConvertUtil.isEmpty(id)){
                AhDepart depart = dao.getDepartOneKey(id);
                if(depart!=null && !ConvertUtil.isEmpty(depart.getAd_id())){
                    depart.setDel(1);
                    dao.updateDepartDel(depart);
                    service_code = ServiceCode.SUCCESS;
                }else{
                    service_code = ServiceCode.fail_depart_noexists;
                }
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    //部门权限tree
    public String rights(String id,AppJson app){
        String service_code = ServiceCode.ERRO;
        //菜单列表
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(!ConvertUtil.isEmpty(id)){
                AhDepart depart = dao.getDepartOneKey(id);
                if(depart!=null&&!ConvertUtil.isEmpty(depart.getAd_id())){
                    String filter = "";
                    List<RightParentBean> parents  = dao.getFirstRights(id,filter);
                    List<RightParentBean> leafs  = dao.getUnFirstRights(id,filter);
                    List<RightTreePage> rights =  new ArrayList<>();
                    if(parents!=null&&parents.size()>0){
                        for(RightParentBean parent:parents){
                            RightTreePage right = new RightTreePage();
                            right.setId(parent.getRight_id());
                            right.setName(parent.getAm_name());
                            right.setIcon(parent.getAm_icon());
                            right.setMenu_id(parent.getAm_id());
                            right.setPmenu_id(parent.getAm_pid());
                            right.setSelect_flg(parent.getSelect_flg());

                            this.right_recurrence(right,leafs);
                            rights.add(right);
                        }
                    }

                    app.setObj(rights);
                    service_code  = ServiceCode.SUCCESS;
                }else{
                    service_code  = ServiceCode.fail_param_error;
                }
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }

        return service_code;
    }

    //递归封装
    public void right_recurrence(RightTreePage right,List<RightParentBean> leafs){
        if(right!=null&&!ConvertUtil.isEmpty(right.getId())){
            if(leafs!=null && leafs.size()>0){
                List<RightTreePage> childrens = new ArrayList<>();
                for(RightParentBean leaf:leafs){
                    if(leaf!=null&&leaf.getHandle_flg()==0){
                        if(!ConvertUtil.isEmpty(leaf.getAm_id())){
                            if(right.getMenu_id().equals(leaf.getAm_pid())){
                                leaf.setHandle_flg(1);

                                RightTreePage children = new RightTreePage();
                                children.setId(leaf.getRight_id());
                                children.setName(leaf.getAm_name());
                                children.setIcon(leaf.getAm_icon());
                                children.setMenu_id(leaf.getAm_id());
                                children.setPmenu_id(leaf.getAm_pid());
                                children.setSelect_flg(leaf.getSelect_flg());

                                this.right_recurrence(children,leafs);

                                childrens.add(children);
                            }
                        }
                    }
                }
                right.setChildren(childrens);
            }
            if(right.getChildren()==null||right.getChildren().size()<=0){
                right.setLeaf_flg(1);
            }
        }
    }

    //更改部门权限
    public String dr_issue(String id,String select_rights,AppJson app){
        String service_code = ServiceCode.ERRO;
        //菜单列表
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(!ConvertUtil.isEmpty(id)){
                AhDepart depart = dao.getDepartOneKey(id);
                if(depart!=null&&!ConvertUtil.isEmpty(depart.getAd_id())){
                    //清除部门所有未选中权限
                    String filter = "";
                    String ids = "";
                    List<String>  rights = null;
                    if(!ConvertUtil.isEmpty(select_rights)){
                        JSONArray jArray = JSONArray.fromObject(select_rights);
                        if(jArray!=null){
                            rights  = JSONArray.toList(jArray,String.class);
                            if(rights!=null&&rights.size()>0){
                                ids = "''";
                                for(String right:rights){
                                    ids+=",'"+right+"'";
                                }
                                //= StringUtils.join(rights.toArray(), ",");
                            }
                        }
                    }
                    if(!ConvertUtil.isEmpty(ids)){
                        filter += " and adr_right not in ("+ids+")";
                    }
                    dao.updateDepartRightDelAll(id,filter);
                    List<AhDepartRight> departRights = dao.getDepartRights(id);
                    Map<String,AhDepartRight> r_map = new HashMap<>();
                    if(departRights!=null && departRights.size()>0){
                        for(AhDepartRight departRight:departRights){
                            r_map.put(departRight.getAdr_right(),departRight);
                        }
                    }

                    if(rights!=null&&rights.size()>0){
                        for(String right:rights){
                            AhDepartRight departRight = r_map.get(right);
                            if(departRight!=null && !ConvertUtil.isEmpty(departRight.getAdr_id())){
                                //更新
                                if(departRight.getDel()==1){
                                    departRight.setDel(0);
                                    dao.updateDepartRightDel(departRight);
                                }
                            }else{
                                //新建
                                departRight = new AhDepartRight();
                                departRight.setAdr_depart(id);
                                departRight.setAdr_right(right);
                                departRight.setDel(0);
                                departRight.setCreate_date(DateUtils.getDate());

                                dao.insertDepartRight(departRight);
                            }
                        }
                    }

                    service_code  = ServiceCode.SUCCESS;
                }else{
                    service_code  = ServiceCode.fail_param_error;
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
