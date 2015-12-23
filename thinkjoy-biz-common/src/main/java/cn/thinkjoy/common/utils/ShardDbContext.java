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

    private static ThreadLocal<Map<String,Object>> context = new ThreadLocal<Map<String,Object>>();

    public static Map<String,Object> getCurrentShardDbMap(){
        return context.get();
    }

    public static void setShardDbMap(Map<String,Object> map){
        context.set(map);
    }

    /**
     * 应该显示调用
     */
    public static void removeCurrentShardDbMap() {
        context.remove();
    }

}
