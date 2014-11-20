package cn.thinkjoy.common.managerui.iauth.client.token;

import cn.thinkjoy.common.managerui.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Michael on 11/10/14.
 */
@Repository
public interface UserStore {

    User storeUser(long key, User user);

    User readUser(long key);

    void postpone(long key);
}
