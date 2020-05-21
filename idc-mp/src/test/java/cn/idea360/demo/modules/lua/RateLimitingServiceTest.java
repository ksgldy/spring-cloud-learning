package cn.idea360.demo.modules.lua;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class RateLimitingServiceTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DefaultRedisScript<Long> redisScript;

    @Test
    void luaTest() {
        List<String> keys = Arrays.asList("aaa");
        // 10秒内小于或等于3次时返回1，否则返回0
        for (int i = 0; i < 4; i++) {
            Object execute = redisTemplate.execute(redisScript, keys, 10, 3);
            System.out.println(execute);
        }
    }
}