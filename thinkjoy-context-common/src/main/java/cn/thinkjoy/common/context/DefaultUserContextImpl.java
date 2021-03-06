package cn.thinkjoy.common.context;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/6/19 上午9:19<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DefaultUserContextImpl implements IUserContext {
    protected Map<String, Object> contexts = new HashMap<>();

    /**
     * 设置上下文，子类重载
     */
    public void setContexts(Map<String, Object> contexts){
        this.contexts = contexts;
    }

    @Override
    public Map<String, Object> getContexts() {
        return contexts;
    }
}
