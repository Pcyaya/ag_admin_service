package com.runyee.agdhome.interceptor;

import com.runyee.agdhome.annotation.UrlSkip;
import com.runyee.agdhome.constant.APPCode;
import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.enums.Skip;
import com.runyee.agdhome.service.UserService;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DataUtil;
import com.runyee.agdhome.util.DateUtils;
import com.runyee.config.db.dynamic.DynamicDataSourceConfig;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by cheri on 2018/2/26.
 */

@Component
public class URLInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService service;

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
        DataUtil.getCurrent().setDb("");

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse response, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）调用,
     *  返回true 则放行， false 则将直接跳出方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        Map<String,String[]> rmap = request.getParameterMap();

        int port = request.getServerPort();
        DataUtil.getCurrent().setPort(port);
        String db = DynamicDataSourceConfig.anygo;
        if(port==80){
            db = DynamicDataSourceConfig.aetos_go;
        }
        DataUtil.getCurrent().setDb(db);


        JSONObject rmap_json = JSONObject.fromObject(rmap);
        String ip = getIpAddr(request);
        String path = request.getServletPath();
        System.out.println  (DateUtils.gettimestamp()+"  interceptor:: "+ip+" url::"+
                    request.getRequestURL().toString().replaceAll(request.getRequestURI().toString(),"")+
                    " uri::"+request.getRequestURI()+"  servletpath::"+path+" data::"+rmap_json);

        HandlerMethod handlerMethod=(HandlerMethod)object;

        //判断 类注解
        UrlSkip urlskip =handlerMethod.getBean().getClass().getAnnotation(UrlSkip.class);
        if(urlskip!=null){
            //维护接口信息
            //service.sync_interface(path);
            if(urlskip.skip()== Skip.NO){
                return true;
            }else if(urlskip.skip() == Skip.MAN){
                AppJson app = new AppJson();
                //游客端接口
                String access_token = request.getHeader("access_token");
                if(ConvertUtil.isEmpty(access_token)){
                    access_token = request.getParameter("access_token");
                }
                DataUtil.getCurrent().setAccess_token(access_token);
                String appcode = service.authVisitorToken(access_token,path);
                if(APPCode.SUCCESS.equals(appcode)){
                    return true;
                }else{
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
                }
                PrintWriter out = response.getWriter();
                JSONObject json = JSONObject.fromObject(app);
                out.write(json.toString());
                out.flush();
            }
            return false;
        }else{
            return  true;
        }
    }


    /**
     * 获取访问的ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
