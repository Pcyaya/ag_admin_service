package com.runyee.agdhome.service;

import com.runyee.agdhome.constant.ServiceCode;
import com.runyee.agdhome.dao.anygo.AgCoinDao;
import com.runyee.agdhome.entity.db.anygo.RyCoin;
import com.runyee.agdhome.entity.db.anygo.RyCoinTransform;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class CoinService {
    @Autowired
    private AgCoinDao dao;


    RyCoin getCoinOne(String visitor){
        RyCoin  coin  = dao.getCoinOne(visitor);
        if(coin==null|| ConvertUtil.isEmpty(coin.getRc_id())){
            coin = new RyCoin();
            coin.setRc_visitor(visitor);
            coin.setRc_number(0);
            coin.setRc_del(0);
            coin.setCreate_date(DateUtils.getDate());
            dao.insertCoin(coin);
        }
        return coin;
    }
    /**
     * 游币变动
     * @param visitor 变动人id
     * @param business // 业务类型 参见通用值表 key:coin_task*
     * @param pid //父id
     * @param flg //0.加1.减
     * @param number 变动数量
     * @param order 业务id
     * @param item 业务详情id
     * */
    public String coin_transform(String visitor, int business,String pid,int flg,int number,String order,String item){
        String service_code = ServiceCode.SUCCESS;
        RyCoin  coin = this.getCoinOne(visitor);
        if(coin!=null&&!ConvertUtil.isEmpty(coin.getRc_id())&&number>0){
            Date date = DateUtils.getDate();
            RyCoinTransform transform = new RyCoinTransform();
            transform.setRct_coin(coin.getRc_id());
            transform.setRct_pid(pid);
            transform.setRct_order(order);
            transform.setRct_item(item);
            transform.setRct_visitor(visitor);
            transform.setRct_business(business);
            transform.setRct_flg(flg);
            transform.setRct_number(number);
            transform.setRct_del(0);
            transform.setCreate_date(date);
            //更改游币数量
            if(transform.getRct_flg()==1){//0.加1.减
                //判断游币
                if(coin.getRc_number()>=number){
                    number = -number;
                }else{
                    service_code = ServiceCode.fail_coin_unadequate;
                }
            }

            if(ServiceCode.SUCCESS.equals(service_code)){
                //添加变动记录
                dao.insertCoinTransForm(transform);

                dao.updateCoinNumber(coin.getRc_id(),number);
            }
        }else{
            service_code = ServiceCode.fail_param_error;//code_param_error;
        }
        return  service_code;
    }

    public List<RyCoinTransform> transforms_interior(String access_key, int business,String pid,int flg,String order,String item, Date date){
        List<RyCoinTransform> transforms = new ArrayList<>();
        if(!ConvertUtil.isEmpty(access_key)){
            String filter = " and rct_business = '"+business+"' and rct_flg = '"+flg+"'";
            if(!ConvertUtil.isEmpty(pid)){
                filter += "and rct_pid = '"+pid+"'";
            }
            if(!ConvertUtil.isEmpty(order)){
                filter += "and rct_order = '"+order+"'";
            }
            if(!ConvertUtil.isEmpty(item)){
                filter += "and rct_item = '"+item+"'";
            }
            if(date!=null){
                Date start = DateUtils.start_date(date);
                Date end = DateUtils.end_date(date);
                filter += " and create_date";
                filter+= "  and create_date >= '"+DateUtils.gettimestamp(start)+"' " +
                        "   and create_date <= '"+DateUtils.gettimestamp(end)+"'                       " ;
            }

            transforms = dao.getCoinTransforms(access_key,filter,0,30);
        }
        return transforms;
    }

    public int transforms_interior_count(String access_key, int business,String pid,int flg,String order,String item, Date date){
        int count = 0;
        if(!ConvertUtil.isEmpty(access_key)){
            String filter = " and rct_business = '"+business+"' and rct_flg = '"+flg+"'";
            if(!ConvertUtil.isEmpty(pid)){
                filter += "and rct_pid = '"+pid+"'";
            }
            if(!ConvertUtil.isEmpty(order)){
                filter += "and rct_order = '"+order+"'";
            }
            if(!ConvertUtil.isEmpty(item)){
                filter += "and rct_item = '"+item+"'";
            }

            if(date!=null){
                Date start = DateUtils.start_date(date);
                Date end = DateUtils.end_date(date);
                filter += " and create_date";
                filter+= "  and create_date >= '"+DateUtils.gettimestamp(start)+"' " +
                        "   and create_date <= '"+DateUtils.gettimestamp(end)+"'                       " ;
            }
            count = dao.getCoinTransformsCount(access_key,filter);
        }
        return count;
    }

}
