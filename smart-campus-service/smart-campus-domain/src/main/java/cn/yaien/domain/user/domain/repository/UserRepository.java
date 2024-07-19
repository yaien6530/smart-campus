package cn.yaien.domain.user.domain.repository;

import cn.yaien.domain.user.domain.entity.UserEntity;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author yanggl
 * @since 2024/7/15/10:41
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long>, ReactiveSortingRepository<UserEntity, Long> {

    @Query("SELECT salt FROM t_user WHERE username = :un")
    Mono<String> findSaltByUsername(@Param("un") String username);

    @Query("SELECT salt FROM t_user WHERE user_id = :uId")
    Mono<String> findSaltById(@Param("uId") String username);

    @Query("SELECT * FROM t_user WHERE username = :un AND password = :pwd")
    Mono<UserEntity> findByUsername(@Param("un") String username, @Param("pwd") String password);
}
