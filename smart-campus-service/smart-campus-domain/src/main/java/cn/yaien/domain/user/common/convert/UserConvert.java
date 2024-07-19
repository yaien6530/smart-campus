package cn.yaien.domain.user.common.convert;


import cn.yaien.api.user.param.user.UserSaveDTO;
import cn.yaien.domain.user.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author yanggl
 * @since 2024/7/15/10:47
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    UserConvert INSTANT = Mappers.getMapper(UserConvert.class);

    UserEntity toEntity(UserSaveDTO dto);

    UserSaveDTO toDTO(UserEntity entity);

}
