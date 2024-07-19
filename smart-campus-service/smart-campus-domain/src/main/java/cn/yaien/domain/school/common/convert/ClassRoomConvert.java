package cn.yaien.domain.school.common.convert;


import cn.yaien.api.school.param.class_room.ClassRoomSaveDTO;
import cn.yaien.domain.school.domain.entity.ClassRoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author yanggl
 * @since 2024/7/15/17:33
 */
@Mapper
public interface ClassRoomConvert {

    ClassRoomConvert INSTANCE = Mappers.getMapper(ClassRoomConvert.class);

    ClassRoomSaveDTO toDTO(ClassRoomEntity classRoom);

    ClassRoomEntity toEntity(ClassRoomSaveDTO dto);
}
