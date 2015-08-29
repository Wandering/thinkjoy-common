package cn.thinkjoy.common.managerui.iauth.client;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.cloudstack.dynconfig.IChangeListener;
import cn.thinkjoy.cloudstack.dynconfig.domain.Configuration;
import cn.thinkjoy.common.context.DefaultUserContextImpl;
import cn.thinkjoy.common.context.IUserContext;
import cn.thinkjoy.common.context.UserContextHolder;
import cn.thinkjoy.common.managerui.domain.User;
import cn.thinkjoy.common.managerui.iauth.client.token.AccessToken;
import cn.thinkjoy.common.managerui.iauth.client.token.storage.UserStore;
import cn.thinkjoy.common.managerui.iauth.core.Authenticator;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequestFactory;
import cn.thinkjoy.common.managerui.iauth.core.Principal;
import cn.thinkjoy.common.managerui.iauth.core.exception.CannotAuthException;
import cn.thinkjoy.common.managerui.iauth.core.handler.EmbedTokenHandler;
import cn.thinkjoy.common.managerui.iauth.core.handler.TokenHandler;
import cn.thinkjoy.common.managerui.iauth.core.handler.TokenResolver;
import cn.thinkjoy.common.managerui.iauth.core.token.storage.TokenStore;
import cn.thinkjoy.common.managerui.iauth.utils.HttpRequestConstant;
import cn.thinkjoy.common.managerui.iauth.utils.LogErrorUtil;
import cn.thinkjoy.common.managerui.iauth.utils.UrlStringUtil;
import cn.thinkjoy.common.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Michael on 11/10/14.
 * ucm集成，登录、授权=等。
 */
@Component
public class DefaultAuthenticator extends Authenticator implements HttpRequestConstant {

    private static Logger logger = LoggerFactory.getLogger(DefaultAuthenticator.class);

    private String debugKey = null;

    public void setDebugKey(String debugKey) {
        this.debugKey = debugKey;
    }

    private String SECRET_KEY = null;

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private UserStore userStore;

    private String redirect_url = "/login";
    private List<TokenHandler> tokenHandlers = Collections.emptyList();
    private DefaultAuthRequestFactory authRequestFactory = new DefaultAuthRequestFactory();

    public DefaultAuthenticator() {
        // for spring bean initial
    }

    public DefaultAuthenticator(List<TokenHandler> tokenHandlers) {
        init(tokenHandlers);
    }

