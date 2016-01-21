package cn.thinkjoy.common.mybatis.core.mybatis.interceptor;

import cn.thinkjoy.common.mybatis.core.mybatis.criteria.Criteria;
import cn.thinkjoy.common.mybatis.core.mybatis.db.DBSelector;
import cn.thinkjoy.common.mybatis.core.mybatis.domain.PageBean;
import cn.thinkjoy.common.mybatis.core.mybatis.paging.dialect.Dialect;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.MybatisUtils;
import cn.thinkjoy.common.mybatis.core.mybatis.utils.SpringHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class PagingHandlerInterceptor extends AbstractInterceptor {

	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

	public static class BoundSqlSqlSource implements SqlSource {
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}

		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
	}

	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	void processIntercept(Object queryArgs[]) {
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();
		Dialect dialect = SpringHolder.getBean(DBSelector.class).selectDialect();
		boolean isHasLimit = false;
		if ((parameter instanceof Criteria || parameter instanceof PageBean)
				&& (dialect.supportsLimit()
//				&& (offset != 0 || limit != 2147483647)
					)) {
			if ((parameter instanceof Criteria)) {
				Criteria criteria = (Criteria) parameter;
				if (criteria.isHasLimit().booleanValue()) {
					isHasLimit = true;
					offset = criteria.getFirst().intValue();
					limit = criteria.getLast().intValue();
				}
			}
			if (parameter instanceof PageBean) {
				isHasLimit = true;
			}
			if (isHasLimit) {
				BoundSql boundSql = ms.getBoundSql(parameter);
				String sql = boundSql.getSql().trim();
				if (dialect.supportsLimitOffset()) {
					sql = dialect.getLimitString(sql, offset, limit);
					offset = 0;
				} else {
					sql = dialect.getLimitString(sql, 0, limit);
				}
//				limit = 2147483647;
				queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
				BoundSql newBoundSql = MybatisUtils.copyBoundSql(ms, boundSql, sql);
				MappedStatement newMs = MybatisUtils.copyMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
				queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
			}
		}
	}

}