package cn.idea360.demo.config;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

@Slf4j
public class ConfigLoader {

    private static ConfigLoader instance = null;
    private Properties globalConfigs = null;

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public ConfigLoader() {
        this.init();
    }

    private void init() {
        boolean hasConfig = this.checkHasConfig(System.getenv("EASYLIAO_HOME"));
        if (!hasConfig) {
            throw new RuntimeException("没找到全局配置文件");
        }
    }

    private boolean checkHasConfig(String baseEnv) {
        String defaultPath = baseEnv + File.separator + "conf" + File.separator;
        File file = new File(defaultPath);
        if (file.exists() && file.isDirectory()) {
            File conf = new File(defaultPath + "conf.properties");
            if (conf.exists() && conf.isFile()) {
                this.globalConfigs = new Properties();
                try {
                    FileInputStream inputStream = new FileInputStream(conf);
                    this.globalConfigs.load(inputStream);
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private String getProperty(String key) {
        return this.globalConfigs.getProperty(key, null);
    }

    public static void main(String[] args) {
        String property = ConfigLoader.getInstance().getProperty("easyliao.outbound.mysql.jdbc.username1");
        log.info(property);
    }
}
