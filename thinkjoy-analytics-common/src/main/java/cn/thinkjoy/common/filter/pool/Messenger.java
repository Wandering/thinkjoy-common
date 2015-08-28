/*
The MIT License
Copyright (c) 2013 Mashape (http://mashape.com)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package cn.thinkjoy.common.filter.pool;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import cn.thinkjoy.common.dap.model.webfilter.*;
import cn.thinkjoy.kafka.DefaultKafkaProducer;
import cn.thinkjoy.rmq.DefaultRMQProducer;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static cn.thinkjoy.common.filter.AnalyticsConstants.*;

/*
 * Opens a connection to Analytics server and sends data
 */
//@Component
public class Messenger implements Work {
	public static final Logger logger = LoggerFactory.getLogger(Messenger.class);

	public void execute(Map<String, Object> analyticsData) {
		Entry entry = (Entry) analyticsData.get(ANALYTICS_DATA);
		//String token = analyticsData.get(ANALYTICS_TOKEN).toString();
		Message msg = getMessage(entry, null, (Map) analyticsData.get(USER_CONTEXT));
		String data = JSON.toJSONString(msg);
//		String analyticsServerUrl = analyticsData.get(ANALYTICS_SERVER_URL).toString();
//		String port = analyticsData.get(ANALYTICS_SERVER_PORT).toString();

        try{
//            DefaultRMQProducer.getInstance().send(CloudContextFactory.getCloudContext().getProduct(), CloudContextFactory.getCloudContext().getApplicationName(),
//                    DefaultRMQProducer.HTTP_REQ, DefaultRMQProducer.HTTP_SERVER_FROM, data);


            DefaultKafkaProducer.getInstance().send(CloudContextFactory.getCloudContext().getProductCode(), CloudContextFactory.getCloudContext().getApplicationName(),
                    DefaultRMQProducer.HTTP_REQ, DefaultRMQProducer.HTTP_SERVER_FROM, data);
        } catch (Exception e) {
            logger.error("rocketMQ producer send data error" + e);;
        }
	}

	public void terminate() {
        DefaultRMQProducer.getInstance().stop();
	}

	public Message getMessage(Entry entry, String token, Map userContext) {
		Message message = new Message();
		message.setHar(setHar(entry));
		message.setServiceToken(token);
        message.setUserContext(userContext);
		return message;
	}

	private Har setHar(Entry entry) {
		Har har = new Har();
		har.setLog(setLog(entry));
		return har;
	}

	private Log setLog(Entry entry) {
		Log log = new Log();
		log.setVersion(HAR_VERSION);
		log.setCreator(setCreator());
		log.getEntries().add(entry);
		return log;
	}

	private Creator setCreator() {
		Creator creator = new Creator();
		creator.setName(AGENT_NAME);
		creator.setVersion(AGENT_VERSION);
		return creator;
	}
}
