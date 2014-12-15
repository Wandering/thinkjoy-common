package cn.thinkjoy.common.managerui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14/12/14 下午9:30<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class HashedDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }

    public static class DynamicDataSourceHolder {
        public static final ThreadLocal<String> holder = new ThreadLocal<String>();

        public static void putDataSource(String name) {
            holder.set(name);
        }

        public static String getDataSouce() {
            return holder.get();
        }
    }
}
