package com.aidijing.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/6
 */
//@Aspect
//@Order( 1 )
//@Component
public class DataSourceSwitch {


    @Before( "execution(* com.aidijing.order.*.*Service.*(..))" )
    public void dataPlatform ( JoinPoint joinPoint ) {
        setDataSourceKey( joinPoint , GlobalConstant.ORDER_DATA_SOURCE_KEY );
    }

    @Before( "execution(* com.aidijing.user.*.*Service.*(..))" )
    public void shop ( JoinPoint joinPoint ) {
        setDataSourceKey( joinPoint , GlobalConstant.USER_DATA_SOURCE_KEY );
    }


    /**
     * 设置数据源key
     */
    private void setDataSourceKey ( JoinPoint joinPoint , final String defaultKey ) {
        final Method            method            = this.currentMethod( joinPoint );
        final DynamicDataSource dynamicDataSource = method.getAnnotation( DynamicDataSource.class );
        if ( Objects.isNull( dynamicDataSource ) ) {
            DynamicMultipleDataSource.setDataSourceKey( defaultKey );
            return;
        }
        DynamicMultipleDataSource.setDataSourceKey( dynamicDataSource.value() );
    }


    /**
     * 获取当前执行的方法
     */
    private Method currentMethod ( JoinPoint joinPoint ) {
        return ( ( MethodSignature ) joinPoint.getSignature() ).getMethod();
    }
}
