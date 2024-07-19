package cn.yaien.domain.school.domain.repository;

import cn.yaien.domain.school.domain.entity.CourseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 课程持久化接口
 *
 * @author yanggl
 * @since 2024/7/15/09:19
 */
@Repository
public interface CourseRepository extends ReactiveCrudRepository<CourseEntity, Long> {
}
