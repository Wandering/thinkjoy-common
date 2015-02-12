package cn.thinkjoy.common.managerui.iauth.core;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Michael on 11/7/14.
 * 应该是抽象类
 */
public abstract class Credential {
    //    private String user_agent;
    private String secret;
    private String key;
    //    private Token token;
    private Map<String, Object> additionalInformation = Collections.emptyMap();

    public String getKey() {


        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    //    public String getUser_agent() {
//        return user_agent;
//    }

//    public void setUser_agent(String user_agent) {
//        this.user_agent = user_agent;
//    }

//    public Token getToken() {
//        return token;
//    }
//
//    public void setToken(Token token) {
//        this.token = token;
//    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
