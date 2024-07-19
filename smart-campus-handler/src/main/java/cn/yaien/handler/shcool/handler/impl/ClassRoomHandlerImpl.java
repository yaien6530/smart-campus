package cn.yaien.handler.shcool.handler.impl;

import cn.yaien.handler.shcool.handler.ClassRoomHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 教室处理器实现类
 *
 * @author yanggl
 * @since 2024/7/18/16:23
 */
@Component
public class ClassRoomHandlerImpl implements ClassRoomHandler {
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
