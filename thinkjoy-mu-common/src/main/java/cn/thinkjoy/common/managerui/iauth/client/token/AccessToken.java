package cn.thinkjoy.common.managerui.iauth.client.token;

import cn.thinkjoy.common.managerui.iauth.core.token.Token;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Michael on 11/7/14.
 *
 * redis manage
 */
public class AccessToken implements Token {

    private final int weight = 10;  // 较高
    private String tokenType = ACCESS_TOKEN.toLowerCase();
    private String value;
    private String secret;
    private Date birthday;
    private Map<String, Object> additionalInformation = Collections.emptyMap();
    private long userId;

    public AccessToken() {
        value = UUID.randomUUID().toString();
        birthday = new Date();
    }

    public AccessToken(String value) {
        this.value = value;
    }

    public AccessToken(String value,  String secret) {
        this.value = value;
        this.secret = secret;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public boolean isSecretRequired() {
        return secret != null;
    }

    @Override
    public long getExpireSecond() {
        return 60*30;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccessToken)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        AccessToken that = (AccessToken) o;

        if (!value.equals(that.getValue())) {
            return false;
        }
        if (isSecretRequired()) {
            if (!secret.equals(that.getSecret())) {
                return false;
            }
        }

        return true;
    }
}
