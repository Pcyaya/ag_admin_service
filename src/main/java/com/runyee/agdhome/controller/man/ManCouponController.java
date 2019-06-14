package com.runyee.agdhome.controller.man;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.CouponTmpForm;
import com.runyee.agdhome.entity.pagebean.ManCouponTmpPageBean;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/man/coupon")
@UrlSkip(value = "man_coupon",skip = Skip.MAN)
public class ManCouponController {
    @Autowired
    private CouponService service;


    /**
     *  优惠券模板 列表
     * */
    @RequestMapping(value="/tmp/list", method = RequestMethod.POST)
    public AppJson tmp_list(ManCouponTmpPageBean pageBean){
        AppJson app = new AppJson();
        String appcode = service.tmp_list(pageBean,app);
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
     *  优惠券模板 删除
     * */
    @RequestMapping(value="/tmp/del", method = RequestMethod.POST)
    public AppJson tmp_list(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String appcode = service.tmp_del(id,app);
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
     *  优惠券模板 新建
     * */
    @RequestMapping(value="/tmp/issue", method = RequestMethod.POST)
    public AppJson tmp_issue(CouponTmpForm formBean){
        AppJson app = new AppJson();
        String appcode = service.tmp_issue(formBean,app);
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
