package cn.yaien.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 通用处理器接口
 *
 * @author yanggl
 * @since 2024/7/13/12:35
 */
public interface IHandler {

    /**
     * 保存实体的方法
     * 处理HTTP请求以保存新的实体
     *
     * @param request 包含请求的详细信息
     * @return 包含响应的Mono对象
     */
    Mono<ServerResponse> save(ServerRequest request);

    /**
     * 通过ID获取实体的方法
     * 处理HTTP请求以通过ID获取实体
     *
     * @param request 包含请求的详细信息
     * @return 包含响应的Mono对象
     */
    Mono<ServerResponse> getById(ServerRequest request);

    /**
     * 获取所有实体的方法
     * 处理HTTP请求以获取所有实体
     *
     * @param request 包含请求的详细信息
     * @return 包含响应的Mono对象
     */
    Mono<ServerResponse> getAll(ServerRequest request);

    /**
     * 更新实体的方法
     * 处理HTTP请求以更新现有的实体
     *
     * @param request 包含请求的详细信息
     * @return 包含响应的Mono对象
     */
    Mono<ServerResponse> update(ServerRequest request);

    /**
     * 通过ID删除实体的方法
     * 处理HTTP请求以通过ID删除实体
     *
     * @param request 包含请求的详细信息
     * @return 包含响应的Mono对象
     */
    Mono<ServerResponse> deleteById(ServerRequest request);
}
