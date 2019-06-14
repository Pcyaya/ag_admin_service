package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyVisitor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AgTestDao {

    /**
     * 获取用户列表
    */
    @Select("select * from ry_visitor limit 0,10 ")
    List<RyVisitor> getVisitors();





}
