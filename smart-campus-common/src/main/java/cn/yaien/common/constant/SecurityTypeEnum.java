package cn.yaien.common.constant;


import lombok.Getter;

/**
 * 加解密工具枚举
 *
 * @author yanggl
 * @since 2024/7/15/21:20
 */
@Getter
public enum SecurityTypeEnum {

    /**
     * REA非对称加密
     */
    RSA("RSA", "RSA/ECB/PKCS1Padding"),

    /**
     * ASE对称加密
     */
    AES("AES", "AES/ECB/PKCS5Padding"),

    /**
     * 针对密码加密
     */
    PWD("PWD", "NON");

    private final String key;

    private final String cipher;

    SecurityTypeEnum(String key, String cipher) {
        this.key = key;
        this.cipher = cipher;
    }

}
