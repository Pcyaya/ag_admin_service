package com.runyee.agdhome.dao.anygo;
import com.runyee.agdhome.entity.db.anygo.RyCoin;
import com.runyee.agdhome.entity.db.anygo.RyCoinTransform;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgCoinDao {
    /**
     * 新增 游币
     */
    @Insert("insert into ry_coin(  rc_id, rc_visitor, rc_number, rc_del, create_date ) " +
            "   values(  #{rc_id}, #{rc_visitor}, #{rc_number}, #{rc_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rvc_id")
    @SelectKey(before=true,keyProperty="rc_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertCoin(RyCoin coin);

    /**
     * 新增 游币变动记录
     */
    @Insert("insert into ry_coin_transform( rct_id, rct_coin, rct_pid, rct_order, rct_item, rct_visitor, rct_business, rct_flg, rct_number, rct_del, create_date ) " +
            "   values(  #{rct_id}, #{rct_coin}, #{rct_pid}, #{rct_order}, #{rct_item}, #{rct_visitor}, #{rct_business}, #{rct_flg}, #{rct_number}, #{rct_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rvc_id")
    @SelectKey(before=true,keyProperty="rct_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertCoinTransForm(RyCoinTransform transform);



    /**
     * 更新 游币
     */
    @Update("update ry_coin set  " +
            "   rc_number=(ifnull(rc_number,0)+${number}) " +
            "   where rc_id = #{id} ")
    void updateCoinNumber(@Param("id")String id,
                          @Param("number") int number);



    /**
     * 取得 游客游币
     */
    @Select("select * " +
            "   from ry_coin rc  " +
            "   where ifnull(rc.rc_del,0) = 0 " +
            "   and ifnull(rc.rc_visitor,'') =#{visitor}   " +
            "   order by create_date desc " +
            "   limit 1 ")
    RyCoin getCoinOne(@Param("visitor") String visitor);


    /**
     * 取得 游客游币变化记录
     */
    @Select("select rct.* " +
            "   from ry_coin_transform rct  " +
            "   where ifnull(rct.rct_del,0) = 0 " +
            "   and ifnull(rct.rct_visitor,'') =#{visitor}  " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit #{page},#{rows}")
    List<RyCoinTransform> getCoinTransforms(@Param("visitor") String visitor,
                                            @Param("filter") String filter,
                                            @Param("page") int page,
                                            @Param("rows") int rows);


    /**
     * 取得 游客游币变化记录
     */
    @Select("select count(1) " +
            "   from ry_coin_transform rct  " +
            "   where ifnull(rct.rct_del,0) = 0 " +
            "   and ifnull(rct.rct_visitor,'') =#{visitor} " +
            "   ${filter} ")
    int getCoinTransformsCount(@Param("visitor") String visitor,
                               @Param("filter") String filter);


}
