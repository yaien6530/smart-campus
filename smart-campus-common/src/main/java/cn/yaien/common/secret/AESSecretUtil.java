package cn.yaien.common.secret;

import cn.yaien.common.constant.SecurityTypeEnum;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES对称加密
 *
 * @author yanggl
 * @since 2024/7/15/21:17
 */
public class AESSecretUtil extends SecretAbstract implements ISecret {

    private static final int KEY_SIZE = 128;

    public AESSecretUtil() {
        super(SecurityTypeEnum.AES);
    }

    /**
     * 获取 AES 密钥
     *
     * @return 密钥信息
     */
    public Mono<String> generateKey() {
        return Mono.fromCallable(() -> {
            try {
                KeyGenerator keyGen = KeyGenerator.getInstance(securityType.getKey());
                keyGen.init(KEY_SIZE, new SecureRandom());
                SecretKey secretKey = keyGen.generateKey();
                return Base64.getEncoder().encodeToString(secretKey.getEncoded());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Key> encryptSecretKey(String key) {
        return Mono.fromCallable(() -> new SecretKeySpec(Base64.getDecoder().decode(key), securityType.getKey()));
    }

    @Override
    public Mono<Key> decryptSecretKey(String key) {
        return Mono.fromCallable(() -> new SecretKeySpec(Base64.getDecoder().decode(key), securityType.getKey()));
    }

    public static void main(String[] args) {
        String pwd = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4qUqse5jVARmNeiSTmYy8jW7ORQ4ZQpPOSgleCMEwfZZJf8qEwxK+ivvjnsDRDda4HuEOAA6QBW6oEI83TGs9ns8+Bka+0jp8iu2/+6Xa8FFZQ/e8qk1JeqFuUjgcwNdZP05w92mnkZaobUAykXtKZcQtoj3RgVEsx1/YQyrvNZGsPgRugykGeL2KiMOzVP5Bn26UuZefBIBSHlBvVvePXllumr4Zh4fjsfxrGyOSn6Dw3EBdE3Gnh6bd9GNjK/NHzChRg+jF/o8dlQBmekCgs+w1u2RqVBHYTDpq0uN4ACydIQk3tHcwoaEcBvUBtRg9RonMJVEERLRHHMZ9ReCzAgMBAAECggEAANnK6XBkE6WYUKSHxpLxpsTmCqp/tZgkXtsXy38SNKRbPpLp5PkmBUJZASahEgtUluMxQAV624HpfN1hcUZESTp6t931jBqbpZWztTbk5kq373xdb7+F42iRzAEUG/AD5/NrhVNl6qrg5ciD9wrggh5cvlEyPfAh1gOkc9dosjYxQ1TjW4M/uN1466nfwTN8kfPw1Rba1WX/VRBG0bZGkUN1jtm4ZdFKqK6u66dshSlRU/Qn5XK48ktk3qNFxHp+4CecUf/N7QUzdomsoNP3pAaUZUvjJfbX5ZM4MRcyrHaAisTWgyLgMkhqhsAIH7+qo3wdA7UazHl50pRSUJ18CQKBgQDlxfdkTeBmrbOjq+6dwIymaBYtFnNQ4z8uEZv3bSftkj7hsstutEYFwfzJXxGK3i0Lz4sFAXItiLyHTuMjTHgBUINnuLZIpG4b+kcHJnAhfQ/U1sB4X7zQTkrlL8MmetziwdLn3IIGIPigRfhos4B2ZBGfyURc+LXP/UraSCJTSQKBgQDNvSQcm7C4KlyOfsNg+xJUyK3rxMrwCBtsWrm83x8YvkiZzLDSLIIGmZrX5mUCFjvqEgeUdcXtphQD20thIIeOWBtgvCsJ8EK4QXoPuf77Ne11UIvGKHPkrCKoO6TNkgDbJS5Z9KFxuDwa0wpAOUvZeYI3BkiqsApUBl4RxtZYGwKBgQDCV3HKpc/CKPMC4V4ekYilCu6OzilU5hA/H+fCG9Ikr4h+D2LfTJzZK91qWP2Wbhq9nZog7rsq5jdHXqLg7UGnIvkx1lpfEKyM/1/qHUnEGWo1hDog1iD2MvJH7ODrgAmpxXRkZe6XznVb4jKcJA7ok/UQKASpejcVJ1CTVWWq+QKBgEBzCcb0dq2ah5gT2bydqiZpHyKGaVQ8FeshmKGz+5fStAeJ+bI9QxYyNDGB+Q5oTKiMsVcudax2pDp5DeoFOLG8pAbvq0PXwe7ahBQ9HcUwJYzkiISw021FBy5QUNYc8Ku1wAlp+tEW960ng0SPLlWYHNSD4Wum7fVYcUt8UFUVAoGBAM4p8/AQ15HT2knA1nEGP44OJVGLGvaxcQzBheAYrritZQsIlCjPv3cKsHI3oGWjkAjGzfnihahmJxQIwCemI+nHGRP/rWgWsDT5S8xP50+5eDAGdcdvVpaUrLaqhyJ45M3Ah6QVmjtW6ZdPN2c2ubbPDPqU7dzpo/lJdeBNf7W+";
        AESSecretUtil util = new AESSecretUtil();
        util.generateKey()
                .flatMap(key -> util.encrypt(key, pwd)
                        .flatMap(nPwd -> {
                            System.out.println(nPwd);
                            return  util.decrypt(key, nPwd);
                        }))
                .subscribe(System.err::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
