package cn.yaien.domain.school.domain.repository;

import cn.yaien.domain.school.domain.entity.ClassRoomEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 教室持久接口
 *
 * @author yanggl
 * @since 2024/7/15/11:22
 */
@Repository
public interface ClassRoomRepository extends ReactiveCrudRepository<ClassRoomEntity, Long> {
}
