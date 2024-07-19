package cn.yaien.router.shcool;

import cn.yaien.handler.shcool.handler.ClassRoomHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author yanggl
 * @since 2024/7/15/11:25
 */
@Configuration
public class ClassRoomRouter {

    @Resource
    private ClassRoomHandler classRoomHandler;

    @Bean
    public RouterFunction<ServerResponse> classRoomRouterFactory() {
        return RouterFunctions.route()
                .POST("/save", RequestPredicates.accept(MediaType.APPLICATION_JSON), classRoomHandler::save)
                .DELETE("/deleteById", RequestPredicates.accept(MediaType.APPLICATION_JSON), classRoomHandler::deleteById)
                .GET("/getById", RequestPredicates.accept(MediaType.APPLICATION_JSON), classRoomHandler::getById)
                .GET("/getAll", RequestPredicates.accept(MediaType.APPLICATION_JSON), classRoomHandler::getAll)
                .build();
    }

}
