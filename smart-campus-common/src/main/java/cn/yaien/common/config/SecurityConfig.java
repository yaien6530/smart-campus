package cn.yaien.common.config;

import cn.yaien.common.cache.RedisCache;
import cn.yaien.common.filter.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;


/**
 * security配置类
 *
 * @author yanggl
 * @since 2024/7/17/16:32
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private RedisCache redisCache;

    /**
     * 配置内存中的用户详细信息服务
     *
     * @return 返回包含单个用户的MapReactiveUserDetailsService实例
     */
//    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }



    /**
     * 配置安全过滤链
     *
     * @param http ServerHttpSecurity实例，用于构建安全过滤链
     * @return 返回SecurityWebFilterChain实例
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(authorize -> authorize
                        // 所有人可以访问
                        .pathMatchers("/user/login", "/user/logout","/user/save").permitAll()
                        // 指定 ADMIN 角色可以访问
                        .pathMatchers("/admin/**").hasRole("ADMIN")
                        // 其他请求需要授权
                        .anyExchange().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

}
