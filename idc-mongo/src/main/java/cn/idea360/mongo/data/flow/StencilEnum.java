package cn.idea360.mongo.data.flow;

import java.io.Serializable;

/**
 * 节点类型
 */
public enum  StencilEnum implements Serializable {

    NORMAL("normal", "普通模块"),
    ANYANSWER("anyanswer", "任答模块"),
    HANGUP("hangup", "挂机模块"),
    ARTIFICIAL("artificial", "转人工模块"),
    ;

    private String value;
    private String text;

    StencilEnum(String value, String text) {
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
