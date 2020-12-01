package com.lcq.pet.server.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2020-11-29 14:32
 */
@Component
public class HelloFilter implements GlobalFilter {
    /**
     * 过滤*/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("过滤器");

        //放行
        return chain.filter(exchange);
    }
}
