package cn.thinkjoy.common.utils;


/**
 * TODO 一句话描述该类用途
 * <p/>
 * 创建时间: 14-9-4 下午12:54<br/>
 *
 * @author qyang
 * @since v0.0.1
 */
@Deprecated
public class SessionCache<K, V> extends LRUCache<K, V> {
    public SessionCache(int maxSize) {
        super(maxSize);
    }

    public SessionCache(int maxSize, int initialCapacity, float loadFactor, boolean accessOrder) {
        super(maxSize, initialCapacity, loadFactor, accessOrder);
    }
}
