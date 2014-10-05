package cn.thinkjoy.common.managerui.filter;


import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.cloudstack.dynconfig.IChangeListener;
import cn.thinkjoy.cloudstack.dynconfig.domain.Configuration;
import cn.thinkjoy.common.utils.UserContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 权限检查filter 简单实现
 * <p/>
 * 创建时间: 14-8-17 上午11:24<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class SecurityFilter implements Filter {
    private String host;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DynConfigClient dynConfigClient = DynConfigClientFactory.getClient();

        try {
            host = dynConfigClient.getConfig("common", "uchost");
        } catch (Exception e) {
            //TODO
        }
        dynConfigClient.registerListeners("common", "uchost", new IChangeListener() {
            @Override
            public Executor getExecutor() {
                return Executors.newSingleThreadExecutor();
            }

            @Override
            public void receiveConfigInfo(final Configuration configuration) {
                System.out.println("=================" + configuration);

                getExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("========ASYN=========" + configuration);
                        host = configuration.getConfig();
                    }
                });
            }
        });
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {//登录后才能访问
            UserContext.setCurrentUser((String) session.getAttribute("username"));
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(host + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
