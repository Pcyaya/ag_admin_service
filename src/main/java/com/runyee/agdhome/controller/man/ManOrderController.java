package com.runyee.agdhome.controller.man;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.OrderForm;
import com.runyee.agdhome.entity.form.OrderHandleForm;
import com.runyee.agdhome.entity.pagebean.ManAppFreeBackPageBean;
import com.runyee.agdhome.entity.pagebean.ManOrderPageBean;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/man/order")
@UrlSkip(value = "man_order",skip = Skip.MAN)
public class ManOrderController {
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

    /**
     *  订购 列表
     * */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public AppJson list(ManOrderPageBean pageBean){
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

    @RequestMapping(value="/info", method = RequestMethod.POST)
    public AppJson maninfo(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String appcode = service.info(id,app);
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
    @RequestMapping(value="/handle", method = RequestMethod.POST)
    public AppJson manhandle(OrderHandleForm form){
        AppJson app = new AppJson();
        String appcode = service.handle(form,app);
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

}
