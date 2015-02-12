package cn.thinkjoy.common.managerui.iauth.core.handler;

import cn.thinkjoy.common.managerui.iauth.core.Authentication;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.core.token.Token;

import java.io.IOException;

/**
 * Created by Michael on 11/13/14.
 */
public interface TokenHandler extends Authentication {

    String getHandleTokenType();

    Token loadTokenFromRequest(BaseRequest baseRequest);


    /**
     * 当验证失败或出错会调用
     * @param baseRequest
     * @param ex
     * @throws IOException
     */
    void callWhenAuthenticationError(BaseRequest baseRequest, RuntimeException ex) throws IOException;


    /**
     * 当验证通过会调用
     * @param baseRequest
     * @return
     * 当 返回true 继续执行，当返回 false终止
     * @throws IOException
     */
    boolean callWhenAuthenticationSuccess(BaseRequest baseRequest) throws IOException;



}
