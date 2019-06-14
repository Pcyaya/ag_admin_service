package com.runyee.config.db.environment;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by cheri on 2018/2/26.
 */
@Configuration
@MapperScan(basePackages = {"com.runyee.agdhome.dao.spk_server"}, sqlSessionFactoryRef = "spk_serverSqlSessionFactory")
public class SpkServerConfig {
    @Autowired
    @Qualifier("spk_server")//修饰语
    private DataSource spk_server;

    /**
     * 配置SqlSessionFactory
     * */
    @Bean
    public SqlSessionFactory spk_serverSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(spk_server);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate spk_serverSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(spk_serverSqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }


    /**
     * 配置事务管理器
     * */
    @Bean
    public PlatformTransactionManager spk_serverTransactionManager() {
        return new DataSourceTransactionManager(spk_server);
    }

}
