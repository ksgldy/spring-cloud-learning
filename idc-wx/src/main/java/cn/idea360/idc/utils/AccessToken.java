package cn.idea360.idc.utils;

public class AccessToken {

    private String accessToken;
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public int getExpiresIn() {
        return expiresIn;
    }
}
