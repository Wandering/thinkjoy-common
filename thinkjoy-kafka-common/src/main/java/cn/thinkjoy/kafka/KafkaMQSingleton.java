package cn.thinkjoy.kafka;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.dap.dataservice.DapConnectInfo;
import cn.thinkjoy.dap.dataservice.DapDataSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/8/26 下午11:45<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class KafkaMQSingleton {
    public static final Logger logger = LoggerFactory.getLogger(KafkaMQSingleton.class);
    private static DapDataSender instance = null;
    private static String RMQ_BROKER_URL = null;

    private KafkaMQSingleton(){
    }

    public static synchronized DapDataSender getInstance(){
        if(RMQ_BROKER_URL == null || RMQ_BROKER_URL.length() == 0){
            try {
                RMQ_BROKER_URL = DynConfigClientFactory.getClient().getConfig("cmc", "common", "kafkaAddr");
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
            String[] ipPorts = new String[]{RMQ_BROKER_URL};
            DapConnectInfo dapConnectInfo = new DapConnectInfo(ipPorts);
            try {
                instance = new DapDataSender(dapConnectInfo);
                //CloudContextFactory.getCloudContext().getApplicationName()
//                instance.setNamesrvAddr(RMQ_BROKER_URL);
                //CloudContextFactory.getCloudContext().getApplicationName()
    //            instance.setInstanceName(group);

//                instance.start();
            } catch (Exception e) {
                logger.error("init kafka error", e);
                System.exit(-1);
            }
        }

        return instance;
    }
}
