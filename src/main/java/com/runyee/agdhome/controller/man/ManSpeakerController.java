package com.runyee.agdhome.controller.man;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.ex.SpkSyncBean;
import com.runyee.agdhome.entity.form.MenuForm;
import com.runyee.agdhome.entity.pagebean.ManSpeakerPageBean;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.MenuService;
import com.runyee.agdhome.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cheri on 2018/2/23.
 */
@RestController
@RequestMapping("/man/speaker")
@UrlSkip(value = "man_speaker",skip = Skip.MAN)
public class ManSpeakerController {
    @Autowired
    private SpeakerService service;

    /**
     * 音箱设备列表
     * */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public AppJson list(ManSpeakerPageBean pageBean){
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

    //激活
    @RequestMapping(value="/active", method = RequestMethod.POST)
    public AppJson active(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String appcode = service.active(id,app);
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

    // 设备连接服务器
    @RequestMapping(value="/server", method = RequestMethod.POST)
    public AppJson server(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String server = request.getParameter("server");//1.测试服务器
        String appcode = service.server(id,server,app);
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

    //解绑设备
    @RequestMapping(value="/unbind", method = RequestMethod.POST)
    public AppJson bind(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        //String visitor = request.getParameter("visitor");
        //String op = request.getParameter("op");
        String appcode = service.unbind(id,app);
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

    //修改绑定码
    @RequestMapping(value="/bindcode", method = RequestMethod.POST)
    public AppJson bindcode(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String appcode = service.bindcode(id,code,app);
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
     *  获取设备实时路径
     */
    @RequestMapping(value = "/path" ,method = RequestMethod.POST)
    public AppJson show_speaker_path(SpkSyncBean form){
        AppJson json = new AppJson();
        String appcode = service.show_speaker_path(form,json);
        if(APPCode.codeMap.containsKey(appcode)){
            //原编码返回
            json.setSuccess(appcode);
            if(!APPCode.SUCCESS.equals(appcode)){
                json.setMsg(APPCode.codeMap.get(appcode));
            }
        }else if(ServiceCode.successMap.containsKey(appcode)){
            //转换成功码
            json.setSuccess(APPCode.SUCCESS);
            json.setMsg(ServiceCode.successMap.get(appcode));
        }else if(ServiceCode.failMap.containsKey(appcode)){
            //转换错误码
            json.setSuccess(APPCode.ERRO);
            json.setMsg(ServiceCode.failMap.get(appcode));
        }
        return json;
    }

}
