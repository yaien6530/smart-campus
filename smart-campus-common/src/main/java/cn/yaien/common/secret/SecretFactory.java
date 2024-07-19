package cn.yaien.common.secret;

import cn.yaien.common.constant.SecurityTypeEnum;
import cn.yaien.common.error.BusinessException;
import reactor.core.publisher.Mono;

/**
 * 加解密工厂
 *
 * @author yanggl
 * @since 2024/7/15/21:25
 */
public class SecretFactory {

    /**
     * 加载加解密工具
     *
     * @param securityTypeEnum 类型
     * @return 工具
     */
    public static Mono<ISecret> option(SecurityTypeEnum securityTypeEnum) {
        switch (securityTypeEnum) {
            case RSA -> {
                return Mono.fromCallable(RSASecretUtil::new);
            }
            case AES -> {
                return Mono.fromCallable(AESSecretUtil::new);
            }
            case PWD -> {
                return Mono.fromCallable(PwdSecretUtil::new);
            }
            default -> throw new BusinessException("找不到加解密工具: " + securityTypeEnum.getKey());
        }
    }

}
