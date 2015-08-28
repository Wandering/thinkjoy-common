package cn.thinkjoy.kafka;

import cn.thinkjoy.dap.dataservice.DapConnectInfo;
import cn.thinkjoy.dap.dataservice.DapDataSender;
import cn.thinkjoy.dap.dataservice.MessageData;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/8/28 下午9:23<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class KafkaTest {
    public static void main(String[] args) {
        try {
            DefaultKafkaProducer.getInstance().send("weixiao", "weixiao", "httpReq", "httpreq", "data");

//            //初始化服务连接对象
//            String[] ipAndPorts = new String[]{
//                    "123.59.110.72:9092", "123.59.110.72:9092"
//            };
//            DapConnectInfo dapConnectInfo = new DapConnectInfo(ipAndPorts);
//
////构造数据发送对象
//            DapDataSender sender = new DapDataSender(dapConnectInfo);
////资源名称，由大数据分析平台统一提供
//            String resourceName = "RESOURCE_YZC_XSCJ";
////组装发送消息
//            List<MessageData> messageDataList = new ArrayList<MessageData>();
//            for (int i = 0; i < 1000; i++) {
////组装一条消息
//                MessageData messageData = new MessageData(resourceName, "msg" + i);
//                messageDataList.add(messageData);
//            }
////执行发送
//            sender.send(messageDataList);
////关闭连接
//            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
