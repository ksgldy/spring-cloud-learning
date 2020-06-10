package cn.idea360.demo.modules.login;

import cn.idea360.demo.common.utils.QrcodeUtil;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 模拟clientApi
 */
@Slf4j
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RestTemplate restTemplate;

    // 业务回调
    String callbackUri = "http://idcmind.free.idcfengye.com/demo/recv";
    // 扫码接口
    String qrLogin = "http://idcmind.free.idcfengye.com/demo/auth?sessionId=";


    /**
     * 获取sessionId
     *
     * http://localhost:7777/demo/login
     * @return
     */
    @GetMapping("/login")
    public ModelAndView loginView() throws Exception{
        ModelAndView modelAndView = new ModelAndView("demo-login");

        // 接收核心服务返回的参数
        String sessionId = UUID.randomUUID().toString();
        log.info("sessionId:{}", sessionId);


        // 请求核心服务
//        String requestUrl = String.format("http://devcrm.easyliao.com/weixin-api/qrconnect/authorize?callbackUri=%s&sessionId=%s", URLEncoder.encode(callbackUri, "UTF-8"), URLEncoder.encode(sessionId, "UTF-8"));
        String requestUrl = String.format("http://78mzt9.natappfree.cc/qrconnect/authorize?callbackUri=%s&sessionId=%s", URLEncoder.encode(callbackUri, "UTF-8"), URLEncoder.encode(sessionId, "UTF-8"));
        log.info("requestUrl={}", requestUrl);

        // 生成二维码
        String qrconnet = restTemplate.getForObject(requestUrl, String.class);
        log.info("qrconnet={}", qrconnet);

        // redis
        redisTemplate.opsForValue().set(sessionId, qrconnet);

        String returnUrl = qrLogin + sessionId;
        String qrCode = QrcodeUtil.getBase64QRCode(returnUrl, 200, 200);

        modelAndView.addObject("qrCode", qrCode);

        return modelAndView;
    }

    @GetMapping("/auth")
    public String auth(@RequestParam String sessionId) {
        Object o = redisTemplate.opsForValue().get(sessionId);
        String url = String.valueOf(o);
        log.info("url:{}", url);
        return "redirect:" + url;
    }

    /**
     * 更新状态
     * http://yfpsh8.natappfree.cc/demo/recv
     */
    @GetMapping("/recv")
    @ResponseBody
    public void recvData() {
        System.out.println("====");
    }


    // http://idcmind.free.idcfengye.com/demo/getInfo/{companyId}
    @PostMapping("/getInfo/{companyId}")
    @ResponseBody
    public R getUsers(@PathVariable Integer companyId, @RequestBody List<String> customerIds) {
        User user = new User();
        user.setCompanyId(1);
        user.setCustomerId("ceshi7");
        user.setHeadimgurl("http://thirdwx.qlogo.cn/mmopen/RFKUCMNiaHBDu2OOyCcvq5uTIteIlicusVTVUVNtIicjSyNY2su0eSYAIUzDtlAkE3Ff6uaKN8UvryLwicX1c2OeLNHJR3ibBeo9G/132");
        user.setInnerName("内部名");
        user.setOuterName("外部名");
        return R.ok(Arrays.asList(user));
    }
}
