package cn.yaien.domain.school.domain.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * 教室实体类
 *
 * @author yanggl
 * @since 2024/7/15/09:31
 */
@Data
@Table("/t_class_room")
public class ClassRoomEntity {

    /**
     * 教室ID
     */
    private Long classRoomId;

    /**
     * 教室名称
     */
    private String classRoomName;

    /**
     * 建筑名称
     */
    private String building;

    /**
     * 所在楼层 默认1
     */
    private Integer floor;

    /**
     * 座位数 默认0
     */
    private Integer capacity;

    /**
     * 是否多媒体教室（0-否 1-是）
     */
    private Integer multimedia;

    /**
     * 状态（0-闲置，1-已分配，2-使用中）
     */
    private Integer status;

    /**
     * 教室描述
     */
    private String description;

    /**
     * 删除标识（0-正常 1-已删除）
     */
    private Integer delFlag;

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
