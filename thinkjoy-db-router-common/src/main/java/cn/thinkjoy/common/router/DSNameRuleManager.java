package cn.thinkjoy.cloudstack.router;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源名称规则获取管理器
 * <p/>
 * 创建时间: 15/4/28 上午11:48<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Component("DSNameRuleManager")
public class DSNameRuleManager {
    private Map<String, IDSNameRuleStrategy> dsNameRuleStrategyMap = new HashMap<>();

    public String dsSlice(String key, Map<String, String> props){
        return dsNameRuleStrategyMap.get(key).dsSlice(props);
    }

    public Map<String, IDSNameRuleStrategy> getDsNameRuleStrategyMap() {
        return dsNameRuleStrategyMap;
    }

    public void setDsNameRuleStrategyMap(Map<String, IDSNameRuleStrategy> dsNameRuleStrategyMap) {
        this.dsNameRuleStrategyMap = dsNameRuleStrategyMap;
    }
}
