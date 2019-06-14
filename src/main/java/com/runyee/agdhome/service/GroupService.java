package com.runyee.agdhome.service;
import com.runyee.agdhome.dao.anygo.AgGroupDao;
import com.runyee.agdhome.entity.db.anygo.RyGroup;
import com.runyee.agdhome.entity.form.GroupInfoBean;
import com.runyee.agdhome.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by cheri on 2018/2/24.
 */
@Service
public class GroupService {
    @Autowired
    private AgGroupDao dao;



    /**
     * 团信息封装
     */
    public GroupInfoBean groupInfo(RyGroup group) {
        GroupInfoBean info = new GroupInfoBean();
        if (group != null && !ConvertUtil.isEmpty(group.getRg_id())) {
            info.setId(group.getRg_id());
            info.setNum(group.getRg_num());
            info.setIcon(group.getRg_icon());//头像
            info.setName(group.getRg_name());//昵称
            info.setGroup_flg(group.getRg_type());
            if (ConvertUtil.isEmpty(info.getName())) {
                info.setName(group.getRg_num());
            }
        }
        return info;
    }


}
