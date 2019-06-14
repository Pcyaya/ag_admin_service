package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyGroup;
import com.runyee.agdhome.entity.db.anygo.RyVisitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AgGroupDao {


    /**
     * 更新 团 变更旅行团
     * */
    @Update("update ry_group " +
            "   set rg_type =0, " +
            "   rg_intell_spk='' " +
            "   where rg_intell_spk=#{spk} ")
    void updateGroupTravelSpk(@Param("spk")String  spk);

    /**
     * 取得 游客信息
     */
    @Select("select * " +
            "   from ry_group " +
            "   where rg_num =#{rg_num} order by create_date desc limit 1 ")
    RyGroup getGroupOneNum(@Param("rg_num") String rg_num);

    /**
     * ID获取 游客信息
     */
    @Select("select * " +
            "   from ry_group " +
            "   where rg_id =#{rg_id} order by create_date desc limit 1")
    RyGroup getGroupOneId(@Param("rg_id") String rg_id);

    /**
     * 取得 导游 团
     */
    @Select("select rg.*  " +
            "   from ry_group rg " +
            "   where ifnull(rg_del,0)=0  " +
            "   and  rg.rg_intell_spk=#{spk} " +
            "   order by create_date desc ")
    List<RyGroup> getGroupSpeakerKey(@Param("spk") String spk );

    /**
     * 取得团 名称或者团编号
     */
    @Select("select * " +
            "   from ry_group rg " +
            "   where rg.rg_num in (${group_ids}) " +
            "   order by rg.create_date")
    List<RyGroup>  getGroupNameorNumByids(@Param("group_ids")String group_ids);

}
