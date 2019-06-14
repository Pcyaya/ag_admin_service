package com.runyee.agdhome.controller;

import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.ag_home.TestDao;
import com.runyee.agdhome.dao.anygo.AgTestDao;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.entity.db.AhTest;
import com.runyee.agdhome.entity.db.anygo.RyVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cheri on 2018/7/27.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private AgTestDao agtestDao;
    @Autowired
    private TestDao testDao;

    @RequestMapping(value = "/test")
    private AppJson test(){
        String appcode = ServiceCode.SUCCESS;
        AppJson app = new AppJson();
        List<RyVisitor> visitors = agtestDao.getVisitors();
        List<AhTest> tests = testDao.getTest();
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
