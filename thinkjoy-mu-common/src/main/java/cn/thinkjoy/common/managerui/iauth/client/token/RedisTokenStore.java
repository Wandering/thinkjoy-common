package cn.thinkjoy.common.managerui.iauth.client.token;

import cn.thinkjoy.cloudstack.cache.RedisRepository;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.cloudstack.dynconfig.IChangeListener;
import cn.thinkjoy.cloudstack.dynconfig.domain.Configuration;
import cn.thinkjoy.common.managerui.iauth.provider.token.Token;
import cn.thinkjoy.common.managerui.iauth.provider.token.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 11/10/14.
 */
@Repository
public class RedisTokenStore implements TokenStore {
    public int TOKEN_EXPIRE_TIME = 60*10;    // second default
    public static final String PREFIX = "token:";

    @Autowired
    private RedisRepository redisRepository;


    @PostConstruct
    public void init(){
        DynConfigClient dynConfigClient = DynConfigClientFactory.getClient();
        try {
            TOKEN_EXPIRE_TIME = Integer.parseInt(dynConfigClient.getConfig("ucm", "common", "tokenExpireTime"));
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
                        TOKEN_EXPIRE_TIME = Integer.parseInt(configuration.getConfig());
                    }
                });
            }
        });
    }


    @Override
    public Token readToken(String key) {
        return (Token) redisRepository.get(PREFIX+key);
    }

    @Override
    public void postpone(String key) {
        redisRepository.expire(PREFIX+key, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);

    }

    @Override
    public void store(String key, Token token)
    {
        redisRepository.set(PREFIX+key, token, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void removeToken(String key) {
        redisRepository.del(PREFIX+key);
    }
}
