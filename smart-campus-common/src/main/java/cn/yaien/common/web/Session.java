package cn.yaien.common.web;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登陆 session
 *
 * @author yanggl
 * @since 2024/7/17/16:21
 */
@Data
@EqualsAndHashCode
public class Session implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户类别
     */
    private String userType;

    /**
     * 用户token
     */
    private String token;
}
