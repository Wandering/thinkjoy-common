package cn.thinkjoy.common.protocol;


import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.utils.RtnCodeEnum;

/**
 * Response 工具类
 * Created by qyang on 14-6-17.
 */
public class Responses {
    /**
     * 返回未知异常
     * @return
     */
    public static Response newUnknow(){
        return new Response.ResponseBuilder(RtnCodeEnum.UNKNOW).build();
    }
    
    /**
     * 
     * 成功返回
     * 
     * @return
     */
    public static Response newOK(){
        return new Response.ResponseBuilder(RtnCodeEnum.SUCCESS).build();
    }

    /**
     * 
     * 业务异常返回
     * 
     * @return
     */
    public static Response newResponse(BizException ex){
        return new Response.ResponseBuilder(ex).build();
    }

    /**
     * 返回网络异常
     * @return
     */
    public static Response newNetError(){
        return new Response.ResponseBuilder(RtnCodeEnum.NET_ERROR).build();
    }

    /**
     * 返回请求参数异常
     * @return
     */
    public static Response newParamError(){
        return new Response.ResponseBuilder(RtnCodeEnum.PARAMETER_ERROR).build();
    }

    /**
     * 返回调用次数超限异常
     * @return
     */
    public static Response newOverLimit(){
        return new Response.ResponseBuilder(RtnCodeEnum.APP_OVER_INVOCATION_LIMIT).build();
    }
}
