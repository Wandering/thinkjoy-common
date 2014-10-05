package cn.thinkjoy.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14-10-3 下午7:22<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;
    private final int         maxSize;

    public LRUCache(int maxSize){
        this(maxSize, 16, 0.75f, false);
    }

    public LRUCache(int maxSize, int initialCapacity, float loadFactor, boolean accessOrder){
        super(initialCapacity, loadFactor, accessOrder);
        this.maxSize = maxSize;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > this.maxSize;
    }
}
