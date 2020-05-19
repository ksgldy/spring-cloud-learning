package cn.idea360.demo;

import cn.idea360.demo.modules.mp.entity.User;
import cn.idea360.demo.modules.mp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class IdcMpApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        List<User> list = userService.list();
        log.info(list.toString());
    }

}
