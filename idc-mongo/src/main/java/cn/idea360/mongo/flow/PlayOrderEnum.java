package cn.idea360.mongo.flow;

import java.io.Serializable;

/**
 * 播放顺序
 */
public enum PlayOrderEnum implements Serializable {

    SEQUENCE("sequence", "顺序播放"),
    RANDOM("random", "随机播放"),
    ;

    private String value;
    private String text;

    PlayOrderEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
