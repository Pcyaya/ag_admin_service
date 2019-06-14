package com.runyee.agdhome.dao.spk_server;

import com.runyee.agdhome.entity.db.anygo.RyIntellSpkBind;
import com.runyee.agdhome.entity.db.anygo.RyIntellSpkMan;
import com.runyee.agdhome.entity.db.anygo.RyIntelligentSpeaker;
import com.runyee.agdhome.entity.db.anygo.RySpkSync;
import com.runyee.agdhome.entity.ex.SpkBean;
import com.runyee.agdhome.entity.ex.SpkSyncBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpkDao {

    /**
     * 激活设备
     */
    @Update("update ry_intelligent_speaker set  " +
            "   ris_active=#{ris_active} " +
            "   where ris_id = #{ris_id} ")
    void updateSpkActive(RyIntelligentSpeaker speaker);

    /**
     * 更改设备连接环境
     */
    @Update("update ry_intelligent_speaker set  " +
            "   ris_test=#{ris_test} " +
            "   where ris_id = #{ris_id} ")
    void updateSpkEnv(RyIntelligentSpeaker speaker);

    /**
     * 更改设备绑定码
     */
    @Update("update ry_intelligent_speaker set  " +
            "   ris_bind=#{ris_bind} " +
            "   where ris_id = #{ris_id} ")
    void updateSpkBindCode(RyIntelligentSpeaker speaker);

    /**
     *  清除  解绑定设备
     */
    @Update("update ry_intell_spk_bind set " +
            "   risb_state = 1   " +
            "   where  ifnull(risb_state,0)=0  and risb_intell_spk=#{spk}  ")
    void updateIntellSpkBindsSpk(@Param("spk") String spk);

    /**
     *  取得 设备
     */
    @Select("select * " +
            "   from ry_intelligent_speaker ris " +
            "   where ris_id = #{id} ")
    RyIntelligentSpeaker getSpkOneKey(@Param("id") String id);

    /**
     * 取得 导游 绑定设备
     */
    @Select("select risb.*," +
            "   ifnull(ris.ris_num,'') speaker_num " +
            "   from ry_intell_spk_bind risb " +
            "   left join ry_intelligent_speaker ris on ris.ris_id = risb.risb_intell_spk " +
            "   where ifnull(risb_del,0) = 0 and ifnull(risb_state,0)=0 " +
            "   and ris.ris_num=#{speaker_num} order by create_date desc limit 1 ")
    RyIntellSpkBind getIntellSpkBindOneNum(@Param("speaker_num") String speaker_num);

    /**
     * 取得 导游 绑定设备
     */
    @Select("select risb.* " +
            "   from ry_intell_spk_bind risb " +
            "   where ifnull(risb_del,0) = 0 and ifnull(risb_state,0)=0 " +
            "   and  risb.risb_intell_spk =#{id} " +
            "   order by create_date desc limit 1 ")
    RyIntellSpkBind getIntellSpkBindOnSpk(@Param("id") String id);


    /**
     * sos 列表
     */
    @Select("select ris.*, " +
            //绑定信息
            //"   (@bind_date:=NULL)," +
            "   if((@visitor:='')='' and (@bind_db:='')='' and (@binddate:='') ='' " +
            "      and (@urgent_name:='')='' and (@urgent_phone:='')=''   " +
            "      and (@urgent_alias:='')='' and (@urgent_toalias:='')=''   " +
            "      and  (select concat(@visitor:=ifnull(risb_guide,'')," +
            "                       @urgent_name:=ifnull(risb_name,'')," +
            "                       @urgent_phone:=ifnull(risb_phone,'')," +
            "                       @urgent_alias:=ifnull(risb_alias,'')," +
            "                       @urgent_toalias:=ifnull(risb_toalias,'')," +
            "                       @bind_db:=ifnull(risb_db,'')," +
            "                       @binddate:= if(create_date is null ,'',date_format(create_date,'%Y-%m-%d %H:%i:%s')) )  " +
            "                   from ry_intell_spk_bind risb " +
            "                   where ifnull(risb_del,0)=0 and ifnull(risb.risb_state,0) = 0 and  risb.risb_intell_spk = ris.ris_id " +
            "                   order by  create_date desc limit 1 ) is not null,1,0) bind_flg," +
            "   @visitor visitor," +
            "   @urgent_name urgent__name, " +
            "   @urgent_phone urgent_phone, " +
            "   @urgent_alias urgent_alias, " +
            "   @urgent_toalias urgent_toalias, " +
            "   @bind_db bind_db, " +
            "   @binddate binddate," +
            //获取设备管理人
            "   if((@spk_manage:='')='' " +
            "   and (select concat(@spk_manage:=ifnull(rism.rism_guide,'')," +
            "                       ifnull(rism.rism_id,'')) " +
            "               from ry_intell_spk_man rism " +
            "               where ifnull(rism.rism_del,0)=0 " +
            "               and ifnull(rism.rism_state,0)=0 " +
            "               and rism.rism_intell_spk = ris.ris_id " +
            "               order by create_date limit 1) ,1,0) manage_flg, " +
            "   @spk_manage spk_manage "+
            "   from ry_intelligent_speaker ris " +
            "   where ifnull(ris_del,0)=0  " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows}  ")
    List<SpkBean> getSpkBeanList(@Param("filter") String filter,
                                 @Param("order") String order,
                                 @Param("page") int page,
                                 @Param("rows") int rows);

    /**
     * sos 列表 count
     */
    @Select("select count(1) " +
            "   from ry_intelligent_speaker ris " +
            "   where ifnull(ris_del,0)=0  " +
            "   ${filter} ")
    int getSpkCount(@Param("filter") String filter);

    /**
     * 获取音箱版本号
     */
    @Select("select * " +
            "   from ry_spk_sync rss" +
            "   where rss.rss_spk_num in (#{spk_nums})" +
            "   and ifnull(rss.rss_del,0)=0" +
            "   order by rss.create_date desc")
    List<RySpkSync> getIntelligentSpeakerVersion(@Param("spk_nums")String spk_nums);


    /**
     *  查询当前设备的路径
     */
    @Select("select * " +
            "   from ry_spk_sync rss" +
            "   where ifnull(rss.rss_del,0)=0" +
            "   and rss.rss_spk_num = #{spk_num}" +
            "   ${filter}")
    List<SpkSyncBean> getRySpkSyncpositionList(@Param("spk_num")String spk_num,
                                               @Param("filter")String filter);

    /**
     * 查询当前音箱的管理者
     */
    @Select("select * " +
            "   from ry_intell_spk_man rism" +
            "   where ifnull(rism.rism_del,0)=0 " +
            "   and rism.rism_intell_spk in (${spk_ids})")
    List<RyIntellSpkMan> getIntellSpkManBySpkIds(@Param("spk_ids")String spk_ids);

}
