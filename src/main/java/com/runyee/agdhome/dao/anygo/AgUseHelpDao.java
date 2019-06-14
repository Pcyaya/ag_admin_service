package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyUrgent;
import com.runyee.agdhome.entity.db.anygo.RyUseHelp;
import com.runyee.agdhome.entity.db.anygo.RyUseHelpCategory;
import com.runyee.agdhome.entity.db.anygo.RyUseHelpDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface AgUseHelpDao {

    /**
     * 新增 使用帮助类别
     */
    @Insert("insert into ry_usehelp_category( ruc_id, ruc_name, ruc_icon, ruc_sync, ruc_del, create_date ) " +
            "   values( #{ruc_id}, #{ruc_name}, #{ruc_icon}, #{ruc_sync}, #{ruc_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rv_id")
    @SelectKey(before=true,keyProperty="ruc_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUseHelpCategory(RyUseHelpCategory category);

    /**
     * 新增 使用帮助
     */
    @Insert("insert into ry_usehelp( ru_id, ru_category, ru_title, ru_icon, ru_sync, ru_del, create_date ) " +
            "   values( #{ru_id}, #{ru_category}, #{ru_title}, #{ru_icon}, #{ru_sync}, #{ru_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rv_id")
    @SelectKey(before=true,keyProperty="ru_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUseHelp(RyUseHelp help);

    /**
     * 新增 使用帮助详情
     */
    @Insert("insert into ry_usehelp_detail( rud_id, rud_usehelp, rud_content, rud_type, rud_url, rud_del, create_date ) " +
            "   values( #{rud_id}, #{rud_usehelp}, #{rud_content}, #{rud_type}, #{rud_url}, #{rud_del}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rv_id")
    @SelectKey(before=true,keyProperty="rud_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUseHelpDetail(RyUseHelpDetail detail);


    /**
     * 更新 使用帮助类别
     */
    @Update("update ry_usehelp_category set  " +
            "   ruc_name=#{ruc_name},  " +
            "   ruc_icon=#{ruc_icon},  " +
            "   ruc_sync=#{ruc_sync}  " +
            "   where ruc_id = #{ruc_id} ")
    void updateUseHelpCategory(RyUseHelpCategory category);


    /**
     * 更新 使用帮助类别 删除
     */
    @Update("update ry_usehelp_category set  " +
            "   ruc_del=#{ruc_del}  " +
            "   where ruc_id = #{ruc_id} ")
    void updateUseHelpCategoryDel(RyUseHelpCategory category);

    /**
     * 更新 使用帮助类别 app 同步
     */
    @Update("update ry_usehelp_category set  " +
            "   ruc_sync=#{ruc_sync}  " +
            "   where ruc_id = #{ruc_id} ")
    void updateUseHelpCategorySync(RyUseHelpCategory category);


    /**
     * 更新 使用帮助类别 排序
     */
    @Update("update ry_usehelp_category set  " +
            "   ruc_sort=#{ruc_sort}  " +
            "   where ruc_id = #{ruc_id} ")
    void updateUseHelpCategorySort(RyUseHelpCategory category);

    /**
     * 更新 使用帮助类别
     */
    @Update("update ry_usehelp set  " +
            "   ru_title=#{ru_title},  " +
            "   ru_icon=#{ru_icon},  " +
            "   ru_sync=#{ru_sync}  " +
            "   where ru_id = #{ru_id} ")
    void updateUseHelp(RyUseHelp useHelp);

    /**
     * 更新 使用帮助 删除
     */
    @Update("update ry_usehelp set  " +
            "   ru_del=#{ru_del}  " +
            "   where ru_id = #{ru_id} ")
    void updateUseHelpDel(RyUseHelp help);

    /**
     * 更新 使用帮助详情 删除
     */
    @Update("update ry_usehelp_detail set  " +
            "   rud_del=1  " +
            "   where rud_usehelp = #{usehelp} ")
    void updateUseHelpDetailDel(@Param("usehelp")String usehelp);

    /**
     * 更新 使用帮助  同步
     */
    @Update("update ry_usehelp set  " +
            "   ru_sync=#{ru_sync}  " +
            "   where ru_id = #{ru_id} ")
    void updateUseHelpSync(RyUseHelp help);


    /**
     * 更新 使用帮助 排序
     */
    @Update("update ry_usehelp set  " +
            "   ru_sort=#{ru_sort}  " +
            "   where ru_id = #{ru_id} ")
    void updateUseHelpSort(RyUseHelp help);

    /**
     * 取得 使用帮助
     */
    @Select("select * " +
            "   from ry_usehelp_category " +
            "   where ruc_id =#{id} " +
            "   order by create_date desc limit 1 ")
    RyUseHelpCategory getUseHelpCategoryOneKey(@Param("id") String id);

    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select * " +
            "   from ry_usehelp_category ruc " +
            "   where ifnull(ruc_del,0)=0 " +
            "   ${filter}" +
            "   order by ruc_sort asc,create_date desc " +
            "   limit #{page},#{rows} ")
    List<RyUseHelpCategory> getUseHelpCategorys(@Param("filter") String filter,
                                                @Param("page") int page,
                                                @Param("rows") int rows);

    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select count(1) " +
            "   from ry_usehelp_category " +
            "   where ifnull(ruc_del,0)=0 " +
            "   ${filter}")
    int getUseHelpCategoryCount(@Param("filter") String filter);


    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select * " +
            "   from ry_usehelp ru " +
            "   where ifnull(ru_del,0)=0 and ru_category=#{category} " +
            "   ${filter}" +
            "   order by ru_sort asc, create_date desc " +
            "   limit #{page},#{rows} ")
    List<RyUseHelp> getUseHelps(@Param("category") String category,
                                @Param("filter") String filter,
                                @Param("page") int page,
                                @Param("rows") int rows);

    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select * " +
            "   from ry_usehelp ru " +
            "   where ifnull(ru_del,0)=0 and ru_category=#{category} " +
            "   order by ru_sort asc, create_date desc ")
    List<RyUseHelp> getUseHelpsCategory(@Param("category") String category);


    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select * " +
            "   from ry_usehelp_detail rud " +
            "   where ifnull(rud_del,0)=0 and rud_usehelp=#{usehelp} " +
            "   order by create_date asc ")
    List<RyUseHelpDetail> getUseHelpDetails(@Param("usehelp") String usehelp);

    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select * " +
            "   from ry_usehelp ru " +
            "   where ru_id =#{id} " +
            "   order by create_date desc limit 1 ")
    RyUseHelp getUseHelpOneKey(@Param("id") String id);

    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select count(1) " +
            "   from ry_usehelp " +
            "   where ifnull(ru_del,0)=0 and ru_category=#{category} " +
            "   ${filter}")
    int getUseHelpCount(@Param("category") String category,
                        @Param("filter") String filter);

    /**
     * 取得 使用帮助 类型列表
     */
    @Select("select * " +
            "   from ry_usehelp_category ruc " +
            "   where ifnull(ruc_del,0)=0 " +
            "   order by ruc_sort asc,create_date desc " )
    List<RyUseHelpCategory> getUseHelpCategorysAll();

}
