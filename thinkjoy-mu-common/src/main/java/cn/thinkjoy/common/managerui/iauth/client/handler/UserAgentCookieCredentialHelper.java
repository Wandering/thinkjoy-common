package cn.thinkjoy.common.managerui.iauth.client.handler;

import cn.thinkjoy.common.managerui.iauth.client.DefaultAuthRequest;
import cn.thinkjoy.common.managerui.iauth.client.EncryptionCookieCredential;
import cn.thinkjoy.common.managerui.iauth.client.token.AccessToken;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.core.exception.AuthNotPassException;
import cn.thinkjoy.common.managerui.iauth.core.Credential;
import cn.thinkjoy.common.managerui.iauth.core.exception.TokenNotExistException;
import cn.thinkjoy.common.managerui.iauth.core.token.EmbedToken;
import cn.thinkjoy.common.managerui.iauth.utils.HttpRequestConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Michael on 11/10/14.
 * 带有credential验证的handler
 */
public class UserAgentCookieCredentialHelper implements HttpRequestConstant{

    private static Logger logger = LoggerFactory.getLogger(UserAgentCookieCredentialHelper.class);

    /**
     * TODO
     */
    private boolean defaultSecret = false;

    private Credential generateCredential(String key, String secret) {
        Credential credential = new EncryptionCookieCredential();
        credential.setKey(key);
        credential.setSecret(secret);
        return credential;
    }


    private Credential generateCredential(HttpServletRequest res, boolean generateSecret) {
        String userAgent = res.getHeader(HTTP_HEADER_USER_AGENT);
        if (generateSecret) {
            return generateCredential(EncryptionCookieCredential.generateKey(userAgent), EncryptionCookieCredential.generateSecret());
        } else {
            return generateCredential(EncryptionCookieCredential.generateKey(userAgent), null);

        }
    }

    /**
     * 获取credential相关信息
     *
     * @param baseRequest
     * @return
     */
    public void checkCredential(BaseRequest baseRequest) {
        AccessToken token = (AccessToken) baseRequest.getToken();
        if (token == null) {
            throw new TokenNotExistException("AccessToken不存在。");
        }

        // 判断是否需要安全认证
        if (token.isSecretRequired()) {
            // 从请求头中获取credential中的信息
            Credential credential = generateCredential(baseRequest.getRequest(), false);
            String userKey = credential.getKey();

            // 从cookie中获取secret信息
            Cookie secretCookie = ((DefaultAuthRequest) baseRequest).getCookieFromRequest(userKey);

            // check cookie
            if (secretCookie == null) {
                throw new AuthNotPassException("验证cookie不存在。");
            }

            // 构建完整的credential
            credential.setSecret(secretCookie.getValue());

            if (!token.getSecret().equals(credential.getSecret())) {
                throw new AuthNotPassException("验证cookie不匹配。");
            }
            ((DefaultAuthRequest) baseRequest).setCredential(credential);

        }
    }

    public void clearCredentialEmbedment(BaseRequest baseRequest) {
        Credential credential = ((DefaultAuthRequest) baseRequest).getCredential();
        if (credential != null) {
            ((DefaultAuthRequest) baseRequest).removeCookieToResponse(credential.getKey());
        }
    }



    /**
     * 生成credential相关信息，并进行埋点
     *
     * @param baseRequest
     * @return
     */
    public void embedCredential(BaseRequest baseRequest) {
        AccessToken token = (AccessToken) ((EmbedToken)baseRequest.getToken()).getEmbedToken();
        if (token == null) {
            throw new TokenNotExistException("进行埋点时，获取不到AccessToken");
        }

        // 创建credential
        Credential credential = generateCredential(baseRequest.getRequest(), true);
        token.setSecret(credential.getSecret());

        Cookie secretCookie = new Cookie(credential.getKey(), credential.getSecret());
//        secretCookie.setDomain(".xy189.cn");
        secretCookie.setPath("/");
        ((DefaultAuthRequest) baseRequest).addCookieToResponse(secretCookie);
    }

}
