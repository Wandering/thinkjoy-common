package cn.thinkjoy.common.managerui.iauth.provider;

import cn.thinkjoy.common.managerui.iauth.provider.token.Token;
import cn.thinkjoy.common.managerui.domain.User;

import javax.security.auth.Subject;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Michael on 11/10/14.
 *
 * 用于验证token后存放验证信息实体的类
 * token是验证后的token
 * 可用来存放User等
 */
public class Principal<T> implements java.security.Principal {
    private T owner;
    private Token token;

    private Map<String, Object> additionalInformation = Collections.emptyMap();


    public T getOwner() {
        return owner;
    }

    public void setOwner(T owner) {
        this.owner = owner;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getSecret(){
        if (token == null ) { return null ;}
        return token.getSecret();
    }

    @Override
    public String getName() {
        if (token == null && owner == null) {
            return null;
        }
        return "passed";
    }


    public boolean implies(Subject subject) {
        return false;
    }
}
