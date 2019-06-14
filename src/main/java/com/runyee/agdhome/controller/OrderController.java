package com.runyee.agdhome.controller;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.OrderForm;
import com.runyee.agdhome.entity.form.RecruitApplyForm;
import com.runyee.agdhome.entity.pagebean.RecruitPageBean;
import com.runyee.agdhome.service.OrderService;
import com.runyee.agdhome.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;


    /**
     * 9.艾侗游产品订购
     * */
    @RequestMapping(value="/apply", method = RequestMethod.POST)
    public AppJson apply(OrderForm form){
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
