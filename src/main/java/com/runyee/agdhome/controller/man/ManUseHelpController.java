package com.runyee.agdhome.controller.man;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.UseHelpCategoryForm;
import com.runyee.agdhome.entity.form.UseHelpForm;
import com.runyee.agdhome.entity.pagebean.ManUseHelpCategoryPageBean;
import com.runyee.agdhome.entity.pagebean.ManUseHelpPageBean;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.UseHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/man/usehelp")
@UrlSkip(value = "man_usehelp",skip = Skip.MAN)
public class ManUseHelpController {
    @Autowired
    private UseHelpService service;

    /**
     * 使用帮助类型 列表
     * */
    @RequestMapping(value="/categorys", method = RequestMethod.POST)
    public AppJson categorys(ManUseHelpCategoryPageBean pageBean){
        AppJson app = new AppJson();
        String appcode = service.categorys(pageBean,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }

    /**
     * 使用帮助类型 新增/编辑
     * */
    @RequestMapping(value="/category/issue", method = RequestMethod.POST)
    public AppJson category_issue(UseHelpCategoryForm formBean){
        AppJson app = new AppJson();
        String appcode = service.category_issue(formBean,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }

    /**
     * 使用帮助类型 删除
     * */
    @RequestMapping(value="/category/del", method = RequestMethod.POST)
    public AppJson category_del(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String appcode = service.category_del(id,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }

    /**
     * 使用帮助类型 app 同步
     * */
    @RequestMapping(value="/category/sync", method = RequestMethod.POST)
    public AppJson category_sync(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String opt = request.getParameter("opt");//1.同步
        String appcode = service.category_sync(id,opt,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }




    /**
     * 使用帮助 列表
     * */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public AppJson list(ManUseHelpPageBean pageBean){
        AppJson app = new AppJson();
        String appcode = service.list(pageBean,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }

    /**
     * 使用帮助 新增/编辑
     * */
    @RequestMapping(value="/issue", method = RequestMethod.POST)
    public AppJson issue(UseHelpForm formBean){
        AppJson app = new AppJson();
        String appcode = service.issue(formBean,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }

    /**
     * 使用帮助 删除
     * */
    @RequestMapping(value="/del", method = RequestMethod.POST)
    public AppJson del(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String appcode = service.del(id,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }

    /**
     * 使用帮助 app 同步
     * */
    @RequestMapping(value="/sync", method = RequestMethod.POST)
    public AppJson sync(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String opt = request.getParameter("opt");//1.同步
        String appcode = service.sync(id,opt,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }


    /**
     *  使用帮助/类型 排序
     * */
    @RequestMapping(value="/sort", method = RequestMethod.POST)
    public AppJson sort(HttpServletRequest request){
        AppJson app = new AppJson();
        String category = request.getParameter("category");//排序类别id
        String id = request.getParameter("id");//使用帮助
        String opt = request.getParameter("opt");//0.上1.下
        String appcode = service.sort(category,id,opt,app);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            app.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                app.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            app.setSuccess(APPCode.SUCCESS);
            app.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            app.setSuccess(APPCode.ERRO);
            app.setMsg(ServiceCode.failMap.get(appcode));
        }
        return app;
    }


}
