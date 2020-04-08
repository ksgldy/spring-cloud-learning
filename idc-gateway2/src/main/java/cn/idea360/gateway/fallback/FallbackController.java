package cn.idea360.gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {

    @RequestMapping("/defaultfallback")
    public Map<String,Object> defaultfallback(){
        System.out.println("降级操作...");
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","服务超时降级");
        map.put("data",null);
        return map;
    }
}
