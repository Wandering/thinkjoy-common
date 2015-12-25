package cn.thinkjoy.common.utils;

/**
 *
 */

import cn.thinkjoy.common.domain.ShardBaseDomain;
import cn.thinkjoy.common.domain.UserDomain;

import java.util.Map;

/**
 * 分库条件上下文
 * Created by wdong on 15/12/22.
 *
 */
public class ShardDbContext {

    private static ThreadLocal<Map<String,String>> context = new ThreadLocal<Map<String,String>>();

    public static Map<String,String> getCurrentShardDbMap(){
        return context.get();
    }

    public static void setShardDbMap(Map<String,String> map){
        context.set(map);
    }

    /**
     * 应该显示调用
     */
    public static void removeCurrentShardDbMap() {
        context.remove();
    }

}
