package cn.thinkjoy.kafka;

import cn.thinkjoy.dap.dataservice.MessageData;
import cn.thinkjoy.dap.dataservice.ResourceListener;

import java.util.HashMap;
import java.util.Map;

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
            Map<String, Object> map = new HashMap<>();
            map.put("product", "ucenter");
            map.put("bizSystem", "ucenter");
            map.put("tag", "httpReq");
            map.put("groupId", "1");
            map.put("isOutNet", false);
            map.put("auto.commit.intervals.ms", "1000");
            map.put("auto.offset.reset", "smallest");
            map.put("pollInterval", "5"); //消息推送间隔时间，默认5秒（可选）
            DefaultKafkaConsumer.getInstance(false).receive(map, new ResourceListener() {
                @Override
                public void onEvent(MessageData messageData) {
                    System.out.println(messageData.getData());

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
