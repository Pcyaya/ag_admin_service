package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.entity.AppJson;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.HttpClientUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class ExternalService {

    private static final String ry_pro = "http://www.runyee.com.cn/bsvice";
    private static final String  img_version = "weathercn0";
    public static final String  system_msg_api = "/anygo/common/system/msg";

    public String province(AppJson app){
        String service_code = ServiceCode.SUCCESS;
        String api = "/city/province";
        Map<String,String> paramters = new HashMap<>();
        byte[] bytes = HttpClientUtil.post(ry_pro+api,"",paramters);
        if(bytes!=null && bytes.length > 0){
            System.out.println(new String(bytes));
            JSONObject json = JSONObject.fromObject(new String(bytes));
            if(json!=null){
                if(ServiceCode.SUCCESS.equals(json.get("success"))){
                    Object obj = json.get("obj");
                    if(obj!=null){
                        app.setObj(obj);
                    }
                }else{
                    service_code = ServiceCode.ERRO;
                }
            }else {
                service_code = ServiceCode.ERRO;
            }
        }else{
            service_code = ServiceCode.ERRO;
        }
        return service_code;
    }

    public String lastlevel(int cityid,AppJson app){
        String service_code = ServiceCode.SUCCESS;
        String api = "/city/lastLevel";
        Map<String,String> paramters = new HashMap();//new HashedMap();
        paramters.put("cityid",cityid+"");
        byte[] bytes = HttpClientUtil.post(ry_pro+api,"",paramters);
        if(bytes!=null && bytes.length > 0){
            JSONObject json = JSONObject.fromObject(new String(bytes));
            if(json!=null){
                if(ServiceCode.SUCCESS.equals(json.get("success"))){
                    Object obj = json.get("obj");
                    if(obj!=null){
                        app.setObj(obj);
                    }
                }else{
                    service_code = ServiceCode.ERRO;
                }
            }else {
                service_code = ServiceCode.ERRO;
            }
        }else{
            service_code = ServiceCode.ERRO;
        }
        return service_code;
    }

    public String citySearch(String search,AppJson app){
        String service_code = ServiceCode.SUCCESS;
        String api = "/city/search";
        Map<String,String> paramters = new HashMap();//new HashedMap();
        paramters.put("search",search);
        byte[] bytes = HttpClientUtil.post(ry_pro+api,"",paramters);
        if(bytes!=null && bytes.length > 0){
            JSONObject json = JSONObject.fromObject(new String(bytes));
            if(json!=null){
                if(ServiceCode.SUCCESS.equals(json.get("success"))){
                    Object obj = json.get("obj");
                    if(obj!=null){
                        app.setObj(obj);
                    }
                }else{
                    service_code = ServiceCode.ERRO;
                }
            }else {
                service_code = ServiceCode.ERRO;
            }
        }else{
            service_code = ServiceCode.ERRO;
        }
        return service_code;
    }

    public static String app_api(String url,String api,Map<String,String> params,AppJson app){
        String service_code = ServiceCode.SUCCESS;
        if(!ConvertUtil.isEmpty(url)&&!ConvertUtil.isEmpty(api)){
            byte[] bytes = HttpClientUtil.post(url+api,"",params);
            if(bytes!=null && bytes.length > 0){
                JSONObject json = JSONObject.fromObject(new String(bytes));
                if(json!=null){
                    if(ServiceCode.SUCCESS.equals(json.get("success"))){

                        app.setObj(json.get("msg"));
                    }else{
                        service_code = ServiceCode.ERRO;
                    }
                }else {
                    service_code = ServiceCode.ERRO;
                }
            }else{
                service_code = ServiceCode.ERRO;
            }
        }
        return service_code;
    }

    public String generateNum(String key,int lenght){
        String num = "";
        String api = "/generate/num";
        Map<String,String> paramters = new HashMap();//new HashedMap();
        paramters.put("key",key);
        paramters.put("lenght",lenght+"");
        byte[] bytes = HttpClientUtil.post(ry_pro+api,"",paramters);
        if(bytes!=null && bytes.length > 0){
            JSONObject json = JSONObject.fromObject(new String(bytes));
            if(json!=null){
                if(ServiceCode.SUCCESS.equals(json.get("success"))){
                    Object obj = json.get("obj");
                    if(obj!=null){
                        num = ConvertUtil.objToString(obj);
                    }
                }
            }
        }
        return num;
    }


    public static Object exurl_post(String url,String api,Map<String,String> params,Class beanClass){
        Object result = null;
        if(!ConvertUtil.isEmpty(url)&&!ConvertUtil.isEmpty(api)){
            byte[] bytes = HttpClientUtil.post(url+api,params);
            if(bytes!=null && bytes.length > 0){
                JSONObject json = JSONObject.fromObject(new String(bytes));
                if(json!=null){
                    result = JSONObject.toBean(json,beanClass);
                }
            }
        }
        return result;
    }

}
