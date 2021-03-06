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

import java.util.Map;

/*
 * @author shashi
 * 
 * Task use a pooled object to send data
 */
public class SendAnalyticsTask implements Runnable {

	private ObjectPool<Work> pool;
	private Map<String, Object> analyticsData;

	public SendAnalyticsTask(ObjectPool<Work> pool, Map<String, Object> analyticsData) {
		this.pool = pool;
		this.analyticsData = analyticsData;
	}

	public void run() {
		Work work = pool.borrowObject();
		work.execute(analyticsData);
		pool.returnObject(work);
	}
}
