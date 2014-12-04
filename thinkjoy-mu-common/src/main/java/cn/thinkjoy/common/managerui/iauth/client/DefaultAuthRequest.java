package cn.thinkjoy.common.managerui.iauth.client;


import cn.thinkjoy.common.managerui.domain.User;
import cn.thinkjoy.common.managerui.iauth.provider.BaseRequest;
import cn.thinkjoy.common.managerui.iauth.provider.Credential;
import cn.thinkjoy.common.managerui.iauth.provider.Principal;

import javax.servlet.http.Cookie;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Michael on 11/10/14.
 */
public class DefaultAuthRequest extends BaseRequest {
    private Principal<User> principal;
    private Credential credential;
    private Map<String, Object> additionalInformation = Collections.emptyMap();

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Principal<User> getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal<User> principal) {
        this.principal = principal;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public Cookie getCookieFromRequest(String name) {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(name))
                return cookie;
        }

        return null;

    }

    public void addCookieToResponse(Cookie... cookies) {
        for (Cookie cookie : cookies) {
            getResponse().addCookie(cookie);
        }
    }

    public void removeCookieToResponse(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        addCookieToResponse(cookie);
    }


}
