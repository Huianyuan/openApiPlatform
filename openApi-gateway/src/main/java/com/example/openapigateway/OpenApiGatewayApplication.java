package com.example.openapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiGatewayApplication.class, args);
    }

    // @Bean
    // public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    //     return builder.routes()
    //             .route("tobaidu", r -> r.path("/baidu")
    //                     .uri("http://www.baidu.com"))
    //             .route("host_route", r -> r.host("*.myhost.org")
    //                     .uri("http://httpbin.org"))
    //             .build();
    // }
}

