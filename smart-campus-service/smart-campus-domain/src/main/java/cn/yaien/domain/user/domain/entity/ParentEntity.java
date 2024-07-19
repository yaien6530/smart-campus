package cn.yaien.domain.user.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * 家长实体类
 *
 * @author yanggl
 * @since 2024/7/15/10:51
 */
@Data
@EqualsAndHashCode()
@Table("t_parent")
public class ParentEntity {

    /**
     * 家长ID
     */
    private Long parentId;

    /**
     * 登陆用户ID
     */
    private Long userId;

    /**
     * 身份证
     */
    private String identityCard;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 状态
     */
    private String status;

    /**
     * 删除标识
     */
    private int delFlag;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 修改人
     */
    private String updatedBy;
}
