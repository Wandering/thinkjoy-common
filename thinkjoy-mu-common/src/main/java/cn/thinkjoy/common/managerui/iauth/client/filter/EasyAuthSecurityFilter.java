package cn.thinkjoy.common.managerui.iauth.client.filter;

import cn.thinkjoy.common.managerui.iauth.provider.Authenticator;
import cn.thinkjoy.common.managerui.iauth.provider.filter.DelegableSecurityFilter;


/**
 * Created by Michael on 11/10/14.
 * 简单的安全过滤器，可以直接使用
 * 需要注入authenticator
 */
public class EasyAuthSecurityFilter extends DelegableSecurityFilter{

    private Authenticator authenticator;


    @Override
    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}
