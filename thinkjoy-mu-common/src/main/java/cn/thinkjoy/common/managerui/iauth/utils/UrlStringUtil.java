package cn.thinkjoy.common.managerui.iauth.utils;

import java.util.Map;

/**
 * Created by Michael on 11/13/14.
 */
public class UrlStringUtil {
    public static String paramsMapToURLString(Map<String,String>  params) {
        StringBuilder ex = new StringBuilder("?");
        for (Map.Entry<String,String> entry : params.entrySet()) {
            ex.append(entry.getKey()+"="+entry.getValue());
        }
        return ex.toString();
    }
}
