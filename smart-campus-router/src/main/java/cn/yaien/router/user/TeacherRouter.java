package cn.yaien.router.user;

import cn.yaien.handler.shcool.handler.TeacherHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * 老师业务相关请求路由
 *
 * @author yanggl
 * @since 2024/7/13/10:22
 */
@Configuration
public class TeacherRouter {

    @Resource
    private TeacherHandler teacherHandler;

    @Bean
    public RouterFunction<ServerResponse> teacherRouterFactory() {
        return RouterFunctions.nest(RequestPredicates.path("/teacher"), route()
                .POST("/save", RequestPredicates.accept(MediaType.APPLICATION_JSON), teacherHandler::save)
                .DELETE("/deleteById", RequestPredicates.accept(MediaType.APPLICATION_JSON), teacherHandler::deleteById)
                .GET("/getById", RequestPredicates.accept(MediaType.APPLICATION_JSON), teacherHandler::getById)
                .GET("/getAll", RequestPredicates.accept(MediaType.APPLICATION_JSON), teacherHandler::getAll)
                .build());
    }
}
