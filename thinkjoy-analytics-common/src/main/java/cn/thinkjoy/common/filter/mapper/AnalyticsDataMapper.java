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
package cn.thinkjoy.common.filter.mapper;

import cn.thinkjoy.common.dap.model.webfilter.*;
import cn.thinkjoy.common.filter.wrapper.RequestInterceptorWrapper;
import cn.thinkjoy.common.filter.wrapper.ResponseInterceptorWrapper;
import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AnalyticsDataMapper {

	Logger logger = LoggerFactory.getLogger(AnalyticsDataMapper.class);

	private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

	private RequestInterceptorWrapper request;
	private ResponseInterceptorWrapper response;
	Message message;

	public AnalyticsDataMapper(RequestInterceptorWrapper request,
			ResponseInterceptorWrapper response) {
		this.request = (RequestInterceptorWrapper) request;
		this.response = (ResponseInterceptorWrapper) response;
	}

	public Entry getAnalyticsData(Date requestReceivedTime, long sendTime,
			long waitTime) {
		Entry entry = new Entry();
		entry.setClientIPAddress(request.getRemoteAddr());
		entry.setServerIPAddress(request.getLocalAddr());
		entry.setStartedDateTime(dateAsIso(requestReceivedTime));
		entry.setRequest(mapRequest());
		entry.setResponse(mapResponse());
		entry.setTimings(mapTimings(requestReceivedTime, sendTime, waitTime));
		return entry;
	}

	private void setRequestHeaders(Request requestHar) {
		Enumeration<String> headers = request.getHeaderNames();
		List<NameValuePair> headerList = requestHar.getHeaders();
		int size = 2; // 2 for CRLF
		while (headers.hasMoreElements()) {
			String name = headers.nextElement();
			size += name.getBytes().length;
			Enumeration<String> values = request.getHeaders(name);
			while (values.hasMoreElements()) {
				NameValuePair pair = new NameValuePair();
				pair.setName(name);
				String headerValue = values.nextElement();
				size += headerValue.getBytes().length;
				pair.setValue(headerValue);
				headerList.add(pair);
				size += 6; // 2 for ": " + 2 for ", " if multiple values present
							// and 2 for CRLF for each header
			}
		}
		requestHar.setHeadersSize(size);
	}

	private void setResponseHeaders(Response responseHar) {
		Collection<String> headers = response.getHeaderNames();
        if(headers == null){
            return;
        }
		List<NameValuePair> headerList = responseHar.getHeaders();
		int size = 2; // 2 for CRLF
		for (String name : headers) {
			Collection<String> values = response.getHeaders(name);
			for (String value : values) {
				NameValuePair pair = new NameValuePair();
				size += name.getBytes().length;
				pair.setName(name);
				size += value.getBytes().length;
				pair.setValue(value);
				headerList.add(pair);
				size += 6;// 2 for ": " + 2 for ", " if multiple values present
							// and 2 for CRLF for each header
			}
		}
		responseHar.setHeadersSize(size);
	}

	private Request mapRequest() {
		Request requestHar = new Request();
		requestHar.setBodySize(request.getContentLength());
		requestHar.setContent(mapRequestContent());
		requestHar.setMethod(request.getMethod());
		requestHar.setUrl(request.getRequestURL().toString());
		requestHar.setHttpVersion(request.getProtocol());
		setRequestHeaders(requestHar);
		setQueryString(requestHar);
		return requestHar;
	}

	private void setQueryString(Request requestHar) {
		Enumeration<String> params = request.getParameterNames();
		List<NameValuePair> paramList = requestHar.getQueryString();

		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String[] values = request.getParameterValues(name);
			for (String value : values) {
				NameValuePair pair = new NameValuePair();
				pair.setName(name);
				pair.setValue(value);
				paramList.add(pair);
			}
		}
	}

	private Content mapRequestContent() {
		Content content = new Content();
		content.setEncoding(request.getCharacterEncoding());
		String mimeType = request.getContentType();
		content.setMimeType(DEFAULT_MIME_TYPE);
		if (mimeType != null && mimeType.length() > 0) {
			content.setMimeType(request.getContentType());
		}
		content.setSize(request.getPayload().length());
		if (request.getPayload().length() > 0) {
			try {
				content.setText(BaseEncoding
						.base64()
						.encode(request
								.getPayload()
								.getBytes(
										request.getCharacterEncoding() == null ? "UTF-8"
												: request
														.getCharacterEncoding())));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("Failed to encode request body");
			}
		}
		return content;
	}

	private Response mapResponse() {
		Response responseHar = new Response();
		responseHar.setBodySize(response.getClone().length);
		responseHar.setContent(mapResponseContent());
		responseHar.setHttpVersion(request.getProtocol());
		responseHar.setStatus(Integer.toString(response.getStatus()));
		responseHar.setStatusText(responseHar.getStatus());
		setResponseHeaders(responseHar);

		return responseHar;
	}

	private Content mapResponseContent() {
		Content content = new Content();
		content.setEncoding(response.getCharacterEncoding());
		String mimeType = response.getContentType();
		content.setMimeType(DEFAULT_MIME_TYPE);
		if (mimeType != null && mimeType.length() > 0) {
			content.setMimeType(mimeType);
		}
		content.setSize(response.getClone().length);
        //modify qyang 暂时屏蔽response body
//		if (response.getClone().length > 0) {
//			content.setText(BaseEncoding.base64().encode(response.getClone()));
//		}
		return content;
	}

	private Timings mapTimings(Date requestReceivedTime, long sendTime,
			long waitTime) {
		Timings timings = new Timings();
		timings.setReceive(0);
		timings.setSend(sendTime);
		timings.setWait(waitTime);
		return timings;
	}

	private String dateAsIso(Date date) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.s'Z'");
		df.setTimeZone(tz);
		return df.format(new Date());
	}
}
