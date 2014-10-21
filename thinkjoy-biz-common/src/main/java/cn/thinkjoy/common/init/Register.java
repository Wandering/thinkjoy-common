package cn.thinkjoy.common.init;

import cn.thinkjoy.cloudstack.cmc.ClusterManagerClientFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 注册自己到 CMC
 * <p/>
 * 创建时间: 14-10-8 下午8:53<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Component("register")
public class Register {
    @PostConstruct
    public void init(){
        ClusterManagerClientFactory.createClient().register();
    }
}
