package cn.thinkjoy.rmq;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClient;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RocketMQ 生产方 单例
 * <p/>
 * 创建时间: 15/6/17 上午10:33<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class RocketMQSingleton {
    public static final Logger logger = LoggerFactory.getLogger(RocketMQSingleton.class);
    private static DefaultMQProducer instance = null;
    private static String RMQ_BROKER_URL = null;

    private RocketMQSingleton(){
    }

    public static synchronized DefaultMQProducer getInstance() {
        if(RMQ_BROKER_URL == null || RMQ_BROKER_URL.length() == 0){
            try {
                RMQ_BROKER_URL = DynConfigClientFactory.getClient().getConfig("cmc", "common", "namesrvAddr");
            } catch (Exception e) {
                logger.error("init RMQ broker url config error!", e);
                System.exit(-1);
            }
        }
        if(RMQ_BROKER_URL == null || RMQ_BROKER_URL.length() == 0){
            logger.error("init RMQ broker url config error! namesrvAddr is empty.");
            System.exit(-1);
        }
        if(instance == null) {
            String group = CloudContextFactory.getCloudContext().getProduct() + "-" + CloudContextFactory.getCloudContext().getApplicationName();
            //CloudContextFactory.getCloudContext().getApplicationName()
            instance = new DefaultMQProducer(group);
            //CloudContextFactory.getCloudContext().getApplicationName()
            instance.setNamesrvAddr(RMQ_BROKER_URL);
            //CloudContextFactory.getCloudContext().getApplicationName()
//            instance.setInstanceName(group);
            try {
                instance.start();
            } catch (MQClientException e) {
                logger.error("init rocketMQ error", e);
                System.exit(-1);
            }
        }

        return instance;
    }
}
