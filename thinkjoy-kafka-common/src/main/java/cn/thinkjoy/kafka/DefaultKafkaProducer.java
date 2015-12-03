package cn.thinkjoy.kafka;

import cn.thinkjoy.dap.dataservice.DapDataSender;
import cn.thinkjoy.dap.dataservice.MessageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/8/26 下午11:44<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DefaultKafkaProducer {
    public static final Logger logger = LoggerFactory.getLogger(DefaultKafkaProducer.class);
    /**
     * web filter http请求的tag
     */
    public static final String HTTP_REQ = "httpReq";
    public static final String KAFKA_PREFIX = "RESOURCE_";
    /**
     * server 采集的数据
     */
    public static final String HTTP_SERVER_FROM = "server";
    public static final String STR_APPEND = "_";
    /**
     * js客户端采集的数据
     */
    public static final String HTTP_JS_FROM = "jsclient";
    private DapDataSender dapDataSender;


    private volatile static DefaultKafkaProducer instance = null;
    private volatile static DefaultKafkaProducer instance1 = null;


    private DefaultKafkaProducer() {
        dapDataSender = KafkaMQSingleton.getInstance();
    }


    public static DefaultKafkaProducer getInstance() {
        if (instance1 == null) {
            instance1 = new DefaultKafkaProducer(false);
        }
        return instance1;
    }

    private DefaultKafkaProducer(boolean isOutNet) {
        if (isOutNet) {
            dapDataSender = KafkaMQSingleton.getInstanceForOutNet();
        } else {
            dapDataSender = KafkaMQSingleton.getInstance();
        }
    }

    public static synchronized DefaultKafkaProducer getInstance(boolean isOutNet) {
        if (isOutNet) {
            if (instance == null) {
                synchronized (DefaultKafkaProducer.class) {
                    if (instance == null) {
                        instance = new DefaultKafkaProducer(isOutNet);
                    }
                }
            }
            return instance;
        } else {
            if (instance1 == null) {
                synchronized (DefaultKafkaProducer.class) {
                    if (instance1 == null) {
                        instance1 = new DefaultKafkaProducer(isOutNet);
                    }
                }
            }
            return instance1;
        }
    }

    /**
     * 内网调用方式
     *
     * @param product
     * @param bizSystem
     * @param tag
     * @param from
     * @param data
     * @throws Exception
     */
    public void send(String product, String bizSystem, String tag, String from, String data) throws Exception {
        MessageData messageData = new MessageData(KAFKA_PREFIX + (product + STR_APPEND + bizSystem + STR_APPEND + tag).toUpperCase(), data);
        dapDataSender.send(messageData);
    }


    public void stop() {

    }
}