    @Override
    public void init(List<TokenHandler> tokenHandlers) {
        boolean conditions = tokenHandlers.size() > 0;

        if (!(tokenHandlers.get(0) instanceof TokenResolver)) {
            conditions = false;
        }
        if (!(tokenHandlers.get(1) instanceof EmbedTokenHandler)) {
            conditions = false;
        }
        if (conditions == false) {
            throw new CannotAuthException();
        }

        this.tokenHandlers = tokenHandlers;

        initRedirectUrl();

        DynConfigClient dynConfigClient = DynConfigClientFactory.getClient();
        try {
            SECRET_KEY = dynConfigClient.getConfig("ucm", "common", "secretKey");
        } catch (Exception e) {
            logger.info("SECRET_KEY没有进行配置，采用默认值: " + SECRET_KEY);
        }
        dynConfigClient.registerListeners("ucm", "common", "tokenExpireTime", new IChangeListener() {
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
                        SECRET_KEY = configuration.getConfig();
                    }
                });
            }
        });
    }

    @Override
    public boolean isLogout(BaseRequest baseRequest) {
//        if ()
        String uri = baseRequest.getRequest().getRequestURI();
        if (uri.endsWith("/logout")) {

            return true;
        }
        return false;
    }

    @Override
    public BaseRequestFactory getRequestFactory() {
        return authRequestFactory;
    }

    @Override
    public List<TokenHandler> getTokenHandlers() {
        return tokenHandlers;
    }

    @Override
    public boolean isNeedAuthentication(BaseRequest baseRequest) {
        return isNeedAuthentication();
    }

    private boolean isNeedAuthentication() {
        if (SECRET_KEY == null) {
            return true;
        }
        if (SECRET_KEY.equals(debugKey)) {
            return false;
        }

        return true;
    }


    private void initRedirectUrl() {
        DynConfigClient dynConfigClient = DynConfigClientFactory.getClient();
        try {
            redirect_url = dynConfigClient.getConfig("ucm", "common", "uchost");
        } catch (Exception e) {
            logger.info("uchost没有进行配置，采用默认值: " + redirect_url);
        }
        dynConfigClient.registerListeners("ucm", "common", "uchost", new IChangeListener() {
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
                        redirect_url = configuration.getConfig();
                    }
                });
            }
        });
    }

    @Override
    public void destroy() {
        tokenHandlers.clear();
    }

    @Override
    public void redirectTologin(BaseRequest baseRequest, RuntimeException ex) throws IOException {
        if (((DefaultAuthRequest) baseRequest).isDebug()) {
            throw ex;
        }

        String url = baseRequest.getRequest().getRequestURL().toString();
//        String from = URLEncoder.encode(url, "utf-8");

        Map<String, String> params = new HashMap<String, String>();
        params.put("from", url);
        params.put("error", ex.getMessage());
        params.put("log", LogErrorUtil.toString(ex));
//        params.put() FIXME

        DefaultAuthRequest request = (DefaultAuthRequest) baseRequest;
        if (request.getPrincipal() != null && request.getPrincipal().getOwner() != null) {
            User user = request.getPrincipal().getOwner();
            params.put("username", user.getName());
        }

        String appKey = CloudContextFactory.getCloudContext().getApplicationName();
        String product = CloudContextFactory.getCloudContext().getProductCode();

        params.put("appKey", appKey);
        params.put("product", product);

        redirectTologinWithParams(baseRequest.getResponse(), params);

    }


    @Override
    public void redirectTologout(BaseRequest baseRequest) throws IOException {
        // TODO 通知ucm
        baseRequest.getResponse().sendRedirect("/");

    }

    @Override
    public void callWhenAuthenticatornSuccess(BaseRequest baseRequest) {
        Principal<User> principal = ((DefaultAuthRequest) baseRequest).getPrincipal();
        if (null == principal || principal.getName() == null) {
            // 验证没通过啊
            throw new CannotAuthException();
        }
        if (principal.getOwner() != null) {
//            baseRequest.getRequest().setAttribute(HTTP_ATTRIBUTE_USER_DETAIL, principal.getOwner());
            UserContext.setCurrentUser(principal.getOwner());

            //供 analytics filter使用
            IUserContext userContext = new DefaultUserContextImpl();
            Map<String, Object> datas = new HashMap<>(4);
            datas.put(IUserContext.UID, principal.getOwner().getId());
            datas.put("product", principal.getOwner().getProduct());
//            datas.put("", principal.getOwner());
            ((DefaultUserContextImpl) userContext).setContexts(datas);
            UserContextHolder.setUserContext(userContext);
        }
        if (principal.getToken() != null) {
            baseRequest.getRequest().setAttribute(HTTP_ATTRIBUTE_ACCESS_TOKEN, principal.getToken());
            baseRequest.getRequest().setAttribute(HTTP_ATTRIBUTE_USER_KEY, ((AccessToken) principal.getToken()).getUserId());
        }
        // 对token进行延期
        tokenStore.postpone(principal.getToken().getValue());
        userStore.postpone(Long.valueOf(String.valueOf(principal.getOwner().getId())));

    }

    @Override
    public void callWhenEmbedmentSuccess(BaseRequest baseRequest) {
        Principal principal = ((DefaultAuthRequest) baseRequest).getPrincipal();
        assert principal != null;

    }

    @Override
    public void setResponseForAjax(BaseRequest baseRequest) {
        // TODO
        try {

            String url = baseRequest.getRequest().getRequestURL().toString();
            baseRequest.getResponse().setHeader("sessionstatus", "timeout");
            Map<String, String> params = new HashMap<String, String>();
            params.put("from", url);

            DefaultAuthRequest request = (DefaultAuthRequest) baseRequest;
            if (request.getPrincipal() != null && request.getPrincipal().getOwner() != null) {
                User user = request.getPrincipal().getOwner();
                params.put("username", user.getName());
            }

            String appKey = CloudContextFactory.getCloudContext().getApplicationName();
            String product = CloudContextFactory.getCloudContext().getProductCode();
            params.put("appKey", appKey);
            params.put("product", product);

            String paramsString = UrlStringUtil.paramsMapToURLString(params);

            String result = redirect_url + (paramsString.length() > 1 ? paramsString.toString() : "");

            PrintWriter out = baseRequest.getResponse().getWriter();
            out.println(result);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void callWhenAuthenticatiornError(BaseRequest baseRequest, RuntimeException ex) throws IOException {
        logger.error("拒绝访问。验证出现异常: " + ex.getMessage(), ex);
        String requestType = baseRequest.getRequest().getHeader("X-Requested-With");
        if (requestType != null && requestType.equals("XMLHttpRequest"))
            baseRequest.getAuthenticator().setResponseForAjax(baseRequest);
        redirectTologin(baseRequest, ex);
    }


    private void redirectTologinWithParams(HttpServletResponse res, Map<String, String> params) throws IOException {
        String paramsString = UrlStringUtil.paramsMapToURLString(params);


        String result = redirect_url + (paramsString.length() > 1 ? paramsString.toString() : "");
        logger.info(CloudContextFactory.getCloudContext().getApplicationName() + "跳转到ucm登录页面: url" + result);
        res.sendRedirect(result);
    }


}
