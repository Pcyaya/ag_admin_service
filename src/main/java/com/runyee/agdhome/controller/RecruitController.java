package com.runyee.agdhome.controller;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.ContactForm;
import com.runyee.agdhome.entity.form.RecruitApplyForm;
import com.runyee.agdhome.entity.pagebean.NewsPageBean;
import com.runyee.agdhome.entity.pagebean.RecruitPageBean;
import com.runyee.agdhome.service.NewsService;
import com.runyee.agdhome.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService service;


    /**
     * 7.招聘列表
     * */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public AppJson list(RecruitPageBean pageBean){
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
     * 8.职位申请
     * */
    @RequestMapping(value="/apply", method = RequestMethod.POST)
    public AppJson apply(RecruitApplyForm form){
        AppJson app = new AppJson();
        String appcode = service.apply(form,app);
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
