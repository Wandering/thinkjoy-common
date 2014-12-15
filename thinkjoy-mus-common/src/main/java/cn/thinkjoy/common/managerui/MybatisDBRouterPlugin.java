package cn.thinkjoy.common.managerui;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14/12/14 下午8:53<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Component
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class MybatisDBRouterPlugin implements Interceptor {
    @Autowired
    private HashedDataSource hashedDataSource;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Connection conn = (Connection)invocation.getArgs()[0];

        ((StatementHandler) invocation.getTarget()).getBoundSql().getParameterObject();
        System.out.println(HashedDataSource.DynamicDataSourceHolder.getDataSouce());
        //如果是采用了我们代理,则路由数据源
//        if(conn instanceof ConnectionProxy){
//            StatementHandler statementHandler = (StatementHandler) invocation
//                    .getTarget();
//
//            MappedStatement mappedStatement = null;
//            if (statementHandler instanceof RoutingStatementHandler) {
//                StatementHandler delegate = (StatementHandler) ReflectionUtils
//                        .getFieldValue(statementHandler, "delegate");
//                mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(
//                        delegate, "mappedStatement");
//            } else {
//                mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(
//                        statementHandler, "mappedStatement");
//            }
//            String key = AbstractRWRoutingDataSourceProxy.WRITE;
//
//            if(mappedStatement.getSqlCommandType() == SqlCommandType.SELECT){
//                key = AbstractRWRoutingDataSourceProxy.READ;
//            }else{
//                key = AbstractRWRoutingDataSourceProxy.WRITE;
//            }
//
//            ConnectionProxy conToUse = (ConnectionProxy)conn;
//            conToUse.getTargetConnection(key);
//
//        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public void setHashedDataSource(HashedDataSource hashedDataSource) {
        this.hashedDataSource = hashedDataSource;
    }
}
