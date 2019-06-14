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
@MapperScan(basePackages = {"com.runyee.agdhome.dao.anygo"}, sqlSessionFactoryRef = "anygosqlSessionFactory")
public class AnygoDataSourceConfig {
    //private static Log logger = LogFactory.getLog(DataSourceConfig.class);
    @Autowired
    @Qualifier("dynamic")//修饰语
    private DataSource dynamic;


    /**
     * 配置SqlSessionFactory
     * */
    @Bean
    public SqlSessionFactory anygosqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamic); // 使用 动态数据源
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate anygosqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(anygosqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }


    /**
     * 配置事务管理器
     * */
    @Bean
    public PlatformTransactionManager anygoTransactionManager() {
        return new DataSourceTransactionManager(dynamic);
    }

}
