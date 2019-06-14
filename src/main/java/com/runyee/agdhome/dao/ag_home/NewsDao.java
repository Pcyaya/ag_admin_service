package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.*;
import com.runyee.agdhome.entity.ex.NewsBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsDao {

    /**
     * 新增 新闻
     */
    @Insert("insert into ah_news( an_id, an_business, an_title, an_content, an_source, an_url, an_origin, an_audit, del, create_date ) " +
            "   values( #{an_id}, #{an_business}, #{an_title}, #{an_content}, #{an_source}, #{an_url}, #{an_origin}, #{an_audit}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="an_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertNews(AhNews news);

    /**
     * 新增 新闻类别
     */
    @Insert("insert into ah_news_business(  anb_id, anb_name, anb_sort, del, create_date ) " +
            "   values(  #{anb_id}, #{anb_name}, #{anb_sort}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="anb_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertNewsBusiness(AhNewsBusiness business);

    /**
     * 新增 新闻详情
     */
    @Insert("insert into ah_news_detail( and_id, and_news, and_title, and_content, and_type, and_url, and_align, and_sort, del, create_date ) " +
            "   values(  #{and_id}, #{and_news}, #{and_title}, #{and_content}, #{and_type}, #{and_url}, #{and_align}, #{and_sort}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="and_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertNewsDetail(AhNewsDetail detail);


    /**
     * 新增 首页新闻
     */
    @Insert("insert into ah_news_index( ani_id, ani_news, del, create_date ) " +
            "   values( #{ani_id}, #{ani_news}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="ani_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertNewsIndex(AhNewsIndex index);

    /**
     * 新增 旅游资讯
     */
    @Insert("insert into ah_news_travel_info( anti_id, anti_news, del, create_date ) " +
            "   values( #{anti_id}, #{anti_news}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="anti_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertNewsTravelInfo(AhNewsTravelInfo travelInfo);

    /**
     * 删除新闻
     */
    @Delete("delete " +
            "   from  ah_news   " +
            "   where an_id = #{an_id} ")
    void deleteNews(AhNews news);

    /**
     * 更新
     */
    @Update("update ah_news set  " +
            "   del= #{del} " +
            "   where an_id = #{an_id} ")
    void updateDelNews(AhNews news);


    /**
     * 更新
     */
    @Update("update ah_news set  " +
            "   an_audit= #{an_audit} " +
            "   where an_id = #{an_id} ")
    void updateAuditNews(AhNews news);

    /**
     * 更新
     */
    @Update("update ah_news_detail set  " +
            "   del= 1 " +
            "   where and_news = #{news} ")
    void updateDelNewsDetail(@Param("news")String news);

    /**
     * 更新 新闻信息
     */
    @Update("update ah_news set  " +
            "   an_business= #{an_business}, " +
            "   an_title= #{an_title}, " +
            "   an_content= #{an_content}, " +
            "   an_audit= #{an_audit}, " +
            "   an_url= #{an_url} " +
            "   where an_id = #{an_id} ")
    void updateNews(AhNews news);


    /**
     * 删除 新闻资讯
     */
    @Update("update ah_news_travel_info set  " +
            "   del= #{del} " +
            "   where anti_news = #{news} ")
    void updateDelNewsTravelInfo(@Param("del")int del,
                                 @Param("news")String news);

    /**
     * 删除 新闻资讯
     */
    @Update("update ah_news_index set  " +
            "   del= #{del} " +
            "   where ani_news = #{news} ")
    void updateDelNewsIndex(@Param("del")int del,
                            @Param("news")String news);

    /**
     * 更新 新闻资讯
     */
    @Update("update ah_news_travel_info set  " +
            "   del= #{del}," +
            "   create_date=#{create_date} " +
            "   where anti_id = #{anti_id} ")
    void updateNewsTravelInfo(AhNewsTravelInfo info);

    /**
     * 更新 新闻资讯
     */
    @Update("update ah_news_index set  " +
            "   del= #{del}," +
            "   create_date=#{create_date} " +
            "   where ani_id = #{ani_id} ")
    void updateNewsIndex(AhNewsIndex index);

    @Select("select an.*," +
            "   ifnull((select ans.ans_name " +
            "       from ah_news_source ans  " +
            "       where ifnull(ans.del,0)=0 and ans.ans_id=an.an_source " +
            "       order by ans.create_date desc limit 1 ),'')  source_name, " +
            "   ifnull((select nd.and_url " +
            "       from ah_news_detail nd  " +
            "       where ifnull(nd.del,0)=0 and nd.and_news=an.an_id " +
            "       and ifnull(nd.and_type,0)=1  " +
            "       order by nd.create_date  limit 1 ),'')  img " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by create_date desc,an_id desc " +
            "   limit 1 ")
    NewsBean getNewsOneFilter(@Param("filter")String filter);

    @Select("select an.*," +
            "   ifnull((select ans.ans_name " +
            "       from ah_news_source ans  " +
            "       where ifnull(ans.del,0)=0 and ans.ans_id=an.an_source " +
            "       order by ans.create_date desc limit 1 ),'')  source_name, " +
            "   ifnull((select nd.and_url " +
            "       from ah_news_detail nd  " +
            "       where ifnull(nd.del,0)=0 and nd.and_news=an.an_id " +
            "       and ifnull(nd.and_type,0)=1  " +
            "       order by nd.create_date  limit 1 ),'')  img " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 " +
            "   and an.an_id = #{id}" +
            "   order by create_date desc " +
            "   limit 1 ")
    NewsBean getNewsBeanOneKey(@Param("id")String id);


    @Select("select an.*," +
            "   ifnull((select ans.ans_name " +
            "       from ah_news_source ans  " +
            "       where ifnull(ans.del,0)=0 and ans.ans_id=an.an_source " +
            "       order by ans.create_date desc limit 1 ),'')  source_name, " +
            "   ifnull((select anb.anb_name" +
            "               from ah_news_business anb " +
            "               where ifnull(anb.del,0)=0 and anb.anb_id = an.an_business " +
            "               order by anb.create_date desc limit 1  ),'')  business_name, " +
            "   if(exists(select anti.anti_id " +
            "                    from ah_news_travel_info anti " +
            "                    where ifnull(anti.del,0)=0 and anti.anti_news = an.an_id ),1,0)  app_flg, " +
            "   if(exists(select ani.ani_id " +
            "                    from ah_news_index ani " +
            "                    where ifnull(ani.del,0)=0 and ani.ani_news = an.an_id ),1,0)  index_flg, " +
            "   ifnull((select nd.and_url " +
            "       from ah_news_detail nd  " +
            "       where ifnull(nd.del,0)=0 and nd.and_news=an.an_id " +
            "       and ifnull(nd.and_type,0)=1  " +
            "       order by nd.create_date  limit 1 ),'')  img " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 " +
            "   and an.an_id = #{id}" +
            "   order by create_date desc " +
            "   limit 1 ")
    NewsBean getManNewsBeanOneKey(@Param("id")String id);


    @Select("select * " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 and an.an_id = #{id} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhNews getNewsOneKey(@Param("id")String id);

    /**
     * 新闻列表 分页 总数
     */
    @Select("select count(1) " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 ${filter} ")
    int getNewsPagesTotal(@Param("filter")String filter);

    @Select("select * " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by create_date desc,an_id desc " +
            "   limit #{page},#{rows} ")
    List<AhNews> getNewsList(@Param("filter")String filter,
                             @Param("page")int page,
                             @Param("rows")int rows);

    @Select("select an.*, " +
            "   ifnull((select ans.ans_name " +
            "       from ah_news_source ans  " +
            "       where ifnull(ans.del,0)=0 and ans.ans_id=an.an_source " +
            "       order by ans.create_date desc limit 1 ),'')  source_name, " +
            "   ifnull((select nd.and_url " +
            "       from ah_news_detail nd  " +
            "       where ifnull(nd.del,0)=0 and nd.and_news=an.an_id " +
            "       and ifnull(nd.and_type,0)=1  " +
            "       order by nd.create_date  limit 1 ),'')  img " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by create_date desc, an_id desc " +
            "   limit #{page},#{rows} ")
    List<NewsBean> getNewsBeanList(@Param("filter")String filter,
                                  @Param("page")int page,
                                  @Param("rows")int rows);

    @Select("select * " +
            "   from ah_news_business anb " +
            "   where ifnull(del,0)=0 " +
            "   order by anb_sort, create_date desc " +
            "   limit #{page},#{rows} ")
    List<AhNewsBusiness> getNewsBusinessAllList(@Param("page")int page,
                             @Param("rows")int rows);

    @Select("select * " +
            "   from ah_news_business anb " +
            "   where ifnull(del,0)=0 and anb_id =#{id} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhNewsBusiness getNewsBusinessOneKey(@Param("id")String id);


    /**
     * 获取新闻列表
     */
    @Select("select " +
            "   (select  an2.an_id  " +
            "       from ah_news an2 " +
            "       where ifnull(an2.del,0)=0 ${filter} and concat(an2.create_date ,an2.an_id) > concat(an.create_date ,an.an_id) " +
            "       and an2.an_id <>an.an_id  " +
            "       order by concat(an2.create_date ,an2.an_id) limit 1 ) last, " +
            "   an.an_id  current, " +
            "   (select  an2.an_id  " +
            "       from ah_news an2 " +
            "       where ifnull(an2.del,0)=0 ${filter} and concat(an2.create_date ,an2.an_id) < concat(an.create_date ,an.an_id) " +
            "       and an2.an_id <>an.an_id " +
            "       order by concat(an2.create_date ,an2.an_id) desc limit 1 ) next " +
            "   from ah_news  an " +
            "   where ifnull(an.del,0)=0 and  an.an_id = #{id} limit 1 ")
    Map<String,String> getNewsPage(@Param("id") String  id,
                                   @Param("filter") String  filter);


    @Select("select * " +
            "   from ah_news_detail nd " +
            "   where ifnull(del,0)=0 and nd.and_news = #{news}" +
            "   order by and_sort,create_date desc ")
    List<AhNewsDetail> getNewsDetailList(@Param("news")String news);

    @Select("select * " +
            "   from ah_news_source ans " +
            "   where ifnull(ans.del,0)=0 " +
            "   order by ans_sort,create_date desc ")
    List<AhNewsSource> getNewsSourceAll();

    @Select("select * " +
            "   from ah_news_business anb " +
            "   where ifnull(del,0)=0 and anb_name =#{name} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhNewsBusiness getNewsBusinessOneName(@Param("name")String name);

    @Select("select * " +
            "   from ah_news an" +
            "   where ifnull(del,0)=0 " +
            "   and an_title =#{title} and create_date =#{date}" +
            "   limit 1 ")
    AhNews getNewsBusinessOneTitleDate(@Param("title")String title,
                                       @Param("date")String date);

    @Select("select * " +
            "   from ah_news an" +
            "   where ifnull(del,0)=0 " +
            "   and an_origin =#{origin} " +
            "   limit 1 ")
    AhNews getNewsBusinessOneOrigin(@Param("origin")String origin);

    @Select("select * " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by create_date desc,an_id desc ")
    List<AhNews> getNewsListFilter(@Param("filter")String filter);

    @Select("select * " +
            "   from ah_news_index ani " +
            "   left join ah_news an on an.an_id = ani.ani_news" +
            "   where ifnull(ani.del,0)=0  and ifnull(an.del,0)=0 " +
            "   order by ani.create_date desc " +
            "   limit 1 ")
    AhNewsIndex getNewsIndexOneLast();


    @Select("select * " +
            "   from ah_news_index ani " +
            "   where ani_news=#{news} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhNewsIndex getNewsIndexOneNewsDel(@Param("news")String news);

    /**
     * 获取新闻列表
     */
    @Select("select * " +
            "   from ah_news  an " +
            "   where ifnull(an.del,0)=0 and  an.an_id <> #{id} " +
            "   ${filter} " +
            "   ${order} " +
            "   limit ${limit} ")
    List<AhNews> getNewsRelevants(@Param("id") String  id,
                                  @Param("filter") String  filter,
                                  @Param("order") String  order,
                                  @Param("limit")int limit);


    @Select("select * " +
            "   from ah_news_source ans " +
            "   where ifnull(del,0)=0 " +
            "   ${filter} " +
            "   order by ans_sort, create_date desc " +
            "   limit #{page},#{rows} ")
    List<AhNewsSource> getNewsSourceAllList(@Param("filter")String filter,
                                            @Param("page")int page,
                                            @Param("rows")int rows);


    @Select("select an.*, " +
            "   ifnull((select ans.ans_name " +
            "       from ah_news_source ans  " +
            "       where ifnull(ans.del,0)=0 and ans.ans_id=an.an_source " +
            "       order by ans.create_date desc limit 1 ),'')  source_name, " +
            "   ifnull((select anb.anb_name" +
            "               from ah_news_business anb " +
            "               where ifnull(anb.del,0)=0 and anb.anb_id = an.an_business " +
            "               order by anb.create_date desc limit 1  ),'')  business_name, " +
            "   if(an.an_id in (select *  " +
            "                       from (select anti.anti_news " +
            "                                       from ah_news_travel_info anti " +
            "                                       where ifnull(anti.del,0)=0 " +
            "                                       order by anti.create_date desc limit 5 ) as app ),1,0)  app_flg, " +
            "   if(exists(select ani.ani_id " +
            "                    from ah_news_index ani " +
            "                    where  ani.ani_news = an.an_id " +
            "                    and ani.ani_id = (select ani2.ani_id  " +
            "                                              from ah_news_index ani2 " +
            "                                              where ifnull(ani2.del,0)=0  " +
            "                                              order by ani2.create_date desc limit 1 ) ),1,0)  index_flg, " +
            "   ifnull((select nd.and_url " +
            "       from ah_news_detail nd  " +
            "       where ifnull(nd.del,0)=0 and nd.and_news=an.an_id " +
            "       and ifnull(nd.and_type,0)=1  " +
            "       order by nd.create_date  limit 1 ),'')  img " +
            "   from ah_news an " +
            "   where ifnull(del,0)=0 " +
            "   ${filter}" +
            "   order by create_date desc, an_id desc " +
            "   limit #{page},#{rows} ")
    List<NewsBean> getManNewsBeanList(@Param("filter")String filter,
                                      @Param("page")int page,
                                      @Param("rows")int rows);

    @Select("select * " +
            "   from ah_news_travel_info anti " +
            "   where  anti.anti_news = #{news} " +
            "   order by create_date desc " +
            "   limit 1 ")
    AhNewsTravelInfo getNewsTravelInfoOneNewsDel(@Param("news")String news);
}
