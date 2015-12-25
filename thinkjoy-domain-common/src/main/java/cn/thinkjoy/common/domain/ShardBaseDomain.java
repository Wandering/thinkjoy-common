package cn.thinkjoy.common.domain;

import java.util.Map;

/**
 * Created by wdong on 15/12/22.
 */
public class ShardBaseDomain<T> extends CreateBaseDomain<T> {
    /**
     * 分库选库条件数据
     */
    private Map<String, String> shardMap;

    public Map<String, String> getShardMap() {
        return shardMap;
    }

    public void setShardMap(Map<String, String> shardMap) {
        this.shardMap = shardMap;
    }

}
