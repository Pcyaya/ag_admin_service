package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyCommodity;
import com.runyee.agdhome.entity.db.anygo.RyCommodityTmp;
import com.runyee.agdhome.entity.ex.CommodityTmpBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgCommodityDao {

    /**
     * 新增 商品模板
     */
    @Insert("insert into ry_commodity_tmp( rct_id, rct_num, rct_name, rct_icon, rct_hot, rct_recommend, rct_business, rct_rel, rct_vprice, rct_rprice, rct_coin, rct_online, rct_ondate, rct_underdate, rct_limit, rct_sell, rct_del, create_date ) " +
            "                   values( #{rct_id}, #{rct_num}, #{rct_name}, #{rct_icon}, #{rct_hot}, #{rct_recommend}, #{rct_business}, #{rct_rel}, #{rct_vprice}, #{rct_rprice}, #{rct_coin}, #{rct_online}, #{rct_ondate}, #{rct_underdate}, #{rct_limit}, #{rct_sell}, #{rct_del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="rct_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertCommodityTmp(RyCommodityTmp tmp);

    /**
     * 新增 商品
     */
    @Insert("insert into ry_commodity( rc_id, rc_tmp, rc_name, rc_icon, rc_hot, rc_recommend, rc_business, rc_rel, rc_vprice, rc_rprice, rc_coin, rc_calculate, rc_online, rc_del, create_date ) " +
            "                   values( #{rc_id}, #{rc_tmp}, #{rc_name}, #{rc_icon}, #{rc_hot}, #{rc_recommend}, #{rc_business}, #{rc_rel}, #{rc_vprice}, #{rc_rprice}, #{rc_coin}, #{rc_calculate}, #{rc_online}, #{rc_del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="rc_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertCommodity(RyCommodity commodity);


    /**
     *  删除 商品模板
     */
    @Update("update ry_commodity_tmp set  " +
            "   rct_del= #{rct_del} " +
            "   where rct_id = #{rct_id} ")
    void updateCommodityTmpDel(RyCommodityTmp tmp);

    /**
     *  模板下 所有商品上下线
     */
    @Update("update ry_commodity set  " +
            "   rc_online = #{online} " +
            "   where rc_tmp = #{tmp} and  ifnull(rc_online,0)<>#{online} ")
    void updateCommodityOnline(@Param("tmp")String tmp,
                               @Param("online")int online);

    /**
     *  更新 商品模板
     */
    @Update("update ry_commodity_tmp set  " +
            "   rct_name= #{rct_name}, " +
            "   rct_icon= #{rct_icon}, " +
            "   rct_hot= #{rct_hot}, " +
            "   rct_recommend= #{rct_recommend}, " +
            "   rct_business= #{rct_business}, " +
            "   rct_rel= #{rct_rel}, " +
            "   rct_vprice= #{rct_vprice}, " +
            "   rct_rprice= #{rct_rprice}, " +
            "   rct_coin= #{rct_coin}, " +
            "   rct_limit= #{rct_limit} " +
            "   where rct_id = #{rct_id} ")
    void updateCommodityTmp(RyCommodityTmp tmp);



    /**
     *  更新 商品模板
     */
    @Update("update ry_commodity_tmp set  " +
            "   rct_online= #{rct_online}, " +
            "   rct_ondate= #{rct_ondate}, " +
            "   rct_underdate= #{rct_underdate} " +
            "   where rct_id = #{rct_id} ")
    void updateCommodityTmpOnline(RyCommodityTmp tmp);


    /**
     * 商品模板 列表
     */
    @Select("select rct.* " +
            "   from ry_commodity_tmp rct   " +
            "   where ifnull(rct_del,0)=0   " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<CommodityTmpBean> getCommodityTmpBeanList(@Param("filter") String filter,
                                                   @Param("order") String order,
                                                   @Param("page") int page,
                                                   @Param("rows") int rows);

    /**
     * 商品模板 列表
     */
    @Select("select count(1) " +
            "   from ry_commodity_tmp rct   " +
            "   where ifnull(rct_del,0)=0  " +
            "   ${filter} ")
   int getCommodityTmpBeanCount(@Param("filter") String filter);


    /**
     * 商品模板 one
     */
    @Select("select * " +
            "   from ry_commodity_tmp rct   " +
            "   where rct_id=#{id} " +
            "   order by create_date desc limit 1  ")
    RyCommodityTmp getCommodityTmpOneKey(@Param("id") String id);

    /**
     * 商品模板 列表
     */
    @Select("select * " +
            "   from ry_commodity_tmp rct   " +
            "   where rct_id=#{id} " +
            "   order by create_date desc limit 1 ")
    CommodityTmpBean getCommodityTmpBeanOneKey(@Param("id") String id);



}
