package cn.idea360.idcwechat.bean;

import cn.idea360.idcwechat.utils.JsonMapper;

import java.io.Serializable;

public class WxAccessToken implements Serializable {

    private String accessToken;

    private int expiresIn = -1;

    public static WxAccessToken fromJson(String json) {
        WxAccessToken wxAccessToken = JsonMapper.build().fromJson(json, WxAccessToken.class);
        return wxAccessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
