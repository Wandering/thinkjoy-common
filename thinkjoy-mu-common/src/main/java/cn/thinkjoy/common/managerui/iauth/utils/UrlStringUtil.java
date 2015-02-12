package cn.thinkjoy.common.managerui.iauth.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 11/13/14.
 */
public class UrlStringUtil {
    public static String paramsMapToURLString(Map<String,String>  params) throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()) {
            return "";
        }

        StringBuilder ex = new StringBuilder("?");
        for (Map.Entry<String,String> entry : params.entrySet()) {
            ex.append(entry.getKey()+"="+ URLEncoder.encode(entry.getValue(), "utf-8"));
            ex.append("&");
        }
        ex.delete(ex.length() -1,ex.length());
        return ex.toString();
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        Map map = new HashMap();
        map.put("username","gbdai");
        map.put("passwd","passwd");
        map.put("error","中文支持");

        System.out.println(UrlStringUtil.paramsMapToURLString(map));
    }
}
