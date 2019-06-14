package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhDepart;
import com.runyee.agdhome.entity.db.ag_home.AhDepartRight;
import com.runyee.agdhome.entity.db.ag_home.AhMenu;
import com.runyee.agdhome.entity.db.ag_home.AhUserDepart;
import com.runyee.agdhome.entity.ex.DepartParentBean;
import com.runyee.agdhome.entity.ex.MenuBean;
import com.runyee.agdhome.entity.ex.MenuParentBean;
import com.runyee.agdhome.entity.ex.RightParentBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface DepartDao {

    /**
     * 新增 部门
     */
    @Insert("insert into ah_depart( ad_id, ad_name, ad_pid, ad_icon, del, create_date ) " +
            "   values( #{ad_id}, #{ad_name}, #{ad_pid}, #{ad_icon}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ad_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertDepart(AhDepart depart);

    /**
     * 新增 部门员工
     */
    @Insert("insert into ah_user_depart( aud_id, aud_user, aud_depart, del, create_date ) " +
            "   values( #{aud_id}, #{aud_user}, #{aud_depart}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="aud_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUserDepart(AhUserDepart userDepart);

    /**
     * 新增 部门权限
     */
    @Insert("insert into ah_depart_right( adr_id, adr_depart, adr_right, del, create_date ) " +
            "   values( #{adr_id}, #{adr_depart}, #{adr_right}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="adr_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertDepartRight(AhDepartRight departRight);

    /**
     *  更新 部门信息
     */
    @Update("update ah_depart set  " +
            "   ad_name= #{ad_name}, " +
            "   ad_pid= #{ad_pid}, " +
            "   ad_icon= #{ad_icon} " +
            "   where ad_id = #{ad_id} ")
    void updateDepartInfo(AhDepart depart);

    /**
     *  更新 部门信息
     */
    @Update("update ah_depart set  " +
            "   ad_pid= #{ad_pid} " +
            "   where ad_id = #{ad_id} ")
    void updateDepartPid(AhDepart depart);


    /**
     *  更新 删除部门
     */
    @Update("update ah_depart set  " +
            "   del= #{del} " +
            "   where ad_id = #{ad_id} ")
    void updateDepartDel(AhDepart depart);

    /**
     *  更新 删除部门权限
     */
    @Update("update ah_depart_right set  " +
            "   del= #{del} " +
            "   where adr_id = #{adr_id} ")
    void updateDepartRightDel(AhDepartRight departRight);

    /**
     *  更新 部门所有权限
     */
    @Update("update ah_depart_right set  " +
            "   del= 1 " +
            "   where adr_depart = #{depart} " +
            "   ${filter} ")
    void updateDepartRightDelAll(@Param("depart")String depart,
                                 @Param("filter")String filter);

    /**
     *  更新 删除部门员工
     */
    @Update("update ah_user_depart set  " +
            "   del= #{del} " +
            "   where aud_id = #{aud_id} ")
    void updateUserDepartDel(AhUserDepart userdepart);

    /**
     *  更新 删除部门员工
     */
    @Update("update ah_user_depart set  " +
            "   del= 1 " +
            "   where aud_depart =#{depart} and  aud_user = #{user} ")
    void updateUserDepartDelUidDid(@Param("depart") String depart,
                                   @Param("user") String user);

    /**
     *  更新 删除部门员工
     */
    @Update("update ah_user_depart set  " +
            "   del=1 " +
            "   where aud_user = #{user} " +
            "    ${filter} ")
    void updateUserDepartDelAll(@Param("user") String user,
                                @Param("filter") String filter);


    /**
     * 获取  一级部门
     */
    @Select("select ad.* " +
            "   from ah_depart ad " +
            "   where ifnull(ad.del,0)=0  and ifnull(ad_pid,'')='' " +
            "   ${filter} " +
            "   order by create_date  " )
    List<DepartParentBean> getFirstDeparts(@Param("filter") String filter);

    /**
     * 获取 非一级部门
    */
    @Select("select ad.* " +
            "   from ah_depart ad " +
            "   where ifnull(ad.del,0)=0  and ifnull(ad_pid,'')<>'' " +
            "   ${filter} " +
            "   order by create_date  " )
    List<DepartParentBean> getUnFirstDeparts(@Param("filter") String filter);


    /**
     * 获取  部门
     */
    @Select("select * " +
            "   from ah_depart ad " +
            "   where ad.ad_id =#{id} " +
            "   order by create_date desc limit 1 " )
    AhDepart getDepartOneKey(@Param("id") String id);

    /**
     * 获取  部门员工
     */
    @Select("select * " +
            "   from ah_user_depart aud " +
            "   where aud.aud_depart =#{depart} and aud.aud_user =#{user} " +
            "   order by create_date desc limit 1 " )
    AhUserDepart getDepartUserOneAssembleKey(@Param("depart") String depart,
                                             @Param("user") String user);


    /**
     * 获取  一级菜单权限
     */
    @Select("select am.*," +
            "   ar.ar_id right_id," +
            "   if(exists(select * " +
            "               from ah_depart_right adr " +
            "               where ifnull(adr.del,0)=0 and adr.adr_depart=#{depart}" +
            "               and adr.adr_right = ar.ar_id )," +
            "   1,0) select_flg " +
            "   from  ah_right ar " +
            "   left join ah_menu am on am.am_id = ar.ar_menu " +
            "   where ifnull(am.del,0)=0 and ifnull(ar.del,0)=0  " +
            "   and ifnull(am_pid,'')='' " +
            "   ${filter} " +
            "   order by am_sort,create_date " )
    List<RightParentBean> getFirstRights(@Param("depart")String depart,
                                         @Param("filter") String filter);

    /**
     * 获取 非一级菜单权限
     */
    @Select("select am.*," +
            "   ar.ar_id right_id, " +
            "   if(exists(select * " +
            "               from ah_depart_right adr " +
            "               where ifnull(adr.del,0)=0 and adr.adr_depart=#{depart}" +
            "               and adr.adr_right = ar.ar_id )," +
            "   1,0) select_flg " +
            "   from  ah_right ar " +
            "   left join ah_menu am on am.am_id = ar.ar_menu " +
            "   where ifnull(am.del,0)=0 and ifnull(ar.del,0)=0  " +
            "   and ifnull(am_pid,'')<>'' " +
            "   ${filter} " +
            "   order by am_sort,create_date " )
    List<RightParentBean> getUnFirstRights(@Param("depart")String depart,
                                           @Param("filter") String filter);

    /**
     * 获取  部门所有权限
     */
    @Select("select * " +
            "   from ah_depart_right adr " +
            "   where adr.adr_depart =#{depart} " )
    List<AhDepartRight> getDepartRights(@Param("depart") String depart);

    /**
     * 获取  管理人员所有部门
     */
    @Select("select * " +
            "   from ah_user_depart aud " +
            "   where aud.aud_user =#{user} " )
    List<AhUserDepart> getUserDeparts(@Param("user") String user);

}
