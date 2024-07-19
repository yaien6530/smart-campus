package cn.yaien.api.user.param.user;

import lombok.Data;

/**
 * @author yanggl
 * @since 2024/7/15/10:48
 */
@Data
public class UserRespDTO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 邮箱
     */
    private String email;

}
