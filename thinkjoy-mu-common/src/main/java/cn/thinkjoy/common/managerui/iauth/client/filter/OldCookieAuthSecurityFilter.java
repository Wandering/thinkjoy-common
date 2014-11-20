package cn.thinkjoy.common.managerui.iauth.client.filter;


import cn.thinkjoy.security.utils.Digests;
import cn.thinkjoy.security.utils.Encodes;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限检查filter 简单实现
 * 并不一定登录才有session，session管理待实现
 * <p/>
 * 创建时间: 14-8-17 上午11:24<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Deprecated
public class OldCookieAuthSecurityFilter implements Filter {
    private String HTTP_UCM_HOST;

//    private IAuthService authService;


    private Cookie getCookieFromRequest(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies){
            if (cookie.getName().equalsIgnoreCase(name))
                return cookie;
        }

        return null;

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String userAgent = req.getHeader("user-agent");
        String userKey = Encodes.encodeHex(
                Digests.md5(userAgent.getBytes()))
                .substring(0, 5);  // userAgent -> md5 first 5 char


        Cookie tokenCookie = getCookieFromRequest(req, "token");

        Cookie credentialCookie = getCookieFromRequest(req, userKey);

        // check cookie
        if (tokenCookie == null || credentialCookie == null) {
            // no cookie -> redirect
            res.sendRedirect(HTTP_UCM_HOST + "/login?from=");
            return;

        } else {
            // have cookie
            String token = tokenCookie.getValue();
            String credential = credentialCookie.getValue();

//            // check islogin
//            if (authService.isLogin(null, null, false)) {
//                // yes -> pass
//                authService.postpone(null, null, false);
//                chain.doFilter(request, response);
//                return;
//            } else {
//                // no -> redirect
//                res.sendRedirect(HTTP_UCM_HOST + "/login?from=");
//                return;
//            }

        }
        // never reached
        // 判断是否登录
        // 登录继续
        // 没登录要跳转到ucm
//        HttpSession session = req.getSession();
//        if (session.getAttribute("username") != null) {//登录后才能访问
//            UserContext.setCurrentUser((String) session.getAttribute("username"));
//            chain.doFilter(request, response);
//        } else {
//            res.sendRedirect(HTTP_UCM_HOST + "/login?from="+from);
//        }
    }

    @Override
    public void destroy() {

    }
}
