package com.runyee.agdhome.service;

import com.runyee.agdhome.dao.anygo.AgNumGenDao;
import com.runyee.agdhome.entity.db.anygo.RyNumGen;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class NumGenService {
    @Autowired
    private AgNumGenDao dao;
    @Autowired
    private ExternalService externalService;

    public  synchronized String generateNum(String key,int lenght) {
        //优先访问编码生成服务器
        String num = externalService.generateNum(key,lenght);
        if(ConvertUtil.isEmpty(num)){
            //访问失败自动生成
            Date date = new Date();
            num = key +"_D"+ DateUtils.formatDate(date,DateUtils.yymmddhhmmss);
            RyNumGen numgen = dao.getNumGenOne(key);
            if(numgen==null || ConvertUtil.isEmpty(numgen.getRng_id())){
                numgen = new RyNumGen();
                numgen.setRng_key(key);
                numgen.setRng_num("0");
                numgen.setCreate_date(date);
                dao.insertNumGen(numgen);
            }
            if(numgen.getRng_num()==null){
                numgen.setRng_num("0");
            }
            num += String.format("%"+lenght+"d",ConvertUtil.convertStrToInt(numgen.getRng_num())).replace(" ", "0");
            int max_num = ConvertUtil.maxInt(lenght);
            int rng_num = ConvertUtil.convertStrToInt(numgen.getRng_num());
            if( rng_num >= max_num){
                dao.updateNumGenSub(numgen);
            }else{
                //更新
                dao.updateNumGen(numgen);
            }
        }
        return  num;
    }
}
