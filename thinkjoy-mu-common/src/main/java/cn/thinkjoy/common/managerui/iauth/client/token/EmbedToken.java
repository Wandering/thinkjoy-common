package cn.thinkjoy.common.managerui.iauth.client.token;

import cn.thinkjoy.common.managerui.iauth.provider.token.Token;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Michael on 11/10/14.
 */
public class EmbedToken implements Token{

    private final int weight = 10;
    private String tokenType = EMBED_TOKEN.toLowerCase();
    private String value;
    private String secret;
    private Date birthday;
    private Map<String, Object> additionalInformation = Collections.emptyMap();
    private long userId;
    private String embedTokenType;

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
        return secret;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
