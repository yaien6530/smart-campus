package cn.yaien.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * 基于 Web-FLux 开发的系统入口
 *
 * @author yanggl
 * @since 2024/7/16/10:57
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.yaien")
@EnableR2dbcRepositories(basePackages = "cn.yaien.domain")
public class SmartCampusRouterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartCampusRouterApplication.class, args);
    }
}