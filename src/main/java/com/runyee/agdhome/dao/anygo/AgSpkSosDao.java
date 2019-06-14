package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RySpkSos;
import com.runyee.agdhome.entity.db.anygo.RySpkSosHandle;
import com.runyee.agdhome.entity.ex.SpkSosBean;
import com.runyee.agdhome.entity.ex.SpkSosInfoBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgSpkSosDao {

    /**
     * 新增 sos handle
     */
    @Insert("insert into ry_spk_sos_handle( rssh_id, rssh_sos, rssh_user, rssh_confirm, rssh_operate, rssh_content, rssh_inform, rssh_del, create_date ) " +
            "   values( #{rssh_id}, #{rssh_sos}, #{rssh_user}, #{rssh_confirm}, #{rssh_operate}, #{rssh_content}, #{rssh_inform}, #{rssh_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rg_id")
    @SelectKey(before=true,keyProperty="rssh_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertSpkSosHandle(RySpkSosHandle sosHandle);

    /**
     *  删除 sos 信息
     */
    @Update("update ry_spk_sos set  " +
            "   rss_del= #{rss_del} " +
            "   where rss_id = #{rss_id} ")
    void updateSpkSosDel(RySpkSos sos);

    /**
     * 更新 sos handle信息
     */
    @Update("update ry_spk_sos_handle set  " +
            "   rssh_confirm= #{rssh_confirm}, " +
            "   rssh_operate= #{rssh_operate}, " +
            "   rssh_content= #{rssh_content}, " +
            "   rssh_inform= #{rssh_inform}, " +
            "   rssh_user= #{rssh_user} " +
            "   where rssh_id = #{rssh_id} ")
    void updateSpkSosHandle(RySpkSosHandle sosHandle);

    /**
     * sos
     */
    @Select("select rss.* " +
            "   from ry_spk_sos rss " +
            "   where ifnull(rss_del,0)=0  and  rss_id =#{id} " +
            "   order by create_date desc limit 1  ")
    RySpkSos getSpkSosOneKey(@Param("id") String id);

    /**
     * sos handle
     */
    @Select("select rssh.* " +
            "   from ry_spk_sos_handle rssh " +
            "   where ifnull(rssh_del,0)=0  and  rssh_sos =#{sos} " +
            "   order by create_date desc limit 1  ")
    RySpkSosHandle getSpkSosHandleOneKey(@Param("sos") String sos);


    /**
     * sos  Bean
     */
    @Select("select rss.*," +
            //基本信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 and  (@age:=null)is null " +
            "       and(select concat(@name:=ifnull(rv_name,rv_num)," +
            "                    @phone:=ifnull(rv_phone,''), " +
            "                    @sex:=ifnull(rv_sex,0), " +
            "                    @age:=rv_age) " +
            "               from ry_visitor rv " +
            "               where ifnull(rv_del,0)=0 and rv.rv_id = rss.rss_visitor " +
            "               order by  create_date desc limit 1 )is not null,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            "   @age age," +
            //导游信息
            "   if((@occupation:='')='' and (@guide_no:='')='' and (@company:='')='' " +
            "        and (select concat(@occupation:=ifnull(rgi_occupation,''), " +
            "                    @guide_no:=ifnull(rgi_guide_no,''), " +
            "                    @company:=ifnull(rgi_company,'')) " +
            "               from ry_guide_info rgi  " +
            "               where ifnull(rgi.rgi_del,0)= 0 and rgi.rgi_guide = rss.rss_visitor " +
            "               order by  create_date desc limit 1 ) is not null, @occupation,'') occupation," +
            "   @guide_no guide_no," +
            "   @company company," +
            //处理信息
            "   if((@handle_schedule:='')='' and (@handle_user:='')='' " +
            "       and (select concat(@handle_schedule:=concat(ifnull(rssh_confirm,0),ifnull(rssh_operate,0),ifnull(rssh_inform,0)), " +
            "                    @handle_user:=ifnull(rssh_user,'')) " +
            "               from ry_spk_sos_handle rssh " +
            "               where  ifnull(rssh.rssh_del,0)=0 and  rssh.rssh_sos =rss.rss_id " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:='-000') handle_schedule," +
            "   @handle_user handle_user, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 5 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name " +
            "   from ry_spk_sos rss " +
            "   where ifnull(rss_del,0)=0  and  rss_id =#{id} " +
            "   order by create_date desc limit 1 ")
    SpkSosInfoBean getSpkSosBeanOneKey(@Param("id") String id);

    /**
     * sos 列表
     */
    @Select("select rss.*," +
            //基本信息
            "   if((@name:='')='' and (@phone:='')='' and   (@sex:=0)=0 and  (@age:=null)is null " +
            "       and(select concat(@name:=ifnull(rv_name,rv_num)," +
            "                    @phone:=ifnull(rv_phone,''), " +
            "                    @sex:=ifnull(rv_sex,0), " +
            "                    @age:=rv_age) " +
            "               from ry_visitor rv " +
            "               where ifnull(rv_del,0)=0 and rv.rv_id = rss.rss_visitor " +
            "               order by  create_date desc limit 1 )is not null,@name,'') name," +
            "   @phone phone," +
            "   @sex sex," +
            "   @age age," +
            //导游信息
            "   if((@occupation:='')='' and (@guide_no:='')='' and (@company:='')='' " +
            "        and (select concat(@occupation:=ifnull(rgi_occupation,''), " +
            "                    @guide_no:=ifnull(rgi_guide_no,''), " +
            "                    @company:=ifnull(rgi_company,'')) " +
            "               from ry_guide_info rgi  " +
            "               where ifnull(rgi.rgi_del,0)= 0 and rgi.rgi_guide = rss.rss_visitor " +
            "               order by  create_date desc limit 1 ) is not null, @occupation,'') occupation," +
            "   @guide_no guide_no," +
            "   @company company," +
            //处理信息
            "   if((@handle_schedule:='')='' and (@handle_user:='')='' " +
            "       and (select concat(@handle_schedule:=concat(ifnull(rssh_confirm,0),ifnull(rssh_operate,0),ifnull(rssh_inform,0)), " +
            "                    @handle_user:=ifnull(rssh_user,'')) " +
            "               from ry_spk_sos_handle rssh " +
            "               where  ifnull(rssh.rssh_del,0)=0 and  rssh.rssh_sos =rss.rss_id " +
            "               order by create_date desc limit 1 ) is not null, @handle_schedule,@handle_schedule:='-000') handle_schedule," +
            "   @handle_user handle_user, " +
            "   ifnull((select rcv.rcv_name " +
            "               from ry_common_val rcv " +
            "               where ifnull(rcv.rcv_del,0)=0 and  rcv.rcv_group = 5 " +
            "               and ifnull(rcv.rcv_val,'')= @handle_schedule " +
            "               order by create_date desc limit 1 ),'') schedule_name " +
            "   from ry_spk_sos rss " +
            "   where ifnull(rss_del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<SpkSosBean> getSpkSosBeanList(@Param("filter") String filter,
                                       @Param("order") String order,
                                       @Param("page") int page,
                                       @Param("rows") int rows);

    /**
     * sos 列表 count
     */
    @Select("select count(1) " +
            "   from ry_spk_sos rss " +
            "   where ifnull(rss_del,0)=0  " +
            "   ${filter} ")
    int getSpkSosCount(@Param("filter") String filter);


    /**
     * 获取半个小时前未处理的SOS求救信息
     * @return List
     */
    @Select("select rss.* " +
            "   from ry_spk_sos rss " +
            "   where ifnull(rss.rss_del,0)=0 " +
            "   and rss.create_date > DATE_SUB(NOW(),INTERVAL  0.5 HOUR) " +
            "   and rss.rss_phone is not null " +
            "   and not exists(select * " +
            "               from ry_spk_sos_handle rssh " +
            "               where rssh.rssh_inform = 1 " +
            "               and ifnull(rssh.rssh_del,0)=0 " +
            "               and rssh.rssh_sos = rss.rss_id ) ")
    List<RySpkSos> getnotexecuteSOSInfoList();

}
