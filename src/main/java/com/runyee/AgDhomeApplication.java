package com.runyee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

// 开启缓存
@EnableCaching
//开启定时任务
@EnableScheduling
/**
 * 开启多数据源
 * 禁掉 DataSourceAutoConfiguration，因为它会读取application.properties文件的spring.datasource.*属性并自动配置单数据源
 * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AgDhomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgDhomeApplication.class, args);
	}
}
