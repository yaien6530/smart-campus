package cn.yaien.api.user.param.user;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * @author yanggl
 * @since 2024/7/15/17:57
 */
@Data
public class UserSaveDTO implements Serializable {

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
     * 邮箱
     */
    private String email;

    /**
     * 用户类型
     *
     * @see cn.yaien.common.constant.UserTypeEnum
     */
    private String userType;

    /**
     * 角色ID
     */
    private Long roleId;
}
