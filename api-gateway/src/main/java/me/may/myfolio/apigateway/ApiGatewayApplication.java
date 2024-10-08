package me.may.myfolio.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**")
                        .uri("http://localhost:8081"))
                .route("portfolio", r -> r.path("/portfolio/**")
                        .uri("http://localhost:8082"))
                .route("file", r -> r.path("/file/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}
