package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.AppUrl;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.MenuDao;
import com.runyee.agdhome.dao.ag_home.RightDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.ag_home.AhMenu;
import com.runyee.agdhome.entity.db.ag_home.AhRight;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.ex.MenuBean;
import com.runyee.agdhome.entity.ex.MenuParentBean;
import com.runyee.agdhome.entity.ex.NewsBean;
import com.runyee.agdhome.entity.form.MenuForm;
import com.runyee.agdhome.entity.page.*;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class MenuService {
    @Autowired
    private MenuDao dao;

    @Autowired
    private RightDao rightDao;

    @Autowired
    private RightService rightService;



    public String shows(AppJson app){
        String service_code = ServiceCode.ERRO;
        //菜单列表
        String filter = "";
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            int level = user.getAu_level();
            if(level!=999){
                //个人 权限
                List<AhRight>  u_rights = rightDao.getRightsUser(user.getAu_id(),0);
                if(u_rights!=null&&u_rights.size()>0){
                    //菜单权限
                    List<String> keys = new ArrayList<>();
                    String menus = "''";
                    for(AhRight u_right:u_rights){
                        keys.add(u_right.getAr_menu());
                        if(!ConvertUtil.isEmpty(u_right.getAr_menu())){
                            menus+=",'"+u_right.getAr_menu()+"'";
                        }
                    }
                    System.out.println(keys.toArray().toString());
                    filter += " and (am.am_id in ("+menus+") or ifnull(am.am_view,0) = 1 ) ";
                }else{
                    return ServiceCode.fail_menu_noright;
                }
            }
            List<MenuParentBean> parents  = dao.getFirstMenus(filter);
            List<MenuParentBean> leafs  = dao.getUnFirstMenus(filter);
            List<MenuPage> menus =  new ArrayList<>();
            this.menu_recurrences_new(leafs,parents,menus);
            app.setObj(menus);
            service_code  = ServiceCode.SUCCESS;

        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }

        return service_code;
    }

    public void menu_recurrences(List<MenuParentBean> leafs,List<MenuParentBean> parents,List<MenuPage> menus){
        if(parents!=null && parents.size()>0){
            for(MenuParentBean parent:parents){
                menu_recurrence(parent,leafs);
            }
            for(MenuParentBean parent:parents){
                menu_recurrence_sort(parent,menus);
            }
        }
    }

    public void menu_recurrences_new(List<MenuParentBean> leafs,List<MenuParentBean> parents,List<MenuPage> menus){
        if(parents!=null && parents.size()>0){
            for(MenuParentBean parent:parents){
                if(parent!=null&&!ConvertUtil.isEmpty(parent.getAm_id())) {
                    parent.setHandle_flg(1);

                    MenuPage menu = new MenuPage();
                    menu.setId(parent.getAm_id());
                    menu.setName(parent.getAm_name());
                    menu.setUrl(parent.getAm_url());
                    menu.setIcon(parent.getAm_icon());
                    menu.setRight_flg(parent.getRight_flg());
                    menu.setView_flg(parent.getAm_view());
                    menu.setLeaf_flg(1);
                    this.menu_recurrence_new(menu,leafs);
                    menus.add(menu);

                }
            }
        }

        if(leafs!=null && leafs.size()>0){
            for(MenuParentBean leaf:leafs){
                if(leaf.getHandle_flg()!=1&&leaf.getAm_level()!=0){
                    leaf.setHandle_flg(1);

                    MenuPage menu = new MenuPage();
                    menu.setId(leaf.getAm_id());
                    menu.setName(leaf.getAm_name());
                    menu.setUrl(leaf.getAm_url());
                    menu.setIcon(leaf.getAm_icon());
                    menu.setRight_flg(leaf.getRight_flg());
                    menu.setView_flg(leaf.getAm_view());
                    menu.setLeaf_flg(1);
                    menus.add(menu);
                }
            }
        }

    }

    //递归封装
    public void menu_recurrence(MenuParentBean parent,List<MenuParentBean> leafs){
           if(leafs!=null && leafs.size()>0){
               for(MenuParentBean leaf:leafs){
                   List<MenuParentBean> childs =   parent.getChilds();
                   if(childs==null){
                       childs = new ArrayList<>();
                       parent.setChilds(childs);
                   }
                   if(leaf!=null&&leaf.getHandle_flg()==0){
                       if(!ConvertUtil.isEmpty(leaf.getAm_pid())){
                           if(parent.getAm_id().equals(leaf.getAm_pid())){
                               leaf.setHandle_flg(1);
                               childs.add(leaf);
                               menu_recurrence(leaf,leafs);
                           }
                       }
                   }
               }
           }
    }

    //递归封装
    public void menu_recurrence_new(MenuPage menu,List<MenuParentBean> leafs){
        if(menu!=null&&!ConvertUtil.isEmpty(menu.getId())){
            if(leafs!=null && leafs.size()>0){
                List<MenuPage> childrens = new ArrayList<>();
                for(MenuParentBean leaf:leafs){
                    if(leaf!=null&&leaf.getHandle_flg()==0){
                        if(!ConvertUtil.isEmpty(leaf.getAm_pid())){
                            if(menu.getId().equals(leaf.getAm_pid())){
                                leaf.setHandle_flg(1);

                                MenuPage children = new MenuPage();
                                children.setId(leaf.getAm_id());
                                children.setName(leaf.getAm_name());
                                children.setUrl(leaf.getAm_url());
                                children.setIcon(leaf.getAm_icon());
                                children.setRight_flg(leaf.getRight_flg());
                                children.setView_flg(leaf.getAm_view());
                                this.menu_recurrence_new(children,leafs);

                                childrens.add(children);
                            }
                        }
                    }
                }
                menu.setChildren(childrens);
            }
            if(menu.getChildren()!=null&&menu.getChildren().size()>0){
                menu.setLeaf_flg(0);
            }else{
                menu.setLeaf_flg(1);
            }
        }
    }

    public void menu_recurrence_sort(MenuParentBean parent,List<MenuPage> menus){
        if(parent!=null&&!ConvertUtil.isEmpty(parent.getAm_id())){

            MenuPage menu = new MenuPage();
            menu.setId(parent.getAm_id());
            menu.setName(parent.getAm_name());
            menu.setUrl(parent.getAm_url());
            menu.setIcon(parent.getAm_icon());
            menu.setRight_flg(parent.getRight_flg());
            menu.setView_flg(parent.getAm_view());
            menus.add(menu);

            List<MenuParentBean> childs =   parent.getChilds();
            if(childs!=null && childs.size()>0){
                List<MenuPage> children = new ArrayList<>();
                for(MenuParentBean child:childs){
                    menu_recurrence_sort(child,children);
                }
                menu.setChildren(children);
            }else{
                menu.setLeaf_flg(1);
            }
            menus.get(0);
        }
    }


    public String menus(AppJson app){
        String service_code = ServiceCode.ERRO;
        //菜单列表
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            List<MenuParentBean> parents  = dao.getFirstMenusAll();
            List<MenuParentBean> leafs  = dao.getUnFirstMenusAll();
            List<MenuPage> menus =  new ArrayList<>();
            this.menu_recurrences(leafs,parents,menus);
            app.setObj(menus);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }

        return service_code;
    }

    public String right(String id,String opt,AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            AhMenu menu = dao.getMenuOneKey(id);
            if(menu!=null&&!ConvertUtil.isEmpty(menu.getAm_id())){
                AhRight right = rightService.getRightMenuOne(menu);
                //1.关联
                if("1".equals(opt)){
                    if(right.getDel()==1){
                        right.setDel(0);
                        rightDao.updateRightDel(right);
                    }
                }else if("0".equals(opt)){
                    if(right.getDel()==0){
                        right.setDel(1);
                        rightDao.updateRightDel(right);
                    }
                }
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    public String view(String id,String opt,AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            AhMenu menu = dao.getMenuOneKey(id);
            if(menu!=null&&!ConvertUtil.isEmpty(menu.getAm_id())){
                //1.可见
                int view = ConvertUtil.convertStrToInt(opt);
                if(menu.getAm_view()!=view){
                    menu.setAm_view(view);
                    dao.updateMenuView(menu);
                }
                service_code  = ServiceCode.SUCCESS;
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    public String issue(MenuForm formBean, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(formBean!=null){
                if(!ConvertUtil.isEmpty(formBean.getName())){
                    String pid = "";
                    int level = 1;
                    MenuBean menu = null;
                    if(!ConvertUtil.isEmpty(formBean.getId())){
                        //更新
                        menu = dao.getMenuBeanOneKey(formBean.getId());
                        if(menu!=null && !ConvertUtil.isEmpty(menu.getAm_id())){

                            if(ServiceCode.ERRO.equals(service_code)){

                                menu.setAm_name(formBean.getName());
                                menu.setAm_url(formBean.getUrl());
                                menu.setAm_view(formBean.getView());
                                menu.setAm_icon(formBean.getIcon());

                                dao.updateMenuInfo(menu);
                            }
                        }else{
                            service_code = ServiceCode.fail_param_error;
                        }

                    }else{
                        //新增
                        int sort = 0;

                        if(!ConvertUtil.isEmpty(formBean.getPid())){
                            pid = formBean.getPid();
                            AhMenu pmenu = dao.getMenuOneKey(pid);
                            if(pmenu!=null&&!ConvertUtil.isEmpty(pmenu.getAm_id())){
                                level = pmenu.getAm_level()+1;
                            }else{
                                service_code = ServiceCode.fail_param_error;
                            }
                        }

                        if(level>3){
                            service_code = ServiceCode.fail_menu_max_level;
                        }

                        if(ServiceCode.ERRO.equals(service_code)){
                            //新增
                            menu = new MenuBean();
                            menu.setAm_name(formBean.getName());
                            menu.setAm_url(formBean.getUrl());
                            menu.setAm_pid(pid);
                            menu.setAm_level(level);

                            menu.setAm_view(formBean.getView());
                            menu.setAm_icon(formBean.getIcon());
                            menu.setDel(0);
                            menu.setCreate_date(DateUtils.getDate());
                            AhMenu last = dao.getMenuOneLastPid(pid);
                            if(last!=null&&!ConvertUtil.isEmpty(last.getAm_id())){
                                sort = last.getAm_sort()+1;
                            }
                            menu.setAm_sort(sort);

                            dao.insertMenu(menu);
                        }
                    }
                    //权限处理
                    if(ServiceCode.ERRO.equals(service_code)){
                        AhRight right = rightService.getRightMenuOne(menu);
                        if(formBean.getRight_flg()==0){
                            if(right.getDel()==0){
                                right.setDel(1);
                                rightDao.updateRightDel(right);
                            }
                        }else{
                            if(right.getDel()==1){
                                right.setDel(0);
                                rightDao.updateRightDel(right);
                            }
                        }
                        service_code = ServiceCode.SUCCESS;
                        app.setObj(menu);
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


    public int max_level(MenuBean menu,int level){
        if(menu!=null && !ConvertUtil.isEmpty(menu.getAm_id())&&menu.getLeaf_flg()==0){
            List<MenuBean> smenus = dao.getMenuBeansPid(menu.getAm_id());
            if(smenus!=null && smenus.size()>0){
                int mlevel = level+1;
                for(MenuBean smenu:smenus){
                    int sub_level = this.max_level(smenu,mlevel);
                    if(sub_level>mlevel){
                        level = sub_level;
                    }else{
                        level = mlevel;
                    }
                }
            }
        }
        return level;
    }

    public void update_sub_level(MenuBean menu){
        if(menu!=null && !ConvertUtil.isEmpty(menu.getAm_id())&&menu.getLeaf_flg()==0){
            int level = menu.getAm_level();
            List<MenuBean> smenus = dao.getMenuBeansPid(menu.getAm_id());
            if(smenus!=null && smenus.size()>0){
                int mlevel = level+1;
                for(MenuBean smenu:smenus){
                    smenu.setAm_level(mlevel);
                    dao.updateMenuLevel(smenu);
                    this.update_sub_level(smenu);
                }
            }
        }
    }



    public String sort(String id,String opt, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            AhMenu menu = dao.getMenuOneKey(id);
            if(menu!=null&&!ConvertUtil.isEmpty(menu.getAm_id())){
                String pid = "";
                if(!ConvertUtil.isEmpty(menu.getAm_pid())){
                    pid = menu.getAm_pid();
                }
                //重新排序
                //List<AhMenu> new_menus = new ArrayList<>();
                List<AhMenu> menus = dao.getMenusPid(pid);
                int operate = ConvertUtil.convertStrToInt(opt);
                //0.上1.下
                for(int i=0;i<menus.size();i++){
                    AhMenu curr = menus.get(i);
                    if(menu.getAm_id().equals(curr.getAm_id())){
                        int change = -1;
                        if(operate==0){
                            if(i==0){
                                service_code = ServiceCode.fail_menu_firsted;
                            }else{
                                change = (i-1);
                            }
                        }else if(operate==1){
                            if(i==(menus.size()-1)){
                                service_code = ServiceCode.fail_menu_lasted;
                            }else{
                                change = (i+1);
                            }
                        }
                        if(change>=0&&change<menus.size()){
                            AhMenu cmenu = menus.get(change);
                            menus.set(i,cmenu);
                            menus.set(change,curr);
                            break;
                        }
                    }
                }

                if(ServiceCode.ERRO.equals(service_code)){
                    for(int i=0;i<menus.size();i++){
                        AhMenu curr = menus.get(i);
                        if(curr.getAm_sort()!=i){
                            curr.setAm_sort(i);
                            dao.updateMenuSort(curr);
                        }
                    }
                    service_code = ServiceCode.SUCCESS;
                }
            }else{
                service_code  = ServiceCode.fail_param_error;
            }
        }else{
            service_code  = APPCode.ACCESS_TOKEN_LOGIN;
        }
        return service_code;
    }

    public String parent(String id,String pid, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            if(!ConvertUtil.isEmpty(id)){
                MenuBean menu  = dao.getMenuBeanOneKey(id);
                if(menu!=null && !ConvertUtil.isEmpty(menu.getAm_id())){
                    if(pid==null){
                        pid = "";
                    }
                    if(!pid.equals(menu.getAm_pid())){
                        if(!pid.equals(menu.getAm_id())){
                            int level = 1;
                            int max_level;
                            if(!ConvertUtil.isEmpty(pid)){
                                AhMenu pmenu = dao.getMenuOneKey(pid);
                                if(pmenu!=null&&!ConvertUtil.isEmpty(pmenu.getAm_id())){
                                    level = pmenu.getAm_level()+1;
                                }else{
                                    service_code = ServiceCode.fail_param_error;
                                }
                            }
                            if(level>3){
                                service_code = ServiceCode.fail_menu_max_level;
                            }else{
                                max_level = level;
                                max_level = this.max_level(menu,max_level);
                                if(max_level>3){
                                    service_code = ServiceCode.fail_menu_max_level;
                                }
                            }

                            if(ServiceCode.ERRO.equals(service_code)){
                                menu.setAm_pid(pid);
                                if(menu.getAm_level()!=level){
                                    menu.setAm_level(level);
                                    //更新所有子节点level
                                    this.update_sub_level(menu);
                                }
                                dao.updateMenuParent(menu);
                                service_code = ServiceCode.SUCCESS;
                            }

                        }else{
                            service_code = ServiceCode.fail_menu_parent_self;
                        }
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
                MenuBean menu  = dao.getMenuBeanOneKey(id);
                if(menu!=null && !ConvertUtil.isEmpty(menu.getAm_id())){
                    this.update_del_menu(menu,1);
                    service_code = ServiceCode.SUCCESS;
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

    public void update_del_menu(MenuBean menu,int del){
        if(menu!=null && !ConvertUtil.isEmpty(menu.getAm_id())){
            menu.setDel(del);
            dao.updateMenuDel(menu);
            if(del==1){
                List<MenuBean> smenus = dao.getMenuBeansPid(menu.getAm_id());
                if(smenus!=null && smenus.size()>0){
                    for(MenuBean smenu:smenus){
                        this.update_del_menu(smenu,del);
                    }
                }
            }
        }
    }
}
