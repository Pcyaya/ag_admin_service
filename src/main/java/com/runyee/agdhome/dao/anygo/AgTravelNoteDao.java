package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyTravelNote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AgTravelNoteDao {

    /**
     * 获取游记内容
     */
    @Select("select * " +
            "   from ry_travel_note rtn " +
            "   where rtn.rtn_num in (${rtn_ids})" +
            "   order by create_date")
    List<RyTravelNote> getTravelNoteContentByIds(@Param("rtn_ids")String rtn_ids);

    /**
     * 获取单条数据
     */
    @Select("select *" +
            "   from ry_travel_note rtn " +
            "   where rtn.rtn_num = #{rtn_num}")
    RyTravelNote getTravelNoteByNum(@Param("rtn_num")String rtn_num);
}
