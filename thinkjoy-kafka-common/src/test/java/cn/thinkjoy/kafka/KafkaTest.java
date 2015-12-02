package cn.thinkjoy.kafka;

import cn.thinkjoy.dap.dataservice.MessageData;
import cn.thinkjoy.dap.dataservice.ResourceListener;
import com.alibaba.fastjson.JSON;

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
            //       DefaultKafkaProducer.getInstance(true).send("ucenter", "ucenter", "httpReq", "httpreq", "xjli111");

            Map<String, Object> map = new HashMap<>();
            map.put("product", "ucenter");
            map.put("bizSystem", "ucenter");
            map.put("tag", "httpReq");
            map.put("groupId", "1");
            map.put("isOutNet", true);
            map.put("auto.offset.reset", "smallest");
            DefaultKafkaConsumer.getInstance(true).receive(map, new ResourceListener() {
                @Override
                public void onEvent(MessageData messageData) {
                    System.out.println(JSON.toJSONString(messageData));

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
