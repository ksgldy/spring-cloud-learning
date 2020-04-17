package cn.idea360.idcwechat.service;

import cn.idea360.idcwechat.bean.*;
import cn.idea360.idcwechat.config.WxConfig;
import cn.idea360.idcwechat.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WxService {

    private static final Logger logger = LoggerFactory.getLogger(WxService.class);

    ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private WxConfig wxConfig;

    /**
     * 签名校验
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public boolean checkSignature(String timestamp, String nonce, String signature) {
        try {

            return SHA1.gen(new String[]{wxConfig.getToken(), timestamp, nonce}).equals(signature);

        } catch (Exception e) {

            logger.error("签名校验失败: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 获取access_token
     * @return
     * @throws Exception
     */
    public String getAccessToken() throws Exception {

        if (wxConfig.getAccessToken() == null) {
            String url = String.format(WxUrl.GET_ACCESS_TOKEN.getPath(), wxConfig.getAppId(), wxConfig.getSecret());
            String responseJson = HttpUtils.newGetRequest(url);

            WxAccessToken wxAccessToken = JsonMapper.build().fromJson(responseJson, WxAccessToken.class, true);
            wxConfig.setAccessToken(wxAccessToken.getAccessToken());
            return wxAccessToken.getAccessToken();
        }
        return wxConfig.getAccessToken();
    }

    /**
     * 刷新token
     * @return
     */
    public String refreshToken() {
        return null;
    }

    /**
     * 创建二维码ticket
     * @return
     */
    public WxQrTicket getTicket(String userId) {
        String url = String.format(WxUrl.CREATE_QRCODE.getPath(), wxConfig.getAccessToken());
        WxQrReq wxQrReq = new WxQrReq(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        WxQrTicket wxQrTicket = null;
        try {
            String json= objectMapper.writeValueAsString(wxQrReq);
            String response = HttpUtils.newPostRequest(url, json);
            wxQrTicket = objectMapper.readValue(response, WxQrTicket.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return wxQrTicket;
    }


//    /**
//     * 通过ticket换取二维码
//     * @return
//     */
//    public String getQrCode(String userId) {
//        QrTicket ticket = getTicket(userId);
//        if (ticket == null) {
//            return null;
//        }
//        String url = String.format(WxUrl.GET_QRCODE.getPath(), ticket.getTicket());
//        String response = HttpUtils.newGetRequest(url);
//        return response;
//    }

    /**
     * 获取用户信息
     * @return
     */
    public WxUser getUserInfo(String openId) throws Exception{
        String url = String.format(WxUrl.GET_USERINFO.getPath(), wxConfig.getAccessToken(), openId);
        String responseJson = HttpUtils.newGetRequest(url);

        logger.info("userJson:{}", responseJson);

        Map<String, Object> readValue = objectMapper.readValue(responseJson, Map.class);
        WxUser wxUser = new WxUser();
        wxUser.setOpenid(readValue.getOrDefault("openid", "").toString());
        wxUser.setNickname(readValue.getOrDefault("nickname", "").toString());
        wxUser.setSex((Integer) readValue.getOrDefault("sex", 0));
        wxUser.setLanguage(readValue.getOrDefault("language", "").toString());
        wxUser.setCity(readValue.getOrDefault("city", "").toString());
        wxUser.setProvince(readValue.getOrDefault("province", "").toString());
        wxUser.setCountry(readValue.getOrDefault("country", "").toString());
        wxUser.setHeadimgurl(readValue.getOrDefault("headimgurl", "").toString());
        wxUser.setSubscribeTime((Integer)readValue.getOrDefault("subscribe_time", 0));
        wxUser.setRemark(readValue.getOrDefault("remark", "").toString());
        wxUser.setGroupid((Integer)readValue.getOrDefault("groupid", 0));
        wxUser.setSubscribeScene(readValue.getOrDefault("subscribe_scene", "").toString());
        wxUser.setQrScene((Integer)readValue.getOrDefault("qr_scene", 0));
        wxUser.setQrSceneStr(readValue.getOrDefault("qr_scene_str", "").toString());

        logger.info("user:{}", wxUser);

        return wxUser;
    }

    /**
     * 接收事件推送
     * @param xmlMessage
     * @return
     */
    public void handleEvent(String xmlMessage) throws Exception{

        Map<String, String> messageMap = XmlUtils.xml2Map(xmlMessage);
        WxXmlMessage wxXmlMessage = new WxXmlMessage();
        wxXmlMessage.setToUser(messageMap.getOrDefault("ToUserName", null));
        wxXmlMessage.setFromUser(messageMap.getOrDefault("FromUserName", null));
        wxXmlMessage.setCreateTime(Long.valueOf(messageMap.getOrDefault("CreateTime", null)));
        wxXmlMessage.setMsgType(messageMap.getOrDefault("MsgType", null));
        wxXmlMessage.setEvent(messageMap.getOrDefault("Event", null));
        wxXmlMessage.setEventKey(messageMap.getOrDefault("EventKey", null));
        wxXmlMessage.setTicket(messageMap.getOrDefault("Ticket", null));


        if (wxXmlMessage.getMsgType().equals("event") && wxXmlMessage.getEvent().equals("subscribe")) {
            logger.info("有新用户关注, openId: {}", wxXmlMessage.getFromUser());
            getAccessToken();
            WxUser userInfo = getUserInfo(wxXmlMessage.getFromUser());

        }

        if (wxXmlMessage.getMsgType().equals("event") && wxXmlMessage.getEvent().equals("unsubscribe")) {
            logger.info("有用户取关了, openId: {}", wxXmlMessage.getFromUser());
        }
    }

    public String sendTemplateMessage(WxTemplateMessage wxTemplateMessage) throws Exception{
        String url = String.format(WxUrl.SEND_TEMPLATE_MESSAGE.getPath(), wxConfig.getAccessToken());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        String json = objectMapper.writeValueAsString(wxTemplateMessage);
        String response = HttpUtils.newPostRequest(url, json);
        return response;
    }
}
