package cn.thinkjoy.common.managerui.iauth.core;

import cn.thinkjoy.common.managerui.iauth.core.token.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Michael on 11/10/14.
 */
public abstract class BaseRequest implements Serializable {

    private HttpServletRequest request;
    private HttpServletResponse response;
    /**
     * request token
     */
    private List<Token> tokens;
    /**
     * token 验证的执行者
     */
    private Authenticator authenticator;

    public boolean hasToken(){
        return getToken() != null;
    }

    public Token getToken() {
        if (tokens == null || tokens.size() == 0) {
            return null;
        }
        return tokens.get(0);
    }

    public void consumeToken() {
        if(tokens != null){
            tokens.remove(0);
        }
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
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
