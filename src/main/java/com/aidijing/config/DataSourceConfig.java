package com.aidijing.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/6
 */
@Configuration
public class DataSourceConfig {

    /**
     * 动态数据源配置
     *
     * @param dataSourceShop         : 商城数据源
     * @param dataSourceDataPlatform : 数据平台数据源
     * @return
     */
    @Bean
    public MultipleDataSource multipleDataSource ( @Qualifier( GlobalConstant.ORDER_DATA_SOURCE_KEY ) DataSource dataSourceShop ,
                                                   @Qualifier( GlobalConstant.USER_DATA_SOURCE_KEY ) DataSource dataSourceDataPlatform ) {
        MultipleDataSource    multipleDataSource = new MultipleDataSource();
        Map< Object, Object > targetDataSources  = new HashMap<>();
        targetDataSources.put( GlobalConstant.ORDER_DATA_SOURCE_KEY , dataSourceShop );
        targetDataSources.put( GlobalConstant.USER_DATA_SOURCE_KEY , dataSourceDataPlatform );
        multipleDataSource.setTargetDataSources( targetDataSources );
        multipleDataSource.setDefaultTargetDataSource( dataSourceShop );
        return multipleDataSource;
    }

    @Primary
    @Bean
    @ConfigurationProperties( prefix = "spring.datasource.druid.order" )
    public DataSource dataSourceOrder () {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties( prefix = "spring.datasource.druid.user" )
    public DataSource dataSourceUser () {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory ( MultipleDataSource multipleDataSource ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource( multipleDataSource );
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager ( MultipleDataSource multipleDataSource ) throws Exception {
        return new DataSourceTransactionManager( multipleDataSource );
    }

    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean ( MultipleDataSource multipleDataSource ) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource( multipleDataSource );
        return sqlSessionFactoryBean;
    }


}



