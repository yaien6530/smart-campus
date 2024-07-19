package cn.yaien.domain.user.impl;


import cn.yaien.api.user.intf.IUserService;
import cn.yaien.api.user.param.user.UserSaveDTO;
import cn.yaien.common.constant.SecurityTypeEnum;
import cn.yaien.common.error.BusinessException;
import cn.yaien.common.secret.PwdSecretUtil;
import cn.yaien.common.secret.RSASecretUtil;
import cn.yaien.common.secret.SecretFactory;
import cn.yaien.common.web.Session;
import cn.yaien.domain.user.common.convert.UserConvert;
import cn.yaien.domain.user.domain.aggregate.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

/**
 * @author yanggl
 * @since 2024/7/15/10:40
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private User user;

    @Override
    public Mono<Session> login(String username, String password, String captcha) {
        return user.login(username, password, captcha)
                .flatMap(userEntity -> user.buildSessionAndCache(userEntity)
                        .switchIfEmpty(Mono.error(new BusinessException("登陆失败"))));
    }

    @Override
    public Mono<Void> save(UserSaveDTO dto) {
        return Mono.just(UserConvert.INSTANT.toEntity(dto))
                .flatMap(entity -> SecretFactory.option(SecurityTypeEnum.PWD)
                        .flatMap(pwdSecret -> ((PwdSecretUtil) pwdSecret).generateSalt()
                                .flatMap(salt -> pwdSecret.encrypt(salt, dto.getPassword())
                                        .flatMap(pwd -> {
                                                    entity.setPassword(pwd);
                                                    entity.setSalt(salt);
                                                    entity.setCreatedTime(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
                                                    entity.setCreatedBy("admin");
                                                    return user.save(entity);
                                                }
                                        )
                                )
                        )
                ).then();
    }

}
