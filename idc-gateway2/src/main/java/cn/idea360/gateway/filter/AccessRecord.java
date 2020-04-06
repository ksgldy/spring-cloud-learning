package cn.idea360.gateway.filter;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

@Data
public class AccessRecord {

    private String path;
    private String schema;
    private String method;
    private MultiValueMap<String, String> formData;
    private String body;
    private long expendTime;
    private HttpHeaders headers;
    // 服务提供者
    private String targetUri;
    // 请求来源
    private String remoteAddress;

}
