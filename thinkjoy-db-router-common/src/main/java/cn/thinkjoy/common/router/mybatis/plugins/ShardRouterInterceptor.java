package cn.thinkjoy.common.router.mybatis.plugins;

import cn.thinkjoy.common.domain.ShardBaseDomain;
import cn.thinkjoy.common.router.DSNameRuleManager;
import cn.thinkjoy.common.router.annotation.DBShardAnnotation;
import cn.thinkjoy.common.router.mybatis.DataSourceContextHolder;
import cn.thinkjoy.common.utils.ShardDbContext;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wdong on 15/12/23.
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
@Component
public class ShardRouterInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShardRouterInterceptor.class);
    //    private static final String tag = TableSegInterceptor.class.getName();
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final String DS_PREFIX = "dataSource-";

    @Autowired
    private AbstractRoutingDataSource abstractRoutingDataSource;
    @Autowired
    private DSNameRuleManager dsNameRuleManager;
    private boolean rwSwitch = true;

    private String defaultRuleKey = "default";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(!rwSwitch){ //关闭
            return invocation.proceed();
        }
        StatementHandler statementHandler = (StatementHandler) invocation
                .getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(
                statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY);
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
                .getValue("delegate.mappedStatement");

        String mappId = mappedStatement.getId();
        Class sourceTarget = Class.forName(mappId.substring(0, mappId.lastIndexOf(".")));
        DBShardAnnotation dbShardAnnotation = (DBShardAnnotation) sourceTarget.getAnnotation(DBShardAnnotation.class);
        //默认 只做 读写分离    约定由于配置   dataSource-r   dataSource-w
        String slice = DS_PREFIX;
        //通过annotation获取对应的分片规则配置 进行分片名称获取   假定我们的DAO命名都是标准的  仅支持  DAO 为Param注释的 参数
        Map<String, String> props = new HashMap<>();
        if(dbShardAnnotation != null) {
            MapperMethod.ParamMap paramMap = null;
            if (dbShardAnnotation.isUseThreadLocal()) {
                props = DataSourceContextHolder.getRouteProps();
            } else {
                BoundSql boundSql= (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
                try
                {
                    Object object = boundSql.getParameterObject();

                    if(object instanceof ShardBaseDomain)
                    {
                        ShardBaseDomain shardBaseDomain = (ShardBaseDomain)object;
                        Map<String,Object> map = shardBaseDomain.getShardMap();
                        for(Map.Entry<String,Object> entry :map.entrySet())
                        {
                            props.put(entry.getKey(),entry.getValue().toString());
                        }
                    }
                    else {
                        paramMap = ((MapperMethod.ParamMap) ((BoundSql) metaStatementHandler.getValue("delegate.boundSql")).getParameterObject());
                        for (String key : dbShardAnnotation.ruleProps()) {
                            if(paramMap.containsKey(key)) {
                                props.put(key, String.valueOf(paramMap.get(key)));
                            }
                        }
                        if (props.size()==0) {
                            Map<String, Object> map = ShardDbContext.getCurrentShardDbMap();
                            if(map!=null) {
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    props.put(entry.getKey(), entry.getValue().toString());
                                }
                            }
                        }
                        //如果没有 对应的参数 或 个数不匹配 不做任何处理 照常进行
                        if (props.size()==0) {
                            LOGGER.warn("{} no router handler", mappId);
                            // 传递给下一个拦截器处理
                            return invocation.proceed();
                        }
                    }


                }catch (Exception ex)
                {
                    return invocation.proceed();
                }


            }

            slice = dsNameRuleManager.dsSlice(dbShardAnnotation.ruleKey(), props);
            DataSourceContextHolder.setContextType(slice);
        }
        else {
            return invocation.proceed();
        }
//        else {
//            Map<String, Object> map = ShardDbContext.getCurrentShardDbMap();
//
//            if(map!=null) {
//                for (Map.Entry<String, Object> entry : map.entrySet()) {
//                    props.put(entry.getKey(), entry.getValue().toString());
//                }
//
//            }
//            slice = dsNameRuleManager.dsSlice(defaultRuleKey, props);
//            DataSourceContextHolder.setContextType(slice);
//
//        }

        invocation.getArgs()[0] = abstractRoutingDataSource.getConnection();
        // 传递给下一个拦截器处理
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的
        // 次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
