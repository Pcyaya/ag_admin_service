package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhContact;
import com.runyee.agdhome.entity.db.ag_home.AhContactHandle;
import com.runyee.agdhome.entity.ex.ContactInfoBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface ContactDao {

    /**
     * 新增 联系我们
     * @param contact
     */
    @Insert("insert into ah_contact( ah_id, ac_name, ac_phone, ac_business, ac_email, ac_sex, ac_content, del, create_date ) " +
            "   values(  #{ah_id}, #{ac_name}, #{ac_phone}, #{ac_business}, #{ac_email}, #{ac_sex}, #{ac_content}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ah_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertContact(AhContact contact);

    /**
     * 新增 联系我们处理
     * @param contactHandle
     */
    @Insert("insert into ah_contact_handle( ach_id, ach_contact, ach_user, ach_status, ach_content, del, create_date ) " +
            "   values(  #{ach_id}, #{ach_contact}, #{ach_user}, #{ach_status}, #{ach_content}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ach_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertContactHandle(AhContactHandle contactHandle);

    @Select("select * " +
            "   from ah_contact an " +
            "   where ifnull(del,0)=0 and an.ac_phone = #{phone} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhContact getContactOnePhone(@Param("phone")String phone);

    /**
     * 联系我们 列表
     */
    @Select("select ac.*, " +
            //处理进度
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' and (@results_name:='')='' " +
            "       and (@date_handle:=null)is null " +
            "       and (select concat(@handle_schedule:=ifnull(ach_status,0), " +
            "                   @handle_user:=ifnull(ach_user,''), " +
            "                   @results_name:=ifnull(ach_content,''), " +
            "                   @date_handle:=ach.create_date) " +
            "               from ah_contact_handle ach " +
            "               where  ifnull(ach.del,0)=0 and ach.ach_contact = ac.ah_id  " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @results_name results_name, " +
            "   @date_handle date_handle " +

            "   from ah_contact ac" +
            "   where ifnull(del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<AhContact> getManContactList(@Param("filter") String filter,
                                      @Param("order") String order,
                                      @Param("page") int page,
                                      @Param("rows") int rows);

    /**
     * 联系我们 详情
     */
    @Select("select ac.*" +
//            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' " +
//            "       and (@handle_results:='')='' and (@date_handle:=null)is null " +
//            "       and (select concat(@handle_schedule:=ifnull(ach_status,0), " +
//            "                         @handle_user:=ifnull(ach_user,''), " +
//            "                         @handle_results:=ifnull(ach_content,''), " +
//            "                         @date_handle:=ac.create_date) " +
//            "                     from ah_contact_handle ach " +
//            "                     where  ifnull(ach.del,0)=0 and ach.ach_contact = ac.ah_id  " +
//            "                     order by create_date desc limit 1 ) is not null , @handle_schedule,@handle_schedule:=-1) handle_schedule," +
//            "   @handle_user handle_user, " +
//            "   @handle_results handle_results, " +
//            "   @date_handle date_handle, " +
//            "   ifnull((select rcv.rcv_name " +
//            "               from ry_common_val rcv " +
//            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 15 " +
//            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
//            "               order by create_date desc limit 1 ),'') schedule_name " +

            "   from ah_contact ac" +
            "   where ifnull(del,0)=0 and  ah_id = #{id} " +
            "   limit 1  ")
    AhContact getContactOneKey(@Param("id") String id);

    /**
     * contact handle
     */
    @Select("select ach.* " +
            "   from ah_contact_handle ach " +
            "   where ifnull(del,0)=0  and  ach_contact =#{contact} " +
            "   order by create_date desc limit 1  ")
    AhContactHandle gethContactHandleOneKey(@Param("contact") String contact);

    @Select("select * " +
            "   from ah_contact ac " +
            "   where ifnull(del,0)=0 and ac.ac_phone = #{phone} " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit 5 ")
    List<AhContact> getContactsPhone( @Param("phone")String phone,
                                      @Param("filter")String filter );

    /**
     * 总数
     */
    @Select("select count(1) " +
            "   from ah_contact ac " +
            "   where ifnull(del,0)=0  " +
            "   ${filter} ")
    int getManContactCount(@Param("filter") String filter);

    /**
     * contact  Bean
     */
    @Select("select ac.* " +
            "   from ah_contact ac " +
            "   where ifnull(del,0)=0  and  ah_id =#{id} " +
            "   order by create_date desc limit 1 ")
    AhContact getContactBeanOneKey(@Param("id") String id);

    /**
     *  删除 contact 信息
     */
    @Update("update ah_contact set  " +
            "   del= #{del} " +
            "   where ah_id = #{ah_id} ")
    void updateContactDel(AhContact ah_del);

    /**
     * 更新 contact handle信息
     */
    @Update("update ah_contact_handle set  " +
            "   ach_status= #{ach_status}, " +
            "   ach_content= #{ach_content}, " +
            "   ach_user= #{ach_user} " +
            "   where ach_id = #{ach_id} ")
    void updateContactHandle(AhContactHandle contactHandle);

}
