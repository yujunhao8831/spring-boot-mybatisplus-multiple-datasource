package com.aidijing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源路由
 *
 * @author : 披荆斩棘
 * @date : 2017/9/6
 */
@Configuration
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal< String > DATA_SOURCE_KEY = new ThreadLocal<>();

    static void setDataSourceKey ( String dataSource ) {
        DATA_SOURCE_KEY.set( dataSource );
    }

    private static void clear () {
        DATA_SOURCE_KEY.remove();
    }

    @Override
    protected Object determineCurrentLookupKey () {
        final String lookupKey = DATA_SOURCE_KEY.get();
        clear();
        return lookupKey;
    }


}
