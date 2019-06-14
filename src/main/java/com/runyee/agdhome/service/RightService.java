package com.runyee.agdhome.service;

import com.runyee.agdhome.dao.ag_home.RightDao;
import com.runyee.agdhome.entity.db.ag_home.AhMenu;
import com.runyee.agdhome.entity.db.ag_home.AhRight;
import com.runyee.agdhome.util.ConvertUtil;
import com.runyee.agdhome.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class RightService {
    @Autowired
    private RightDao dao;

    public AhRight getRightMenuOne(AhMenu menu){
        AhRight right = null;
        right = dao.getRightMenuOne(menu.getAm_id());
        if(right==null|| ConvertUtil.isEmpty(right.getAr_id())){
            right = new AhRight();

            right.setAr_menu(menu.getAm_id());
            right.setAr_name(menu.getAm_name());
            right.setAr_opt(0);
            right.setDel(0);
            right.setCreate_date(DateUtils.getDate());

            dao.insertRight(right);
        }
        return right;
    }

}
