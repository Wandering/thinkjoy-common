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
public class UcmAuthRequest extends BaseRequest {
    private Principal<User> principal;
    private Credential credential;

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    private Map<String, Object> additionalInformation = Collections.emptyMap();


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
        for (Cookie cookie: cookies){
            if (cookie.getName().equalsIgnoreCase(name))
                return cookie;
        }

        return null;

    }

    public void addCookieToResponse(Cookie ... cookies ) {
        for (Cookie cookie: cookies) {
            getResponse().addCookie(cookie);
        }
    }




}
