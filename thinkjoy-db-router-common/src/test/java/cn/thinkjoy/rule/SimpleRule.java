package cn.thinkjoy.rule;

import cn.thinkjoy.common.router.IDSNameRuleStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/4/28 下午5:22<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Component("SimpleRule")
public class SimpleRule implements IDSNameRuleStrategy {
    @Override
    public String dsSlice(Map<String, String> props) {
        //mock test
        return "ds1-";
    }
}
