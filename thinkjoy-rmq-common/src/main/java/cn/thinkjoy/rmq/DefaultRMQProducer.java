package cn.thinkjoy.rmq;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * RMQ消息发送者 默认实现
 * <p/>
 * 创建时间: 15/6/25 上午10:49<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DefaultRMQProducer{
    public static final Logger logger = LoggerFactory.getLogger(DefaultRMQProducer.class);
    /** web filter http请求的tag */
    public static final String HTTP_REQ = "httpReq";
    /** server 采集的数据 */
    public static final String HTTP_SERVER_FROM = "server";
    /** js客户端采集的数据 */
    public static final String HTTP_JS_FROM = "jsclient";
    private DefaultMQProducer producer;

    private static class DefaultRMQProducerHolder{
        private static DefaultRMQProducer instance = new DefaultRMQProducer();
    }
    private void DefaultRMQProducer(){
        producer = RocketMQSingleton.getInstance();
    }

    public static DefaultRMQProducer getInstance(){
        return DefaultRMQProducerHolder.instance;
    }

    /**
     * 发送RMQ消息
     * @param product  产品线
     * @param bizSystem 产品线的业务系统
     * @param tag  消息tag
     * @param data 消息内容
     */
    public void send(String product, String bizSystem, String tag, String from, String data) throws Exception {
        send(product, bizSystem, tag, from, data, null);
    }

    /**
     *
     * @param product
     * @param bizSystem
     * @param tag
     * @param from
     * @param data
     * @param callback 回调处理
     * @throws Exception
     */
    public void send(String product, String bizSystem, String tag, String from, String data, SendCallback callback) throws Exception {
        //topic 生成策略   product - bizSys   topic 为 product 保证 消费方不漏消息；
        com.alibaba.rocketmq.common.message.Message message = new com.alibaba.rocketmq.common.message.Message(
                product,// topic  //CloudContextFactory.getCloudContext().getProduct()
                tag,// tag
                data.getBytes()// body
        );
        List<String> keys = new ArrayList<>(2);
        keys.add(product); //CloudContextFactory.getCloudContext().getProduct()
        keys.add(bizSystem); //CloudContextFactory.getCloudContext().getApplicationName()
        keys.add(from);
        message.setKeys(keys);

        if(callback != null) {
            producer.send(message, callback);
        } else {
            producer.send(message);
        }
    }

    public void stop(){
        if(producer != null){
            logger.debug("Closing rocketMQ producer ");
            producer.shutdown();
        }
    }
}
