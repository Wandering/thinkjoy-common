package cn.thinkjoy.common.managerui.iauth.client.handler;

import cn.thinkjoy.common.managerui.domain.User;
import cn.thinkjoy.common.managerui.iauth.client.DefaultAuthRequest;
import cn.thinkjoy.common.managerui.iauth.client.token.AccessToken;
import cn.thinkjoy.common.managerui.iauth.client.token.EmbedToken;
import cn.thinkjoy.common.managerui.iauth.client.token.UserStore;
import cn.thinkjoy.common.managerui.iauth.provider.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.provider.CannotAuthException;
import cn.thinkjoy.common.managerui.iauth.provider.Principal;
import cn.thinkjoy.common.managerui.iauth.provider.handler.AbstractTokenBundledHandler;
import cn.thinkjoy.common.managerui.iauth.provider.token.Token;
import cn.thinkjoy.common.managerui.iauth.provider.token.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.io.IOException;

/**
 * Created by Michael on 11/10/14.
 * 对access token进行验证
 */
public class AccessTokenHandler extends AbstractTokenBundledHandler {
    private static Logger logger = LoggerFactory.getLogger(AccessTokenHandler.class);

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
    public boolean invoke(BaseRequest baseRequest) {
        AccessToken token = (AccessToken) baseRequest.getToken();
        if (token == null) {
            throw new CannotAuthException();
        }
        // 验证通过，创建pincipal
        Principal principal = new Principal();
        principal.setToken(token);
        if (isUserDetailRequired()) {
            User user = userStore.readUser(token.getUserId());
            if (user == null) {
                return false;
            }
            principal.setOwner(user);
        }
        ((DefaultAuthRequest) baseRequest).setPrincipal(principal);
        return true;
    }

    @Override
    public void clear(BaseRequest baseRequest) {
        ((DefaultAuthRequest) baseRequest).removeCookieToResponse(HTTP_COOKIE_ACCESS_TOKEN);
    }

    /**
     * principal -> credential
     *
     * @param baseRequest
     * @return
     */
    @Override
    public boolean embed(BaseRequest baseRequest) {
        EmbedToken embedToken = (EmbedToken) baseRequest.getToken();

        // 获取用户
        Long userId = embedToken.getUserId();
        User user = userStore.readUser(userId);
        if (user == null) {
            return false;
        }

        // 判断系统访问权限
//        String appName = CloudContextFactory.getCloudContext().getApplicationName();
//        if (Strings.isNullOrEmpty(appName)) {
//
//        }
        // FIXME

        if (user.getBizDimension() == null || user.getBizDimension() == 0) {
            logger.info("该用户没有权限登录");
            return false;
        }


        // 创建principal和access token
        Principal<User> principal = new Principal();
        principal.setOwner(user);
        ((DefaultAuthRequest) baseRequest).setPrincipal(principal);


        AccessToken accessToken = new AccessToken();
        accessToken.setUserId(principal.getOwner().getId());

        tokenStore.store(accessToken.getValue(), accessToken);

        Cookie tokenCookie = new Cookie(HTTP_COOKIE_ACCESS_TOKEN, accessToken.getValue());
        //tokenCookie.setDomain(".xy189.cn");
        tokenCookie.setPath("/");
        ((DefaultAuthRequest) baseRequest).addCookieToResponse(tokenCookie);

        baseRequest.setToken(accessToken);
        return true;
    }

    @Override
    public String getHandleTokenType() {
        return handleTokenType;
    }

    @Override
    public Token getTokenFromRequest(BaseRequest baseRequest) {
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
    public void callWhenAuthenticationFailed(BaseRequest baseRequest) throws IOException {
        logger.info("拒绝访问。获取用户信息失败: userId=" + ((AccessToken) baseRequest.getToken()).getUserId());
        baseRequest.getAuthenticator().redirectTologin(baseRequest);

    }

    @Override
    public void callWhenAuthenticationError(BaseRequest baseRequest, Exception ex) throws IOException {
        logger.error("访问异常。获取用户信息异常:" + ex.getMessage(), ex);
        baseRequest.getAuthenticator().redirectTologin(baseRequest);

    }
}
