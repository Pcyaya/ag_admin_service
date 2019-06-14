package com.runyee.config.db.datasource;


import com.runyee.config.db.dynamic.DynamicDataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * Created by cheri on 2018/2/26.
 */
@Configuration
@PropertySource({"classpath:anygo.properties",
                 "classpath:aetos_go.properties",
                 "classpath:ag_home.properties",
                 "classpath:spk_server.properties"})//注意路径
public class DataSourceConfig {
    /**
     * 配置datasource
     * */
    @Bean(name = DynamicDataSourceConfig.anygo)
    @Qualifier(DynamicDataSourceConfig.anygo)
    @Primary
    @ConfigurationProperties(prefix = "spring.anygo") // anygo.properteis中对应属性的前缀
    public DataSource anygo() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置datasource
     * */
    @Bean(name = DynamicDataSourceConfig.aetos_go)
    @Qualifier(DynamicDataSourceConfig.aetos_go)
    @Primary
    @ConfigurationProperties(prefix = "spring.aetos_go") // aetos_go.properteis中对应属性的前缀
    public DataSource aetos_go() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置datasource
     * */
    @Bean(name = "ag_home")
    @Qualifier("ag_home")
    @Primary
    @ConfigurationProperties(prefix = "spring.ag_home") // ag_home.properteis中对应属性的前缀
    public DataSource ag_home() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 配置datasource
     * */
    @Bean(name = "spk_server")
    @Qualifier("spk_server")
    @Primary
    @ConfigurationProperties(prefix = "spring.spk_server") // spk_server.properteis中对应属性的前缀
    public DataSource smartspeaker() {
        return DataSourceBuilder.create().build();
    }

}

