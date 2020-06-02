package cn.idea360.demo.modules.login;

import cn.idea360.demo.common.utils.QrcodeUtil;
import cn.idea360.demo.enums.QrCodeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/wx")
public class LoginController {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RestTemplate restTemplate;

    Map<String, String> qrCodeMap = new HashMap<>();

    /**
     * http://localhost/wx/loginView
     * @return
     */
    @RequestMapping("/loginView")
    public ModelAndView loginView() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping("/home")
    public ModelAndView homeView() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping(value = "/createQr", method = RequestMethod.GET)
    @ResponseBody
    public R createQrCode() {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid:{}", uuid);

        String returnUrl = "http://474tya.natappfree.cc/wx/login?uuid=" + uuid;
        String qrCode = QrcodeUtil.getBase64QRCode(returnUrl, 200, 200);

        qrCodeMap.put(qrCode, uuid);
        redisTemplate.opsForValue().set("uuid:" + uuid, QrCodeEnum.NOT_SCAN);

        return R.ok(qrCode);
    }

    @PostMapping("/query")
    @ResponseBody
    public R queryIsScannedOrVerified(@RequestParam("img")String img){
        String uuid = qrCodeMap.get(img);
        QrCodeEnum qrCodeEnum = (QrCodeEnum)redisTemplate.opsForValue().get("uuid:" + uuid);
        log.info("status:{}", qrCodeEnum);
        return R.ok(qrCodeEnum);
    }

    @GetMapping("/login")
    public String login(@RequestParam String uuid) throws Exception{
        String appId = "wx3f2f5354f615c639";
        String redirectURI = "http://474tya.natappfree.cc/wx/openId";
        String scope = "snsapi_userinfo";
        String state = uuid;
        String redirectURL = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect", appId, URLEncoder.encode(redirectURI, "UTF-8"), scope, URLEncoder.encode(state, "UTF-8"));
        return "redirect:" + redirectURL;
    }

    @GetMapping("/openId")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String state) throws Exception {
        log.info("code={}, state={}", code, state);
        redisTemplate.opsForValue().set("uuid:" + state, QrCodeEnum.SCANNED);

        String appId = "wx3f2f5354f615c639";
        String secret = "80ae2299328c6c8f6ae0c774a69b08b0";

        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appId, secret, code);
        String restTemplateForObject = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSON.parseObject(restTemplateForObject);
        log.info("openid:{}", jsonObject.getString("openid"));
        log.info("access_token:{}", jsonObject.getString("access_token"));
        log.info("refresh_token:{}", jsonObject.getString("refresh_token"));
        log.info("scope:{}", jsonObject.getString("scope"));
        log.info("expires_in:{}", jsonObject.getString("expires_in"));

        String redirectURL = "http://474tya.natappfree.cc/wx/userId";
        log.info("returnUrl:{}", redirectURL);

        return "redirect:" + redirectURL + "?openId=" + jsonObject.getString("openid") + "&uuid=" + state;
    }

    @GetMapping("/userId")
    @ResponseBody
    public String getUserId(@RequestParam("openId") String openId, @RequestParam("uuid") String uuid) {
        log.info("根据openid={}获取userId", openId);
        redisTemplate.opsForValue().set("uuid:" + uuid, QrCodeEnum.VERIFIED);
        return "777";
    }

}
