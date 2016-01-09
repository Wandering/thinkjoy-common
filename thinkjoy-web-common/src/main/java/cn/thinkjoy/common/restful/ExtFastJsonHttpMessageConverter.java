package cn.thinkjoy.common.restful;

import cn.thinkjoy.common.protocol.RequestT;
import cn.thinkjoy.common.protocol.ResponseT;
import cn.thinkjoy.common.serializer.filter.ExtPropertyFilter;
import cn.thinkjoy.common.utils.RtnCodeEnum;
import cn.thinkjoy.common.utils.StyleEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * 扩展的fastjson convert  针对习悦特有协议扩展
 * <p/>
 * 创建时间: 14/11/23 下午6:38<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class ExtFastJsonHttpMessageConverter<T> extends AbstractHttpMessageConverter<T> {
    public final static Charset UTF8     = Charset.forName("UTF-8");

    private Charset             charset  = UTF8;

    private SerializerFeature[] features = new SerializerFeature[0];

    public ExtFastJsonHttpMessageConverter(){
        super(new MediaType("application", "json", UTF8), new MediaType("application", "*+json", UTF8));
        features = new SerializerFeature[1];
        features[0] = SerializerFeature.DisableCircularReferenceDetect;
    }

    private ITypeReference iTypeReference;
    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature... features) {
        this.features = features;
    }

    public ITypeReference getiTypeReference() {
        return iTypeReference;
    }

    public void setiTypeReference(ITypeReference iTypeReference) {
        this.iTypeReference = iTypeReference;
    }

    private ThreadLocal<StyleEnum> styleEnumThreadLocal = new ThreadLocal<>();

    @Override
    protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        InputStream in = inputMessage.getBody();

        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }

        byte[] bytes = baos.toByteArray();
        //String url = ((ServletServerHttpRequest) inputMessage).getURI().getPath();
        String url = ((ServletServerHttpRequest)((ServletServerHttpRequest) inputMessage)).getServletRequest().getServletPath();
        //支持 url包含？参数
        int size = url.indexOf("?");
        if(size != -1){
            url = url.substring(0, size);
        }

        T t = JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), iTypeReference.getTypeReference(url).getType());

        //RequestT中data处理
        if(t instanceof RequestT){
            RequestT requestT = (RequestT)t;
            StyleEnum style = requestT.getStyle();
            styleEnumThreadLocal.set(style);
        }
        return t;
    }

    @Override
    protected void writeInternal(T t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ResponseT<T> response = new ResponseT<T>(RtnCodeEnum.SUCCESS);
        ////RequestT中data处理
        StyleEnum style = styleEnumThreadLocal.get();
        if(style != null){
            response.setStyle(style);
        }

        response.setBizData(t);

        OutputStream out = outputMessage.getBody();
        String text = JSON.toJSONString(response, getSerializeFilter(), features);
        byte[] bytes = text.getBytes(charset);
        out.write(bytes);
    }

    //获取序列化过滤器
    private SerializeFilter[] getSerializeFilter(){
        return new SerializeFilter[]{new ExtPropertyFilter()};
    }
}
