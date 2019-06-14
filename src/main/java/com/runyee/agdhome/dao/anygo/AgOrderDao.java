package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyOrder;
import com.runyee.agdhome.entity.ex.AppOrderBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgOrderDao {

    /**
     * 新增 app 订单
     */
    @Insert("insert into ry_order( ro_id, ro_num, ro_visitor, ro_status, ro_price, ro_coin, ro_payprice, ro_paycoin, ro_del, create_date ) " +
            "   values( #{ro_id}, #{ro_num}, #{ro_visitor}, #{ro_status}, #{ro_price}, #{ro_coin}, #{ro_payprice}, #{ro_paycoin}, #{ro_del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ro_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertOrder(RyOrder order);

    /**
     * 订单 列表
     */
    @Select("select ro.*, " +
            "   if((@name:='')='' " +
            "       and (@phone:='')='' " +
            "       and (select concat(@name:=ifnull(rv_name,''), " +
        "                           @phone:=ifnull(rv_phone,'') )" +
            "               from ry_visitor rv " +
            "               where  ifnull(rv.rv_del,0)=0 and ro.ro_visitor = rv.rv_id  " +
            "               order by create_date desc limit 1 ) is not null,@name,'') name, " +
            "   @phone phone " +
            "   from ry_order ro   " +
            "   where ifnull(ro_del,0)=0   " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<AppOrderBean> getOrderBeanList(@Param("filter") String filter,
                                        @Param("order") String order,
                                        @Param("page") int page,
                                        @Param("rows") int rows);

    /**
     * 订单
     */
    @Select("select count(1) " +
            "   from ry_order ro   " +
            "   where ifnull(ro_del,0)=0  " +
            "   ${filter} ")
    int getOrderBeanCount(@Param("filter") String filter);




}
