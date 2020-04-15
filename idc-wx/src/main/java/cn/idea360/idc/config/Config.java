package cn.idea360.idc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
public class Config {


    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(inputStream);

        String appID = properties.getProperty("wx.appID");
        String appsecret = properties.getProperty("wx.appsecret");

        logger.info("appId={}, appsecret={}", appID, appsecret);


    }

    @Bean
    public WxConfig getConfig() {
        Properties properties = new Properties();
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);

            String appID = properties.getProperty("wx.appID");
            String appsecret = properties.getProperty("wx.appsecret");
            String token = properties.getProperty("wx.token");
            WxConfig config = new WxConfig(appID, appsecret, token);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public class WxConfig{

        private String appId;
        private String appsecret;
        private String token;

        public WxConfig(String appId, String appsecret, String token) {
            this.appId = appId;
            this.appsecret = appsecret;
            this.token = token;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
