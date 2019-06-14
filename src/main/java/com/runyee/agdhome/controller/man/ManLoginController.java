package com.runyee.agdhome.controller.man;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.UserService;
import com.runyee.agdhome.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/man/")
@UrlSkip(value = "man",skip = Skip.NO)
public class ManLoginController {
    @Autowired
    private UserService service;
    /**
     * 1.登陆
     * */
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public AppJson login(HttpServletRequest request){
        AppJson app = new AppJson();
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        String source = request.getParameter("source");
        String appcode = service.login(phone,pwd, ConvertUtil.convertStrToInt(source),app);
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
