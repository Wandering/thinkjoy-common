package cn.thinkjoy.common.managerui.iauth.core.handler;

import cn.thinkjoy.common.managerui.iauth.core.exception.TokenNotExistException;
import cn.thinkjoy.common.managerui.iauth.core.token.EmbedToken;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.core.exception.CannotAuthException;
import cn.thinkjoy.common.managerui.iauth.core.token.Token;
import cn.thinkjoy.common.managerui.iauth.core.token.storage.TokenStore;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by Michael on 11/10/14.
 * <p/>
 * 对embed token进行验证、处理
 */
public class EmbedTokenHandler extends AbstractTokenHandler {
    private static Logger logger = LoggerFactory.getLogger(EmbedTokenHandler.class);


    @Autowired
    private TokenStore tokenStore;


    private String handleTokenType = Token.EMBED_TOKEN;


    /**
     * 如果验证通过，调用所有handler进行埋点
     *
     * @param baseRequest
     * @return
     * @throws IOException
     */
    @Override
    public void invoke(BaseRequest baseRequest) throws IOException {
        EmbedToken token = (EmbedToken) baseRequest.getToken();
        if (token == null) {
            throw new TokenNotExistException("EmbedToken不存在。");
        }
        // 客户端对已经注册的handler进行埋点
        baseRequest.getAuthenticator().embedment(baseRequest, token.getEmbedTokenType());
    }

    @Override
    public void clear(BaseRequest baseRequest) {
        assert false;
    }


    @Override
    public void callWhenAuthenticationError(BaseRequest baseRequest, RuntimeException ex) throws IOException {
        logger.error("埋点失败。埋点处理出现异常: " + ex.getMessage(), ex);
        baseRequest.getAuthenticator().redirectTologin(baseRequest, ex);

    }

    @Override
    public boolean callWhenAuthenticationSuccess(BaseRequest baseRequest) throws IOException {
        // 埋点完成，结束当前验证，重定向到原访问路径
        String url = baseRequest.getRequest().getRequestURI();
        logger.info("埋点完成。重定向到当前访问路径: uri=" + url);
        baseRequest.getResponse().sendRedirect(url);
        baseRequest.consumeToken();
        // 停止执行
        return false;
    }

    /**
     * @param baseRequest
     * @return
     */
    @Override
    public void embed(BaseRequest baseRequest) {
        assert false;   // unreachable
    }

    @Override
    public String getHandleTokenType() {
        return handleTokenType;
    }

    @Override
    public Token loadTokenFromRequest(BaseRequest baseRequest) {
        // 获取parameter中的token
        String tokenParam = baseRequest.getRequest().getParameter(HTTP_PARAMETER_EMBED_TOKEN);

        if (Strings.isNullOrEmpty(tokenParam)) {
            return null;
        }
        String tokenValue = tokenParam;

        // 获取服务器存储的token
        Token storedToken = tokenStore.readToken(tokenValue);
        if (storedToken != null) {
            tokenStore.removeToken(storedToken.getValue());
        }
        return storedToken;
    }
}
