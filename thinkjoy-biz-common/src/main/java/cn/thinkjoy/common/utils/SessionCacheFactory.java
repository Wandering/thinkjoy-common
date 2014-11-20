package cn.thinkjoy.common.utils;


import cn.thinkjoy.common.domain.UserDomain;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14-9-4 下午1:50<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Deprecated
public class SessionCacheFactory {
    private static class SessionCacheHolder{
        private static SessionCache<String, UserDomain> instance = new SessionCache<String, UserDomain>(500);
    }

    private SessionCacheFactory(){}

    public static SessionCache<String, UserDomain> getInstance(){
        return SessionCacheHolder.instance;
    }
}
