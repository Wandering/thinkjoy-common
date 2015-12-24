package cn.thinkjoy.common.router;

import java.util.Map;

/**
 * 数据源名称规则 策略接口
 * <p/>
 * 创建时间: 15/4/28 上午11:51<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IDSNameRuleStrategy {
    /**
     * 根据 路由原值 获取数据源分片值
     * @param props
     * @return
     */
    public String dsSlice(Map<String, String> props);
}
