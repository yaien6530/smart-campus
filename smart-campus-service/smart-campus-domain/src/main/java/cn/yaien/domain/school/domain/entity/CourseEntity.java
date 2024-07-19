package cn.yaien.domain.school.domain.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * 课程实体类
 *
 * @author yanggl
 * @since 2024/7/15/09:07
 */
@Data
@Table("t_course")
public class CourseEntity {

    /**
     * 课程Id
     */
    private Long courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程类型（0-必修课 1-选修课）
     */
    private Integer courseType;

    /**
     * 标签
     */
    private Integer courseTags;

    /**
     * 状态
     */
    private String status;

    /**
     * 删除标识（0-正常 1-已删除）
     */
    private int delFlag;

    /**
     * 课程描述
     */
    private String description;

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
