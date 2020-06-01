package cn.idea360.demo.modules.login;

import cn.idea360.demo.common.utils.QrcodeUtil;
import cn.idea360.demo.enums.QrCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * https://segmentfault.com/a/1190000020999524
 * https://juejin.im/post/5e83e716e51d4546c27bb559?utm_source=gold_browser_extension
 * https://my.oschina.net/u/566591/blog/3217790
 *
 * http://localhost:8888/login/view
 *
 * 我们使用redis来存储每一张二维码的状态
 *
 * 状态:
 *
 * NOT_SCAN 未被扫描
 * SCANNED 被扫描
 * VERIFIED 确认完后
 * EXPIRED 过期
 * FINISH 完成
 *
 * 生成二维码和uuid
 * 手机微信扫码
 * 获取授权码, 获取openid
 * 根据openid获取customerId和companyId并返回
 * 前端根据customerId和companyId实现快捷登录获取token
 * 请求头根据token请求业务接口
 *
 * */
@Slf4j
@Controller
@RequestMapping("/login")
public class QrLoginController {

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 创建二维码
     * @return
     */
    @RequestMapping("/createQrCode")
    public ModelAndView createQrCode() {
        ModelAndView login = new ModelAndView("login");
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid=" + uuid);

        redisTemplate.opsForValue().set(uuid, QrCodeEnum.NOT_SCAN.getValue());

        String base64QRCode = QrcodeUtil.getBase64QRCode("localhost:8888/login?uuid=" + uuid, 400, 400);
        login.addObject("qrcode", "data:image/jpg;base64," + base64QRCode);
        return login;
    }

    /**
     * 前端轮询法判断二维码是否被扫描
     * @param uuid
     * @return
     */
    @GetMapping("/query")
    @ResponseBody
    public Object queryIsScannedOrVerified(@RequestParam("uuid")String uuid){
        Object redisUuidStatus = redisTemplate.opsForValue().get(uuid);
        if (uuid == null) {
            return "uuid过期或者不存在";
        }
        int uuidStatus = (int)redisUuidStatus;
        return uuidStatus;
    }


}
