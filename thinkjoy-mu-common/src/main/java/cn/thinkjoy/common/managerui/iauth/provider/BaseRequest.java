package cn.thinkjoy.common.managerui.iauth.provider;

import cn.thinkjoy.common.managerui.iauth.provider.token.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by Michael on 11/10/14.
 */
public abstract class BaseRequest implements Serializable {

    private HttpServletRequest request;
    private HttpServletResponse response;
    /**
     * request token
     */
    private Token token;
    /**
     * token 验证的执行者
     */
    private Authenticator authenticator;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }


}
