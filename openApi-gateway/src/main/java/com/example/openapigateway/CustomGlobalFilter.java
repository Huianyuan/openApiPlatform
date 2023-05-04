package com.example.openapigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*
1. 用户发送请求到API网关
2. 请求日志
3. 黑白名单
4. 用户鉴权（判断ak，sk是否合法）
5. 请求的模拟接口是否存在？
6. **请求转发，调用模拟接口**
7. 响应日志
8. 调用成功，接口调用次数+1
9. 调用失败，返回规范错误码
*/

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求唯一标识"+request.getId());
        log.info("请求路径"+request.getPath().value());
        log.info("请求方法"+request.getMethod());
        log.info("请求参数"+request.getQueryParams());
        log.info("请求来源地址"+request.getRemoteAddress());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}