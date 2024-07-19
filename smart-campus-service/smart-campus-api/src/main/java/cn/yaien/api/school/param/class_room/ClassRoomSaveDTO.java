package cn.yaien.api.school.param.class_room;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yanggl
 * @since 2024/7/15/11:32
 */
@Data
public class ClassRoomSaveDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 教室名称
     */
    private String classRoomName;

    /**
     * 建筑名称
     */
    private String building;

    /**
     * 所在楼层
     */
    private int floor;

    /**
     * 座位数
     */
    private int capacity;

    /**
     * 是否多媒体教室（0-否，1-是）
     */
    private int isMultimedia;

    /**
     * 教室描述
     */
    private String description;

}
