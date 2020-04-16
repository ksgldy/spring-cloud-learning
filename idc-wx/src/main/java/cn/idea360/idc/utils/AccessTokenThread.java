package cn.idea360.idc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenThread extends Thread{
    private static Logger log = LoggerFactory.getLogger(AccessTokenThread.class);
    // 当accessToken获取不成功时重试次数
    private static Integer RETRY_TIMES = 3;

    @Override
    public void run() {
        int retryTimes = 0;
        while (true) {
            AccessToken accessToken = getAccessToken();
            if (null != accessToken) {
                WechartConst.ACCESS_TOKEN = accessToken.getAccessToken();
                try {
                    Thread.sleep((accessToken.getExpiresIn()-200)*1000);//休眠7000秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                retryTimes = 0;
            } else {
                if (retryTimes >= RETRY_TIMES) {
                    break;
                }
                retryTimes += 1;
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken(){
        String appId = WechartConst.appId;
        String appSecret = WechartConst.appSecret;
        AccessToken accessToken = new AccessToken();
        String url = WechartConst.ACCESS_TOKEN_URL.replace("APPID" ,appId).replace("APPSECRET",appSecret);
//        JSONObject jsonObject =  HttpUtil.request(url, "GET", null);
//        if(jsonObject !=null){
//            Integer errCode = jsonObject.getInteger("errcode");
//            if (errCode == null) {
//                accessToken.setAccessToken(jsonObject.getString("access_token"));
//                accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
//            }else {
//                log.error("获取access_token失败:"+jsonObject.toJSONString());
//                return null;
//            }
//        }
        return accessToken;
    }
}
