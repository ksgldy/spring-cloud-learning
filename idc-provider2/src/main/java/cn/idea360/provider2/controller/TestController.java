package cn.idea360.provider2.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    Environment env;

    @GetMapping("/provider2")
    public Object getTest() {

        String port = env.getProperty("local.server.port");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("port", port);

        System.out.println("idc-provider2:" + port);

        return jsonObject;
    }
}
