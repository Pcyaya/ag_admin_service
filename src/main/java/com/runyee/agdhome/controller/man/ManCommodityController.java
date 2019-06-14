package com.runyee.agdhome.controller.man;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.form.CommodityTmpForm;
import com.runyee.agdhome.entity.pagebean.ManCommodityTmpPageBean;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.CommodityService;
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
@RequestMapping("/man/commodity")
@UrlSkip(value = "man_commodity",skip = Skip.MAN)
public class ManCommodityController {
    @Autowired
    private CommodityService service;


    /**
     *  商品模板 列表
     * */
    @RequestMapping(value="/tmp/list", method = RequestMethod.POST)
    public AppJson tmp_list(ManCommodityTmpPageBean pageBean){
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
     *  商品模板 信息
     * */
    @RequestMapping(value="/tmp/info", method = RequestMethod.POST)
    public AppJson tmp_info(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");//模板id
        String appcode = service.tmp_info(id,app);
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
     *  商品模板 删除
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
     *  商品模板 新建/编辑
     * */
    @RequestMapping(value="/tmp/issue", method = RequestMethod.POST)
    public AppJson tmp_issue(CommodityTmpForm formBean){
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


    /**
     *  商品模板 上下架
     * */
    @RequestMapping(value="/tmp/rack", method = RequestMethod.POST)
    public AppJson tmp_rack(HttpServletRequest request){
        AppJson app = new AppJson();
        String id = request.getParameter("id");
        String opt = request.getParameter("opt");
        String appcode = service.tmp_rack(id, ConvertUtil.convertStrToInt(opt),app);
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
