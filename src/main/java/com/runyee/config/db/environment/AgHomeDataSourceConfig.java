package com.runyee.config.db.environment;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by cheri on 2018/2/26.
 */
@Configuration
@MapperScan(basePackages = {"com.runyee.agdhome.dao.ag_home"}, sqlSessionFactoryRef = "ag_homesqlSessionFactory")
public class AgHomeDataSourceConfig {
    @Autowired
    @Qualifier("ag_home")//修饰语
    private DataSource ag_home;


    /**
     * 配置SqlSessionFactory
     * */
    @Bean
    public SqlSessionFactory ag_homesqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ag_home); // 使用ag_home数据源, 连接ag_home库
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate ag_homesqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(ag_homesqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }


    /**
     * 配置事务管理器
     * */
    @Bean
    public PlatformTransactionManager ag_homeTransactionManager() {
        return new DataSourceTransactionManager(ag_home);
    }

}
