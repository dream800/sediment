package com.dream.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * 描述信息
 *
 * @author dream
 * @create 2022-06-04
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
})
public class MyBatisLogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        PreparedStatement statement = (PreparedStatement) invocation.getArgs()[0];
        PreparedStatement sqlStatement = null;
        //等待方法结果出来
        Object result = invocation.proceed();

        if (Proxy.isProxyClass(statement.getClass())) {
            InvocationHandler handler = Proxy.getInvocationHandler(statement);
            if (handler.getClass().getName().endsWith(".PreparedStatementLogger")) {
                Field field = handler.getClass().getDeclaredField("statement");
                field.setAccessible(true);
                sqlStatement = (PreparedStatement) field.get(handler);
                //原文参考：https://www.zhangshengrong.com/p/OgN5DgLDan/
                //以下应该是使用了 druid 原因 ，没有使用的话下面几行代码请注释掉
//                Field stmt = sqlStatement.getClass().getDeclaredField("stmt");
//                stmt.setAccessible(true);
//                sqlStatement = (PreparedStatement) stmt.get(sqlStatement);
//                Field clientStatement = sqlStatement.getClass().getDeclaredField("statement");
//                clientStatement.setAccessible(true);
//                sqlStatement = (PreparedStatement) clientStatement.get(sqlStatement);

            }
        }
        System.out.println("》》》》》===================================================mybatis execute sql =============================================================================");
        System.out.println((sqlStatement.toString().replaceAll("\\s{3,}", " ").substring(sqlStatement.toString().indexOf(":") + 1)));
        System.out.println("》》》》》===================================================mybatis execute sql =============================================================================");

        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

