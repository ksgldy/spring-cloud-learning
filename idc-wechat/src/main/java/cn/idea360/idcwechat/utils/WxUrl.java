package cn.idea360.idcwechat.utils;

public enum  WxUrl {

    // 获取access_token
    GET_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"),

    // 获取二维码ticket
    CREATE_QRCODE("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s"),

    // 换取二维码
    GET_QRCODE("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s"),

    // 获取用户信息
    GET_USERINFO("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN"),

    // 发送模板消息
    SEND_TEMPLATE_MESSAGE("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s")
    ;

    private String path;

    WxUrl(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
