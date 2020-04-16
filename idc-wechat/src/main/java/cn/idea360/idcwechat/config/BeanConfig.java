package cn.idea360.idcwechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Properties;

@Configuration
public class BeanConfig {

    @Bean
    public WxConfig loadConfig() {
        Properties properties = new Properties();
        InputStream inputStream = BeanConfig.class.getClassLoader().getResourceAsStream("application.properties");
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
}
