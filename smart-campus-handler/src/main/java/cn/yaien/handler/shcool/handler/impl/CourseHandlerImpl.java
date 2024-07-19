package cn.yaien.handler.shcool.handler.impl;

import cn.yaien.handler.shcool.handler.CourseHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author yanggl
 * @since 2024/7/18/16:24
 */
@Component
public class CourseHandlerImpl implements CourseHandler {
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
