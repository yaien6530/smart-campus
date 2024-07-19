package cn.yaien.domain.user.common.convert;

import cn.yaien.api.user.param.teacher.TeacherDTO;
import cn.yaien.domain.school.domain.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 教师实体与DTO之间的映射接口
 *
 * @author yanggl
 * @since 2024/7/13/13:20
 */
@Mapper(componentModel = "spring")
public interface TeacherConvert {

    TeacherConvert INSTANCE = Mappers.getMapper(TeacherConvert.class);

    TeacherDTO toDTO(TeacherEntity teacher);

    TeacherEntity toEntity(TeacherDTO teacherDTO);

}
