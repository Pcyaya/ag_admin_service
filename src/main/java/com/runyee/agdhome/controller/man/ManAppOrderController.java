package com.runyee.agdhome.controller.man;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.OrderForm;
import com.runyee.agdhome.entity.pagebean.ManAppOrderPageBean;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.AppOrderService;
import com.runyee.agdhome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/man/apporder")
@UrlSkip(value = "man_apporder",skip = Skip.MAN)
public class ManAppOrderController {
    @Autowired
    private AppOrderService service;


    /**
     * app 订单列表
     * */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public AppJson list(ManAppOrderPageBean pageBean){
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

}
