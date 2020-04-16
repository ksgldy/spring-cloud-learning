package cn.idea360.idc.utils;

public class WechartConst {

    //公众号的唯一标识
    public static final String appId = "appId";
    //公众号的appsecret
    public static final String appSecret = "appSecret";
    //accessToken
    public static String ACCESS_TOKEN = "";

    /**
     * 获取access_token的URL
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

}
