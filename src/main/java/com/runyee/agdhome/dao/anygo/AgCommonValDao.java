package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyCommonVal;
import com.runyee.agdhome.entity.db.anygo.RyGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AgCommonValDao {
    /**
     * 取得 游客信息
     */
    @Select("select * " +
            "   from ry_common_val  " +
            "   where ifnull(rcv_del,0)=0 and rcv_group =#{group} " +
            "   order by rcv_priority, create_date desc ")
    List<RyCommonVal> getCommons(@Param("group") String group);

    @Select("select * " +
            "   from ry_common_val " +
            "   where ifnull(rcv_del,0) = 0 " +
            "   and rcv_key=#{val_key} " +
            "   order by create_date desc limit 1 ")
    RyCommonVal getCommonValOneValKey(@Param("val_key") String val_key);


    /**
     * 根据条件取得common_val值
     */
    @Select("select * " +
            "   from ry_common_val rcv" +
            "   where ifnull(rcv.rcv_del,0)=0 " +
            "   and (rcv.rcv_group = 6" +
            "   or  rcv.rcv_group = 12 " +
            "   or  rcv.rcv_group = 13 ) ")
    List<RyCommonVal> getCommonValsByConditions();



}
