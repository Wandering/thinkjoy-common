package cn.thinkjoy.kafka;

import cn.thinkjoy.dap.dataservice.DapConnectInfo;
import cn.thinkjoy.dap.dataservice.DapDataReceiver;
import cn.thinkjoy.dap.dataservice.MessageData;
import cn.thinkjoy.dap.dataservice.ResourceListener;

import java.util.Date;
import java.util.Properties;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/8/28 下午9:23<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class KafkaTest {
    public static void main(String[] args) throws Exception {
//
//        try {
//            DefaultKafkaProducer.getInstance(true).send("ucenter", "ucenter", "httpReq", "httpreq", "xjli111");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

          System.out.println(new Date(1445311155278l));


//        try {
//            Map<String, Object> map = new HashMap<>();
//            map.put("product", "ucenter");
//            map.put("bizSystem", "ucenter");
//            map.put("tag", "httpReq");
//            map.put("groupId", "1");
//            map.put("isOutNet", false);
//            map.put("auto.commit.intervals.ms", "1000");
//            map.put("auto.offset.reset", "smallest");
//            map.put("pollInterval", "5"); //消息推送间隔时间，默认5秒（可选）
//            DefaultKafkaConsumer.getInstance(false).receive(map, new ResourceListener() {
//                @Override
//                public void onEvent(MessageData messageData) {
//                    System.out.println(messageData.getData());
//
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //初始化服务连接对象
//        String[] ipAndPorts = new String[]{
//                "dapda-pro.qtonecloud.cn:8082"
//        };
//        DapConnectInfo dapConnectInfo = new DapConnectInfo(ipAndPorts);
//
////构造数据接收对象
//        DapDataReceiver receiver = new DapDataReceiver(dapConnectInfo, "rest");
//
//        Properties property = new Properties();
//        property.put("resourceName", "resource_ucenter_ucenter_httpReq".toUpperCase()); //资源名称（必填）
//        property.put("clientId", "client_yzt_ctb_v001"); //消息接收端唯一标识，可保证接收端重启后根据上次接收位置读取数据。命名格式（client_产品名称_子产品名称_...)保证不同产品线clientId不同（必填）
//        property.put("pollInterval", "5"); //消息推送间隔时间，默认5秒（可选）
//
////注册监听，客户端需实现异步接收接口ResourceListener来接收数据
//        receiver.registerListener(property, new ResourceListener() {
//            @Override
//            public void onEvent(MessageData messageData) {
//                System.out.println("data:" + messageData.getData());
//
//            }
//        });
//
//        receiver.start();


    }
}
