package cn.idea360.idcwechat.config;

import java.io.Serializable;

public class WxConfig implements Serializable {

    protected volatile String appId;
    protected volatile String secret;
    protected volatile String token;
    protected String accessToken;

    public WxConfig(String appId, String secret, String token) {
        this.appId = appId;
        this.secret = secret;
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
