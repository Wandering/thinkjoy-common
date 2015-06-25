package cn.thinkjoy;

import cn.thinkjoy.rmq.DefaultRMQProducer;
import org.junit.Test;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 15/6/25 下午8:21<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class DefaultRMQProducerTest {
    @Test
    public void test(){
        try {
            DefaultRMQProducer.getInstance().send("","","","","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
