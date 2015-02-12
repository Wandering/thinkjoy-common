package cn.thinkjoy.common.managerui.iauth.core.handler;

import cn.thinkjoy.common.managerui.iauth.core.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.core.exception.TokenNotExistException;
import cn.thinkjoy.common.managerui.iauth.core.token.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    public void callWhenAuthenticationError(BaseRequest baseRequest, RuntimeException ex) throws IOException {
        logger.error("Token解析出错。解析器异常: " + ex.getMessage(), ex);
        baseRequest.getAuthenticator().redirectTologin(baseRequest, ex);

    }

    @Override
    public boolean callWhenAuthenticationSuccess(BaseRequest baseRequest) throws IOException {
        logger.info("Token解析完成。");
        return true;
    }

    /**
     * 进行token存储
     *
     * @param baseRequest
     * @return
     */
    @Override
    public void embed(BaseRequest baseRequest) {
        assert false; // unreachable
    }

    /**
     * 从请求中获取token
     *
     * @param baseRequest
     * @return
     */
    @Override
    public void invoke(BaseRequest baseRequest) {
        List<Token> tokens = baseRequest.getAuthenticator().getTokens(baseRequest);
        if (tokens.isEmpty()) {
            throw new TokenNotExistException("未登录。");
        }

        Collections.sort(tokens, new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });

        baseRequest.setTokens(tokens);
    }

    @Override
    public void clear(BaseRequest baseRequest) {

    }

    @Override
    public String getHandleTokenType() {
        return null;
    }

    @Override
    public Token loadTokenFromRequest(BaseRequest baseRequest) {
        return null;
    }
}
