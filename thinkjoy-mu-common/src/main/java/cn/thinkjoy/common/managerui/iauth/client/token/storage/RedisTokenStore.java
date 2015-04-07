package cn.thinkjoy.common.managerui.iauth.client.token.storage;

import cn.thinkjoy.cloudstack.cache.RedisRepository;
import cn.thinkjoy.cloudstack.cache.RedisRepositoryFactory;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.cloudstack.dynconfig.IChangeListener;
import cn.thinkjoy.cloudstack.dynconfig.domain.Configuration;
import cn.thinkjoy.common.managerui.iauth.core.token.Token;
import cn.thinkjoy.common.managerui.iauth.core.token.storage.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(RedisTokenStore.class);
    public int TOKEN_EXPIRE_TIME = 60*10;    // second default
    public static final String PREFIX = "token:";

    private RedisRepository tokenStorage;

    public RedisRepository getTokenStorage() {
        return tokenStorage;
    }

    @PostConstruct
    public void init() throws Exception {

        DynConfigClient dynConfigClient = DynConfigClientFactory.getClient();
        try {
            TOKEN_EXPIRE_TIME = Integer.parseInt(dynConfigClient.getConfig("ucm", "common", "tokenExpireTime"));
        } catch (Exception e) {
            logger.info("tokenExpireTime没有进行配置，采用默认值: "+TOKEN_EXPIRE_TIME);
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
        tokenStorage = RedisRepositoryFactory.getRepository("ucm", "common", "tokenStorage");
    }


    @Override
    public Token readToken(String key) {
        return (Token) tokenStorage.get(PREFIX+key);
    }

    @Override
    public void postpone(String key) {
        tokenStorage.expire(PREFIX+key, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);

    }

    @Override
    public void store(String key, Token token)
    {
        tokenStorage.set(PREFIX+key, token, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void removeToken(String key) {
        tokenStorage.del(PREFIX+key);
    }
}
