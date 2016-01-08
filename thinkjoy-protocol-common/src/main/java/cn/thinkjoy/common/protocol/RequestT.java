package cn.thinkjoy.common.protocol;

import cn.thinkjoy.common.utils.AES256Utils;
import cn.thinkjoy.common.utils.ByteUtils;
import cn.thinkjoy.common.utils.StringGZIPUtils;
import cn.thinkjoy.common.utils.StyleEnum;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zxxiao on 14/11/6.
 */
public class RequestT<T> implements Serializable {
    private static final long serialVersionUID = -5125919776534597878L;

    /*
    data的处理方式
     */
    private StyleEnum style;

    /*
    请求数据，明文
     */
    private T data;

    /*
    请求数据，style处理后的
     */
    private String styledData;

    /*
    请求头
     */
    private Map<String, Object> clientInfo;

    public RequestT() {
        style = StyleEnum.PLAIN;
    }

    public RequestT(StyleEnum style) {
        this.style = style;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }

    public T getData() {
        if(StyleEnum.PLAIN.equals(style)){
            return data;
        }else {
            //unwrapper data with styled data
            String jsonData;
            if(StyleEnum.GZIP.equals(style)){
                jsonData = StringGZIPUtils.uncompressToString(ByteUtils.HexString2Bytes(styledData));
                if(jsonData != null){
                    return (T) JSON.parse(jsonData);
                }
            }else if(StyleEnum.AES.equals(style)){
                try {
                    jsonData = AES256Utils.decrypt2str(ByteUtils.HexString2Bytes(styledData));
                    if(jsonData != null){
                        return (T) JSON.parse(jsonData);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void setData(T data) {
        if(data == null || StyleEnum.PLAIN.equals(style)){
            this.data = data;
        }else {
            this.data = null;
            //wrapper data with style
            String jsonData = JSON.toJSONString(data);
            if(StyleEnum.GZIP.equals(style)){
                styledData = ByteUtils.Bytes2HexString(StringGZIPUtils.compressToByte(jsonData));
                this.setStyledData(styledData);
            }else if(StyleEnum.AES.equals(style)){
                try {
                    styledData = ByteUtils.Bytes2HexString(AES256Utils.encrypt(jsonData));
                    this.setStyledData(styledData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public String getStyledData() {
        return styledData;
    }

    public void setStyledData(String styledData) {
        this.styledData = styledData;
    }

    public Map<String, Object> getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(Map<String, Object> clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Override
    public String toString() {
        return "RequestT{" +
                "style='" + style + '\'' +
                ", data=" + data +
                ", clientInfo=" + clientInfo +
                '}';
    }
}
