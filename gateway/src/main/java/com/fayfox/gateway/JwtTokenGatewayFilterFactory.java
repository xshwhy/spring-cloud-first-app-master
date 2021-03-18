package com.fayfox.gateway;

import com.fayfox.gateway.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

public class JwtTokenGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Autowired
    private JWTUtil jwtUtil;

    public GatewayFilter apply() {
        return apply(o -> {
        });
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            // 优先从请求参数中获取Token（测试方便）
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            String authToken = request.getQueryParams().getFirst("authToken");
            if (authToken == null || authToken.isEmpty()) {
                // 尝试从Header中获取Token
                authToken = request.getHeaders().getFirst("Authorization");
            }

            if (authToken == null || authToken.isEmpty()) {
                // 没有Token，直接报错
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            if (jwtUtil.checkJWT(authToken)) {
                // Token验证成功，继续下一个filter
                return chain.filter(exchange);
            }

            // Token验证失败，返回401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        };
    }
}
