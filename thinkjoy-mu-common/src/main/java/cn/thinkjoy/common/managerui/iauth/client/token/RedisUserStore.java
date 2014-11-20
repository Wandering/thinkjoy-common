package cn.thinkjoy.common.managerui.iauth.client.token;

import cn.thinkjoy.cloudstack.cache.RedisRepository;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.cloudstack.dynconfig.IChangeListener;
import cn.thinkjoy.cloudstack.dynconfig.domain.Configuration;
import cn.thinkjoy.common.managerui.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 11/13/14.
 */
@Repository
public class RedisUserStore implements UserStore{
    public int USER_EXPIRE_TIME = 60*10;    // second default
    public static final String PREFIX = "user:";

    @Autowired
    private RedisRepository redisRepository;
    @PostConstruct
    public void init(){
        DynConfigClient dynConfigClient = DynConfigClientFactory.getClient();
        try {
            USER_EXPIRE_TIME = Integer.parseInt(dynConfigClient.getConfig("ucm", "common", "tokenExpireTime"));
        } catch (Exception e) {
            //TODO
        }
        dynConfigClient.registerListeners("ucm", "common", "tokenExpireTime", new IChangeListener() {
            @Override
            public Executor getExecutor() {
                return Executors.newSingleThreadExecutor();
            }

            @Override
            public void receiveConfigInfo(final Configuration configuration) {
                System.out.println("=================" + configuration);

                getExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("========ASYN=========" + configuration);
                        USER_EXPIRE_TIME = Integer.parseInt(configuration.getConfig());
                    }
                });
            }
        });
    }


    @Override
    public User storeUser(long key, User user) {
        redisRepository.set(PREFIX+key, user, USER_EXPIRE_TIME, TimeUnit.SECONDS);
        return null;
    }

    @Override
    public User readUser(long key) {
        return (User) redisRepository.get(PREFIX+key);
    }

    @Override
    public void postpone(long key) {
        redisRepository.expire(PREFIX+key, USER_EXPIRE_TIME, TimeUnit.SECONDS);
    }
}
