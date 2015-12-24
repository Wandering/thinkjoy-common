package cn.thinkjoy.common.router.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据库分库支持
 * <p/>
 * 创建时间: 15/4/27 下午8:00<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DBShardRoutingDataSource  extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getContextType();
    }
}
