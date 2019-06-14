package com.runyee.agdhome.dao.ag_home;

import com.runyee.agdhome.entity.db.AhTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestDao {

    /**
     * 获取用户列表
    */
    @Select("select * from ah_test limit 0,10 ")
    List<AhTest> getTest();





}
