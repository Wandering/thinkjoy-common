package cn.thinkjoy.common.managerui.iauth.provider.filter;


import cn.thinkjoy.common.managerui.iauth.provider.Authentication;
import cn.thinkjoy.common.managerui.iauth.provider.Authenticator;
import cn.thinkjoy.common.managerui.iauth.provider.CannotAuthException;
import cn.thinkjoy.common.managerui.iauth.provider.TokenHandler;
import cn.thinkjoy.common.managerui.iauth.provider.token.Token;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael on 11/10/14.
 * <p/>
 * 需要委托给spring的DelegatingFilterProxy
 */

public abstract class DelegableSecurityFilter implements Filter {

    abstract public Authenticator getAuthenticator();
    private List<TokenHandler> tokenHandlers = Collections.emptyList();

    /**
     * need init
     * @param tokenHandlers
     */
    public void setAuthentications(List<TokenHandler> tokenHandlers) {
        this.tokenHandlers = tokenHandlers;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (getAuthenticator() == null) {
            throw new CannotAuthException();
        }
        getAuthenticator().init(tokenHandlers);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        chain.doFilter(request,response);
        if (getAuthenticator() == null) {
            throw new CannotAuthException();
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        getAuthenticator().authentication(req, res, chain);

    }

    @Override
    public void destroy() {
        if (getAuthenticator() == null) {
            throw new CannotAuthException();
        }
        getAuthenticator().destroy();
    }
}
