package cn.thinkjoy.common.filter.context;

import java.util.Map;

/**
 * 获取业务上下文接口，需要业务方实现
 * <p/>
 * 创建时间: 15/6/19 上午9:15<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IUserContext {
    /** 用户id key  */
    public static final String UID = "uid";

    /**
     * 获取业务上下文
     * @return
     */
    public Map<String, Object> getContexts();
}
