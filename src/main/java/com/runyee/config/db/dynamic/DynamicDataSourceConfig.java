package com.runyee.config.db.dynamic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cheri on 2018/2/26.
 */
@Configuration
public class DynamicDataSourceConfig {
    public static final String anygo = "anygo";
    public static final String aetos_go = "aetos_go";
    @Autowired
    @Qualifier(anygo)
    private DataSource ds_anygo;

    @Autowired
    @Qualifier(aetos_go)
    private DataSource ds_aetos_go;


    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dynamic")
    public DataSource dyDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(ds_anygo);
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put(anygo, ds_anygo);//测试库 集群
        dsMap.put(aetos_go, ds_aetos_go);//正式库 集群
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }



}
