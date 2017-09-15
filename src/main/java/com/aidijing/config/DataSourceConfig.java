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
     * @param dataSourceOrder : 订单数据源
     * @param dataSourceUser  : 用户数据源
     * @return
     */
    @Bean
    public DynamicMultipleDataSource multipleDataSource ( @Qualifier( GlobalConstant.ORDER_DATA_SOURCE_KEY ) DataSource dataSourceOrder ,
                                                          @Qualifier( GlobalConstant.USER_DATA_SOURCE_KEY ) DataSource dataSourceUser ) {
        DynamicMultipleDataSource dynamicMultipleDataSource = new DynamicMultipleDataSource();
        Map< Object, Object >     targetDataSources         = new HashMap<>();
        targetDataSources.put( GlobalConstant.ORDER_DATA_SOURCE_KEY , dataSourceOrder );
        targetDataSources.put( GlobalConstant.USER_DATA_SOURCE_KEY , dataSourceUser );
        dynamicMultipleDataSource.setTargetDataSources( targetDataSources );
        dynamicMultipleDataSource.setDefaultTargetDataSource( dataSourceOrder );
        return dynamicMultipleDataSource;
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
    public SqlSessionFactory sqlSessionFactory ( DynamicMultipleDataSource dynamicMultipleDataSource ) throws
                                                                                                       Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource( dynamicMultipleDataSource );
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager ( DynamicMultipleDataSource dynamicMultipleDataSource ) throws
                                                                                                                   Exception {
        return new DataSourceTransactionManager( dynamicMultipleDataSource );
    }

    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean ( DynamicMultipleDataSource dynamicMultipleDataSource ) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource( dynamicMultipleDataSource );
        return sqlSessionFactoryBean;
    }


}



