package cn.yaien.common.utils;

import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * 随机数工具类
 *
 * @author yanggl
 * @since 2024/7/18/01:31
 */
public class RandomUtil {

    /**
     * 生产密码盐
     *
     * @return base64密码盐
     */
    public static Mono<String> generateSalt() {
        return Mono.fromCallable(() -> {
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);
            return Base64.getEncoder().encodeToString(salt);
        });
    }

}
