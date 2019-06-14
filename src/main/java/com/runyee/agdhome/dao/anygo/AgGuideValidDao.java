package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyGuideValid;
import com.runyee.agdhome.entity.db.anygo.RyGuideValidHandle;
import com.runyee.agdhome.entity.db.anygo.RySpkSos;
import com.runyee.agdhome.entity.db.anygo.RySpkSosHandle;
import com.runyee.agdhome.entity.ex.GuideValidBean;
import com.runyee.agdhome.entity.ex.GuideValidInfoBean;
import com.runyee.agdhome.entity.ex.SpkSosBean;
import com.runyee.agdhome.entity.ex.SpkSosInfoBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgGuideValidDao {

    /**
     * 新增 guide valid handle
     */
    @Insert("insert into ry_guide_valid_handle( rgvh_id, rgvh_valid, rgvh_user, rgvh_status, rgvh_content, rgvh_del, create_date ) " +
            "   values( #{rgvh_id}, #{rgvh_valid}, #{rgvh_user}, #{rgvh_status}, #{rgvh_content}, #{rgvh_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rg_id")
    @SelectKey(before=true,keyProperty="rgvh_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertGuideValidHandle(RyGuideValidHandle handle);

    /**
     *  删除 valid 信息
     */
    @Update("update ry_guide_valid set  " +
            "   rgv_del= #{rgv_del} " +
            "   where rgv_id = #{rgv_id} ")
    void updateGuideValidDel(RyGuideValid valid);

    /**
     * 更新 guide valid handle信息
     */
    @Update("update ry_guide_valid_handle set  " +
            "   rgvh_status= #{rgvh_status}, " +
            "   rgvh_user= #{rgvh_user}, " +
            "   rgvh_content= #{rgvh_content} " +
            "   where rgvh_id = #{rgvh_id} ")
    void updateGuideValidHandle(RyGuideValidHandle handle);

    /**
     * 认证信息
     */
    @Select("select rgv.* " +
            "   from ry_guide_valid rgv " +
            "   where ifnull(rgv_del,0)=0  and  rgv_id =#{id} " +
            "   order by create_date desc limit 1  ")
    RyGuideValid getGuideValidOneKey(@Param("id") String id);

    /**
     * guide valid handle
     */
    @Select("select rgvh.* " +
            "   from ry_guide_valid_handle rgvh " +
            "   where ifnull(rgvh_del,0)=0  and  rgvh_valid =#{valid} " +
            "   order by create_date desc limit 1  ")
    RyGuideValidHandle getGuideValidHandleOneValid(@Param("valid") String valid);


    /**
     * guide valid  Bean
     */
    @Select("select rgv.*," +
            //基本信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 and  (@age:=null)is null " +
            "       and(select concat(@name:=ifnull(rv_name,rv_num)," +
            "                    @phone:=ifnull(rv_phone,''), " +
            "                    @sex:=ifnull(rv_sex,0), " +
            "                    @age:=rv_age) " +
            "               from ry_visitor rv " +
            "               where ifnull(rv_del,0)=0 and rv.rv_id = rgv.rgv_visitor " +
            "               order by  create_date desc limit 1 )is not null,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            "   @age age," +
            //处理信息
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' and (@handle_result:='')='' " +
            "       and (select concat(@handle_schedule:=ifnull(rgvh_status,0), " +
            "                    @handle_user:=ifnull(rgvh_user,'')," +
            "                    @handle_result:=ifnull(rgvh_content,'')) " +
            "               from ry_guide_valid_handle rgvh " +
            "               where  ifnull(rgvh.rgvh_del,0)=0 and  rgvh.rgvh_valid =rgv.rgv_id " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @handle_result handle_result, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 14 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name " +
            "   from ry_guide_valid rgv " +
            "   where ifnull(rgv_del,0)=0  and  rgv_id =#{id} " +
            "   order by create_date desc limit 1 ")
    GuideValidInfoBean getGuideValidBeanOneKey(@Param("id") String id);

    /**
     * guide valid 列表
     */
    @Select("select rgv.*," +
            //基本信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 and  (@age:=null)is null " +
            "       and(select concat(@name:=ifnull(rv_name,rv_num)," +
            "                    @phone:=ifnull(rv_phone,''), " +
            "                    @sex:=ifnull(rv_sex,0), " +
            "                    @age:=rv_age) " +
            "               from ry_visitor rv " +
            "               where ifnull(rv_del,0)=0 and rv.rv_id = rgv.rgv_visitor " +
            "               order by  create_date desc limit 1 )is not null,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            "   @age age," +
            //处理信息
            "   if((@handle_schedule:=0)=0 and (@handle_user:='')='' " +
            "       and (@handle_result:='')='' and (@handle_date:=null)is null  " +
            "       and (select concat(@handle_schedule:=ifnull(rgvh_status,0), " +
            "                    @handle_user:=ifnull(rgvh_user,'')," +
            "                    @handle_result:=ifnull(rgvh_content,'')," +
            "                    @handle_date:= rgvh.create_date) " +
            "               from ry_guide_valid_handle rgvh " +
            "               where  ifnull(rgvh.rgvh_del,0)=0 and  rgvh.rgvh_valid =rgv.rgv_id " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:=-1) handle_schedule," +
            "   @handle_user handle_user, " +
            "   @handle_result handle_result, " +
            "   @handle_date handle_date, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 14 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name " +
            "   from ry_guide_valid rgv " +
            "   where ifnull(rgv_del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<GuideValidBean> getGuideValidBeanList(@Param("filter") String filter,
                                               @Param("order") String order,
                                               @Param("page") int page,
                                               @Param("rows") int rows);

    /**
     * guide valid 数量
     */
    @Select("select count(1) " +
            "   from ry_guide_valid rgv " +
            "   where ifnull(rgv_del,0)=0  " +
            "   ${filter} ")
    int getGuideValidCount(@Param("filter") String filter);




}
