package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.ag_home.AhVideo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface VideoDao {

    /**
     * 新增 视频
     */
    @Insert("insert into ah_video( av_id, av_business, av_title, av_content, av_url, del, create_date ) " +
            "   values( #{av_id}, #{av_business}, #{av_title}, #{av_content}, #{av_url}, #{del}, #{create_date} )")
    @SelectKey(before=true,keyProperty="av_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertVideo(AhVideo video);

    /**
     * 获取用户列表
     */
    @Select("select * " +
            "   from ah_video " +
            "   where ifnull(del,0)=0 and av_business=#{business} " +
            "   limit #{page},#{rows}")
    List<AhVideo> getVideosBusiness(@Param("business")int business,
                                    @Param("page")int page,
                                    @Param("rows")int rows);



}
