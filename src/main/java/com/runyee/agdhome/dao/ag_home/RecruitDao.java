package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.ex.RecruitApplyBean;
import com.runyee.agdhome.entity.ex.RecruitBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface RecruitDao {

    /**
     * 新增 招聘信息
     */
    @Insert("insert into ah_recruit(  ar_id, ar_business, ar_name, ar_addr, ar_exper, ar_exper_end, ar_edu, ar_sort, ar_audit, del, create_date ) " +
            "   values(  #{ar_id}, #{ar_business}, #{ar_name}, #{ar_addr}, #{ar_exper}, #{ar_exper_end}, #{ar_edu}, #{ar_sort}, #{ar_audit}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ar_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertRecruit(AhRecruit recruit);

    /**
     * 新增 招聘信息详情
     */
    @Insert("insert into ah_recruit_detail( ard_id, ard_recruit, ard_title, ard_content, ard_sort, ard_type, ard_url, del, create_date ) " +
            "   values( #{ard_id}, #{ard_recruit}, #{ard_title}, #{ard_content}, #{ard_sort}, #{ard_type}, #{ard_url}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ard_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertRecruitDetail(AhRecruitDetail detail);


    /**
     * 新增 招聘申请
     */
    @Insert("insert into ah_recruit_apply( ara_id, ara_recruit, ara_name, ara_phone, ara_email, ara_sex, ara_content, del, create_date ) " +
            "   values( #{ara_id}, #{ara_recruit}, #{ara_name}, #{ara_phone}, #{ara_email}, #{ara_sex}, #{ara_content}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ara_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertRecruitApply(AhRecruitApply apply);

    /**
     * 新增 招聘申请详情
     */
    @Insert("insert into ah_recruit_apply_detail(  arad_id, arad_apply, arad_title, arad_content, arad_sort, arad_type, arad_url, del, create_date ) " +
            "   values(  #{arad_id}, #{arad_apply}, #{arad_title}, #{arad_content}, #{arad_sort}, #{arad_type}, #{arad_url}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="arad_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertRecruitApplyDetail(AhRecruitApplyDetail detail);


    /**
     * 删除招聘职位
     */
    @Update("update ah_recruit set  " +
            "   del= #{del} " +
            "   where ar_id = #{ar_id} ")
    void updateDelRecruit(AhRecruit recruit);

    /**
     * 审核招聘职位
     */
    @Update("update ah_recruit set  " +
            "   ar_audit= #{ar_audit} " +
            "   where ar_id = #{ar_id} ")
    void updateAuditRecruit(AhRecruit recruit);

    /**
     * 更新
     */
    @Update("update ah_recruit_detail set  " +
            "   del= 1 " +
            "   where ard_recruit = #{recruit} ")
    void updateDelRecruitDetail(@Param("recruit")String recruit);

    /**
     * 更新 招聘信息
     */
    @Update("update ah_recruit set  " +
            "   ar_business= #{ar_business}, " +
            "   ar_name= #{ar_name}, " +
            "   ar_addr= #{ar_addr}, " +
            "   ar_exper= #{ar_exper}, " +
            "   ar_exper_end= #{ar_exper_end}, " +
            "   ar_audit= #{ar_audit}, " +
            "   ar_edu= #{ar_edu} " +
            "   where ar_id = #{ar_id} ")
    void updateNews(AhRecruit recruit);

    @Select("select * " +
            "   from ah_recruit_business arb " +
            "   where ifnull(del,0)=0 " +
            "   order by arb_sort,create_date desc  " +
            "   limit #{page},#{rows} ")
    List<AhRecruitBusiness> getRecruitBusinessAllList(@Param("page")int page,
                                                      @Param("rows")int rows);

    @Select("select * " +
            "   from ah_recruit ar " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by ar_sort,create_date desc " +
            "   limit #{page},#{rows} ")
    List<AhRecruit> getRecruitList(@Param("filter")String filter,
                                   @Param("page")int page,
                                   @Param("rows")int rows);

    @Select("select * " +
            "   from ah_recruit ar " +
            "   where ifnull(del,0)=0 and ar.ar_id = #{recruit} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhRecruit getRecruitOneKey(@Param("recruit")String recruit);


    @Select("select * " +
            "   from ah_recruit_detail ard " +
            "   where ifnull(del,0)=0 and ard.ard_recruit = #{recruit}" +
            "   order by ard_sort,create_date desc ")
    List<AhRecruitDetail> getRecruitDetailList(@Param("recruit")String recruit);

    @Select("select * " +
            "   from ah_recruit_apply ara " +
            "   where ifnull(del,0)=0 and ara.ara_phone = #{phone} " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit 5 ")
    List<AhRecruitApply> getRecruitApplysPhone(@Param("phone")String phone,
                                               @Param("filter")String filter);

    @Select("select ar.*, " +
            "   ifnull((select arb.arb_name " +
            "               from ah_recruit_business arb " +
            "               where arb.arb_id = ar.ar_business ),'')business_name," +
            "   ifnull((select count(1) " +
            "                 from ah_recruit_apply ara" +
            "                 where ifnull(ara.del,0)=0 and ara.ara_recruit = ar.ar_id),0)apply_num " +
            "   from ah_recruit ar " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by ar_sort,create_date desc " +
            "   limit #{page},#{rows} ")
    List<RecruitBean> getManRecruitList(@Param("filter")String filter,
                                        @Param("page")int page,
                                        @Param("rows")int rows);

    @Select("select count(1) " +
            "   from ah_recruit ar " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by ar_sort,create_date desc ")
    int getManRecruitCount(@Param("filter")String filter);


    @Select("select ara.*, " +
            "   if(exists(select arad.arad_id " +
            "                   from ah_recruit_apply_detail arad " +
            "                   where ifnull(arad.del,0)=0 and arad.arad_apply = ara.ara_id " +
            "                   and ifnull(arad_type,0)=4  ),1,0)resume_flg " +
            "   from ah_recruit_apply ara " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by create_date desc " +
            "   limit #{page},#{rows} ")
    List<RecruitApplyBean> getManRecruitApplyList(@Param("filter")String filter,
                                                  @Param("page")int page,
                                                  @Param("rows")int rows);


    @Select("select count(1) " +
            "   from ah_recruit_apply ara " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by create_date desc ")
    int getManRecruitApplyCount(@Param("filter")String filter);


    @Select("select * " +
            "   from ah_recruit_apply_detail arad " +
            "   where ifnull(del,0)=0 and arad.arad_apply = #{apply}" +
            "   order by arad_sort,create_date desc ")
    List<AhRecruitApplyDetail> getRecruitApplyDetailList(@Param("apply")String apply);



}
