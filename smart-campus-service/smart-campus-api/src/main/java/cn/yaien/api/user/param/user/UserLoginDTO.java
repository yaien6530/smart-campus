package cn.yaien.api.user.param.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yanggl
 * @since 2024/7/15/20:44
 */
@Data
public class UserLoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;
}
