package cn.idea360.demo.modules.login;

import cn.idea360.demo.common.utils.QrcodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class QrLoginController {

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
     * @return
     */
    @RequestMapping("/view")
    public ModelAndView login() {
        ModelAndView login = new ModelAndView("login");
        String base64QRCode = QrcodeUtil.getBase64QRCode("C:\\Users\\Administrator\\Desktop\\1.jpg", 400, 400);
        login.addObject("qrcode", "data:image/jpg;base64," + base64QRCode);
        return login;
    }

}
