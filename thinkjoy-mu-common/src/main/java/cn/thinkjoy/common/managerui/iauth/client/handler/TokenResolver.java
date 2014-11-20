package cn.thinkjoy.common.managerui.iauth.client.handler;

import cn.thinkjoy.common.managerui.iauth.provider.AbstractTokenHandler;
import cn.thinkjoy.common.managerui.iauth.provider.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.provider.token.Token;
import cn.thinkjoy.common.managerui.iauth.provider.token.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Michael on 11/12/14.
 * <p/>
 * token的验证基础类
 */
public class TokenResolver extends AbstractTokenHandler {
    private static Logger logger = LoggerFactory.getLogger(TokenResolver.class);


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
     * 进行token存储
     *
     * @param baseRequest
     * @return
     */
    @Override
    public boolean embed(BaseRequest baseRequest) {
//        Token token = baseRequest.getToken();
//        if (token == null) { return false; }
        //tokenStore.store(token.getValue(), token);
        assert false; // unreachable
        return true;
    }

    /**
     * 从请求中获取token
     *
     * @param baseRequest
     * @return
     */
    @Override
    public boolean invoke(BaseRequest baseRequest) {
        List<Token> tokens = baseRequest.getAuthenticator().getTokens(baseRequest);
        if (tokens.isEmpty()) {
            return false;
        }

        Collections.sort(tokens, new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });

        baseRequest.setToken(tokens.get(0));
        return true;
    }

    @Override
    public String getHandleTokenType() {
        return null;
    }

    @Override
    public Token getTokenFromRequest(BaseRequest baseRequest) {
        return null;
    }
}
