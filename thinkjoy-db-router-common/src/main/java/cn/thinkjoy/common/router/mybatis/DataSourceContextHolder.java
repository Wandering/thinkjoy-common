package cn.thinkjoy.common.router.mybatis;

import java.util.Map;

/**
 * 线程上下文holder
 * <p/>
 * 创建时间: 15/4/27 下午7:57<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    private static final ThreadLocal<Map<String, String>> routePropsContextHolder = new ThreadLocal<>();

    public static void setContextType(String contextType) {
        contextHolder.set(contextType);
    }

    public static String getContextType() {
        return contextHolder.get();
    }

    public static void getRouteProps(Map<String, String> props) {
        routePropsContextHolder.set(props);
    }

    public static Map<String, String> getRouteProps() {
        return routePropsContextHolder.get();
    }

    public static void clearContextType() {
        contextHolder.remove();
        routePropsContextHolder.remove();
    }
}
