package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface AgSurvivalDao {


    /**
     * 新增 生存锦囊
     */
    @Insert("insert into ry_survival( rs_id, rs_title, rs_content, rs_sync, rs_del, create_date ) " +
            "   values( #{rs_id}, #{rs_title}, #{rs_content}, #{rs_sync}, #{rs_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rvc_id")
    @SelectKey(before=true,keyProperty="rs_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertSurvival(RySurvival survival);

    /**
     * 新增 国家
     */
    @Insert("insert into ry_urgent( ru_id, ru_name, ru_sync, ru_del, create_date ) " +
            "   values( #{ru_id}, #{ru_name}, #{ru_sync}, #{ru_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rvc_id")
    @SelectKey(before=true,keyProperty="ru_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUrgent(RyUrgent urgent);


    /**
     * 新增 紧急电话
     */
    @Insert("insert into ry_urgent_phone( rup_id, rup_urgent, rup_title, rup_phone, rup_sync, rup_del, create_date ) " +
            "   values( #{rup_id}, #{rup_urgent}, #{rup_title}, #{rup_phone}, #{rup_sync}, #{rup_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rvc_id")
    @SelectKey(before=true,keyProperty="rup_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUrgentPhone(RyUrgentPhone uphone);

    /**
     * 更新 生存锦囊 信息
     */
    @Update("update ry_survival set  " +
            "   rs_title=#{rs_title}, " +
            "   rs_content=#{rs_content} " +
            "   where rs_id = #{rs_id} ")
    void updateSurvival(RySurvival survival);

    /**
     * 更新 国家 信息
     */
    @Update("update ry_urgent set  " +
            "   ru_name=#{ru_name}  " +
            "   where ru_id = #{ru_id} ")
    void updateUrgent(RyUrgent urgent);

    /**
     * 更新 国家 信息
     */
    @Update("update ry_urgent_phone set  " +
            "   rup_title=#{rup_title},  " +
            "   rup_phone=#{rup_phone}  " +
            "   where rup_id = #{rup_id} ")
    void updateUrgentPhone(RyUrgentPhone uphone);

    /**
     * 更新 生存锦囊 删除
     */
    @Update("update ry_survival set  " +
            "   rs_del=#{rs_del} " +
            "   where rs_id = #{rs_id} ")
    void updateSurvivalDel(RySurvival survival);

    /**
     * 更新 生存锦囊 app同步
     */
    @Update("update ry_survival set  " +
            "   rs_sync=#{rs_sync} " +
            "   where rs_id = #{rs_id} ")
    void updateSurvivalSync(RySurvival survival);

    /**
     * 更新 生存锦囊 详情删除
     */
    @Update("update ry_survival_capsule set  " +
            "   rsc_del=1 " +
            "   where rsc_survival = #{survival} ")
    void updateSurvivalCapsuleDelAll(@Param("survival")String survival);

    /**
     * 更新 国家 删除
     */
    @Update("update ry_urgent set  " +
            "   ru_del=#{ru_del}  " +
            "   where ru_id = #{ru_id} ")
    void updateUrgentDel(RyUrgent urgent);

    /**
     * 更新 国家 app同步
     */
    @Update("update ry_urgent set  " +
            "   ru_sync=#{ru_sync}  " +
            "   where ru_id = #{ru_id} ")
    void updateUrgentSync(RyUrgent urgent);

    /**
     * 更新 紧急电话 删除
     */
    @Update("update ry_urgent_phone set  " +
            "   rup_del=#{rup_del}  " +
            "   where rup_id = #{rup_id} ")
    void updateUrgentPhoneDel(RyUrgentPhone uphone);

    /**
     * 更新 紧急电话 app同步
     */
    @Update("update ry_urgent_phone set  " +
            "   rup_sync=#{rup_sync}  " +
            "   where rup_id = #{rup_id} ")
    void updateUrgentPhoneSync(RyUrgentPhone uphone);


    @Select("select (@rs_id:=rs.rs_id) rs_id, " +
            "   rs_sync, " +
            "   rs.rs_title, " +
            "   rs.create_date, " +
            "   concat(substr(if(ifnull(rs.rs_content,'')=''," +
            "             ifnull((select concat(ifnull(rsc_title,''),ifnull(rsc_content,'')) " +
            "                       from (select * from ry_survival_capsule rsc " +
            "                               where  ifnull(rsc.rsc_del,0)=0 and ifnull(rsc.rsc_title,'')<> '' " +
            "                               and rsc.rsc_survival = @rs_id " +
            "                             union all " +
            "                             select * from ry_survival_capsule rsc " +
            "                               where  ifnull(rsc.rsc_del,0)=0 and ifnull(rsc.rsc_content,'')<> '' " +
            "                               and rsc.rsc_survival = @rs_id " +
            "                       ) sur" +
            "                       order by rsc_id asc limit 1),'')," +
            "             rs.rs_content),1,30),'...') rs_content " +
            "   from ry_survival rs " +
            "   where ifnull(rs_del,0)=0 " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit #{page},#{rows} ")
    List<RySurvival> getSurvivals(@Param("filter") String filter,
                                  @Param("page") int page,
                                  @Param("rows") int rows);


    @Select("select count(1) " +
            "   from ry_survival rs " +
            "   where ifnull(rs_del,0)=0 " +
            "   ${filter} ")
    int getSurvivalCount(@Param("filter") String filter);

    @Select("select rs.*  " +
            "   from ry_survival rs " +
            "   where  rs_id = #{id} " +
            "   order by create_date desc " +
            "   limit 1 ")
    RySurvival getSurvivalOneKey(@Param("id") String id);

    @Select("select rsc.*  " +
            "   from ry_survival_capsule rsc " +
            "   where ifnull(rsc_del,0)=0  and  rsc_survival = #{survival}" +
            "   order by rsc_id asc ")
    List<RySurvivalCapsule> getSurvivalCapsules(@Param("survival") String survival);

    @Select("select * " +
            "   from ry_urgent ru  " +
            "   where ifnull(ru_del,0)=0 " +
            "   ${filter} " +
            "   order by create_date desc  " +
            "   limit #{page},#{rows} ")
    List<RyUrgent> getUrgentList(@Param("filter") String filter,
                                 @Param("page") int page,
                                 @Param("rows") int rows);


    @Select("select count(1) " +
            "   from ry_urgent ru " +
            "   where ifnull(ru_del,0)=0 " +
            "    ${filter} ")
    int getUrgentCount(@Param("filter") String filter);

    @Select("select * " +
            "   from ry_urgent_phone rup  " +
            "   where ifnull(rup_del,0)=0 and rup_urgent=#{urgent}" +
            "   order by create_date desc  ")
    List<RyUrgentPhone> getUrgentPhones(@Param("urgent") String urgent);

    @Select("select *  " +
            "   from ry_urgent ru " +
            "   where  ru_id = #{id} " +
            "   order by create_date desc " +
            "   limit 1 ")
    RyUrgent getUrgentOneKey(@Param("id") String id);


    @Select("select count(1) " +
            "   from ry_urgent_phone rup " +
            "   where ifnull(rup_del,0)=0 " +
            "   ${filter} ")
    int getUrgentPhoneCount(@Param("filter") String filter);

    @Select("select *  " +
            "   from ry_urgent_phone rup " +
            "   where  rup_id = #{id} " +
            "   order by create_date desc " +
            "   limit 1 ")
    RyUrgentPhone getUrgentPhoneOneKey(@Param("id") String id);


}
