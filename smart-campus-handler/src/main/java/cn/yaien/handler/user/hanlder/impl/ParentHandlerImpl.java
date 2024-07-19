package cn.yaien.handler.user.hanlder.impl;

import cn.yaien.handler.user.hanlder.ParentHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 家长处理器实现类
 *
 * @author yanggl
 * @since 2024/7/18/16:26
 */
@Component
public class ParentHandlerImpl implements ParentHandler {
    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        return null;
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
