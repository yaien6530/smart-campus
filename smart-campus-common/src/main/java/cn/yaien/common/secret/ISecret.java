package cn.yaien.common.secret;

import reactor.core.publisher.Mono;

/**
 * 加解密接口
 *
 * @author yanggl
 * @since 2024/7/15/21:09
 */
public interface ISecret {

    /**
     * 加密
     *
     * @param key  密钥
     * @param data 待加密的数据
     * @return 加密后的数据
     */
    Mono<String> encrypt(String key, String data);

    /**
     * 解密
     *
     * @param key         密钥
     * @param encryptData 待解密的数据
     * @return 解密后的数据
     */
    Mono<String> decrypt(String key, String encryptData);
}
