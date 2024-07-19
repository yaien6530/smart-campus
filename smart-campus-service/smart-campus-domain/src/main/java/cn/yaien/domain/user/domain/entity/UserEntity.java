package cn.yaien.domain.user.domain.entity;

import cn.yaien.domain.infrastructure.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * 通用用户实体类
 *
 * @author yanggl
 * @since 2024/7/15/11:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table("t_user")
public class UserEntity extends EntityBase {

    /**
     * 用户ID
     */
    @Id
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户类型（Admin-管理员、Teacher-老师、Parent-家长）
     */
    private String userType;

    /**
     * 角色ID
     */
    private Long roleId;
}
