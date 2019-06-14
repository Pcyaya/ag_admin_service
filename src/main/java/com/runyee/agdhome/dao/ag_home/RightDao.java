package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhNews;
import com.runyee.agdhome.entity.db.ag_home.AhOrder;
import com.runyee.agdhome.entity.db.ag_home.AhRight;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface RightDao {


    /**
     * 新增 权限
     */
    @Insert("insert into ah_right( ar_id, ar_name, ar_menu, ar_opt, del, create_date ) " +
            "   values( #{ar_id}, #{ar_name}, #{ar_menu}, #{ar_opt}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ar_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertRight(AhRight right);

    /**
     *  更新 权限 删除
     */
    @Update("update ah_right set  " +
            "   del= #{del} " +
            "   where ar_id = #{ar_id} ")
    void updateRightDel(AhRight right);


    /**
     * 获取 单个用户 权限
     */
    @Select("select ar.* " +
            "       from ah_right ar " +
            "       where ifnull(ar.del,0)=0 and ifnull(ar_opt,0)=#{opt} " +
            "       and ar.ar_id in (select adr.adr_right " +
            "                           from ah_depart_right adr " +
            "                           left join ah_depart ad on ad.ad_id = adr_depart " +
            "                           left join ah_user_depart aud on aud.aud_depart=adr.adr_depart " +
            "                           where ifnull(adr.del ,0)=0 and ifnull(ad.del,0)=0  and ifnull(aud.del ,0)=0 " +
            "                           and aud.aud_user = #{user} " +
            "                        ) ")
    List<AhRight> getRightsUser(@Param("user") String user,
                                @Param("opt") int opt);


    /**
     * 获取 单个用户权限的所有
     */
    @Select("select ar.* " +
            "       from ah_right ar " +
            "       where ifnull(ar.del,0)=0  " +
            "       and ar.ar_id in (select adr.adr_right " +
            "                           from ah_depart_right adr " +
            "                           left join ah_user_depart aud on aud.aud_depart=adr.adr_depart " +
            "                           where ifnull(adr.del ,0)=0  and ifnull(aud.del ,0)=0 " +
            "                           and aud.aud_user = #{user} " +
            "                        ) ")
    List<AhRight> getRightsUserAll(@Param("user") String user);


    /**
     * 获取 单个用户的菜单权限
     */
    @Select("select ar.* " +
            "       from ah_right ar " +
            "       where ifnull(ar.del,0)=0  and ifnull(ar_opt,0)=0 and ar_menu=#{menu}  " +
            "       and ar.ar_id in (select adr.adr_right " +
            "                           from ah_depart_right adr " +
            "                           left join ah_user_depart aud on aud.aud_depart=adr.adr_depart " +
            "                           where ifnull(adr.del ,0)=0  and ifnull(aud.del ,0)=0 " +
            "                           and aud.aud_user = #{user} " +
            "                        ) " +
            "       order by create_date desc limit 1 ")
    AhRight getRightUserMenuOne(@Param("user") String user,
                                @Param("menu") String  menu);

    /**
     * 获取 单个用户的菜单权限
     */
    @Select("select ar.* " +
            "       from ah_right ar " +
            "       where ifnull(ar_opt,0)=0 and ar_menu=#{menu}  " +
            "       order by create_date desc  limit 1 ")
    AhRight getRightMenuOne(@Param("menu") String  menu);



}
