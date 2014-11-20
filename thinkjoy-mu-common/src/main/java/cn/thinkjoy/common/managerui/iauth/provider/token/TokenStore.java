package cn.thinkjoy.common.managerui.iauth.provider.token;

import org.springframework.stereotype.Repository;

/**
 * Created by Michael on 11/10/14.
 */

@Repository
public interface TokenStore {
    Token readToken(String key);

    void postpone(String key);

    // 注意token的有效期
    void store(String key, Token token);

    void removeToken(String key);
}
