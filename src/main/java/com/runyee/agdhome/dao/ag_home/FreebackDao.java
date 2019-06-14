package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.ex.AppFreebackBean;
import com.runyee.agdhome.entity.ex.AppManFreebackBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface FreebackDao {

    /**
     * 新增 意见反馈
     */
    @Insert("insert into ah_freeback( af_id, af_name, af_phone, af_email, af_title, af_content, del, create_date ) " +
            "   values( #{af_id}, #{af_name}, #{af_phone}, #{af_email}, #{af_title}, #{af_content}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="af_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertFreeback(AhFreeback freeback);

    /**
     * 新增 意见反馈详情
     */
    @Insert("insert into ah_freeback_detail( afd_id, afd_freeback, afd_content, afd_type, afd_url, del, create_date ) " +
            "   values( #{afd_id}, #{afd_freeback}, #{afd_content}, #{afd_type}, #{afd_url}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="afd_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertFreebackDetail(AhFreebackDetail detail);

    /**
     * 新增 意见反馈处理
     */
    @Insert("insert into ah_freeback_handle( afh_id, afh_freeback, afh_user, afh_status, afh_content, del, create_date ) " +
            "   values(  #{afh_id}, #{afh_freeback}, #{afh_user}, #{afh_status}, #{afh_content}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="afh_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertFreebackHandle(AhFreebackHandle contactHandle);

    /**
     *  删除 问题反馈 信息
     */
    @Update("update ah_freeback set  " +
            "   del= #{del} " +
            "   where af_id = #{af_id} ")
    void updateFreebackDel(AhFreeback freeback);

    /**
     * 取得 问题反馈
     */
    @Select("select * " +
            "   from ah_freeback af " +
            "   where af_id=#{id}  " +
            "   order by create_date desc limit 1 ")
    AhFreeback getFreebackOneKey(@Param("id") String id);

    @Update("update ah_freeback_handle set  " +
            "   afh_status= #{afh_status}, " +
            "   afh_content= #{afh_content}, " +
            "   afh_user= #{afh_user} " +
            "   where afh_id = #{afh_id} ")
    void updateFreebackHandle(AhFreebackHandle handle);

    @Select("select * " +
            "   from ah_freeback_handle afh " +
            "   where afh_freeback=#{freeback}  " +
            "   order by create_date desc limit 1 ")
    AhFreebackHandle getHandleOneFreeback(@Param("freeback") String freeback);

    @Select("select * " +
            "   from ah_freeback af " +
            "   where ifnull(del,0)=0 and af.af_phone = #{phone} " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit 5 ")
    List<AhFreeback> getFreebacksPhone(@Param("phone")String phone,
                                       @Param("filter")String filter);


    /**
     * 问题反馈 列表
     */
    @Select("select af.*, " +
            //处理进度
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' and (@results_name:='')=''" +
            "       and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=ifnull(afh_status,0), " +
            "                   @handle_user:=ifnull(afh_user,''), " +
            "                   @results_name:=ifnull(afh_content,''), " +
            "                   @date_handle:=afh.create_date) " +
            "               from ah_freeback_handle afh " +
            "               where  ifnull(afh.del,0)=0 and afh.afh_freeback = af.af_id  " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @results_name results_name, " +
            "   @date_handle date_handle " +

            "   from ah_freeback af" +
            "   where ifnull(del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<AppManFreebackBean> getManFreebackBeanList(@Param("filter") String filter,
                                                    @Param("order") String order,
                                                    @Param("page") int page,
                                                    @Param("rows") int rows);

    @Select("select * " +

            "   from ah_freeback " +
            "   where ifnull(del,0)=0 and  af_id = #{id}  " +
            "   limit 1  ")
    AppManFreebackBean getFreebackBeanOneKey(@Param("id") String id);

    /**
     * 总数
     */
    @Select("select count(1) " +
            "   from ah_freeback af " +
            "   where ifnull(del,0)=0  " +
            "   ${filter} ")
    int getManFreebackCount(@Param("filter") String filter);
}
