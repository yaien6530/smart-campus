package cn.yaien.domain.user.domain.repository;

import cn.yaien.domain.school.domain.entity.TeacherEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 老师仓库接口
 *
 * @author yanggl
 * @since 2024/7/13/12:45
 */
@Repository
public interface TeacherRepository extends ReactiveCrudRepository<TeacherEntity, Long> {
}
