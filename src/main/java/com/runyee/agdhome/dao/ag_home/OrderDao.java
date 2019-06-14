package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhOrder;
import com.runyee.agdhome.entity.db.ag_home.AhOrderHandle;
import com.runyee.agdhome.entity.ex.OrderBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface OrderDao {

    /**
     * 新增 联系我们
     */
    @Insert("insert into ah_order( ao_id, ao_name, ao_phone, ao_addr, ao_content, ao_handle, del, create_date ) " +
            "   values(  #{ao_id}, #{ao_name}, #{ao_phone}, #{ao_addr}, #{ao_content}, #{ao_handle}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ao_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertOrder(AhOrder order);

    /**
     * 新增 订购处理
     * @param orderHandle
     */
    @Insert("insert into ah_order_handle( aoh_id, aoh_order, aoh_user, aoh_status, aoh_content, aoh_name, aoh_color, aoh_price, aoh_amount, aoh_total, aoh_paytype, aoh_payflat, aoh_trans, aoh_transno, del, create_date ) " +
            "   values(  #{aoh_id}, #{aoh_order}, #{aoh_user}, #{aoh_status}, #{aoh_content}, #{aoh_name}, #{aoh_color}, #{aoh_price}, #{aoh_amount}, #{aoh_total}, #{aoh_paytype}, #{aoh_payflat}, #{aoh_trans}, #{aoh_transno}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="aoh_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertAgentHandle(AhOrderHandle orderHandle);

    @Select("select * " +
            "   from ah_order ao " +
            "   where ifnull(del,0)=0 and ao.ao_phone = #{phone} " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit 5 ")
    List<AhOrder> getOrdersPhone(@Param("phone")String phone,
                                 @Param("filter")String filter);

    /**
     * 代理商 列表
     */
    @Select("select ao.*, " +
            "   if((@handle_schedule:=0)=0 " +
            "       and (@handle_user:='')='' " +
            "       and (@results_name:='')='' " +
            "       and (@order_name:='')='' " +
            "       and (@order_color:='')='' " +
            "       and (@order_price:=0)=0 " +
            "       and (@order_amount:=0)=0 " +
            "       and (@order_total:=0)=0 " +
            "       and (@order_paytype:='')='' " +
            "       and (@order_payflat:='')='' " +
            "       and (@order_trans:='')='' " +
            "       and (@order_transno:='')='' " +
            "       and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=ifnull(aoh_status,0), " +
            "                   @handle_user:=ifnull(aoh_user,''), " +
            "                   @order_name:=ifnull(aoh_name,''), " +
            "                   @order_color:=ifnull(aoh_color,''), " +
            "                   @order_price:=ifnull(aoh_price,0), " +
            "                   @order_amount:=ifnull(aoh_amount,0), " +
            "                   @order_total:=ifnull(aoh_total,0), " +
            "                   @order_paytype:=ifnull(aoh_paytype,''), " +
            "                   @order_payflat:=ifnull(aoh_payflat,''), " +
            "                   @order_trans:=ifnull(aoh_trans,''), " +
            "                   @order_transno:=ifnull(aoh_transno,''), " +
            "                   @date_handle:=aoh.create_date) " +
            "               from ah_order_handle aoh " +
            "               where  ifnull(aoh.del,0)=0 and aoh.aoh_order = ao.ao_id  " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @results_name results_name, " +
            "   @order_name order_name, " +
            "   @order_color order_color, " +
            "   @order_price order_price, " +
            "   @order_amount order_amount, " +
            "   @order_total order_total, " +
            "   @order_paytype order_paytype, " +
            "   @order_payflat order_payflat, " +
            "   @order_trans order_trans, " +
            "   @order_transno order_transno, " +
            "   @date_handle date_handle " +

            "   from ah_order ao" +
            "   where ifnull(del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<OrderBean> getOrderBeanList(@Param("filter") String filter,
                                               @Param("order") String order,
                                               @Param("page") int page,
                                               @Param("rows") int rows);

    @Select("select * " +
            "   from ah_order " +
            "   where ifnull(del,0)=0 and  ao_id = #{id}  " +
            "   limit 1  ")
    OrderBean getOrderBeanOneKey(@Param("id") String id);

    @Select("select * " +
            "   from ah_order " +
            "   where ao_id=#{id}  " +
            "   order by create_date desc limit 1 ")
    AhOrder getOrderOneKey(@Param("id") String id);

    @Update("update ah_order set  " +
            "   del= #{del} " +
            "   where ao_id = #{ao_id} ")
    void updateOrderDel(AhOrder order);


    @Select("select * " +
            "   from ah_order_handle aoh " +
            "   where aoh_order=#{order}  " +
            "   order by create_date desc limit 1 ")
    AhOrderHandle getHandleOrderOneKey(@Param("order") String order);

    /**
     * 总数
     */
    @Select("select count(1) " +
            "   from ah_order ao " +
            "   where ifnull(del,0)=0  " +
            "   ${filter} ")
    int getOrderCount(@Param("filter") String filter);

    @Update("update ah_order_handle set  " +
            "   aoh_status= #{aoh_status}, " +
            "   aoh_user= #{aoh_user}, " +
            "   aoh_content= #{aoh_content}, " +
            "   aoh_name= #{aoh_name}, " +
            "   aoh_color= #{aoh_color}, " +
            "   aoh_price= #{aoh_price}, " +
            "   aoh_amount= #{aoh_amount}, " +
            "   aoh_total= #{aoh_total}, " +
            "   aoh_paytype= #{aoh_paytype}, " +
            "   aoh_payflat= #{aoh_payflat}, " +
            "   aoh_trans= #{aoh_trans}, " +
            "   aoh_transno= #{aoh_transno} " +
            "   where aoh_id = #{aoh_id} ")
    void updateOrderHandle(AhOrderHandle handle);

    @Update("update ah_order set  " +
            "   ao_name= #{ao_name}, " +
            "   ao_addr= #{ao_addr}, " +
            "   ao_content= #{ao_content} " +
            "   where ao_id = #{ao_id} ")
    void updateOrderInfo(AhOrder order);
}
