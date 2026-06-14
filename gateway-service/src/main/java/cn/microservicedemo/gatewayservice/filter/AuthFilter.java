package cn.microservicedemo.gatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthFilter implements GlobalFilter {

    private static final int MAX_REQUESTS = 5;
    private static final long WINDOW_MILLIS = 10 * 1000L;

    private final Map<String, RequestWindow> requestWindows = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");

        System.out.println("gateway token = " + token);

        if (!"1".equals(token)) {
            return writeText(exchange, HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        String clientKey = getClientKey(exchange);
        if (isRateLimited(clientKey)) {
            return writeText(exchange, HttpStatus.TOO_MANY_REQUESTS, "Too many requests");
        }

        return chain.filter(exchange);
    }

    private boolean isRateLimited(String clientKey) {
        long now = System.currentTimeMillis();
        RequestWindow window = requestWindows.computeIfAbsent(clientKey, key -> new RequestWindow(now));

        synchronized (window) {
            if (now - window.windowStart >= WINDOW_MILLIS) {
                window.windowStart = now;
                window.count = 1;
                return false;
            }

            window.count++;
            return window.count > MAX_REQUESTS;
        }
    }

    private String getClientKey(ServerWebExchange exchange) {
        InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        if (remoteAddress == null || remoteAddress.getAddress() == null) {
            return "unknown";
        }
        return remoteAddress.getAddress().getHostAddress();
    }

    private Mono<Void> writeText(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);

        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    private static class RequestWindow {
        private long windowStart;
        private int count;

        private RequestWindow(long windowStart) {
            this.windowStart = windowStart;
            this.count = 0;
        }
    }
}