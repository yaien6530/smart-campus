package cn.yaien.domain.infrastructure.cache;

import cn.yaien.common.cache.RedisCache;
import cn.yaien.common.utils.JsonUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Redis缓存实现类
 *
 * @author yanggl
 * @since 2024/7/15/23:20
 */
@Component
public class RedisCacheImpl implements RedisCache {

    @Resource
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    /**
     * 将对象序列化为JSON字符串并存储到Redis
     *
     * @param key   Redis键
     * @param value 要存储的对象
     * @param <T>   对象的类型
     * @return 包含操作结果的Mono
     */
    @Override
    public <T> Mono<Boolean> set(String key, T value) {
        return JsonUtil.toJson(value)
                .flatMap(json -> reactiveRedisTemplate.opsForValue().set(key, json));
    }

    /**
     * 将对象序列化为JSON字符串并存储到Redis，带有过期时间
     *
     * @param key     Redis键
     * @param value   要存储的对象
     * @param timeout 过期时间（秒）
     * @param <T>     对象的类型
     * @return 包含操作结果的Mono
     */
    @Override
    public <T> Mono<Boolean> set(String key, T value, long timeout) {
        return JsonUtil.toJson(value)
                .flatMap(json -> reactiveRedisTemplate.opsForValue().set(key, json, Duration.ofSeconds(timeout)));
    }

    /**
     * 从Redis中获取指定键的值，并将其反序列化为指定的Java对象
     *
     * @param key   Redis键
     * @param clazz Java对象的Class类型
     * @param <T>   Java对象的类型
     * @return 包含Java对象的Mono
     */
    @Override
    public <T> Mono<T> get(String key, Class<T> clazz) {
        return reactiveRedisTemplate.opsForValue().get(key)
                .switchIfEmpty(Mono.empty())
                .flatMap(value -> JsonUtil.toObj(value, clazz));
    }

    /**
     * 从Redis中获取指定键的值（JSON字符串）
     *
     * @param key Redis键
     * @return 包含JSON字符串的Mono
     */
    @Override
    public Mono<String> get(String key) {
        return reactiveRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定键的值
     *
     * @param keys Redis键
     * @return 包含操作结果的Mono
     */
    @Override
    public Mono<Long> del(String... keys) {
        return reactiveRedisTemplate.delete(keys);
    }

    /**
     * 设置指定键的过期时间
     *
     * @param key     Redis键
     * @param timeout 过期时间（秒）
     * @return 包含操作结果的Mono
     */
    @Override
    public Mono<Boolean> expireKey(String key, long timeout) {
        return reactiveRedisTemplate.expire(key, Duration.ofSeconds(timeout));
    }
}
