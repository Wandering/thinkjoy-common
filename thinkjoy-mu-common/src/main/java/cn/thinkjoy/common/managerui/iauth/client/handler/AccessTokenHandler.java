package cn.thinkjoy.common.managerui.iauth.client.handler;

import cn.thinkjoy.common.managerui.domain.User;
import cn.thinkjoy.common.managerui.iauth.client.DefaultAuthRequest;
import cn.thinkjoy.common.managerui.iauth.client.UserNotExistException;
import cn.thinkjoy.common.managerui.iauth.client.token.AccessToken;
import cn.thinkjoy.common.managerui.iauth.core.exception.AuthNotPassException;
import cn.thinkjoy.common.managerui.iauth.core.exception.TokenNotExistException;
import cn.thinkjoy.common.managerui.iauth.core.handler.AbstractTokenHandler;
import cn.thinkjoy.common.managerui.iauth.core.token.EmbedToken;
import cn.thinkjoy.common.managerui.iauth.client.token.storage.UserStore;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.core.exception.CannotAuthException;
import cn.thinkjoy.common.managerui.iauth.core.Principal;
import cn.thinkjoy.common.managerui.iauth.core.handler.AbstractTokenBundledHandler;
import cn.thinkjoy.common.managerui.iauth.core.token.Token;
import cn.thinkjoy.common.managerui.iauth.core.token.storage.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * Created by Michael on 11/10/14.
 * 对access token进行验证
 */
public class AccessTokenHandler extends AbstractTokenHandler {
    private static Logger logger = LoggerFactory.getLogger(AccessTokenHandler.class);

    private UserAgentCookieCredentialHelper userAgentCookieCredentialHandlerHelper = new UserAgentCookieCredentialHelper();

    private String handleTokenType = Token.ACCESS_TOKEN;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserStore userStore;

    /**
     * 是否在每次请求中获取用户信息
     * 配合框架，必须要获取
     */
    private boolean userDetailRequired = true;

    public TokenStore getTokenStore() {
        return tokenStore;
    }

    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    public boolean isUserDetailRequired() {
        return userDetailRequired;
    }

    @Deprecated
    public void setUserDetailRequired(boolean userDetailRequired) {
        this.userDetailRequired = userDetailRequired;
    }

    /**
     * 从token中获取用户信息
     * 生成principal
     *
     * @param baseRequest
     * @return
     */
    @Override
    public void invoke(BaseRequest baseRequest) {
        AccessToken token = (AccessToken) baseRequest.getToken();
        if (token == null) {
            throw new TokenNotExistException("获取不到AccessToken。");
        }
        // 验证通过，创建pincipal
        Principal principal = new Principal();
        principal.setToken(token);
        if (isUserDetailRequired()) {
            try {
                User user = userStore.readUser(token.getUserId());
                if(user == null) {
                    throw new CannotAuthException("userStore中不存在此用户。");
                }
                principal.setOwner(user);
            }catch (Exception e) {
                throw new CannotAuthException("从userStore获取用户失败。", e);
            }
        }
        ((DefaultAuthRequest) baseRequest).setPrincipal(principal);
        userAgentCookieCredentialHandlerHelper.checkCredential(baseRequest);
    }

    @Override
    public void clear(BaseRequest baseRequest) {
        ((DefaultAuthRequest) baseRequest).removeCookieToResponse(HTTP_COOKIE_ACCESS_TOKEN);
        userAgentCookieCredentialHandlerHelper.clearCredentialEmbedment(baseRequest);
    }

    /**
     * principal -> credential
     *
     * @param baseRequest
     * @return
     */
    @Override
    public void embed(BaseRequest baseRequest) {
        EmbedToken embedToken = (EmbedToken) baseRequest.getToken();

        // 获取用户
        Object userId = embedToken.getAdditionalInformation().get("userId");
        User user = null;
        try {
            user = userStore.readUser(userId);
            if (user == null) {
                throw new UserNotExistException("userStore中不存在此用户。");
            }
        }catch (Exception e){
            throw new UserNotExistException("从userStore获取用户失败。",e);
        }

        // FIXME 判断系统访问权限
//        String appName = CloudContextFactory.getCloudContext().getApplicationName();
//        if (Strings.isNullOrEmpty(appName)) {
//
//        }
        //

//        if (user.getBizDimension() == null || user.getBizDimension() == 0) {
//            logger.info("该用户{0}({1})没有权限登录。", user.getName(), user.getBizDimension());
//            throw new AuthNotPassException(String.format("该用户%s(%s)没有权限登录，请联系管理员授权。", user.getName(), user.getBizDimension()));
//        }

        AccessToken accessToken = new AccessToken();
        accessToken.setUserId(Long.valueOf(String.valueOf(user.getId())));

        tokenStore.store(accessToken.getValue(), accessToken);

        Cookie tokenCookie = new Cookie(HTTP_COOKIE_ACCESS_TOKEN, accessToken.getValue());
        //tokenCookie.setDomain(".xy189.cn");
        tokenCookie.setPath("/");
        ((DefaultAuthRequest) baseRequest).addCookieToResponse(tokenCookie);

        embedToken.setEmbedToken(accessToken);

        userAgentCookieCredentialHandlerHelper.embedCredential(baseRequest);
        tokenStore.store(accessToken.getValue(), accessToken);

    }

    @Override
    public String getHandleTokenType() {
        return handleTokenType;
    }

    @Override
    public Token loadTokenFromRequest(BaseRequest baseRequest) {
        // 获取cookie中的token
        Cookie tokenCookie = ((DefaultAuthRequest) baseRequest).getCookieFromRequest(HTTP_COOKIE_ACCESS_TOKEN);
        if (tokenCookie == null) {
            return null;
        }
        String tokenValue = tokenCookie.getValue();
        // 获取服务器存储的token
        Token storedToken = tokenStore.readToken(tokenValue);
        return storedToken;
    }


    @Override
    public void callWhenAuthenticationError(BaseRequest baseRequest, RuntimeException ex) throws IOException {
        logger.error("访问失败。访问出现异常:" + ex.getMessage(), ex);
        String requestType = baseRequest.getRequest().getHeader("X-Requested-With");
        if(requestType!=null && !requestType.equals(""))
            baseRequest.getAuthenticator().setResponseForAjax(baseRequest);
        baseRequest.getAuthenticator().redirectTologin(baseRequest, ex);
    }

    @Override
    public boolean callWhenAuthenticationSuccess(BaseRequest baseRequest) throws IOException {
        baseRequest.consumeToken();
        return true;
    }


    public static void main(String[] args) {
        System.out.println(String.format("该用户%s(%s)没有权限登录。", "gbdai", "1"));
    }
}
