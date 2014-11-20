package cn.thinkjoy.common.managerui.iauth.provider;

/**
 * Created by Michael on 11/11/14.
 */
public interface HttpRquestConstant {
    public static final String MAIN_PREFIX = "iauth-";
    public static final String TOKEN = "token";


    public static final String HTTP_HEADER_USER_AGENT = "user-agent";
    
//    public static final String HTTP_COMMON_TOKEN = MAIN_PREFIX+TOKEN;
    public static final String HTTP_COOKIE_ACCESS_TOKEN = MAIN_PREFIX+"access-"+TOKEN;
    public static final String HTTP_PARAMETER_EMBED_TOKEN = MAIN_PREFIX+"embed-"+TOKEN;
    public static final String HTTP_ATTRIBUTE_ACCESS_TOKEN = MAIN_PREFIX+"access-"+TOKEN;


    public static final String HTTP_ATTRIBUTE_USER_DETAIL = MAIN_PREFIX+"user-detial";
    public static final String HTTP_ATTRIBUTE_USER_KEY = MAIN_PREFIX+"user-key";



}
