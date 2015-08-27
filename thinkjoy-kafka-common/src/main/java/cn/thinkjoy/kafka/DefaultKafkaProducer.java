package cn.thinkjoy.kafka;

import cn.thinkjoy.dap.dataservice.DapDataSender;
import cn.thinkjoy.dap.dataservice.MessageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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

    private static class DefaultKafkaProducerHolder {
        private static DefaultKafkaProducer instance = new DefaultKafkaProducer();
    }

    private DefaultKafkaProducer() {
        dapDataSender = KafkaMQSingleton.getInstance();
    }

    public static DefaultKafkaProducer getInstance() {
        return DefaultKafkaProducerHolder.instance;
    }

    /**
     * 发送RMQ消息
     *
     * @param product   产品线
     * @param bizSystem 产品线的业务系统
     * @param tag       消息tag
     * @param data      消息内容
     */
//    public void send(String product, String bizSystem, String tag, String from, String data) throws Exception {
//        send(product, bizSystem, tag, from, data);
//    }

    /**
     * @param product
     * @param bizSystem
     * @param tag
     * @param from
     * @param data
     *
     * @throws Exception
     */
    public void send(String product, String bizSystem, String tag, String from, String data) throws Exception {
        MessageData messageData = new MessageData(product+STR_APPEND+bizSystem+STR_APPEND+tag, data);
        dapDataSender.send(messageData);
    }

    public void stop() {

    }
}
