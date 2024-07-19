package cn.yaien.router.shcool;

import cn.yaien.handler.shcool.handler.CourseHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 课程路由
 *
 * @author yanggl
 * @since 2024/7/15/09:02
 */
@Configuration
public class CourseRouter {

    @Resource
    private CourseHandler courseHandler;

    @Bean
    public RouterFunction<ServerResponse> courseRouterFactory() {
        return RouterFunctions.route()
                .POST("/save", RequestPredicates.accept(MediaType.APPLICATION_JSON), courseHandler::save)
                .DELETE("/deleteById", RequestPredicates.accept(MediaType.APPLICATION_JSON), courseHandler::deleteById)
                .GET("/getById", RequestPredicates.accept(MediaType.APPLICATION_JSON), courseHandler::getById)
                .GET("/getAll", RequestPredicates.accept(MediaType.APPLICATION_JSON), courseHandler::getAll)
                .build();
    }

}
