package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyFreeback;
import com.runyee.agdhome.entity.db.anygo.RyFreebackDetail;
import com.runyee.agdhome.entity.db.anygo.RyFreebackHandle;
import com.runyee.agdhome.entity.ex.AppFreebackBean;
import com.runyee.agdhome.entity.ex.AppFreebackInfoBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgFreebackDao {



    /**
     * 新增 freeback handle
     */
    @Insert("insert into ry_freeback_handle( rfh_id, rfh_freeback, rfh_user, rfh_status, rfh_content, rfh_del, create_date ) " +
            "                   values( #{rfh_id}, #{rfh_freeback}, #{rfh_user}, #{rfh_status}, #{rfh_content}, #{rfh_del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="rfh_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertFreebackHandle(RyFreebackHandle handle);


    /**
     *  删除 问题反馈 信息
     */
    @Update("update ry_freeback set  " +
            "   rf_del= #{rf_del} " +
            "   where rf_id = #{rf_id} ")
    void updateFreebackDel(RyFreeback freeback);

    /**
     *  删除 问题反馈 信息
     */
    @Update("update ry_freeback_handle set  " +
            "   rfh_status= #{rfh_status}, " +
            "   rfh_content= #{rfh_content}, " +
            "   rfh_user= #{rfh_user} " +
            "   where rfh_id = #{rfh_id} ")
    void updateFreebackHandle(RyFreebackHandle handle);

    /**
     * 问题反馈 列表
     */
    @Select("select rf.*," +
            //反馈人信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 " +
            "       and (select concat(@name:=ifnull(rv_name,rv_num)," +
            "                          @phone:=ifnull(rv_phone,''), " +
            "                          @sex:=ifnull(rv_sex,0)) " +
            "                   from ry_visitor rv " +
            "                   where ifnull(rv_del,0)=0 and rv.rv_id = rf.rf_visitor " +
            "                   order by  create_date desc limit 1 ) is not null,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            //标签
            "   (select group_concat(rcv.rcv_name )" +
            "           from ry_freeback_tag rft " +
            "           left join ry_common_val rcv on rcv.rcv_id = rft.rft_val " +
            "           where ifnull(rft.rft_del,0)=0 and ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 2 " +
            "           and rft.rft_freeback = rf.rf_id )tags,    " +
            //处理进度
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' " +
            "       and (@handle_results:='')='' and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=ifnull(rfh_status,0), " +
            "                         @handle_user:=ifnull(rfh_user,''), " +
            "                         @handle_results:=ifnull(rfh_content,''), " +
            "                         @date_handle:=rfh.create_date) " +
            "                     from ry_freeback_handle rfh " +
            "                     where  ifnull(rfh.rfh_del,0)=0 and rfh.rfh_freeback = rf.rf_id  " +
            "                     order by create_date desc limit 1 ) is not null , @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @handle_results handle_results, " +
            "   @date_handle date_handle, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 15 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name " +

            "   from ry_freeback rf  " +
            "   where ifnull(rf_del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<AppFreebackBean> getFreebackBeanList(@Param("filter") String filter,
                                              @Param("order") String order,
                                              @Param("page") int page,
                                              @Param("rows") int rows);




    /**
     * 总数
     */
    @Select("select count(1) " +
            "   from ry_freeback rf " +
            "   where ifnull(rf_del,0)=0  " +
            "   ${filter} ")
    int getFreebackCount(@Param("filter") String filter);


    /**
     * 取得 问题反馈
     */
    @Select("select * " +
            "   from ry_freeback rf " +
            "   where rf_id=#{id}  " +
            "   order by create_date desc limit 1 ")
    RyFreeback getFreebackOneKey(@Param("id") String id);


    /**
     * APP反馈 列表
     */
    @Select("select rf.*," +
            //反馈人信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 " +
            "       and (select concat(@name:=ifnull(rv_name,rv_num)," +
            "                       @phone:=ifnull(rv_phone,''), " +
            "                       @sex:=ifnull(rv_sex,0)) " +
            "                   from ry_visitor rv " +
            "                   where ifnull(rv_del,0)=0 and rv.rv_id = rf.rf_visitor " +
            "                   order by  create_date desc limit 1 ) is not null ,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            //标签
            "   (select group_concat(rcv.rcv_name )" +
            "           from ry_freeback_tag rft " +
            "           left join ry_common_val rcv on rcv.rcv_id = rft.rft_val " +
            "           where ifnull(rft.rft_del,0)=0 and ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 2 " +
            "           and rft.rft_freeback = rf.rf_id )tags,    " +
            //处理进度
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' " +
            "       and (@handle_results:='')='' and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=ifnull(rfh_status,0), " +
            "                         @handle_user:=ifnull(rfh_user,''), " +
            "                         @handle_results:=ifnull(rfh_content,''), " +
            "                         @date_handle:=rfh.create_date) " +
            "                     from ry_freeback_handle rfh " +
            "                     where  ifnull(rfh.rfh_del,0)=0 and rfh.rfh_freeback = rf.rf_id  " +
            "                     order by create_date desc limit 1 )is not null , @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @handle_results handle_results, " +
            "   @date_handle date_handle, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 15 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name " +

            "   from ry_freeback rf  " +
            "   where ifnull(rf_id,0)=#{id}  " +
            "   order by create_date desc limit 1  ")
    AppFreebackInfoBean getFreebackBeanOneKey(@Param("id") String id);

    /**
     * 取得 问题反馈 处理
     */
    @Select("select * " +
            "   from ry_freeback_handle rfh " +
            "   where rfh_freeback=#{freeback}  " +
            "   order by create_date desc limit 1 ")
    RyFreebackHandle getHandleOneFreeback(@Param("freeback") String freeback);

    /**
     * 取得 问题反馈 处理
     */
    @Select("select * " +
            "   from ry_freeback_detail rfd " +
            "   where ifnull(rfd_del,0)=0  and rfd_freeback=#{freeback}  " )
    List<RyFreebackDetail> getFreebackDetails(@Param("freeback") String freeback);



}
