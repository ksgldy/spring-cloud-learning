package cn.idea360.gateway.filter1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class BFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("BFilter前置逻辑");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("BFilter后置逻辑");
        }));
    }
}