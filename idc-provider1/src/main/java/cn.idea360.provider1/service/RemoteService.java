package cn.idea360.provider1.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("idc-provider2")
public interface RemoteService {

    /**
     * 方法名随意，url路径匹配即可
     * @return
     */
    @GetMapping("/provider2")
    Object getProvider2();


}
