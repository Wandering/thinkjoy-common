package cn.thinkjoy.kafka;

import cn.thinkjoy.ReadEnv;
import cn.thinkjoy.cloudstack.dynconfig.DynConfigClientFactory;
import cn.thinkjoy.dap.dataservice.DapDataReceiver;
import cn.thinkjoy.dap.dataservice.MessageData;
import cn.thinkjoy.dap.dataservice.ResourceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/8/26 下午11:44<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DefaultKafkaConsumer {
    public static final Logger logger = LoggerFactory.getLogger(DefaultKafkaConsumer.class);

    public static final String KAFKA_PREFIX = "RESOURCE_";
    public static final String GROUP_PREFIX = "GROUP_";
    public static final String CLIENT_PREFIX = "CLIENT_";

    public static final String STR_APPEND = "_";
    private DapDataReceiver dapDataReceiver;

    private volatile static DefaultKafkaConsumer instance = null;
    private volatile static DefaultKafkaConsumer instanceInner = null;


    private DefaultKafkaConsumer(boolean isOutNet) {
        if (isOutNet) {
            dapDataReceiver = KafkaMQSingleton.getReceiverInstanceForOutNet();
        } else {
            dapDataReceiver = KafkaMQSingleton.getReceiverInstance();
        }

    }

    public static synchronized DefaultKafkaConsumer getInstance(boolean isOutNet) {
        if (isOutNet) {
            if (instance == null) {
                synchronized (DefaultKafkaConsumer.class) {
                    if (instance == null) {
                        instance = new DefaultKafkaConsumer(isOutNet);
                    }
                }
            }
            return instance;
        } else {
            if (instanceInner == null) {
                synchronized (DefaultKafkaConsumer.class) {
                    if (instanceInner == null) {
                        instanceInner = new DefaultKafkaConsumer(isOutNet);
                    }
                }
            }
            return instanceInner;
        }
    }


    public void receive(Map<String, Object> param, ResourceListener listener) throws Exception {

        String product = null;
        String bizSystem = null;
        String groupId = null;
        String tag = null;
        Boolean isOutNet = false;
        try {
            product = param.get("product").toString();
            bizSystem = param.get("bizSystem").toString();
            groupId = param.get("groupId").toString();
            tag = param.get("tag").toString();
        } catch (Exception e) {
            logger.error("参数有误", e);
        }
        if (param.containsKey("isOutNet")) {
            isOutNet = (Boolean) param.get("isOutNet");
        }

        String resourceName = KAFKA_PREFIX + (product + STR_APPEND + bizSystem + STR_APPEND + tag + STR_APPEND + ReadEnv.readEnv()).toUpperCase();
        String clientId = CLIENT_PREFIX + (product + STR_APPEND + bizSystem + STR_APPEND + groupId).toUpperCase();
        String group = GROUP_PREFIX + (product + STR_APPEND + bizSystem + STR_APPEND + groupId).toUpperCase();

        Properties props = new Properties();
        props.put("resourceName", resourceName); //资源名称（必填）
        for (String key : param.keySet()) {
            if (!props.containsKey(key)) {
                props.put(key, param.get(key));
            }
        }

        if (isOutNet) {
            props.put("clientId", clientId); //消息接收端唯一标识，可保证接收端重启后根据上次接收位置读取数据。命名格式（client_产品名称_子产品名称_...)保证不同产品线clientId不同（必填）
            //注册监听，客户端需实现异步接收接口ResourceListener来接收数据
            dapDataReceiver.registerListener(props, listener);
            dapDataReceiver.start();

        } else {
            props.put("group.id", group); ////消息接收端唯一标识，可保证接收端重启后根据上次接收位置读取数据。命名格式（group_产品名称_子产品名称_...)保证不同产品线group.id不同（必填）
            props.put("zookeeper.connect", DynConfigClientFactory.getClient().getConfig("cmc", "cmc", "common", "kafkaConsumer"));
            dapDataReceiver.registerListener(props,listener);
            dapDataReceiver.start();
        }

    }

    public void stop() {

    }
}
