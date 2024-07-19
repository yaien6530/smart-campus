package cn.yaien.common.utils;

import cn.yaien.common.error.BusinessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

/**
 * SecurityUtil 工具类，用于获取当前登录用户的信息
 *
 * @author yanggl
 * @since 2024/7/18/00:56
 */
public class SecurityUtil {

    /**
     * 获取当前登录的用户名
     *
     * @return 当前登录用户名的 Mono
     */
    public static Mono<String> getCurrentUsername() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .switchIfEmpty(Mono.error(new BusinessException("当前登陆用户名信息获取失败")));
    }
}
