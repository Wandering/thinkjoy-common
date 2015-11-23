package cn.thinkjoy.common.filter;

import cn.thinkjoy.common.filter.common.Util;
import cn.thinkjoy.common.context.UserContextHolder;
import cn.thinkjoy.common.filter.mapper.AnalyticsDataMapper;
import cn.thinkjoy.common.dap.model.webfilter.Entry;
import cn.thinkjoy.common.filter.pool.Messenger;
import cn.thinkjoy.common.filter.pool.ObjectPool;
import cn.thinkjoy.common.filter.pool.SendAnalyticsTask;
import cn.thinkjoy.common.filter.pool.Work;
import cn.thinkjoy.common.filter.wrapper.RequestInterceptorWrapper;
import cn.thinkjoy.common.filter.wrapper.ResponseInterceptorWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.thinkjoy.common.filter.AnalyticsConstants.*;

/**
 * http 请求 数据记录拦截器   支持 习悦技术体系（基于zk的云化配置） 和 非习悦体系两类
 *  每个产品线都有自己的本地元数据描述文件；
 *  每个产品线的日志数据都天然隔离；
 *
 *  此filter需要放在身份验证的filter之后
 *
 * <p/>
 * 创建时间: 15/6/15 下午5:09<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class AnalyticsFilter implements Filter {
    final static Logger logger = LoggerFactory.getLogger(AnalyticsFilter.class);

    private ExecutorService analyticsServicexeExecutor;
//    private String analyticsServerUrl;
//    private String analyticsServerPort;
//    private String analyticsToken;
    private ObjectPool<Work> pool;
    private boolean isAnlayticsEnabled = true;

    @Override
    public void destroy() {
        analyticsServicexeExecutor.shutdown();
        pool.terminate();
    }

    /**
     * Wraps the request and response for future read , chain the request and
     * finally send the compiled data in HAR format to Analytics server
     *
     * @see RequestInterceptorWrapper
     * @see ResponseInterceptorWrapper
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        String contentType = req.getContentType();
        if(StringUtils.isEmpty(contentType) && !contentType.startsWith("multipart"))
        {
            chain.doFilter(req, res);
        }
        else if (isAnlayticsEnabled) {
            long sendStartTime = System.currentTimeMillis();
            Date requestReceivedTime = new Date();
            RequestInterceptorWrapper request = new RequestInterceptorWrapper(
                    (HttpServletRequest) req);
            ResponseInterceptorWrapper response = new ResponseInterceptorWrapper(
                    (HttpServletResponse) res);
            long waitStartTime = System.currentTimeMillis();
            chain.doFilter(request, response);
            long waitEndTime = System.currentTimeMillis();
            callAsyncAnalytics(requestReceivedTime, request, response,
                    waitStartTime - sendStartTime, waitEndTime - waitStartTime);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * A pool of of thread handles the data transfer to Analytics server
     *
     * @param requestReceivedTime
     *            Date/Time when request received
     * @param request
     *            Http request intercepted by the filter
     * @param response
     *            Http response intercepted by the filter
     * @param sendTime
     *            Time taken by filter to send the intercepted request to next
     *            sevlet/filter in chain
     * @param waitTime
     *            Wait time before receiving the response
     *
     * @see AnalyticsDataMapper
     * @see SendAnalyticsTask
     */
    private void callAsyncAnalytics(Date requestReceivedTime,
                                    RequestInterceptorWrapper request,
                                    ResponseInterceptorWrapper response, long sendTime, long waitTime) {
        try {
            long recvStartTime = System.currentTimeMillis();
            Map<String, Object> messageProperties = new HashMap<String, Object>();
//            messageProperties.put(ANALYTICS_SERVER_URL, analyticsServerUrl);
//            messageProperties.put(ANALYTICS_SERVER_PORT, analyticsServerPort);
            Entry analyticsData = new AnalyticsDataMapper(request, response)
                    .getAnalyticsData(requestReceivedTime, sendTime, waitTime);
            long recvEndTime = System.currentTimeMillis();
            analyticsData.getTimings().setReceive(recvEndTime - recvStartTime);
            analyticsData.setTime((recvEndTime - recvStartTime) + sendTime
                    + waitTime);
            messageProperties.put(ANALYTICS_DATA, analyticsData);
            if(UserContextHolder.getUserContext() != null) { //部分页面不进行 用户身份验证
                messageProperties.put(USER_CONTEXT, UserContextHolder.getUserContext().getContexts());
            }
//            messageProperties.put(ANALYTICS_TOKEN, analyticsToken);
            analyticsServicexeExecutor.execute(new SendAnalyticsTask(pool,
                    messageProperties));
        } catch (Throwable x) {
            logger.error("Failed to send analytics data", x);
        }
    }

    /**
     * Thread pools and socket pools are created
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        if (isAnlayticsEnabled) {
//            if (!(Util.notBlank(analyticsServerUrl = config
//                    .getInitParameter(ANALYTICS_SERVER_URL))
//                    && Util.notBlank(analyticsServerPort = config
//                    .getInitParameter(ANALYTICS_SERVER_PORT)) && Util
//                    .notBlank(analyticsToken = System
//                            .getProperty(ANALYTICS_TOKEN)))) {
//                isAnlayticsEnabled = false;
//                logger.error("Analytics URl or Port or Token not set");
//                return;
//            }
            int poolSize = getEnvVarOrDefault(WORKER_COUNT, Runtime
                    .getRuntime().availableProcessors() * 2);
            int socketPoolMin = getEnvVarOrDefault(SOCKET_POOL_SIZE_MIN, 10);
            int socketPoolMax = getEnvVarOrDefault(SOCKET_POOL_SIZE_MAX, 20);
            int poolUpdateInterval = getEnvVarOrDefault(
                    SOCKET_POOL_UPDATE_INTERVAL, 5);
            pool = new ObjectPool<Work>(socketPoolMin, socketPoolMax,
                    poolUpdateInterval) {
                @Override
                public Work createPoolObject() {
                    return new Messenger();
                }
            };
            analyticsServicexeExecutor = Executors.newFixedThreadPool(poolSize);
        }
    }

    private int getEnvVarOrDefault(String name, int defaultVal) {
        String val = System.getProperty(name);
        if (Util.notBlank(val)) {
            return Integer.parseInt(val);
        }
        return defaultVal;
    }

    private boolean isAnalyticsFlagEnabled() {
        return (Boolean.parseBoolean(System.getProperty(ANALYTICS_ENABLED)));
    }
}
