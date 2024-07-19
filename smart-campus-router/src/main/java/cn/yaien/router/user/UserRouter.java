package cn.yaien.router.user;

import cn.yaien.handler.user.hanlder.UserHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author yanggl
 * @since 2024/7/15/10:44
 */
@Configuration
public class UserRouter {

    @Resource
    private UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> userRouterFactory() {
        return RouterFunctions.nest(RequestPredicates.path("/user"), route()
                .POST("/save", RequestPredicates.accept(MediaType.APPLICATION_JSON), userHandler::save)
                .POST("/login", RequestPredicates.accept(MediaType.APPLICATION_JSON), userHandler::login)
                .build());
    }

}
