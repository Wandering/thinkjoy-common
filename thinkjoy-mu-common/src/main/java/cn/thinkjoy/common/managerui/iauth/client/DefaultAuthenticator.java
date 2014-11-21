package cn.thinkjoy.common.managerui.iauth.client;

import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.cloudstack.dynconfig.IChangeListener;
import cn.thinkjoy.cloudstack.dynconfig.domain.Configuration;
import cn.thinkjoy.common.managerui.iauth.client.handler.EmbedTokenHandler;
import cn.thinkjoy.common.managerui.iauth.client.handler.TokenResolver;
import cn.thinkjoy.common.managerui.iauth.client.token.AccessToken;
import cn.thinkjoy.common.managerui.iauth.client.token.UserStore;
import cn.thinkjoy.common.managerui.iauth.provider.*;
import cn.thinkjoy.common.managerui.domain.User;
import cn.thinkjoy.common.managerui.iauth.provider.token.TokenStore;
import cn.thinkjoy.common.managerui.iauth.utils.UrlStringUtil;
import cn.thinkjoy.common.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Michael on 11/10/14.
 * ucm集成，登录、授权等。
 */
@Component
public class DefaultAuthenticator extends Authenticator implements HttpRquestConstant {

    private static Logger logger = LoggerFactory.getLogger(DefaultAuthenticator.class);

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
    }

    @Override
    public BaseRequestFactory getRequestFactory() {
        return authRequestFactory;
    }

    @Override
    public List<TokenHandler> getTokenHandlers() {
        return tokenHandlers;
    }


    private void initRedirectUrl() {
        DynConfigClient dynConfigClient = DynConfigClientFactory.getClient();
        try {
            redirect_url = dynConfigClient.getConfig("ucm", "common", "uchost");
        } catch (Exception e) {
            //TODO
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


    public void redirectTologin(BaseRequest baseRequest) throws IOException {
        String url = baseRequest.getRequest().getRequestURL().toString();
        String from = URLEncoder.encode(url, "utf-8");

        Map<String, String> params = new HashMap<String, String>();
        params.put("from", from);
//        params.put() FIXME

        DefaultAuthRequest request = (DefaultAuthRequest) baseRequest;
        if (request.getPrincipal() != null && request.getPrincipal().getOwner() != null) {
            User user = request.getPrincipal().getOwner();
            params.put("username", user.getName());
        }


        redirectTologinWithParams(baseRequest.getResponse(), params);

    }

    @Override
    public void authenticationDone(BaseRequest baseRequest) {
        //TODO
        Principal<User> principal = ((DefaultAuthRequest) baseRequest).getPrincipal();
        if (null == principal || principal.getName() == null) {
            // 验证没通过啊
            throw new CannotAuthException();
        }
        if (principal.getOwner() != null) {
//            baseRequest.getRequest().setAttribute(HTTP_ATTRIBUTE_USER_DETAIL, principal.getOwner());
            UserContext.setCurrentUser(principal.getOwner());
        }
        if (principal.getToken() != null) {
            baseRequest.getRequest().setAttribute(HTTP_ATTRIBUTE_ACCESS_TOKEN, principal.getToken());
            baseRequest.getRequest().setAttribute(HTTP_ATTRIBUTE_USER_KEY, ((AccessToken) principal.getToken()).getUserId());
        }
        // 对token进行延期
        tokenStore.postpone(principal.getToken().getValue());
        userStore.postpone(principal.getOwner().getId());

    }

    @Override
    public void embedmentDone(BaseRequest baseRequest) {
        Principal principal = ((DefaultAuthRequest) baseRequest).getPrincipal();
        assert principal != null;

    }
    @Override
    public void callWhenAuthenticatiorFailed(BaseRequest baseRequest) throws IOException {
        logger.info("验证没通过。");
        redirectTologin(baseRequest);

    }

    @Override
    public void callWhenAuthenticatiorError(BaseRequest baseRequest, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        redirectTologin(baseRequest);

    }


    private void redirectTologinWithParams(HttpServletResponse res, Map<String, String> params) throws IOException {
        String paramsString = UrlStringUtil.paramsMapToURLString(params);


        String result = redirect_url + (paramsString.length() > 1 ? paramsString.toString() : "");
        res.sendRedirect(result);
    }


}