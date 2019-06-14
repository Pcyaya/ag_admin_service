package com.runyee.agdhome.controller;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.AgentApplyBean;
import com.runyee.agdhome.entity.form.FreeBackBean;
import com.runyee.agdhome.service.AgentService;
import com.runyee.agdhome.service.FreebackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    private AgentService service;

    /**
     *  13.代理商申请
     * */
    @RequestMapping(value="/apply", method = RequestMethod.POST)
    public AppJson apply(AgentApplyBean form){
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
