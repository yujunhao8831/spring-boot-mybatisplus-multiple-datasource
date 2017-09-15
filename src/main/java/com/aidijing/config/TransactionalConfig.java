package com.aidijing.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 披荆斩棘
 * @date : 2017/9/8
 */
@Configuration
public class TransactionalConfig {


    /**
     * 配置事务拦截器
     *
     * @param transactionManager : 事务管理器
     * @return
     */
    @Bean
    public TransactionInterceptor customizeTransactionInterceptor ( PlatformTransactionManager transactionManager ) {
        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();

        RuleBasedTransactionAttribute readOnly = this.readOnlyTransactionRule();
        RuleBasedTransactionAttribute required = this.requiredTransactionRule();

        Map< String, TransactionAttribute > nameMap = new HashMap<>();
        nameMap.put( "get*" , readOnly );
        nameMap.put( "count*" , readOnly );
        nameMap.put( "find*" , readOnly );
        nameMap.put( "query*" , readOnly );
        nameMap.put( "select*" , readOnly );
        nameMap.put( "load*" , readOnly );
        nameMap.put( "list*" , readOnly );
        nameMap.put( "*" , readOnly );

        nameMap.put( "add*" , required );
        nameMap.put( "save*" , required );
        nameMap.put( "insert*" , required );
        nameMap.put( "delete*" , required );
        nameMap.put( "update*" , required );
        nameMap.put( "edit*" , required );
        nameMap.put( "do*" , required );
        nameMap.put( "begin*" , required );
        nameMap.put( "batch*" , required );
        nameMap.put( "pay*" , required );
        nameMap.put( "register*" , required );
        nameMap.put( "service*" , required );
        nameMap.put( "out*" , required );
        nameMap.put( "recharge*" , required );
        nameMap.put( "into*" , required );
        nameMap.put( "subtraction*" , required );
        nameMap.put( "add*" , required );
        nameMap.put( "create*" , required );
        nameMap.put( "service*" , required );
        nameMap.put( "buy*" , required );
        nameMap.put( "dispatcher*" , required );
        nameMap.put( "extract*" , required );
        nameMap.put( "reversal*" , required );

        transactionAttributeSource.setNameMap( nameMap );
        return new TransactionInterceptor( transactionManager , transactionAttributeSource );
    }


    /**
     * 配置事务 AOP 切入点
     *
     * @param transactionInterceptor : {@link #customizeTransactionInterceptor(PlatformTransactionManager)}
     */
    @Bean
    public AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor ( @Qualifier( "customizeTransactionInterceptor" ) TransactionInterceptor transactionInterceptor ) {
        AspectJExpressionPointcutAdvisor pointcut = new AspectJExpressionPointcutAdvisor();
        pointcut.setAdvice( transactionInterceptor );
        pointcut.setExpression( "execution (* com.aidijing.*.*service.*.*(..))" );
        return pointcut;
    }


    /**
     * 支持当前事务;如果不存在创建一个新的
     */
    private RuleBasedTransactionAttribute requiredTransactionRule () {
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        required.setRollbackRules( Collections.singletonList( new RollbackRuleAttribute( Exception.class ) ) );
        required.setPropagationBehavior( TransactionDefinition.PROPAGATION_REQUIRED );
        required.setTimeout( TransactionDefinition.TIMEOUT_DEFAULT );
        return required;
    }

    /**
     * 只读事务
     */
    private RuleBasedTransactionAttribute readOnlyTransactionRule () {
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly( true );
        readOnly.setPropagationBehavior( TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
        return readOnly;
    }


}
