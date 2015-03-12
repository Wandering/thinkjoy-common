/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: samples
 * $$Id: ExtGsonConverter.java 14-11-25 上午9:22 $$
 */

package cn.thinkjoy.android.protocol.common;

import com.alibaba.fastjson.JSON;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

import java.io.*;
import java.lang.reflect.Type;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14/11/25 上午9:22<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class FastJSONConverter implements Converter{
    public static class FastJSONConverterBuild{
        private static final FastJSONConverter instance = new FastJSONConverter();
    }

    public static FastJSONConverter getInstance(){
        return FastJSONConverterBuild.instance;
    }

    private String charset;

    /**
     * Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public FastJSONConverter() {
        this("UTF-8");
    }

    /**
     * Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use the specified charset.
     */
    public FastJSONConverter(String charset) {
        this.charset = charset;
    }

    @Override public Object fromBody(TypedInput body, Type type) throws ConversionException {
        String charset = this.charset;
        if (body.mimeType() != null) {
            charset = MimeUtil.parseCharset(body.mimeType(), charset);
        }
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(body.in(), charset);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream in = body.in();
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


            return JSON.parseObject(bytes, type); //gson.fromJson(isr, type1);
        } catch (IOException e) {
            throw new ConversionException(e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    @Override public TypedOutput toBody(Object object) {
        try {
            return new JsonTypedOutput(JSON.toJSONString(object).getBytes(charset), charset);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    private static class JsonTypedOutput implements TypedOutput {
        private final byte[] jsonBytes;
        private final String mimeType;

        JsonTypedOutput(byte[] jsonBytes, String encode) {
            this.jsonBytes = jsonBytes;
            this.mimeType = "application/json; charset=" + encode;
        }

        @Override public String fileName() {
            return null;
        }

        @Override public String mimeType() {
            return mimeType;
        }

        @Override public long length() {
            return jsonBytes.length;
        }

        @Override public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }
    }
}
