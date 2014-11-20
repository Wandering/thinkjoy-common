package cn.thinkjoy.common.managerui.iauth.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael on 11/10/14.
 */
public interface BaseRequestFactory {
    BaseRequest buildFromHttpServletRequest(HttpServletRequest request, HttpServletResponse response, Authenticator authenticator);

}
