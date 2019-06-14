package com.runyee.config.db.dynamic;


import com.runyee.agdhome.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by cheri on 2018/11/8.
 * 自定义一个javax.sql.DataSource接口的实现，这里只需要继承Spring为我们预先实现好的父类AbstractRoutingDataSource即可：
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("数据源为{}", DataUtil.getCurrent().getDb());
        return DataUtil.getCurrent().getDb();
    }

}
