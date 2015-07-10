package cn.thinkjoy.common.managerui.iauth.core;

import cn.thinkjoy.common.managerui.iauth.core.exception.CannotAuthException;
import cn.thinkjoy.common.managerui.iauth.core.handler.AbstractTokenBundledHandler;
import cn.thinkjoy.common.managerui.iauth.core.handler.TokenHandler;
import cn.thinkjoy.common.managerui.iauth.core.handler.TokenResolver;
import cn.thinkjoy.common.managerui.iauth.core.token.Token;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 11/10/14.
 * filter used autnenticator
 */
public abstract class Authenticator {

    public abstract List<TokenHandler> getTokenHandlers();


    public abstract boolean isNeedAuthentication(BaseRequest baseRequest);

    public abstract void init(List<TokenHandler> tokenHandlers);

    public List<Token> getTokens(BaseRequest baseRequest) {
        List<Token> tokens = new ArrayList<Token>();
        for (TokenHandler tokenHandler : getTokenHandlers()) {
            Token token = tokenHandler.loadTokenFromRequest(baseRequest);
            if (token != null) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public abstract boolean isLogout(BaseRequest baseRequest);

    /**
     * 找到相应的token handler进行执行
     * 注意执行顺序
     *
     * @param req
     * @param res
     * @param chain
     * @throws cn.thinkjoy.common.managerui.iauth.core.exception.CannotAuthException
     * @throws IOException
     * @throws ServletException
     */
    public void authentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{
        BaseRequestFactory requestFactory = getRequestFactory();
        BaseRequest baseRequest = requestFactory.buildFromHttpServletRequest(req, res, this);


        if (!isNeedAuthentication(baseRequest)) {
            chain.doFilter(req, res);
            return;
        }

        try {
            TokenResolver tokenResolver = (TokenResolver) getTokenHandlers().get(0);
            tokenResolver.invoke(baseRequest);
            while (baseRequest.hasToken()) {
                TokenHandler tokenHandler = getTokenHandler(baseRequest.getToken().getTokenType());
                if (tokenHandler == null) {
                    throw new CannotAuthException(String.format("没有找到%s处理器", baseRequest.getToken().getTokenType()));
                }

//              if (!checkTokenType(baseRequest, tokenHandler)) {
//                  continue;
//              }

                try {
                    tokenHandler.invoke(baseRequest);
                    if (!tokenHandler.callWhenAuthenticationSuccess(baseRequest)) {
                        return;
                    }

                } catch (RuntimeException e) {
                    tokenHandler.callWhenAuthenticationError(baseRequest, e);
                    // TODO
                    return;
                }

            }
            // passed
            if (isLogout(baseRequest)) {
                for (TokenHandler tokenHandler : getTokenHandlers()) {
                    tokenHandler.clear(baseRequest);
                }
                redirectTologout(baseRequest);
                return;
            }
            callWhenAuthenticatornSuccess(baseRequest);
            chain.doFilter(req, res);
        } catch (RuntimeException ex) {
            callWhenAuthenticatiornError(baseRequest, ex);

        }


    }

    private TokenHandler getTokenHandler(String tokenType) {
        for (TokenHandler tokenHandler : getTokenHandlers()) {
            if (checkTokenType(tokenType, tokenHandler)) {
                return tokenHandler;
            }
        }
        return null;
    }

    @Deprecated
    protected boolean checkTokenType(BaseRequest baseRequest, TokenHandler tokenHandler) {
        if (tokenHandler instanceof TokenResolver) {
            return true;
        }
        return checkTokenType(baseRequest.getToken().getTokenType(), tokenHandler);
    }

    protected boolean checkTokenType(String tokenType, TokenHandler tokenHandler) {
        if (tokenHandler.getHandleTokenType() == null) {
            return false;
        }
        // string compare
        if (tokenHandler.getHandleTokenType().equals(tokenType)) {
            return true;
        }
        return false;
    }


    /**
     * 找到相应的token handler进行执行
     * 如果有绑定关系的handler，全部找到执行
     *
     * @param baseRequest
     */
    public void embedment(BaseRequest baseRequest, String tokenType) {
        TokenHandler tokenHandler = getTokenHandler(tokenType);
        if (tokenHandler == null) {
            throw new CannotAuthException(String.format("没有找到%s解析器", baseRequest.getToken().getTokenType()));
        }
        if (tokenHandler instanceof AbstractTokenBundledHandler) {
            // 处理绑定关系的handler
            AbstractTokenBundledHandler bundledTokenHandler = (AbstractTokenBundledHandler) tokenHandler;
            if (bundledTokenHandler.isBundled()) {
                tokenHandler.embed(baseRequest);

                for (Authentication bundle : bundledTokenHandler.getOthers()) {
                    bundle.embed(baseRequest);
                }
            }
        } else {
            tokenHandler.embed(baseRequest);
        }

    }

    public abstract BaseRequestFactory getRequestFactory();

    public abstract void destroy();

    /**
     * 中断验证后进行需要的跳转
     *
     * @param baseRequest
     * @throws IOException
     */
    public abstract void redirectTologin(BaseRequest baseRequest, RuntimeException ex) throws IOException;


    /**
     * for ajax request auth response
     * @param baseRequest
     */
    public abstract void setResponseForAjax(BaseRequest baseRequest);


    /**
     * 登出后进行需要的跳转
     *
     * @param baseRequest
     * @throws IOException
     */
    public abstract void redirectTologout(BaseRequest baseRequest) throws IOException;

    /**
     * 完成验证后，给请求加上已经验证通过的标示和userid
     *
     * @param baseRequest
     */
    public abstract void callWhenAuthenticatornSuccess(BaseRequest baseRequest);


    /**
     * 完成埋点后，对请求进行封装处理
     *
     * @param baseRequest
     */
    public abstract void callWhenEmbedmentSuccess(BaseRequest baseRequest);


    /**
     * 验证器出异常，进行调用
     *
     * @param baseRequest
     */
    public abstract void callWhenAuthenticatiornError(BaseRequest baseRequest, RuntimeException ex) throws IOException;

}
