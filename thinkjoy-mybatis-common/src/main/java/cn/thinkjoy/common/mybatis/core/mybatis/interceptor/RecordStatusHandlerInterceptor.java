package cn.thinkjoy.common.mybatis.core.mybatis.interceptor;

import cn.thinkjoy.common.mybatis.core.mybatis.domain.StatusEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Date;

/**
 * 数据模型状态属性拦截变更
 * 
 * @author shadow
 * 
 */
@Intercepts({
	@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class RecordStatusHandlerInterceptor extends AbstractInterceptor {

	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;

	public Object intercept(Invocation invocation) throws Throwable {
		Object queryArgs[] = invocation.getArgs();
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object entity = queryArgs[PARAMETER_INDEX];
		if (entity instanceof StatusEntity<?>) {
			@SuppressWarnings("unchecked")
			StatusEntity<Long> recordStatus = (StatusEntity<Long>) entity;
			Date currentDate = new Date();
			Long currentUserId = Long.valueOf(-4L);
			try {
				currentUserId = Long.valueOf(-4L);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (recordStatus.getUpdateCount() == null) {
				recordStatus.setUpdateCount(1l);
			} else {
				recordStatus.setUpdateCount(Long.valueOf(((Long) recordStatus.getUpdateCount()).longValue() + 1L));
			}
			recordStatus.setUpdaterId(currentUserId);
			recordStatus.setUpdateDate(currentDate);
			if (ms.getSqlCommandType() == SqlCommandType.INSERT) {
				recordStatus.setRecordStatus(StatusEntity.ACTIVE);
				recordStatus.setCreatorId(currentUserId);
				recordStatus.setCreateDate(currentDate);
				recordStatus.setUpdateCount(Long.valueOf(0L));
			}
		}
		return invocation.proceed();
	}

}
