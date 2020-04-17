package cn.idea360.idcwechat.service;

import cn.idea360.idcwechat.bean.WxAccessToken;
import cn.idea360.idcwechat.config.WxConfig;
import cn.idea360.idcwechat.utils.HttpUtils;
import cn.idea360.idcwechat.utils.JsonMapper;
import cn.idea360.idcwechat.utils.WxUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AccessTokenThread implements Runnable {

    private static Logger log = LoggerFactory.getLogger(AccessTokenThread.class);

    private static WxAccessToken accessToken = null;

    private static WxConfig wxConfig;

    public AccessTokenThread(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    // 简单处理，非线程安全
    @Override
    public void run() {
        int retryTimes = 0;
        while (true) {
            WxAccessToken accessToken = getAccessToken();
            wxConfig.setAccessToken(accessToken.getAccessToken());
            try {
                Thread.sleep((accessToken.getExpiresIn()-200)*1000);//休眠7000秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static WxAccessToken getAccessToken() {
        if (accessToken == null) {
            // 获取token
            String url = String.format(WxUrl.GET_ACCESS_TOKEN.getPath(), wxConfig.getAppId(), wxConfig.getSecret());
            String responseJson = HttpUtils.newGetRequest(url);

            accessToken = JsonMapper.build().fromJson(responseJson, WxAccessToken.class, true);
            wxConfig.setAccessToken(accessToken.getAccessToken());
        }
        return accessToken;
    }
}
