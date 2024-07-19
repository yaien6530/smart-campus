package cn.yaien.domain.school.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * 老师实体类
 *
 * @author yanggl
 * @since 2024/7/13/12:48
 */
@Data
@Table("t_teacher")
public class TeacherEntity {
    /**
     * 老师ID
     */
    @Id
    private Long teacherId;

    /**
     * 登陆用户ID
     */
    private Long userId;

    /**
     * 身份证
     */
    private String identityCard;

    /**
     * 出生日期
     */
    private String birthDate;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 状态
     */
    private String status;

    /**
     * 删除标识（0-正常 1-已删除）
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
