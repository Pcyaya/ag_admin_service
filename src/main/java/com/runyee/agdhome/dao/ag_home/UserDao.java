package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhAgentApply;
import com.runyee.agdhome.entity.db.ag_home.AhUser;
import com.runyee.agdhome.entity.db.ag_home.AhUserToken;
import com.runyee.agdhome.entity.ex.UserBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface UserDao {

    /**
     * 新增 管理人员
     */
    @Insert("insert into ah_user( au_id, au_no, au_name, au_phone, au_pwd, au_position, au_level, au_email, au_sex, au_descript, del, create_date  ) " +
            "   values( #{au_id}, #{au_no}, #{au_name}, #{au_phone}, #{au_pwd}, #{au_position}, #{au_level}, #{au_email}, #{au_sex}, #{au_descript}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="au_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUser(AhUser user);

    /**
     * 新增 管理人员token
     */
    @Insert("insert into ah_user_token( aut_id, aut_user, aut_token, aut_expire, aut_source, aut_invalid, aut_acs_uri, aut_acs_date, create_date ) " +
            "   values( #{aut_id}, #{aut_user}, #{aut_token}, #{aut_expire}, #{aut_source}, #{aut_invalid}, #{aut_acs_uri}, #{aut_acs_date}, #{create_date} )")
    @SelectKey(before=true,keyProperty="aut_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertUserToken(AhUserToken token);



    /**
     * 更新 游客 token 过期时间
     */
    @Update("update ah_user_token set  " +
            "   aut_expire=#{aut_expire}," +
            "   aut_acs_uri=#{aut_acs_uri}," +
            "   aut_acs_date=#{aut_acs_date} " +
            "   where aut_id = #{aut_id} ")
    void updateUserTokenExpire(AhUserToken token);

    /**
     * 更新 导游token 过期时间
     */
    @Update("update ah_user_token set  " +
            "   aut_invalid=1 " +
            "   where ifnull(aut_invalid,0)=0 " +
            "   and aut_user = #{user} and aut_source = #{source} ")
    void updateUserTokenInvalid(@Param("user")String user,
                                @Param("source")int source);

    /**
     * 更新 导游token 过期时间
     */
    @Update("update ah_user set  " +
            "   au_pwd=#{au_pwd} " +
            "   where au_id = #{au_id} ")
    void updateUserPwd(AhUser user);

    /**
     * 更新 导游token 过期时间
     */
    @Update("update ah_user set  " +
            "   au_no=#{au_no}, " +
            "   au_name=#{au_name}, " +
            "   au_phone=#{au_phone}, " +
            "   au_pwd=#{au_pwd}, " +
            "   au_position=#{au_position}, " +
            "   au_email=#{au_email}, " +
            "   au_sex=#{au_sex}, " +
            "   au_descript=#{au_descript} " +
            "   where au_id = #{au_id} ")
    void updateUserInfo(AhUser user);

    @Update("update ah_user set  " +
            "   del= #{del} " +
            "   where au_id = #{au_id} ")
    void updateDepartDel(AhUser depart);

    @Select("select * " +
            "   from ah_user au " +
            "   where ifnull(del,0)=0 and au.au_phone = #{phone} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhUser getUserOnePhone(@Param("phone") String phone);

    @Select("select * " +
            "   from ah_user au " +
            "   where ifnull(del,0)=0 and au.au_id = #{id} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhUser getUserOne(@Param("id") String id);

    @Select("select * " +
            "   from ah_user au " +
            "   where  au.au_id = #{id} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhUser getUserOneKey(@Param("id") String id);


    @Select("select * " +
            "   from ah_user_token aut " +
            "   where aut.aut_token = #{token} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhUserToken getUserTokenOne(@Param("token") String token);

    @Select("select * " +
            "   from ah_user au " +
            "   where ifnull(del,0)=0 " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit #{page},#{rows} ")
    List<AhUser> getUsers(@Param("filter") String filter,
                          @Param("page") int page,
                          @Param("rows") int rows);

    @Select("select * " +
            "   from ah_user au " +
            "   where ifnull(del,0)=0 and au.au_id in (${ids}) ")
    List<AhUser> getUsersKeys(@Param("ids") String ids);

    @Select("select au.*," +
            "   (select group_concat(ad.ad_name) " +
            "       from ah_depart ad " +
            "       where ifnull(ad.del,0)=0 " +
            "       and exists(select * " +
            "                   from ah_user_depart aud " +
            "                   where ifnull(aud.del,0)=0 and aud.aud_user = au.au_id " +
            "                   and aud.aud_depart = ad.ad_id " +
            "                   )" +
            "   )departs " +
            "   from ah_user au " +
            "   where ifnull(del,0)=0 " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit #{page},#{rows} ")
    List<UserBean> getUserBeans(@Param("filter") String filter,
                                @Param("page") int page,
                                @Param("rows") int rows);


    @Select("select count(1) " +
            "   from ah_user au " +
            "   where ifnull(del,0)=0 " +
            "   ${filter} ")
    int getUserBeansCount(@Param("filter") String filter);

    @Select("select * " +
            "   from ah_user au " +
            "   where ifnull(del,0)=0 and au.au_name = #{name} " +
            "   ${filter} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhUser getUserOneName(@Param("name") String name,
                          @Param("filter") String filter);

}
