package cn.thinkjoy.common.managerui.iauth.client;

import cn.thinkjoy.common.managerui.iauth.core.Authenticator;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.core.BaseRequestFactory;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael on 11/10/14.
 */
public class DefaultAuthRequestFactory implements BaseRequestFactory {


    @Override
    public BaseRequest buildFromHttpServletRequest(HttpServletRequest request, HttpServletResponse response, Authenticator authenticator) {
        BaseRequest baseRequest = new DefaultAuthRequest();
        baseRequest.setRequest(request);
        baseRequest.setResponse(response);
        baseRequest.setAuthenticator(authenticator);

        if (!Strings.isNullOrEmpty(request.getParameter("debug"))) {
            ((DefaultAuthRequest) baseRequest).setDebug(true);
        }
        return baseRequest;
    }
}
