package cn.idea360.idcwechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/weixin/code")
public class WxCodeController {

    private static final Logger logger = LoggerFactory.getLogger(WxCodeController.class);

    @GetMapping
    public Object code(@RequestParam("code") String code, @RequestParam("state") String state) {
        logger.info("code={}, state={}", code, state);
        return code;
    }
}
