package cn.thinkjoy.common.managerui.iauth.client.handler;

import cn.thinkjoy.common.managerui.iauth.client.DefaultAuthRequest;
import cn.thinkjoy.common.managerui.iauth.client.EncryptionCookieCredential;
import cn.thinkjoy.common.managerui.iauth.provider.*;
import cn.thinkjoy.common.managerui.iauth.client.token.AccessToken;
import cn.thinkjoy.common.managerui.iauth.provider.token.Token;
import cn.thinkjoy.common.managerui.iauth.provider.token.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Michael on 11/10/14.
 * 带有credential验证的handler
 */
public class UserAgentCookieCredentialHandler extends AbstractTokenBundledHandler {

    private static Logger logger = LoggerFactory.getLogger(UserAgentCookieCredentialHandler.class);

    /**
     * TODO
     */
    private boolean defaultSecret = false;

    @Autowired
    private TokenStore tokenStore;
    private String handleTokenType = Token.ACCESS_TOKEN;

//    public Credential loadCredential(HttpServletRequest res) {
//        String userAgent = res.getHeader(HTTP_HEADER_USER_AGENT);
//        Credential credential = new EncryptionCookieCredential();
//        String key = Encodes.encodeHex(
//                Digests.md5(userAgent.getBytes()))
//                .substring(0, 5);
//
//        credential.setKey(key);
//        return credential;
//    }

    private Credential generateCredential(String key, String secret) {
        Credential credential = new EncryptionCookieCredential();
        credential.setKey(key);
        credential.setSecret(secret);
        return credential;
    }


    private Credential generateCredential(HttpServletRequest res , boolean generateSecret) {
        String userAgent = res.getHeader(HTTP_HEADER_USER_AGENT);
        if (generateSecret) {
            return generateCredential(EncryptionCookieCredential.generateKey(userAgent), EncryptionCookieCredential.generateSecret());
        } else {
            return generateCredential(EncryptionCookieCredential.generateKey(userAgent), null);

        }
    }

    /**
     * 获取credential相关信息
     * @param baseRequest
     * @return
     */
    @Override
    public boolean invoke(BaseRequest baseRequest) {
        AccessToken token = (AccessToken) baseRequest.getToken();
        if (token == null) { throw new CannotAuthException(); }

        // 判断是否需要安全认证
        if (token.isSecretRequired()) {
            // 从请求头中获取credential中的信息
            Credential credential = generateCredential(baseRequest.getRequest(), false);
            String userKey = credential.getKey();

            // 从cookie中获取secret信息
            Cookie secretCookie = ((DefaultAuthRequest)baseRequest).getCookieFromRequest(userKey);

            // check cookie
            if (secretCookie == null) {
                return false;
            }

            // 构建完整的credential
            credential.setSecret(secretCookie.getValue());

            // 判断credential是否存在
            if (credential == null) {
                return false;
            }

            if (!token.getSecret().equals(credential.getSecret())) {
                return false;
            }
            ((DefaultAuthRequest) baseRequest).setCredential(credential);

        }
        return true;
    }

    @Override
    public void callWhenAuthenticationFailed(BaseRequest baseRequest) throws IOException {
        logger.info("验证没通过。");
        baseRequest.getAuthenticator().redirectTologin(baseRequest);

    }

    @Override
    public void callWhenAuthenticationError(BaseRequest baseRequest, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        baseRequest.getAuthenticator().redirectTologin(baseRequest);

    }

    /**
     * 生成credential相关信息，并进行埋点
     * @param baseRequest
     * @return
     */
    @Override
    public boolean embed(BaseRequest baseRequest) {
        AccessToken token = (AccessToken) baseRequest.getToken();
        if (token == null) { throw new CannotAuthException();}

        // 创建credential
        Credential credential = generateCredential(baseRequest.getRequest(), true);
        token.setSecret(credential.getSecret());

        tokenStore.store(token.getValue(), token);
        Cookie secretCookie = new Cookie(credential.getKey(), credential.getSecret());
//        secretCookie.setDomain(".xy189.cn");
        secretCookie.setPath("/");
        ((DefaultAuthRequest) baseRequest).addCookieToResponse(secretCookie);
        return true;
    }

    @Override
    public String getHandleTokenType() {
        return handleTokenType;
    }

    @Override
    public Token getTokenFromRequest(BaseRequest baseRequest) {
        return null;
    }
}
