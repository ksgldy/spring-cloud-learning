package cn.idea360.provider1.controller;

import cn.idea360.provider1.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    Environment env;
    @Autowired
    RemoteService remoteService;

    @GetMapping("/provider1/{id}")
    public Object getTest(@PathVariable(required = false) Integer id, @RequestParam(required = false) String username) {
        return env.getProperty("local.server.port");
    }

    @PostMapping("/provider1")
    public Object postTest(@RequestBody Map<String, String> params, @RequestParam(required = false) String username) {
        System.out.println(params);
        return env.getProperty("local.server.port");
    }

    @GetMapping("/remote/get")
    public Object remoteGetTest() {
        Object provider2 = remoteService.getProvider2();
        System.out.println("remote load data from provider2:" + provider2);
        return provider2;
    }

    @GetMapping("/timeout")
    public String timeout() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠了2秒");
        return "timeout test";
    }

    @GetMapping("/exception")
    public String exception() {
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
    }
}
