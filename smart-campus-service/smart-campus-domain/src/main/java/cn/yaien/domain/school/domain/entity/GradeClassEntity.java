package cn.yaien.domain.school.domain.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * 班级实体类
 *
 * @author yanggl
 * @since 2024/7/15/11:14
 */
@Data
@Table("t_grade_class")
public class GradeClassEntity {

    /**
     * 班级ID
     */
    private Long classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级ID
     */
    private Long gradeId;

    /**
     * 班主任
     */
    private String classTeacher;

    /**
     * 班级描述
     */
    private String description;

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
