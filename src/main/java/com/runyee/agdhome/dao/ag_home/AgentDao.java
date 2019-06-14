package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhAgentApply;
import com.runyee.agdhome.entity.db.ag_home.AhAgentApplyHandle;
import com.runyee.agdhome.entity.db.ag_home.AhFreebackHandle;
import com.runyee.agdhome.entity.ex.ManAhAgentBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface AgentDao {

    /**
     * 新增 代理申请
     */
    @Insert("insert into ah_agent_apply(  aaa_id, aaa_name, aaa_phone, aaa_content, aaa_addr, aaa_province, aaa_provinceid, aaa_city, aaa_cityid, aaa_area, aaa_areaid, del, create_date ) " +
            "   values(  #{aaa_id}, #{aaa_name}, #{aaa_phone}, #{aaa_content}, #{aaa_addr}, #{aaa_province}, #{aaa_provinceid}, #{aaa_city}, #{aaa_cityid}, #{aaa_area}, #{aaa_areaid}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="aaa_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertAgentApply(AhAgentApply apply);


    /**
     * 新增 代理申请处理
     * @param contactHandle
     */
    @Insert("insert into ah_apply_handle( aah_id, aah_apply, aah_user, aah_status, aah_content, del, create_date ) " +
            "   values(  #{aah_id}, #{aah_apply}, #{aah_user}, #{aah_status}, #{aah_content}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="aah_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertAgentHandle(AhAgentApplyHandle contactHandle);

    @Select("select * " +
            "   from ah_agent_apply aaa " +
            "   where ifnull(del,0)=0 and aaa.aaa_phone = #{phone} " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit 5 ")
    List<AhAgentApply> getAgentApplysPhone( @Param("phone")String phone,
                                            @Param("filter")String filter );

    /**
     * 代理商 列表
     */
    @Select("select aaa.*, " +
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' and (@results_name:='')=''" +
            "       and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=ifnull(aah_status,0), " +
            "                   @handle_user:=ifnull(aah_user,''), " +
            "                   @results_name:=ifnull(aah_content,''), " +
            "                   @date_handle:=aah.create_date) " +
            "               from ah_apply_handle aah " +
            "               where  ifnull(aah.del,0)=0 and aah.aah_apply = aaa.aaa_id  " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @results_name results_name, " +
            "   @date_handle date_handle " +

            "   from ah_agent_apply aaa" +
            "   where ifnull(del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<ManAhAgentBean> getManAhAgentBeanList(@Param("filter") String filter,
                                                @Param("order") String order,
                                                @Param("page") int page,
                                                @Param("rows") int rows);

    @Select("select * " +
            "   from ah_agent_apply " +
            "   where ifnull(del,0)=0 and  aaa_id = #{id}  " +
            "   limit 1  ")
    ManAhAgentBean getAhAgentBeanOneKey(@Param("id") String id);

    /**
     * 总数
     */
    @Select("select count(1) " +
            "   from ah_agent_apply aaa " +
            "   where ifnull(del,0)=0  " +
            "   ${filter} ")
    int getManAhAgentCount(@Param("filter") String filter);

    @Select("select * " +
            "   from ah_agent_apply aaa " +
            "   where aaa_id=#{id}  " +
            "   order by create_date desc limit 1 ")
    AhAgentApply getAhAgentOneKey(@Param("id") String id);

    @Update("update ah_agent_apply set  " +
            "   del= #{del} " +
            "   where aaa_id = #{aaa_id} ")
    void updateFreebackDel(AhAgentApply agent);

    @Select("select * " +
            "   from ah_agent_apply " +
            "   where ifnull(del,0)=0 and  aaa_id = #{id}  " +
            "   limit 1  ")
    ManAhAgentBean getAgentBeanOneKey(@Param("id") String id);

    @Select("select * " +
            "   from ah_apply_handle aah " +
            "   where aah_apply=#{agent}  " +
            "   order by create_date desc limit 1 ")
    AhAgentApplyHandle getHandleOneAgent(@Param("agent") String agent);

    @Update("update ah_apply_handle set  " +
            "   aah_status= #{aah_status}, " +
            "   aah_content= #{aah_content}, " +
            "   aah_user= #{aah_user} " +
            "   where aah_id = #{aah_id} ")
    void updateAgentHandle(AhAgentApplyHandle handle);


}
