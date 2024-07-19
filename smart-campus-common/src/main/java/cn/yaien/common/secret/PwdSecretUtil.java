package cn.yaien.common.secret;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author yanggl
 * @since 2024/7/18/08:49
 */
public class PwdSecretUtil implements ISecret {


    /**
     * 定义算法
     */
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * 生成密钥长度
     */
    private static final int KEY_LENGTH = 256;

    /**
     * 迭代次数
     */
    private static final int ITERATIONS = 10000;

    /**
     * 生成随机盐值
     *
     * @return 盐值
     */
    public Mono<String> generateSalt() {
        return Mono.fromCallable(() -> {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[32];
            sr.nextBytes(salt);
            return Base64.getEncoder().encodeToString(salt);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 验证原始密码和加密后的密码是否匹配
     *
     * @param oldPassword 原始密码
     * @param salt        盐值
     * @param password    加密后的密码
     * @return 是否匹配
     */
    public Mono<Boolean> checkPassword(String oldPassword, String salt, String password) {
        return encrypt(oldPassword, salt)
                .map(encryptedPassword -> encryptedPassword.equals(password));
    }

    /**
     * 使用盐值和密码生成加密后的密码
     *
     * @param password 原始密码
     * @param salt     盐值
     * @return 加密后的密码
     */
    public Mono<String> encrypt(String password, String salt) {
        return Mono.fromCallable(() -> {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<String> decrypt(String key, String encryptData) {
        return Mono.empty();
    }

    public static void main(String[] args) {
        PwdSecretUtil util = new PwdSecretUtil();
        util.generateSalt()
                .flatMap(salt -> {
                    System.err.println("Salt: " + salt);
                    System.err.println("Salt length: " + salt.length());

                    return util.encrypt("Admin", salt)
                            .flatMap(pwd -> {
                                System.err.println("Encrypted Password: " + pwd);
                                return util.checkPassword("Admin", salt, pwd);
                            });
                })
                .subscribe(isValid -> System.out.println("Password validation: " + isValid));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
