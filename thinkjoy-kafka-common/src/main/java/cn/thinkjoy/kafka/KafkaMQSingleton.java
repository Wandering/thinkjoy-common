package cn.thinkjoy.kafka;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.dap.dataservice.DapConnectInfo;
import cn.thinkjoy.dap.dataservice.DapDataReceiver;
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
    private static String OUT_NET_FLAG = "rest";
    private static DapDataSender outNetinstance = null;
    private static String RMQ_BROKER_URL_OUT_NET = null;

    private static DapDataReceiver outNetReceiverInstance = null;
    private static DapDataReceiver receiverInstance = null;
    private static String REVEIVER_RMQ_BROKER_URL_OUT_NET = null;


    private KafkaMQSingleton() {
    }

    public static synchronized DapDataSender getInstance() {
        if (RMQ_BROKER_URL == null || RMQ_BROKER_URL.length() == 0) {
            try {
                RMQ_BROKER_URL = DynConfigClientFactory.getClient().getConfig("cmc", "cmc", "common", "kafkaAddr");
            } catch (Exception e) {
                logger.error("init RMQ broker url config error!", e);
                System.exit(-1);
            }
        }
        if (RMQ_BROKER_URL == null || RMQ_BROKER_URL.length() == 0) {
            logger.error("init RMQ broker url config error! namesrvAddr is empty.");
            System.exit(-1);
        }
        if (instance == null) {
            String group = CloudContextFactory.getCloudContext().getProduct() + "-" + CloudContextFactory.getCloudContext().getApplicationName();
            //CloudContextFactory.getCloudContext().getApplicationName()
            String[] ipPorts = RMQ_BROKER_URL.split(",");//   new String[]{RMQ_BROKER_URL, RMQ_BROKER_URL};
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


    public static synchronized DapDataSender getInstanceForOutNet() {
        if (RMQ_BROKER_URL_OUT_NET == null || RMQ_BROKER_URL_OUT_NET.length() == 0) {
            try {
                RMQ_BROKER_URL_OUT_NET = DynConfigClientFactory.getClient().getConfig("cmc", "cmc", "common", "kafkaAddr");
            } catch (Exception e) {
                logger.error("init RMQ broker url config error!", e);
                System.exit(-1);
            }
        }
        if (RMQ_BROKER_URL_OUT_NET == null || RMQ_BROKER_URL_OUT_NET.length() == 0) {
            logger.error("init RMQ broker url config error! namesrvAddr is empty.");
            System.exit(-1);
        }
        if (outNetinstance == null) {
            String[] ipPorts = RMQ_BROKER_URL_OUT_NET.split(",");//   new String[]{RMQ_BROKER_URL, RMQ_BROKER_URL};
            DapConnectInfo dapConnectInfo = new DapConnectInfo(ipPorts);
            try {
                outNetinstance = new DapDataSender(dapConnectInfo, OUT_NET_FLAG);
            } catch (Exception e) {
                logger.error("init kafka error", e);
                System.exit(-1);
            }
        }

        return outNetinstance;
    }


    public static synchronized DapDataReceiver getReceiverInstanceForOutNet() {
        if (REVEIVER_RMQ_BROKER_URL_OUT_NET == null || REVEIVER_RMQ_BROKER_URL_OUT_NET.length() == 0) {
            try {
                REVEIVER_RMQ_BROKER_URL_OUT_NET = DynConfigClientFactory.getClient().getConfig("cmc", "cmc", "common", "kafkaConsumer");
            } catch (Exception e) {
                logger.error("init RMQ broker url config error!", e);
                System.exit(-1);
            }
        }
        if (REVEIVER_RMQ_BROKER_URL_OUT_NET == null || REVEIVER_RMQ_BROKER_URL_OUT_NET.length() == 0) {
            logger.error("init RMQ broker url config error! namesrvAddr is empty.");
            System.exit(-1);
        }
        if (outNetReceiverInstance == null) {
            String[] ipPorts = REVEIVER_RMQ_BROKER_URL_OUT_NET.split(",");//   new String[]{RMQ_BROKER_URL, RMQ_BROKER_URL};
            DapConnectInfo dapConnectInfo = new DapConnectInfo(ipPorts);
            try {
                outNetReceiverInstance = new DapDataReceiver(dapConnectInfo, OUT_NET_FLAG);
            } catch (Exception e) {
                logger.error("init kafka error", e);
                System.exit(-1);
            }
        }

        return outNetReceiverInstance;
    }


    public static synchronized DapDataReceiver getReceiverInstance() {
        if (receiverInstance == null) {
            try {
                receiverInstance = new DapDataReceiver(null);
            } catch (Exception e) {
                logger.error("init kafka error", e);
                System.exit(-1);
            }
        }

        return receiverInstance;
    }
}
