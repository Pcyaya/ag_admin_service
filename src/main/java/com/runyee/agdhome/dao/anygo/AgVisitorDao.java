package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.anygo.RyUseHelpCategory;
import com.runyee.agdhome.entity.db.anygo.RyVisitor;
import com.runyee.agdhome.entity.ex.VisitorBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgVisitorDao {

    /**
     * 新增 游客
     */
    @Insert("insert into ry_visitor(rv_id,  rv_name, rv_num, rv_passwd, rv_phone, rv_test, rv_source, rv_plat, rv_code, rv_invite, rv_del, create_by, create_date, update_by ) " +
            "   values(#{rv_id},  #{rv_name}, #{rv_num}, #{rv_passwd}, #{rv_phone}, #{rv_test}, #{rv_source}, #{rv_plat}, #{rv_code}, #{rv_invite}, #{rv_del}, #{create_by}, #{create_date}, #{update_by} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rv_id")
    @SelectKey(before=true,keyProperty="rv_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertVisitor(RyVisitor visitor);



    /**
     * 更新 游客 冻结
     */
    @Update("update ry_visitor set  " +
            "   rv_freeze=#{rv_freeze}  " +
            "   where rv_id = #{rv_id} ")
    void updateVisitorFreeze(RyVisitor visitor);

    /**
     * 取得 游客信息
     */
    @Select("select * " +
            "   from ry_visitor " +
            "   where rv_id =#{id} " +
            "   order by create_date desc limit 1 ")
    RyVisitor getVisitorOneKey(@Param("id") String id);

    /**
     * 取得 游客信息
     */
    @Select("select * " +
            "   from ry_visitor " +
            "   where rv_num =#{rv_num} " +
            "   order by create_date desc limit 1 ")
    RyVisitor getVisitorOneNum(@Param("rv_num") String rv_num);

    /**
     * 取得 游客信息
     */
    @Select("select * " +
            "   from ry_visitor " +
            "   where ifnull(rv_del,0)=0 and rv_phone =#{phone} " +
            "   order by create_date desc limit 1 ")
    RyVisitor getVisitorOnePhoneDel(@Param("phone") String phone);



    @Select("select * " +
            "   from ry_visitor rv " +
            "   where ifnull(rv_del,0)=0  " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit #{page},#{rows} ")
    List<RyVisitor> getVisitors(@Param("filter") String filter,
                                @Param("page") int page,
                                @Param("rows") int rows);

    @Select("select * " +
            "   from ry_visitor rv  " +
            "   where ifnull(rv_del,0)=0  and rv.rv_id in (${ids}) ")
    List<RyVisitor> getVisitorsKeys(@Param("ids") String ids);


    @Select("select rv.*, " +
            //注册来源
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 18 " +
            "               and ifnull(rcv.rcv_val,'')= rv.rv_source " +
            "               order by create_date desc limit 1 ),'') source_name, " +
            //认证状态
            "   if( (@valid_status:=0)=0 " +
            "       and (select ifnull((select " +
            "                               @valid_status:=ifnull(rgvh.rgvh_status,0) " +
            "                               from ry_guide_valid_handle rgvh " +
            "                               where ifnull(rgvh.rgvh_del,0)=0 and rgvh.rgvh_valid = rgv.rgv_id " +
            "                               order by create_date desc limit 1 )," +
            "                           @valid_status:=-1) " +
            "                from ry_guide_valid rgv " +
            "                where ifnull(rgv.rgv_del,0)=0 " +
            "                and rgv.rgv_id = (select rgv1.rgv_id " +
            "                                       from ry_guide_valid rgv1 " +
            "                                       where ifnull(rgv1.rgv_del,0)=0 and rgv1.rgv_visitor = rv.rv_id " +
            "                                       order by create_date limit 1 ) " +
            "                order by create_date desc limit 1 )is not null, " +
            "       @valid_status, " +
            "       @valid_status:=-2) valid_status , " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 19 " +
            "               and ifnull(rcv.rcv_val,'')= @valid_status " +
            "               order by create_date desc limit 1 ),'') status_name, " +
            //游币
            "   ifnull((select rc.rc_number " +
            "               from ry_coin rc " +
            "               where ifnull(rc.rc_del,0)=0 and rc.rc_visitor = rv.rv_id " +
            "               order by create_date desc limit 1),0) coin_amount, " +
            "   ifnull((select sum(ifnull(rct.rct_number,0)) " +
            "               from ry_coin_transform rct " +
            "               where ifnull(rct.rct_del,0)=0 and ifnull(rct.rct_flg,0)=1 and rct.rct_visitor=rv.rv_id ),0) convert_amount " +
            "   from ry_visitor rv " +
            "   where ifnull(rv_del,0)=0  " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit #{page},#{rows} ")
    List<VisitorBean> getVisitorBeans(@Param("filter") String filter,
                                      @Param("page") int page,
                                      @Param("rows") int rows);

    @Select("select count(1) " +
            "   from ry_visitor rv " +
            "   where ifnull(rv_del,0)=0  " +
            "   ${filter} ")
    int getVisitorBeanCount(@Param("filter") String filter);

    /**
     * 条件搜索visitor
     * @param filter 过滤条件
     * @return 返回List
     */

    @Select("select * " +
            "   from ry_visitor rv " +
            "   where 1 = 1" +
            "   ${filter}")
    List<VisitorBean> getVisitorBeansByCondition(@Param("filter")String filter);

    /**
     * 取得用户信息
     * @param ids ids
     * @return List
     */
    @Select("select * " +
            "   from ry_visitor rv  " +
            "   where rv.rv_num in (${ids}) " +
            "   order by create_date")
    List<RyVisitor> getVisitorsByKeys(@Param("ids") String ids);

}
