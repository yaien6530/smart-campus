package cn.yaien.common.filter;

import cn.yaien.common.cache.RedisCache;
import cn.yaien.common.constant.Const;
import cn.yaien.common.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

/**
 * JWT认证过滤器
 *
 * @author yanggl
 * @since 2024/7/17/22:05
 */
@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        return extractToken(exchange)
                .flatMap(token -> extractSecretKey(exchange)
                        .flatMap(secretKey -> JwtUtil.validateToken(token, secretKey)
                                .flatMap(valid -> {
                                    if (valid) {
                                        return redisCache.get(token)
                                                .flatMap(username -> {
                                                    if (username != null) {
                                                        UsernamePasswordAuthenticationToken authentication =
                                                                new UsernamePasswordAuthenticationToken(username, null, null);
                                                        SecurityContextHolder.getContext().setAuthentication(authentication);
                                                    }
                                                    return chain.filter(exchange);
                                                });
                                    } else {
                                        return chain.filter(exchange);
                                    }
                                })));
    }

    /**
     * 提取Token
     *
     * @param exchange ServerWebExchange
     * @return 提取的Token
     */
    private Mono<String> extractToken(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(Const.AUTHORIZATION))
                .filter(authorization -> authorization.startsWith(Const.BEARER + " "))
                .map(authorization -> authorization.substring(7));
    }


    /**
     * 提取密钥
     *
     * @param exchange ServerWebExchange
     * @return 提取的密钥
     */
    private Mono<String> extractSecretKey(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(Const.SECRET));
    }
}
