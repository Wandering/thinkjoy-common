package cn.thinkjoy.common.context;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/6/19 下午1:33<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class UserContextHolder {
    private static ThreadLocal<IUserContext> userContextThreadLocal = new ThreadLocal<>();

    public static void setUserContext(IUserContext userContext){
        userContextThreadLocal.set(userContext);
    }

    public static IUserContext getUserContext(){
        return userContextThreadLocal.get();
    }
}
