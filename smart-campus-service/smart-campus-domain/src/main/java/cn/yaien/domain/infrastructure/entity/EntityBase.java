package cn.yaien.domain.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 所有数据库映射实体的基类
 *
 * @author yanggl
 * @since 2024/7/18/00:40
 */
@Data
public class EntityBase {

    /**
     * 删除标识（0-正常，1-已删除）
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    @CreatedBy
    private String createdBy;

    /**
     * 修改时间
     */
    @LastModifiedDate
    private LocalDateTime updatedTime;

    /**
     * 修改人
     */
    @LastModifiedBy
    private String updatedBy;

}
