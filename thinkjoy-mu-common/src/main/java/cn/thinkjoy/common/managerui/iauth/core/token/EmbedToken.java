package cn.thinkjoy.common.managerui.iauth.core.token;

import cn.thinkjoy.common.managerui.iauth.core.token.Token;

import java.util.*;

/**
 * Created by Michael on 11/10/14.
 */
public class EmbedToken implements Token{

    private final int weight = 100; // 非常高
    private String tokenType = EMBED_TOKEN.toLowerCase();
    private String value;
    private Date birthday;
    private Map<String, Object> additionalInformation = new HashMap<String, Object>();
    private String embedTokenType;
    private Token embedToken;

    public EmbedToken() {
        value = UUID.randomUUID().toString();
        birthday = new Date();
    }
    public String getEmbedTokenType() {
        return embedTokenType;
    }

    public void setEmbedTokenType(String embedTokenType) {
        this.embedTokenType = embedTokenType;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getSecret() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public long getExpireSecond() {
        return 5;
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

    public Token getEmbedToken() {
        return embedToken;
    }

    public void setEmbedToken(Token embedToken) {
        this.embedToken = embedToken;
    }
}
