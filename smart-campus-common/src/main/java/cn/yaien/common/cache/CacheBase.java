package cn.yaien.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存父类
 *
 * @author yanggl
 * @since 2024/7/15/15:11
 */
public abstract class CacheBase<V> {

    /**
     * 缓存map
     */
    private final Map<String, V> cache = new ConcurrentHashMap<>();

    public V get(String key) {
        return cache.get(key);
    }
}
