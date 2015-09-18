package cn.thinkjoy.common.managerui.iauth.client.token.storage;

import cn.thinkjoy.common.managerui.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Michael on 11/10/14.
 */
@Repository
public interface UserStore {

    User storeUser(Object key, User user);

    User readUser(Object key);

    void postpone(Object key);

    void removeUser(String key);
}
