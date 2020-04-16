package cn.idea360.idc.utils;

import cn.idea360.idc.config.Config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    @Autowired
    private Config.WxConfig config;

    public void testGet(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(response.getEntity());
            logger.info("======={}", s);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    public String getAccessToken() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", config.getAppId(), config.getAppsecret());
        testGet(url);
        return null;
    }
}
