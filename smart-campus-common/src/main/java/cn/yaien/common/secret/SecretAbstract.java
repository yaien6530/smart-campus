package cn.yaien.common.secret;

import cn.yaien.common.constant.SecurityTypeEnum;
import cn.yaien.common.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

/**
 * 加解密实现父类
 *
 * @author yanggl
 * @since 2024/7/15/21:16
 */
@Slf4j
public abstract class SecretAbstract {

    protected final SecurityTypeEnum securityType;

    protected SecretAbstract(SecurityTypeEnum securityTypeEnum) {
        this.securityType = securityTypeEnum;
    }

    public Mono<String> encrypt(String key, String data) {
        return decryptSecretKey(key)
                .flatMap(secretKey -> doEncrypt(secretKey, data))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<String> decrypt(String key, String encryptData) {
        return encryptSecretKey(key)
                .flatMap(secretKey -> doDecrypt(secretKey, encryptData))
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<String> doEncrypt(Key secretKey, String data) {
        return Mono.fromCallable(() -> {
            try {
                Cipher cipher = Cipher.getInstance(securityType.getKey());
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
                return Base64.getEncoder().encodeToString(encryptedBytes);
            } catch (Exception e) {
                throw new BusinessException(String.format("加密数据发生未知错误：{key=%s,data=%s} \n%s", secretKey, data, e));
            }
        });
    }

    private Mono<String> doDecrypt(Key secretKey, String encryptData) {
        return Mono.fromCallable(() -> {
            try {
                Cipher cipher = Cipher.getInstance(securityType.getKey());
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptData));
                return new String(decryptedBytes, StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new BusinessException(String.format("解密数据发生未知错误：{key=%s,data=%s}", secretKey, encryptData));
            }
        });
    }

    /**
     * 获取加密密钥
     *
     * @param key 字符串密钥
     * @return 密钥 {@link Key}
     */
    public Mono<Key> encryptSecretKey(String key) {
        return Mono.empty();
    }

    /**
     * 获取解密密钥
     *
     * @param key 字符串密钥
     * @return 密钥 {@link Key}
     */
    public Mono<Key> decryptSecretKey(String key) {
        return Mono.empty();
    }


}
