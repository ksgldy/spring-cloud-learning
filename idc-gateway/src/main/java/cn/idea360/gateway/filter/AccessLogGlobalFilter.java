package cn.idea360.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBufAllocator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class AccessLogGlobalFilter implements GlobalFilter {

    private final ObjectMapper mapper = new ObjectMapper();
    private final DataBufferFactory dataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 请求相对路径
        String path = request.getPath().pathWithinApplication().value();
        // 请求schema: http/https
        String scheme = request.getURI().getScheme();
        // 请求方法
        HttpMethod method = request.getMethod();
        // 路由服务地址
        URI targetUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        // 请求头Content-Type
        String contentType = request.getHeaders().getFirst("Content-Type");
        // 设置startTime
        exchange.getAttributes().put("startTime", System.currentTimeMillis());
        // 获取请求地址
        InetSocketAddress remoteAddress = request.getRemoteAddress();


        StringBuilder builder = new StringBuilder();
        if (HttpMethod.GET.equals(method)) {
            MultiValueMap<String, String> queryParams = request.getQueryParams();
            try {
                builder.append(mapper.writeValueAsString(queryParams));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
        } else if (HttpMethod.POST.equals(method)) {

            // 获取body数据
            handlePostBody2(request, builder);

            // 重写请求体,因为请求体数据只能被消费一次
            ServerHttpRequest serverHttpRequest = request.mutate().uri(request.getURI()).build();
            request = new ServerHttpRequestDecorator(serverHttpRequest) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return Flux.just(dataBufferFactory.wrap(builder.toString().getBytes(StandardCharsets.UTF_8)));
                }
            };
        }

        return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(() -> {

            Long startTime = exchange.getAttribute("startTime");
            long executeTime = (System.currentTimeMillis() - startTime);
            ServerHttpResponse response = exchange.getResponse();
            HttpStatus statusCode = response.getStatusCode();

            // log
            AccessRecord accessRecord = new AccessRecord();
            accessRecord.setPath(path);
            accessRecord.setScheme(scheme);
            accessRecord.setMethod(method.name());
            accessRecord.setTargetUri(targetUri.toString());
            accessRecord.setContentType(contentType);
            accessRecord.setRemoteAddress(remoteAddress.toString());
            accessRecord.setRequestString(builder.toString());
            accessRecord.setExpendTime(executeTime);
            accessRecord.setResponseCode(statusCode.value());

            log.info("请求路径:{},远程IP地址:{},请求方法:{},请求参数:{},目标URI:{},响应码:{}", path, remoteAddress, method, builder.toString(), targetUri, statusCode);

            writeAccessLog(accessRecord);
        }));
    }


    private void handlePostBody(ServerHttpRequest request, StringBuilder builder) {
        // 获取请求体
        Flux<DataBuffer> body = request.getBody();

        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            builder.append(bodyString);
        });
    }

    private void handlePostBody2(ServerHttpRequest request, StringBuilder builder) {
        // 获取请求体
        Flux<DataBuffer> body = request.getBody();

        body.subscribe(buffer -> {
            InputStream inputStream = buffer.asInputStream();
            try {
                builder.append(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    private void writeAccessLog(AccessRecord accessLog){

        log.info("accessLog: {}", accessLog);

        File file = new File("access.log");
        if (!file.exists()){
            try {
                if (file.createNewFile()){
                    file.setWritable(true);
                }
            } catch (IOException e) {
                log.error("创建访问日志文件失败.{}",e.getMessage(),e);
            }
        }

        try(FileWriter fileWriter = new FileWriter(file.getName(),true)){
            fileWriter.write(JSON.toJSONString(accessLog));
        } catch (IOException e) {
            log.error("写访问日志到文件失败. {}", e.getMessage(),e);
        }

    }


    @Data
    private class AccessRecord{
        private String path;
        private String scheme;
        private String method;
        private String targetUri;
        private String contentType;
        private String remoteAddress;
        private String requestString;
//        private String body;
//        private MultiValueMap<String,String> queryParams;
        private long expendTime;
        private int responseCode;
    }
}
