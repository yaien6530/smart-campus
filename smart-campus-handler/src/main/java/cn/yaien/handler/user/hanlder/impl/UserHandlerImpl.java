package cn.yaien.handler.user.hanlder.impl;

import cn.yaien.api.user.intf.IUserService;
import cn.yaien.api.user.param.user.UserLoginDTO;
import cn.yaien.api.user.param.user.UserSaveDTO;
import cn.yaien.common.error.CodeMsg;
import cn.yaien.common.web.Result;
import cn.yaien.handler.user.hanlder.UserHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 用户业务处理器实现类
 *
 * @author yanggl
 * @since 2024/7/18/15:57
 */
@Slf4j
@Component
public class UserHandlerImpl implements UserHandler {

    @Resource
    private IUserService userService;

    @Override
    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(UserLoginDTO.class)
                .flatMap(dto -> userService.login(dto.getUsername(), dto.getPassword(), dto.getCaptcha()))
                .flatMap(e -> ServerResponse.ok().bodyValue(new Result<>(CodeMsg.SUCCESS, e)));
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(UserSaveDTO.class)
                .flatMap(dto -> userService.save(dto))
                .then(Mono.defer(() -> ServerResponse.ok().bodyValue("保存成功")));
    }

    @Override
    public Mono<ServerResponse> getById(ServerRequest request) {
        return null;
    }

    @Override
    public Mono<ServerResponse> getAll(ServerRequest request) {
        return null;
    }

    @Override
    public Mono<ServerResponse> update(ServerRequest request) {
        return null;
    }

    @Override
    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return null;
    }
}
