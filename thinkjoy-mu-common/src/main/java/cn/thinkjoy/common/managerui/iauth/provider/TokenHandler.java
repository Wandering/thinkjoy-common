package cn.thinkjoy.common.managerui.iauth.provider;

import cn.thinkjoy.common.managerui.iauth.provider.token.Token;

import java.io.IOException;

/**
 * Created by Michael on 11/13/14.
 */
public interface TokenHandler extends Authentication{

    String getHandleTokenType();

    Token getTokenFromRequest(BaseRequest baseRequest);

    /**
     * 当验证失败会调用
     * @param baseRequest
     * @throws java.io.IOException
     */
    void callWhenAuthenticationFailed(BaseRequest baseRequest) throws IOException;

    /**
     * 党验证出错会调用
     * @param baseRequest
     * @param ex
     * @throws IOException
     */
    void callWhenAuthenticationError(BaseRequest baseRequest, Exception ex) throws IOException;

}
