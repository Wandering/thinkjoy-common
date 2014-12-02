package cn.thinkjoy.common.managerui.iauth.provider;

import cn.thinkjoy.common.managerui.iauth.provider.handler.TokenResolver;
import cn.thinkjoy.common.managerui.iauth.provider.handler.AbstractTokenBundledHandler;
import cn.thinkjoy.common.managerui.iauth.provider.handler.TokenHandler;
import cn.thinkjoy.common.managerui.iauth.provider.token.Token;

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


    public abstract boolean isNeedAuthentication();

    public abstract void init(List<TokenHandler> tokenHandlers);

    public List<Token> getTokens(BaseRequest baseRequest) {
        List<Token> tokens = new ArrayList<Token>();
        for (TokenHandler tokenHandler : getTokenHandlers()) {
            Token token = tokenHandler.getTokenFromRequest(baseRequest);
            if (token != null) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    /**
     * 找到相应的token handler进行执行
     * 注意执行顺序
     * @param req
     * @param res
     * @param chain
     * @throws CannotAuthException
     * @throws IOException
     * @throws ServletException
     */
    public void authentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{
        if(!isNeedAuthentication()){
            chain.doFilter(req,res);
            return;
        }

        BaseRequestFactory requestFactory = getRequestFactory();
            BaseRequest baseRequest = requestFactory.buildFromHttpServletRequest(req,res,this);
        try {
            for (TokenHandler tokenHandler: getTokenHandlers()) {
                if (!checkTokenTypeBeforeInvoke(baseRequest, tokenHandler)) {
                    continue;
                }
                try {
                    if (tokenHandler.invoke(baseRequest)) {
                        // TODO 增加验证结果
                    } else {
                        tokenHandler.callWhenAuthenticationFailed(baseRequest);
                        return;
                    }
                } catch (Exception e) {
                    tokenHandler.callWhenAuthenticationError(baseRequest, e);
                    // TODO

                    return;
                }
            }
            // passed
            authenticationDone(baseRequest);
            chain.doFilter(req, res);
        } catch (CannotAuthException ex) {
            callWhenAuthenticatiorError(baseRequest, ex);

        }

    }

    protected boolean checkTokenTypeBeforeInvoke(BaseRequest baseRequest, TokenHandler tokenHandler) {
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
        if (tokenHandler.getHandleTokenType().equals(tokenType) ) {
            return true;
        }
        return false;
    }


    /**
     * 找到相应的token handler进行执行
     * 如果有绑定关系的handler，全部找到执行
     * @param baseRequest
     */
    public boolean embedment(BaseRequest baseRequest, String tokenType)  {
//        List<TokenHandler> copy = new ArrayList<TokenHandler>();
////        Collections.copy(copy, getTokenHandlers());
//        copy.addAll(getTokenHandlers());
//        Collections.reverse(copy);
        for (TokenHandler tokenHandler : getTokenHandlers()) {
            if (!checkTokenType(tokenType, tokenHandler)) {
                continue;
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
                continue;
            }
            if (tokenHandler.embed(baseRequest)) {
                // TODO
            } else {
                return false;
            }

        }
        return true;
    }

    public abstract BaseRequestFactory getRequestFactory();

    public abstract void destroy();

    /**
     * 中断验证后进行需要的跳转
     * @param baseRequest
     * @throws IOException
     */
    public abstract void redirectTologin(BaseRequest baseRequest) throws IOException;

    /**
     * 完成验证后，给请求加上已经验证通过的标示和userid
     * @param baseRequest
     */
    public abstract void authenticationDone(BaseRequest baseRequest);


    /**
     * 完成埋点后，对请求进行封装处理
     * @param baseRequest
     */
    public abstract void embedmentDone(BaseRequest baseRequest);


    /**
     * 验证器出异常，进行调用
     * @param baseRequest
     */
    public abstract void callWhenAuthenticatiorError(BaseRequest baseRequest, CannotAuthException ex) throws IOException;

    /**
     * 验证器没通过，进行调用
     * @param baseRequest
     * @throws IOException
     */
    @Deprecated
    public abstract void callWhenAuthenticatiorFailed(BaseRequest baseRequest) throws IOException;
}
