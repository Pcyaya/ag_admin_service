package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyCouponTmp;
import com.runyee.agdhome.entity.db.anygo.RyFreeback;
import com.runyee.agdhome.entity.db.anygo.RyFreebackDetail;
import com.runyee.agdhome.entity.db.anygo.RyFreebackHandle;
import com.runyee.agdhome.entity.ex.AppFreebackBean;
import com.runyee.agdhome.entity.ex.AppFreebackInfoBean;
import com.runyee.agdhome.entity.ex.CouponTmpBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgCouponDao {

    /**
     * 新增 优惠券模板
     */
    @Insert("insert into ry_coupon_tmp( rct_id, rct_den, rct_unit, rct_type, rct_inteval, rct_start, rct_end, rct_backup, rct_del, create_date ) " +
            "                   values( #{rct_id}, #{rct_den}, #{rct_unit}, #{rct_type}, #{rct_inteval}, #{rct_start}, #{rct_end}, #{rct_backup}, #{rct_del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="rct_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertCouponTmp(RyCouponTmp tmp);


    /**
     *  删除 优惠券模板
     */
    @Update("update ry_coupon_tmp set  " +
            "   rct_del= #{rct_del} " +
            "   where rct_id = #{rct_id} ")
    void updateCouponTmpDel(RyCouponTmp tmp);

    /**
     *  删除 优惠券模板
     */
    @Update("update ry_coupon_tmp set  " +
            "   rct_den= #{rct_den}, " +
            "   rct_unit= #{rct_unit}, " +
            "   rct_type= #{rct_type}, " +
            "   rct_inteval= #{rct_inteval}, " +
            "   rct_start= #{rct_start}, " +
            "   rct_end= #{rct_end} " +
            "   where rct_id = #{rct_id} ")
    void updateCouponTmp(RyCouponTmp tmp);


    /**
     * 优惠券模板 列表
     */
    @Select("select rct.* " +
            "   from ry_coupon_tmp rct   " +
            "   where ifnull(rct_del,0)=0  and ifnull(rct_backup,'')='' " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<CouponTmpBean> getCouponTmpBeanList(@Param("filter") String filter,
                                             @Param("order") String order,
                                             @Param("page") int page,
                                             @Param("rows") int rows);

    /**
     * 优惠券模板 列表
     */
    @Select("select count(1) " +
            "   from ry_coupon_tmp rct   " +
            "   where ifnull(rct_del,0)=0  " +
            "   ${filter} ")
   int getCouponTmpBeanCount(@Param("filter") String filter);


    /**
     * 优惠券模板 列表
     */
    @Select("select * " +
            "   from ry_coupon_tmp rct   " +
            "   where rct_id=#{id} " +
            "   order by create_date desc limit 1  ")
    RyCouponTmp getCouponTmpOneKey(@Param("id") String id);

    /**
     * 优惠券模板 列表
     */
    @Select("select rct.* " +
            "   from ry_coupon_tmp rct   " +
            "   where rct_id in(${ids}) " +
            "   order by create_date desc ")
    List<RyCouponTmp> getCouponTmpsKeys(@Param("ids") String ids);



}
