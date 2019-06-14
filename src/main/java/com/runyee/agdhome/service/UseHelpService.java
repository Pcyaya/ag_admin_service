package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.anygo.AgUseHelpDao;
import com.runyee.agdhome.dao.anygo.AgVisitorDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.autopage.VisitorAutoPage;
import com.runyee.agdhome.entity.db.ag_home.AhMenu;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.anygo.RyUseHelp;
import com.runyee.agdhome.entity.db.anygo.RyUseHelpCategory;
import com.runyee.agdhome.entity.db.anygo.RyUseHelpDetail;
import com.runyee.agdhome.entity.db.anygo.RyVisitor;
import com.runyee.agdhome.entity.form.FreeBackDetailBean;
import com.runyee.agdhome.entity.form.UseHelpCategoryForm;
import com.runyee.agdhome.entity.form.UseHelpDetailForm;
import com.runyee.agdhome.entity.form.UseHelpForm;
import com.runyee.agdhome.entity.page.UseHelpCategoryPage;
import com.runyee.agdhome.entity.page.UseHelpDetailPage;
import com.runyee.agdhome.entity.page.UseHelpPage;
import com.runyee.agdhome.entity.pagebean.ManAutoUserPageBean;
import com.runyee.agdhome.entity.pagebean.ManUseHelpCategoryPageBean;
import com.runyee.agdhome.entity.pagebean.ManUseHelpPageBean;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class UseHelpService {
    @Autowired
    private AgUseHelpDao dao;

    /**
     * 使用帮助类型 列表
     */
    public String categorys(ManUseHelpCategoryPageBean pageBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            String filter = "";
            List<RyUseHelpCategory> categorys = dao.getUseHelpCategorys(filter,pageBean.getCurrPage(),pageBean.getRows());
            if(categorys!=null && categorys.size()>0){
                List<UseHelpCategoryPage> pages  =  new ArrayList<>();
                for(RyUseHelpCategory category:categorys){
                    UseHelpCategoryPage page = new UseHelpCategoryPage();
                    page.setId(category.getRuc_id());
                    page.setName(category.getRuc_name());
                    page.setIcon(category.getRuc_icon());
                    page.setSync(category.getRuc_sync());

                    pages.add(page);
                }
                pageBean.setData(pages);
            }
            app.setObj(pageBean);
            service_code  = ServiceCode.SUCCESS;
        }else{
            service_code = APPCode.ERRO;
        }
        return service_code;
    }


    public String category_issue(UseHelpCategoryForm formBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        service_code = this.category_valid_form(formBean);
        if(ServiceCode.SUCCESS.equals(service_code)){
            RyUseHelpCategory category = null;
            if(!ConvertUtil.isEmpty(formBean.getId())){
                //更新
                category = dao.getUseHelpCategoryOneKey(formBean.getId());
                if(category!=null&&!ConvertUtil.isEmpty(category.getRuc_id())){
                    category.setRuc_name(formBean.getName());
                    category.setRuc_icon(formBean.getIcon());
                    category.setRuc_sync(formBean.getSync());

                    dao.updateUseHelpCategory(category);

                }else{
                    service_code = ServiceCode.fail_param_error;
                }
            }else{
                //新增
                category = new RyUseHelpCategory();
                category.setRuc_name(formBean.getName());
                category.setRuc_icon(formBean.getIcon());
                category.setRuc_sync(formBean.getSync());
                category.setRuc_del(0);
                category.setCreate_date(DateUtils.getDate());

                dao.insertUseHelpCategory(category);

            }
            app.setObj(category);
        }
        return service_code;
    }

    public String category_valid_form(UseHelpCategoryForm formBean){
        String service_code = ServiceCode.ERRO;
        if(formBean!=null){
            String msg = "类别";
            if(!ConvertUtil.isEmpty(formBean.getName())){
                String filter = " and ruc_name = '"+formBean.getName()+"' ";
                if(!ConvertUtil.isEmpty(formBean.getId())){
                    filter += " and ruc_id <> '"+formBean.getId()+"' ";
                }

                int count = dao.getUseHelpCategoryCount(filter);
                if(count>0){
                    msg = "类别:"+formBean.getName();
                    service_code = ServiceCode.fail_uhcategory_exists;
                }else{
                    service_code = ServiceCode.SUCCESS;
                }
            }else{
                service_code = ServiceCode.fail_uhcategory_empty;
            }
            if(!ServiceCode.SUCCESS.equals(service_code)){
                List<String> msgs = new ArrayList<>();
                msgs.add(msg);
                DataUtil.getCurrent().setMsgs(msgs);
            }
        }
        return service_code;
    }

    public String category_del(String  id, AppJson app) {
        String service_code = ServiceCode.ERRO;
            if(!ConvertUtil.isEmpty(id)){
                //删除
                RyUseHelpCategory category = dao.getUseHelpCategoryOneKey(id);
                if(category!=null&&!ConvertUtil.isEmpty(category.getRuc_id())){
                    category.setRuc_del(1);
                    dao.updateUseHelpCategoryDel(category);
                    service_code = ServiceCode.SUCCESS;
                }else{
                    service_code = ServiceCode.fail_param_error;
                }
            }
        return service_code;
    }

    /**
     *
     * @param opt 1.同步
     * */
    public String category_sync(String  id, String opt,AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            //删除
            RyUseHelpCategory category = dao.getUseHelpCategoryOneKey(id);
            if(category!=null&&!ConvertUtil.isEmpty(category.getRuc_id())){
                category.setRuc_sync(ConvertUtil.convertStrToInt(opt));
                dao.updateUseHelpCategorySync(category);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }


    /**
     * 使用帮助 列表
     */
    public String list(ManUseHelpPageBean pageBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(pageBean!=null){
            if(!ConvertUtil.isEmpty(pageBean.getCategory())){
                String filter = "";
                List<RyUseHelp> useHelps = dao.getUseHelps(pageBean.getCategory(),filter,pageBean.getCurrPage(),pageBean.getRows());
                if(useHelps!=null && useHelps.size()>0){
                    List<UseHelpPage> pages  =  new ArrayList<>();
                    for(RyUseHelp useHelp:useHelps){
                        UseHelpPage page = new UseHelpPage();
                        page.setId(useHelp.getRu_id());
                        page.setCategory(useHelp.getRu_category());
                        page.setTitle(useHelp.getRu_title());
                        page.setIcon(useHelp.getRu_icon());
                        page.setSync(useHelp.getRu_sync());

                        List<RyUseHelpDetail> details = dao.getUseHelpDetails(page.getId());
                        if(details!=null&&details.size()>0){
                            List<UseHelpDetailPage> detailPages = new ArrayList<>();
                            for(RyUseHelpDetail detail:details){
                                UseHelpDetailPage detailPage = new UseHelpDetailPage();
                                detailPage.setId(detail.getRud_id());
                                detailPage.setUsehlep(detail.getRud_usehelp());
                                detailPage.setContent(detail.getRud_content());
                                detailPage.setType(detail.getRud_type());
                                detailPage.setUrl(detail.getRud_url());

                                detailPages.add(detailPage);
                            }
                            page.setDetails(detailPages);
                        }

                        pages.add(page);
                    }
                    pageBean.setData(pages);
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



    public String issue(UseHelpForm formBean, AppJson app) {
        String service_code = ServiceCode.ERRO;
        service_code = this.valid_form(formBean);
        if(ServiceCode.SUCCESS.equals(service_code)){
            RyUseHelp useHelp = null;
            if(!ConvertUtil.isEmpty(formBean.getId())){
                //更新
                useHelp = dao.getUseHelpOneKey(formBean.getId());
                if(useHelp!=null&&!ConvertUtil.isEmpty(useHelp.getRu_id())){
                    useHelp.setRu_title(formBean.getTitle());
                    useHelp.setRu_icon(formBean.getIcon());
                    useHelp.setRu_sync(formBean.getSync());
                    dao.updateUseHelp(useHelp);
                    dao.updateUseHelpDetailDel(useHelp.getRu_id());

                }else{
                    service_code = ServiceCode.fail_param_error;
                }
            }else{
                //新增
                useHelp = new RyUseHelp();
                useHelp.setRu_category(formBean.getCategory());
                useHelp.setRu_title(formBean.getTitle());
                useHelp.setRu_icon(formBean.getIcon());
                useHelp.setRu_sync(formBean.getSync());
                useHelp.setRu_del(0);
                useHelp.setCreate_date(DateUtils.getDate());

                dao.insertUseHelp(useHelp);
            }

            if(!ConvertUtil.isEmpty(formBean.getDetails())){
                List<UseHelpDetailForm> detailForms = null;
                JSONArray jArray = JSONArray.fromObject(formBean.getDetails());
                if(jArray!=null){
                    detailForms = JSONArray.toList(jArray,UseHelpDetailForm.class);
                    if(detailForms!=null&&detailForms.size()>0){
                        for(UseHelpDetailForm detailForm:detailForms ){
                            RyUseHelpDetail detail = new RyUseHelpDetail();
                            detail.setRud_usehelp(useHelp.getRu_id());
                            detail.setRud_content(detailForm.getContent());
                            detail.setRud_type(detailForm.getType());
                            detail.setRud_url(detailForm.getUrl());
                            detail.setRud_del(0);
                            detail.setCreate_date(DateUtils.getDate());

                            dao.insertUseHelpDetail(detail);
                        }
                    }
                }
            }
            app.setObj(useHelp);
        }
        return service_code;
    }

    public String valid_form(UseHelpForm formBean){
        String service_code = ServiceCode.ERRO;
        if(formBean!=null){
            String msg = "类别";
            if(!ConvertUtil.isEmpty(formBean.getCategory())){
                RyUseHelpCategory category = dao.getUseHelpCategoryOneKey(formBean.getCategory());
                if(category!=null&&!ConvertUtil.isEmpty(category.getRuc_id())){
                    if(!ConvertUtil.isEmpty(formBean.getTitle())){
                        String filter=" and ru_title='"+formBean.getTitle()+"' ";
                        if(!ConvertUtil.isEmpty(formBean.getId())){
                            filter += " and ru_id <> '"+formBean.getId()+"' ";
                        }

                        int count = dao.getUseHelpCount(formBean.getCategory(),filter);
                        if(count>0){
                            msg = "类别:"+category.getRuc_name()+"下的问题："+formBean.getTitle();
                            service_code = ServiceCode.fail_usehelp_exists;
                        }else{
                            service_code = ServiceCode.SUCCESS;
                        }
                    }else{
                        msg = "问题";
                        service_code = ServiceCode.fail_usehelp_empty;
                    }
                }else{
                    service_code = ServiceCode.fail_usehelp_empty;
                }
            }else{
                service_code = ServiceCode.fail_param_error;
            }
            if(!ServiceCode.SUCCESS.equals(service_code)){
                List<String> msgs = new ArrayList<>();
                msgs.add(msg);
                DataUtil.getCurrent().setMsgs(msgs);
            }
        }
        return service_code;
    }

    public String del(String  id, AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            //删除
            RyUseHelp useHelp = dao.getUseHelpOneKey(id);
            if(useHelp!=null&&!ConvertUtil.isEmpty(useHelp.getRu_id())){
                useHelp.setRu_del(1);
                dao.updateUseHelpDel(useHelp);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    /**
     *
     * @param opt 1.同步
     * */
    public String sync(String  id, String opt,AppJson app) {
        String service_code = ServiceCode.ERRO;
        if(!ConvertUtil.isEmpty(id)){
            //删除
            RyUseHelp useHelp = dao.getUseHelpOneKey(id);
            if(useHelp!=null&&!ConvertUtil.isEmpty(useHelp.getRu_id())){
                useHelp.setRu_sync(ConvertUtil.convertStrToInt(opt));
                dao.updateUseHelpSync(useHelp);
                service_code = ServiceCode.SUCCESS;
            }else{
                service_code = ServiceCode.fail_param_error;
            }
        }
        return service_code;
    }

    public String sort(String category,String id,String opt, AppJson app){
        String service_code = ServiceCode.ERRO;
        AhUser user = DataUtil.getCurrent().getUser();
        if(user!=null && !ConvertUtil.isEmpty(user.getAu_id())){
            //类别排序
            if(!ConvertUtil.isEmpty(category)){
                //重新排序
                RyUseHelpCategory useHelpCategory = dao.getUseHelpCategoryOneKey(category);
                if(useHelpCategory!=null&&!ConvertUtil.isEmpty(useHelpCategory.getRuc_id())){
                    List<RyUseHelpCategory> categorys = dao.getUseHelpCategorysAll();
                    int operate = ConvertUtil.convertStrToInt(opt);
                    //0.上1.下
                    for(int i=0;i<categorys.size();i++){
                        RyUseHelpCategory curr = categorys.get(i);
                        if(useHelpCategory.getRuc_id().equals(curr.getRuc_id())){
                            int change = -1;
                            if(operate==0){
                                if(i==0){
                                    service_code = ServiceCode.fail_uhcategory_firsted;
                                }else{
                                    change = (i-1);
                                }
                            }else if(operate==1){
                                if(i==(categorys.size()-1)){
                                    service_code = ServiceCode.fail_uhcategory_lasted;
                                }else{
                                    change = (i+1);
                                }
                            }
                            if(change>=0&&change<categorys.size()){
                                RyUseHelpCategory cone = categorys.get(change);
                                categorys.set(i,cone);
                                categorys.set(change,curr);
                                break;
                            }
                        }
                    }

                    if(ServiceCode.ERRO.equals(service_code)){
                        for(int i=0;i<categorys.size();i++){
                            RyUseHelpCategory curr = categorys.get(i);
                            if(curr.getRuc_sort()!=i){
                                curr.setRuc_sort(i);
                                dao.updateUseHelpCategorySort(curr);
                            }
                        }
                        service_code = ServiceCode.SUCCESS;
                    }
                }else {
                    service_code  = ServiceCode.fail_param_error;
                }


            }else if(!ConvertUtil.isEmpty(id)){
                RyUseHelp useHelp = dao.getUseHelpOneKey(id);
                if(useHelp!=null&&!ConvertUtil.isEmpty(useHelp.getRu_id())){
                    String pid = "";
                    if(!ConvertUtil.isEmpty(useHelp.getRu_category())){
                        pid = useHelp.getRu_category();
                    }
                    //重新排序
                    List<RyUseHelp> useHelps = dao.getUseHelpsCategory(pid);
                    int operate = ConvertUtil.convertStrToInt(opt);
                    //0.上1.下
                    for(int i=0;i<useHelps.size();i++){
                        RyUseHelp curr = useHelps.get(i);
                        if(useHelp.getRu_id().equals(curr.getRu_id())){
                            int change = -1;
                            if(operate==0){
                                if(i==0){
                                    service_code = ServiceCode.fail_menu_firsted;
                                }else{
                                    change = (i-1);
                                }
                            }else if(operate==1){
                                if(i==(useHelps.size()-1)){
                                    service_code = ServiceCode.fail_menu_lasted;
                                }else{
                                    change = (i+1);
                                }
                            }
                            if(change>=0&&change<useHelps.size()){
                                RyUseHelp cone = useHelps.get(change);
                                useHelps.set(i,cone);
                                useHelps.set(change,curr);
                                break;
                            }
                        }
                    }

                    if(ServiceCode.ERRO.equals(service_code)){
                        for(int i=0;i<useHelps.size();i++){
                            RyUseHelp curr = useHelps.get(i);
                            if(curr.getRu_sort()!=i){
                                curr.setRu_sort(i);
                                dao.updateUseHelpSort(curr);
                            }
                        }
                        service_code = ServiceCode.SUCCESS;
                    }
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
