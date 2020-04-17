package cn.idea360.idcwechat.controller;


import cn.idea360.idcwechat.bean.WxQrTicket;
import cn.idea360.idcwechat.bean.WxTemplateMessage;
import cn.idea360.idcwechat.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/console/Service/weixin/receive")
public class WxPortalController {

    private static final Logger logger = LoggerFactory.getLogger(WxPortalController.class);

    @Autowired
    private WxService wxService;

    /**
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return
     */
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {

        logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
                timestamp, nonce, echostr);

        if (wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "非法请求";

    }

    /**
     *
     * @param requestBody 回调消息
     * @param signature
     * @param timestamp
     * @param nonce
     * @param openid
     * @param encType
     * @param msgSignature
     * @return
     */
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {

        // 获取到openId
        logger.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                openid, signature, encType, msgSignature, timestamp, nonce, requestBody);

        try {
            wxService.handleEvent(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * http://localhost:80/weixin/receive/90/qr
     * 换取二维码    https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s
     * @return
     */
    @RequestMapping(value = "/qr", method = RequestMethod.GET)
    public Object createQrCode() {
        String userId = "1";
        try {
            wxService.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WxQrTicket ticket = wxService.getTicket(userId);
        return ticket;
    }

    /**
     * http://localhost:80/weixin/receive/90/sendTemplateMessage
     * @param wxTemplateMessage
     * @return
     */
    @RequestMapping(value = "sendTemplateMessage", method = RequestMethod.POST)
    public Object sendTemplateMessage(@RequestBody WxTemplateMessage wxTemplateMessage) {
        String response = null;
        try {
            response = wxService.sendTemplateMessage(wxTemplateMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
        return response;
    }
}
