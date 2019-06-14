package com.runyee.agdhome.dao.anygo;

import com.runyee.agdhome.entity.db.anygo.RyNumGen;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;


@Mapper
public interface AgNumGenDao {

    /**
     * 新增 编码
     */
    @Insert("insert into ry_num_gen(rng_id, rng_key, rng_num, create_date ) " +
            "   values(#{rng_id}, #{rng_key}, #{rng_num}, #{create_date} )")
    //@  Options(useGeneratedKeys = true,keyProperty = "rng_id")
    @SelectKey(before=true,keyProperty="rng_id",resultType=String.class,statementType= StatementType.STATEMENT,statement="select  uuid()")
    void  insertNumGen(RyNumGen num);


    /**
     * 获取编码
    */
    @Select("select * " +
            "   from ry_num_gen " +
            "   where rng_key = #{key} order by create_date desc limit 1 ")
    RyNumGen getNumGenOne(@Param("key") String key);


    /**
     * 更新 编码
     */
    @Update("update ry_num_gen set  " +
            "   rng_num=(ifnull(rng_num,0)+1) " +
            "   where rng_id = #{rng_id} ")
    void updateNumGen(RyNumGen num);

    /**
     * 更新 编码
     */
    @Update("update ry_num_gen set  " +
            "   rng_num=(ifnull(rng_num,0)-#{rng_num}) " +
            "   where rng_id = #{rng_id} ")
    void updateNumGenSub(RyNumGen num);

}
