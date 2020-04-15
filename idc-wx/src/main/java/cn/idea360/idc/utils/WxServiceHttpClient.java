package cn.idea360.idc.utils;

import cn.idea360.idc.config.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Component
public class WxServiceHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(WxServiceHttpClient.class);

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private Config.WxConfig config;

    public String getAccessToken() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", config.getAppId(), config.getAppsecret());

        String result = restTemplate.getForObject(url, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            String access_token = jsonNode.get("access_token").asText();
            return access_token;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object getUserInfo(String accessToken, String openId) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", accessToken, openId);
        Object result = restTemplate.getForObject(url, Object.class);
        return result;
    }


    public void sendTemplateMessage(String toId, String accessToken) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", accessToken);
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTouser(toId);
        templateMessage.setTemplate_id("7BsKKP1MsDxbNmfYfTaNVm9guRvgwH8l2PmFbDCMip0");
        templateMessage.setUrl("http://www.baidu.com");
        TemplateMessage.Item item = new TemplateMessage.Item();
        item.setValue("当我遇上你");
        item.setColor("#173177");
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", item);
        templateMessage.setData(objectObjectHashMap);
        restTemplate.postForObject(url, templateMessage, Object.class);

    }
}
