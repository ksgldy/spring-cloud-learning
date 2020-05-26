package cn.idea360.demo.modules.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
public class TestController {

    // http://localhost:8888/user
    @GetMapping("/user")
    public User getUser() {
        return new User("admin", "123");
    }

    // http://localhost:8888/list
    @GetMapping("/list")
    public List<User> list() {
        return Arrays.asList(new User("admin", "456"));
    }
}
