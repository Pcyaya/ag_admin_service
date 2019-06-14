package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhMenu;
import com.runyee.agdhome.entity.ex.MenuBean;
import com.runyee.agdhome.entity.ex.MenuParentBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface MenuDao {

    /**
     * 新增 菜单
     */
    @Insert("insert into ah_menu( am_id, am_name, am_url, am_pid, am_level, am_view, am_icon, am_sort, del, create_date ) " +
            "   values( #{am_id}, #{am_name}, #{am_url}, #{am_pid}, #{am_level}, #{am_view}, #{am_icon}, #{am_sort}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="am_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertMenu(AhMenu menu);

    /**
     *  更新 菜单顺序
     */
    @Update("update ah_menu set  " +
            "   am_sort= #{am_sort} " +
            "   where am_id = #{am_id} ")
    void updateMenuSort(AhMenu menu);

    /**
     *  更新 菜单可见
     */
    @Update("update ah_menu set  " +
            "   am_view= #{am_view} " +
            "   where am_id = #{am_id} ")
    void updateMenuView(AhMenu menu);

    /**
     *  更新 菜单顺序
     */
    @Update("update ah_menu set  " +
            "   am_level= #{am_level} " +
            "   where am_id = #{am_id} ")
    void updateMenuLevel(AhMenu menu);

    /**
     *  更新 菜单顺序
     */
    @Update("update ah_menu set  " +
            "   del= #{del} " +
            "   where am_id = #{am_id} ")
    void updateMenuDel(AhMenu menu);

    /**
     *  更新 菜单pid
     */
    @Update("update ah_menu set  " +
            "  am_level= #{am_level}, " +
            "  am_pid= #{am_pid} " +
            "  where am_id = #{am_id} ")
    void updateMenuParent(AhMenu menu);

    /**
     *  更新 菜单 信息
     */
    @Update("update ah_menu set  " +
            "   am_name= #{am_name}, " +
            "   am_url= #{am_url}, " +
            "   am_level= #{am_level}, " +
            "   am_view= #{am_view}, " +
            "   am_icon= #{am_icon} " +
            "   where am_id = #{am_id} ")
    void updateMenuInfo(AhMenu menu);

    /**
     * 获取  一级菜单
     */
    @Select("select am.* " +
            "   from ah_menu am " +
            "   where ifnull(am.del,0)=0 " +
            "   and ifnull(am_pid,'')='' " +
            "   ${filter} " +
            "   order by am_sort,create_date  " )
    List<MenuParentBean> getFirstMenus(@Param("filter") String filter);

    /**
     * 获取 非一级菜单
    */
    @Select("select am.* " +
            "   from ah_menu am  " +
            "   where ifnull(am.del,0)=0 " +
            "   and ifnull(am_pid,'')<>'' " +
            "   ${filter} " +
            "   order by am_sort ,create_date  " )
    List<MenuParentBean> getUnFirstMenus(@Param("filter") String filter);

    /**
     * 获取  一级菜单
     */
    @Select("select am.*," +
            "   if(exists(select ar_id " +
            "               from ah_right ar " +
            "               where ifnull(ar.del,0)=0 and ifnull(ar_opt,0)=0 " +
            "               and ar_menu = am.am_id ),1,0) right_flg " +
            "   from ah_menu am " +
            "   where ifnull(am.del,0)=0 " +
            "   and ifnull(am_pid,'')='' " +
            "   order by am_sort ,create_date  " )
    List<MenuParentBean> getFirstMenusAll();

    /**
     * 获取 非一级菜单
     */
    @Select("select am.*, " +
            "   if(exists(select ar_id " +
            "               from ah_right ar " +
            "               where ifnull(ar.del,0)=0 and ifnull(ar_opt,0)=0 " +
            "               and ar_menu = am.am_id ),1,0) right_flg " +
            "   from ah_menu am  " +
            "   where ifnull(am.del,0)=0 " +
            "   and ifnull(am_pid,'')<>'' " +
            "   order by am_sort ,create_date  " )
    List<MenuParentBean> getUnFirstMenusAll();


    /**
     * 获取  菜单
     */
    @Select("select * " +
            "   from ah_menu am " +
            "   where am.am_id =#{id} " +
            "   order by create_date desc limit 1 " )
    AhMenu getMenuOneKey(@Param("id") String id);


    /**
     * 获取  菜单
     */
    @Select("select * " +
            "   from ah_menu am " +
            "   where ifnull(am.am_pid,'') =#{pid} " +
            "   order by am_sort desc, create_date desc limit 1 " )
    AhMenu getMenuOneLastPid(@Param("pid") String pid);

    /**
     * 获取  菜单
     */
    @Select("select * " +
            "   from ah_menu am " +
            "   where ifnull(am.del,0)=0  and ifnull(am.am_pid,'') =#{pid} " +
            "   order by am_sort , create_date   " )
    List<AhMenu> getMenusPid(@Param("pid") String pid);

    /**
     * 获取  菜单
     */
    @Select("select am.*, " +
            "   if(exists(select am1.am_id " +
            "               from ah_menu am1" +
            "               where ifnull(am1.del,0)=0 and ifnull(am1.am_pid,'') =am.am_id ), " +
            "       1,0 " +
            "    ) leaf_flg " +
            "   from ah_menu am " +
            "   where am.am_id =#{id} " +
            "   order by create_date desc limit 1 " )
    MenuBean getMenuBeanOneKey(@Param("id") String id);

    /**
     * 获取  菜单
     */
    @Select("select am.*," +
            "   if(exists(select am1.am_id " +
            "               from ah_menu am1" +
            "               where ifnull(am1.del,0)=0 and ifnull(am1.am_pid,'') =am.am_id ), " +
            "       1,0 " +
            "    ) leaf_flg " +
            "   from ah_menu am " +
            "   where ifnull(am.am_pid,'') =#{pid} " +
            "   order by am_sort , create_date  " )
    List<MenuBean> getMenuBeansPid(@Param("pid") String pid);





}
