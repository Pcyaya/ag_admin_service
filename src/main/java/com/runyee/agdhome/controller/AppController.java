package com.runyee.agdhome.controller;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.service.AppService;
import com.runyee.agdhome.service.ExternalService;
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
@RequestMapping("/app")
public class AppController {
    @Autowired
    private AppService service;
    @Autowired
    private ExternalService externalService;

    /**
     * 1.最新版本App 下载url
     * */
    @RequestMapping(value="/downloads", method = RequestMethod.POST)
    public AppJson downloads(HttpServletRequest request){
        AppJson app = new AppJson();
        String appcode = service.downloads(app);
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
     * 11.省列表
     * */
    @RequestMapping(value = "province",method = RequestMethod.POST)
    public AppJson province(){
        AppJson app = new AppJson();
        String appcode = externalService.province(app);
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
     *  12.子城市
     * */
    @RequestMapping(value = "/lastlevel",method = RequestMethod.POST)
    public AppJson lastlevel(HttpServletRequest req){
        AppJson app = new AppJson();
        String cityid = req.getParameter("cityid");
        String appcode = externalService.lastlevel(ConvertUtil.convertStrToInt(cityid),app);
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
     * 15.名片
     * */
    @RequestMapping(value="/card", method = RequestMethod.POST)
    public AppJson card(HttpServletRequest req){
        AppJson app = new AppJson();
        //tag  1-团 2-侗友 3-设备 4-登陆二维码
        String num = req.getParameter("num");
        String tag = req.getParameter("tag");
        String appcode = service.card(num,tag,app);
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
     * 16.注册（领取游币）
     * */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public AppJson register(HttpServletRequest req){
        AppJson app = new AppJson();
        //tag  1-团 2-侗友 3-设备 4-登陆二维码
        String num = req.getParameter("num");
        String tag = req.getParameter("tag");
        String phone = req.getParameter("phone");
        String source = req.getParameter("source");
        String appcode = service.register(num,tag,phone,ConvertUtil.convertStrToInt(source),app);
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
     * 22.sos 处理信息
     * */
    @RequestMapping(value="/sosinfo", method = RequestMethod.POST)
    public AppJson sosinfo(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String appcode = service.sosinfo(id,app);
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
