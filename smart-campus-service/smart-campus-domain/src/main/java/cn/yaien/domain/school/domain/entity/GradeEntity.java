package cn.yaien.domain.school.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * 年级实体类
 *
 * @author yanggl
 * @since 2024/7/15/11:13
 */
@Data
@EqualsAndHashCode
@Table("/t_grade")
public class GradeEntity {

    /**
     * 年级ID
     */
    private Long gradeId;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 年级描述
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
