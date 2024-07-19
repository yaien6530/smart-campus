package cn.yaien.common.cache;

import reactor.core.publisher.Mono;

/**
 * Redis缓存接口
 *
 * @author yanggl
 * @since 2024/7/15/15:12
 */
public interface RedisCache {

    /**
     * 永久设置数据
     *
     * @param key   键
     * @param value 值
     * @param <T>   范型
     * @return 设置结果
     */
    <T> Mono<Boolean> set(String key, T value);

    /**
     * 设置数据并指定过期时间
     *
     * @param key     键
     * @param value   值
     * @param <T>     范型
     * @param timeout 过期时间，单位为秒
     * @return 设置结果
     */
    <T> Mono<Boolean> set(String key, T value, long timeout);

    /**
     * 获取指定类型的数据
     *
     * @param key   键
     * @param clazz 类型
     * @param <T>   范型
     * @return 获取结果
     */
    <T> Mono<T> get(String key, Class<T> clazz);

    /**
     * 获取数据
     *
     * @param key 键
     * @return 获取结果
     */
    Mono<String> get(String key);

    /**
     * 删除键
     *
     * @param keys 键
     * @return 删除结果
     */
    Mono<Long> del(String... keys);

    /**
     * 设置键的过期时间
     *
     * @param key     键
     * @param seconds 过期时间，单位为秒
     * @return 设置结果
     */
    Mono<Boolean> expireKey(String key, long seconds);

}
