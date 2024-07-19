package cn.yaien.common.secret;

import cn.yaien.common.constant.SecurityTypeEnum;
import cn.yaien.common.error.BusinessException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA非对称加密
 *
 * @author yanggl
 * @since 2024/7/15/21:17
 */
public class RSASecretUtil extends SecretAbstract implements ISecret {
    private static final Integer KEY_SIZE = 2048;

    public RSASecretUtil() {
        super(SecurityTypeEnum.RSA);
    }

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public Mono<KeyPair> generateKey() {
        return Mono.fromCallable(() -> {
            try {
                KeyPairGenerator keyGen = KeyPairGenerator.getInstance(securityType.getKey());
                keyGen.initialize(KEY_SIZE);
                return keyGen.generateKeyPair();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Key> decryptSecretKey(String key) {
        return Mono.fromCallable(() -> KeyFactory.getInstance(securityType.getKey())
                .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(key))));
    }

    @Override
    public Mono<Key> encryptSecretKey(String key) {
        return Mono.fromCallable(() -> KeyFactory.getInstance(securityType.getKey())
                .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key))));
    }

    public static void main(String[] args) {
        String pwd = "Admin RSASecretUtil";
        RSASecretUtil util = new RSASecretUtil();
        util.generateKey()
                .flatMap(keyPair -> {
                    String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
                    String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
                    return util.encrypt(publicKey, pwd)
                            .flatMap(encryptedPwd -> util.decrypt(privateKey, encryptedPwd));
                })
                .subscribe(System.err::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
