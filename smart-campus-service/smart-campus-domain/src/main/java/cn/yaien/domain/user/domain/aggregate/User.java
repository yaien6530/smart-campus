package cn.yaien.domain.user.domain.aggregate;


import cn.yaien.common.cache.RedisCache;
import cn.yaien.common.constant.RedisPrefix;
import cn.yaien.common.constant.SecurityTypeEnum;
import cn.yaien.common.secret.SecretFactory;
import cn.yaien.common.utils.JwtUtil;
import cn.yaien.common.web.Session;
import cn.yaien.domain.user.domain.entity.UserEntity;
import cn.yaien.domain.user.domain.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


/**
 * 用户聚合根
 *
 * @author yanggl
 * @since 2024/7/15/20:53
 */
@Slf4j
@Component
public class User {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RedisCache redisCache;

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @return 登陆结果
     */
    public Mono<UserEntity> login(String username, String password, String captcha) {
        return validateCaptcha(username, captcha)
                .then(getSaltByUsername(username))
                .flatMap(salt -> validateUserPassword(salt, username, password));
    }

    /**
     * 新增用户信息
     *
     * @param userEntity 用户实体
     * @return 用户实体
     */
    public Mono<UserEntity> save(UserEntity userEntity) {
        log.info("新增用户：{}", userEntity);
        return userRepository.save(userEntity);
    }

    /**
     * 获取用户详情
     *
     * @return
     */
    public Mono<UserEntity> getUserDetail(Long userId) {
        return userRepository.findById(userId);
    }


    /**
     * 验证验证码
     *
     * @param captcha 验证码
     * @return 验证结果
     */
    private Mono<Void> validateCaptcha(String username, String captcha) {
        String captchaKey = RedisPrefix.CAPTCHA + username;
        return redisCache.get(captchaKey)
                .flatMap(str -> {
                    if (!str.equals(captcha)) {
                        log.warn("用户[{}]验证码校验失败：{} , {}!", username, str, captcha);
                        return Mono.error(new RuntimeException("验证码错误"));
                    }
                    return Mono.just(str);
                }).switchIfEmpty(Mono.defer(() -> {
                    log.warn("用户[{}]验证码信息[{}]查询失败!", username, captchaKey);
                    return Mono.error(new RuntimeException("验证码错误"));
                }))
                .then();
    }

    /**
     * 根据用户名查找用户密钥
     *
     * @param username 用户名
     * @return 用户实体
     */
    private Mono<String> getSaltByUsername(String username) {
        return userRepository.findSaltByUsername(username)
                .switchIfEmpty(Mono.error(new RuntimeException("用户信息不存在")));
    }

    /**
     * 验证用户密码
     *
     * @param salt     盐
     * @param username 用户实体
     * @param password 密码
     * @return 验证结果
     */
    private Mono<UserEntity> validateUserPassword(String salt, String username, String password) {
        return SecretFactory.option(SecurityTypeEnum.PWD)
                .flatMap(secret -> secret.encrypt(salt, password))
                .flatMap(pwd -> userRepository.findByUsername(username, pwd))
                .switchIfEmpty(Mono.error(new RuntimeException("账号或密码错误")));
    }

    /**
     * 生成用户session
     *
     * @param userEntity 用户信息
     * @return 用户session
     */
    public Mono<Session> buildSession(UserEntity userEntity) {
        return JwtUtil.generateToken(userEntity.getUsername(), userEntity.getSalt())
                .flatMap(token -> {
                    Session session = new Session();
                    session.setToken(token);
                    session.setUsername(userEntity.getUsername());
                    session.setUserId(userEntity.getUserId());
                    session.setUserType(userEntity.getUserType());
                    session.setRoleId(userEntity.getRoleId());
                    return Mono.just(session);
                });
    }

    /**
     * 生成用户session并缓存
     *
     * @param userEntity 用户信息
     * @return
     */
    public Mono<Session> buildSessionAndCache(UserEntity userEntity) {
        return buildSession(userEntity)
                .flatMap(session -> redisCache.set(session.getToken(), session, JwtUtil.EXPIRATION_TIME / 1000)
                        .flatMap(aBoolean -> {
                            if (Boolean.TRUE.equals(aBoolean)) {
                                log.info("用户[{}]Session缓存成功", userEntity.getUsername());
                                return Mono.just(session);
                            } else {
                                log.error("用户[{}]Session缓存失败", userEntity.getUsername());
                                return Mono.empty();
                            }
                        })
                );
    }


}
