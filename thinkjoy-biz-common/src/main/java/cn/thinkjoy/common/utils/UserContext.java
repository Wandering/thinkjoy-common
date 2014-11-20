package cn.thinkjoy.common.utils;


import cn.thinkjoy.common.domain.UserDomain;

/**
 * 用户上下文
 * <p/>
 * 创建时间: 14-9-1 下午11:21<br/>
 *
 * @author qyang
 * @since v0.0.1
 * @author Michael
 * @since v0.0.10
 */
public class UserContext {
    private static ThreadLocal<UserDomain> context = new ThreadLocal<UserDomain>();

    public static UserDomain getCurrentUser(){
       return context.get();
    }

    public static void setCurrentUser(UserDomain user){
        //缓存记录
//        SessionCacheFactory.getInstance().put(user.getName(), user);
        context.set(user);
    }

    /**
     * 应该显示调用
     */
    public static void removeCurrentUser() {
        context.remove();
    }

    /**
     * 将缓存的数据放入当前线程
     * @param userName
     */
    @Deprecated
    public static void setCurrentUser(String userName){
        context.set(SessionCacheFactory.getInstance().get(userName));
    }

    @Deprecated
    public static void removeUser(String userName){
        SessionCacheFactory.getInstance().remove(userName);
    }
}
