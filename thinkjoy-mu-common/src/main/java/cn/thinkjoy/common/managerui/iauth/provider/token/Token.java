package cn.thinkjoy.common.managerui.iauth.provider.token;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Michael on 11/7/14.
 */
public interface  Token extends Serializable{
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EMBED_TOKEN = "embed_token";

    /**
     * 按照权重顺序进行执行，权重高的优先
     * @return
     */
    int getWeight();

    String getValue();
    String getSecret();
    boolean isSecretRequired();
    long getExpireSecond();

    String getTokenType();
    Date getBirthday();

    Map<String, Object> getAdditionalInformation();

}
