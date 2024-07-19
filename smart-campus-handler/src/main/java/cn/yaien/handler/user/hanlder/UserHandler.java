package cn.yaien.handler.user.hanlder;


import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import cn.yaien.handler.IHandler;

/**
 * 用户业务处理器
 *
 * @author yanggl
 * @since 2024/7/15/10:38
 */
public interface UserHandler extends IHandler {

    /**
     * 用户登陆
     *
     * @param request 请求
     * @return 响应
     */
    Mono<ServerResponse> login(ServerRequest request);


}
