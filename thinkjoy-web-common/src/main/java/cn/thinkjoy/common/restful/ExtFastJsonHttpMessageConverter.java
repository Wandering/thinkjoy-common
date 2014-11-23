package cn.thinkjoy.common.restful;

import cn.thinkjoy.common.protocol.Response;
import cn.thinkjoy.common.utils.RtnCodeEnum;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * 扩展的fastjson convert  针对习悦特有协议扩展
 * <p/>
 * 创建时间: 14/11/23 下午6:38<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class ExtFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {
    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return super.readInternal(clazz, inputMessage);
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Response response = new Response.ResponseBuilder(RtnCodeEnum.SUCCESS).bizData(obj).build();
        super.writeInternal(response, outputMessage);
    }
}
