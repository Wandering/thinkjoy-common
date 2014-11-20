package cn.thinkjoy.common.managerui.iauth.client;

import cn.thinkjoy.common.managerui.iauth.provider.Authenticator;
import cn.thinkjoy.common.managerui.iauth.provider.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.provider.BaseRequestFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael on 11/10/14.
 */
public class DefaultAuthRequestFactory implements BaseRequestFactory{


    @Override
    public BaseRequest buildFromHttpServletRequest(HttpServletRequest request, HttpServletResponse response, Authenticator authenticator) {
        BaseRequest baseRequest = new DefaultAuthRequest();
        baseRequest.setRequest(request);
        baseRequest.setResponse(response);
        baseRequest.setAuthenticator(authenticator);
        return baseRequest;
    }
}
