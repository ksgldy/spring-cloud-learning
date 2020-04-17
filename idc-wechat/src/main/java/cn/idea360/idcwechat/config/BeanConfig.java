package cn.idea360.idcwechat.config;

import cn.idea360.idcwechat.service.AccessTokenThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfig {

    @Autowired
    WxConfig wxConfig;

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

    @PostConstruct
    public void initWx() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.execute(new AccessTokenThread(wxConfig));
    }
}
