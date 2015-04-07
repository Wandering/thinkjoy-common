package cn.thinkjoy.android.protocol.common;

/**
 * 远端服务器地址注入接口
 * <p/>
 * 创建时间: 14/11/27 上午9:27<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public interface IEndpointAware {
    public String getEndpoint();
}
