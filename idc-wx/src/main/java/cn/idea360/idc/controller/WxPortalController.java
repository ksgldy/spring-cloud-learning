package cn.idea360.idc.controller;

import cn.idea360.idc.service.WxService;
import cn.idea360.idc.utils.HttpUtils;
import cn.idea360.idc.utils.WxServiceHttpClient;
import cn.idea360.idc.utils.WxXmlMessage;
import cn.idea360.idc.utils.XmlConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weixin/receive/90")
public class WxPortalController {

    private static final Logger logger = LoggerFactory.getLogger(WxPortalController.class);

    @Autowired
    private WxService wxService;
    @Autowired
    private WxServiceHttpClient wxServiceHttpClient;
    @Autowired
    private HttpUtils httpUtils;

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

        // 获取到消息内容
        WxXmlMessage callBackMessage = XmlConvert.convertXml2Message(requestBody);
        logger.info("收到message: {}", callBackMessage.toString());

        if (callBackMessage.getEvent().equals("subscribe")) {
            // 获取access_token
            String accessToken = httpUtils.getAccessToken();
            logger.info("accessToken: {}", accessToken);

            // 获取用户信息
            Object userInfo = wxServiceHttpClient.getUserInfo(accessToken, openid);
            logger.info("userInfo: {}", userInfo);

            // 发送模板消息
//            wxServiceHttpClient.sendTemplateMessage(openid, accessToken);
        }


        return null;
    }
}
