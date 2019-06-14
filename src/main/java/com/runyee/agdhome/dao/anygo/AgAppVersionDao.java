package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.AppVersion;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgAppVersionDao {

    /**
     * 新增 应用版本
     */
    @Insert("insert into app_version( id, create_date,  new_version, apk_file_url, update_log, target_size, new_md5, constraint_flg, flat_form, app_type ) " +
            "   values( #{id}, #{create_date}, #{new_version}, #{apk_file_url}, #{update_log}, #{target_size}, #{new_md5}, #{constraint_flg}, #{flat_form}, #{app_type} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rvc_id")
    @SelectKey(before=true,keyProperty="id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertAppVersion(AppVersion version);

    /**
     * 更新 应用版本
     */
    @Update("update app_version set  " +
            "   app_type=#{app_type} " +
            "   where id = #{id} ")
    void updateAppVersionDel(AppVersion version);

    /**
     * 更新 应用版本信息
     */
    @Update("update app_version set  " +
            "   new_version=#{new_version}, " +
            "   apk_file_url=#{apk_file_url}, " +
            "   update_log=#{update_log}, " +
            "   target_size=#{target_size}, " +
            "   constraint_flg=#{constraint_flg}, " +
            "   flat_form=#{flat_form} " +
            "   where id = #{id} ")
    void updateAppVersion(AppVersion version);


    /**
     * 取得 应用版本
     */
    @Select("select * " +
            "   from app_version appv " +
            "   where ifnull(appv.flat_form,0) = #{platform}  " +
            "   order by new_version desc limit 1 ")
    AppVersion getAppVersionOnePlat(@Param("platform") int platform);



    /**
     * 取得 应用最新版本
     */
    @Select("select * " +
            "   from app_version appv " +
            "   where ifnull(appv.app_type,0) = 0 " +
            "   and appv.id = (select appv2.id " +
            "                       from app_version appv2 " +
            "                       where  ifnull(appv2.app_type,0) = 0  and ifnull(appv2.flat_form,0) = ifnull(appv.flat_form,0) " +
            "                       order by  appv2.new_version desc limit 1 )" +
            "   order by new_version desc  ")
    List<AppVersion> getAppVersionNews();


    /**
     * 取得 应用版本列表
     */
    @Select("select * " +
            "   from app_version appv " +
            "   where  ifnull(appv.app_type,0) = 0 " +
            "   ${filter} " +
            "   ${order} " +
            "   limit #{page},#{rows} ")
    List<AppVersion> getAppVersionList(@Param("filter")String filter,
                                       @Param("order") String order,
                                       @Param("page")int page,
                                       @Param("rows")int rows);

    /**
     * 取得 应用版本列表
     */
    @Select("select count(1) " +
            "   from app_version appv " +
            "   where  ifnull(appv.app_type,0) = 0  " +
            "   ${filter} ")
    int getAppVersionCount(@Param("filter")String filter);

    /**
     * 取得 应用版本
     */
    @Select("select * " +
            "   from app_version appv " +
            "   where id = #{id}  " +
            "   order by create_date desc limit 1 ")
    AppVersion getAppVersionOneKey(@Param("id") String id);



}
